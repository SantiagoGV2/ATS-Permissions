package com.fibrateltec.atsapp.ui.gallery3

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.itextpdf.text.Document
import android.widget.Button
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentGallery3Binding
import com.fibrateltec.atsapp.ui.Signature.SignaturePad
import com.fibrateltec.atsapp.ui.pdf2.PdfFragment2
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import com.itextpdf.text.Image
import java.io.File
import java.io.FileOutputStream






class GalleryFragment3 : Fragment() {
    private var _binding: FragmentGallery3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val galleryViewModel2 = ViewModelProvider(this).get(GalleryViewModel3::class.java)

        _binding = FragmentGallery3Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFragmentGallery3
        galleryViewModel2.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    class ThirdActivity : AppCompatActivity() {
        private var isCreatePDFButtonVisible = true
        private var btnNextVisibility = View.VISIBLE
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_gallery3)

            val scrollView = findViewById<ScrollView>(R.id.scroll3)
            val signaturePad = findViewById<SignaturePad>(R.id.signature_pad)
            signaturePad.setScrollView(scrollView)


            val clearButton = findViewById<Button>(R.id.clear)
            btnNextVisibility = clearButton.visibility
            clearButton.setOnClickListener {
                val signaturePad = findViewById<SignaturePad>(R.id.signature_pad)
                signaturePad.clearSignature()
            }

            val btnxml: Button = findViewById(R.id.btnxml)
            btnxml.setOnClickListener {
                isCreatePDFButtonVisible = !isCreatePDFButtonVisible
                btnxml.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE
                exportToPDF()
            }
            val nxtboton2: Button = findViewById(R.id.button3)
            btnNextVisibility = nxtboton2.visibility
            nxtboton2.setOnClickListener {
                val intent = Intent(this, PdfFragment2.EleventhActivity::class.java)
                startActivity(intent)

            }

        }

        private fun exportToPDF() {
            val document = Document()
            val path = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Tareas_criticas.pdf").absolutePath
            val file = File(path)
            try {
                val fileOutputStream = FileOutputStream(file)

                PdfWriter.getInstance(document, fileOutputStream)

                document.open()

                // Agrega el primer contenido al documento PDF
                val constraint: ConstraintLayout = findViewById(R.id.constraint)
                addViewToPDF(document, constraint)

                document.close()
                findViewById<Button>(R.id.button3).visibility = btnNextVisibility
                findViewById<Button>(R.id.clear).visibility = btnNextVisibility
                Toast.makeText(this, "Guardar exitosamente en $path", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }



        private fun addViewToPDF(document: Document, view: View) {
            val btnCreatePDF = view.findViewById<Button>(R.id.btnxml)
            btnCreatePDF.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE

            findViewById<Button>(R.id.button3).visibility = View.GONE
            findViewById<Button>(R.id.clear).visibility = View.GONE

            // Calcula el margen del documento
            // Calcula el margen del documento
            val margin = 0f

            val increasedPageWidth = 550f
            // Calcula el tamaño de la página del documento
            val pageSize = document.pageSize
            val pageWidth = increasedPageWidth - margin * 2.5f
            val pageHeight = pageSize.height - margin * 2.5f

            // Convierte la vista a un bitmap
            val bitmap = convertViewToBitmap(view)

            // Convierte el bitmap a bytes para agregarlo al documento PDF
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val image = Image.getInstance(stream.toByteArray())

            // Ajusta el tamaño de la imagen al documento
            val aspectRatio = image.width.toFloat() / image.height.toFloat()
            val newWidth = pageWidth * 1f // Ajusta el ancho según tu preferencia
            val newHeight = newWidth / aspectRatio

            // Si la imagen es más grande que la página, divide la imagen en varias partes
            if (newHeight > pageHeight) {
                divideBitmapIntoSections(document, bitmap, pageHeight, aspectRatio, pageWidth)
            } else {
                // Ajusta el tamaño de la imagen al documento
                image.scaleToFit(newWidth, newHeight)

                // Agrega la imagen al documento
                document.add(image)
            }
        }

        private fun convertViewToBitmap(view: View): Bitmap {
            val bitmap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }

        private fun divideBitmapIntoSections(document: Document, bitmap: Bitmap, pageHeight: Float, aspectRatio: Float, pageWidth: Float) {
            val numVerticalSections = Math.ceil((bitmap.height.toDouble() / pageHeight)).toInt()
            var startY = 0f

            for (i in 0 until numVerticalSections) {
                var sectionHeight = pageHeight
                val remainingHeight = bitmap.height - startY

                if (remainingHeight < sectionHeight) {
                    sectionHeight = remainingHeight
                }

                val sectionBitmap = Bitmap.createBitmap(bitmap, 0, startY.toInt(), bitmap.width, sectionHeight.toInt())

                val byteArrayOutputStream = ByteArrayOutputStream()
                sectionBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
                val byteArray = byteArrayOutputStream.toByteArray()

                val sectionImage = Image.getInstance(byteArray)
                sectionImage.scaleToFit(pageWidth, sectionHeight)

                document.newPage()
                document.add(sectionImage)

                startY += sectionHeight
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
