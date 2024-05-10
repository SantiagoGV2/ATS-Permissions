package com.fibrateltec.atsapp.ui.gallery3

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.DisplayMetrics
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
import java.io.IOException
import kotlin.math.ceil
import kotlin.math.min


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


            val nxtboton2: Button = findViewById(R.id.button3)
            btnNextVisibility = nxtboton2.visibility
            nxtboton2.setOnClickListener {
                val intent = Intent(this, PdfFragment2.EleventhActivity::class.java)
                startActivity(intent)
                convertXmlToPdf(showNextButton = false)
            }

        }

        private fun convertXmlToPdf(showNextButton: Boolean) {
            // Inflate the XML layout file
            // Oculta el botón btnxml3


            // Oculta el botón nextBtn si showNextButton es falso
            val nextBtn = findViewById<View>(R.id.button3)
            if (showNextButton == showNextButton) {
                nextBtn.visibility = View.GONE
            }

            val clearButton = findViewById<Button>(R.id.clear)
            clearButton.visibility = View.GONE

            val constraint = findViewById<ConstraintLayout>(R.id.constraint)
            val displayMetrics = DisplayMetrics()
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                this.display!!.getRealMetrics(displayMetrics)
            } else {
                this.windowManager.defaultDisplay.getMetrics(displayMetrics)
            }

            // Measure the view with UNSPECIFIED height
            constraint.measure(
                View.MeasureSpec.makeMeasureSpec(
                    displayMetrics.widthPixels,
                    View.MeasureSpec.EXACTLY
                ),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
            )

            // Calculate total number of pages needed based on view height
            val totalHeight = constraint.measuredHeight
            val totalPages = ceil((totalHeight.toFloat() / displayMetrics.heightPixels).toDouble())
                .toInt()

            // Create a new PdfDocument instance
            val document = PdfDocument()

            // Obtain the width and height of the view
            val viewWidth = constraint.measuredWidth
            for (i in 0 until totalPages) {
                // Create a PageInfo object specifying the page attributes
                val pageInfo =
                    PdfDocument.PageInfo.Builder(viewWidth, displayMetrics.heightPixels, i + 1)
                        .create()

                // Start a new page
                val page = document.startPage(pageInfo)

                // Get the Canvas object to draw on the page
                val canvas = page.canvas

                // Calculate the portion of the view to be drawn on this page
                val start = i * displayMetrics.heightPixels
                val end =
                    min((start + displayMetrics.heightPixels).toDouble(), totalHeight.toDouble())
                        .toInt()

                // Translate the canvas to draw the correct portion of the view
                canvas.translate(0f, -start.toFloat())

                // Draw the portion of the view on the canvas
                constraint.layout(0, -start, viewWidth, end)
                constraint.draw(canvas)

                // Finish the page
                document.finishPage(page)
            }

            // Specify the path and filename of the output PDF file
            val downloadsDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
            val fileName = "Tareas_criticas.pdf"
            val filePath = File(downloadsDir, fileName)
            try {
                // Save the document to a file
                val fos = FileOutputStream(filePath)
                document.writeTo(fos)
                document.close()


                // Restablece la visibilidad del botón nextBtn si showNextButton es true
                nextBtn.visibility = View.VISIBLE

                fos.close()
                // PDF conversion successful
                Toast.makeText(this, "Guardado", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
