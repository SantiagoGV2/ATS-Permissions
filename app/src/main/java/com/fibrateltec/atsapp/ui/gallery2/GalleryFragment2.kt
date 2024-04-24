package com.fibrateltec.atsapp.ui.gallery2

import android.Manifest
import android.content.Context
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
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.fibrateltec.atsapp.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.databinding.FragmentGallery2Binding

import com.fibrateltec.atsapp.ui.gallery3.GalleryFragment3
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class GalleryFragment2 : Fragment() {


    private var _binding: FragmentGallery2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val galleryViewModel2 = ViewModelProvider(this).get(GalleryViewModel2::class.java)

        _binding = FragmentGallery2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFragmentGallery2
        galleryViewModel2.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    class SecondActivity : AppCompatActivity() {

        private var isCreatePDFButtonVisible = true
        private var btnNextVisibility = View.VISIBLE
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_gallery2)
            seleccion(this)
            seleccion4(this)
            seleccion5(this)
            seleccion10(this)

            val nxtboton2: Button = findViewById(R.id.nextBtn2)
            btnNextVisibility = nxtboton2.visibility
            nxtboton2.setOnClickListener {
                val intent = Intent(this, GalleryFragment3.ThirdActivity::class.java)
                startActivity(intent)

            }

            val btnxml2: Button = findViewById(R.id.btnxml2)
            btnxml2.setOnClickListener {
                isCreatePDFButtonVisible = !isCreatePDFButtonVisible
                btnxml2.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE
                exportToPDF()
            }


        }

        fun seleccion(context: Context) {
            val button: Button = findViewById(R.id.btnfisico)
            val selectedItems = mutableListOf<String>()
            button.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.fisico)
                val segundoItems = resources.getStringArray(R.array.fisico2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("**Medidas de control**")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 0) {
                        // Ignora el texto separador y las acciones asociadas

                        if (b) {
                            // Si el elemento está marcado, agrégalo a la lista de selecciones
                            selectedItems.add(items[i])
                        } else {
                            // Si el elemento está desmarcado, remuévelo de la lista de selecciones
                            selectedItems.remove(items[i])
                        }
                    }
                }

                builder.setPositiveButton("Aceptar") { _, _ ->
                    // Acciones a realizar al hacer clic en Aceptar
                    mostrarSelecciones(selectedItems)
                }

                builder.show()
            }
        }

        fun seleccion4(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button4: Button = findViewById(R.id.btnalturas)
            button4.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.alturas)
                val segundoItems = resources.getStringArray(R.array.alturas2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("**Medidas de control**")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 0) {
                        // Ignora el texto separador y las acciones asociadas

                        if (b) {
                            // Si el elemento está marcado, agrégalo a la lista de selecciones
                            selectedItems.add(items[i])
                        } else {
                            // Si el elemento está desmarcado, remuévelo de la lista de selecciones
                            selectedItems.remove(items[i])
                        }
                    }
                }

                builder.setPositiveButton("Aceptar") { _, _ ->
                    // Acciones a realizar al hacer clic en Aceptar
                    mostrarSelecciones3(selectedItems)
                }

                builder.show()
            }
        }

        fun seleccion5(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button5: Button = findViewById(R.id.btnbiomecanicos)
            button5.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.biomecanicos)
                val segundoItems = resources.getStringArray(R.array.biomecanicos2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("**Medidas de control**")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 0) {
                        // Ignora el texto separador y las acciones asociadas

                        if (b) {
                            // Si el elemento está marcado, agrégalo a la lista de selecciones
                            selectedItems.add(items[i])
                        } else {
                            // Si el elemento está desmarcado, remuévelo de la lista de selecciones
                            selectedItems.remove(items[i])
                        }
                    }
                }

                builder.setPositiveButton("Aceptar") { _, _ ->
                    // Acciones a realizar al hacer clic en Aceptar
                    mostrarSelecciones4(selectedItems)
                }

                builder.show()
            }
        }

        fun seleccion10(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button10: Button = findViewById(R.id.btneléctrico)
            button10.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.electrico)
                val segundoItems = resources.getStringArray(R.array.electrico2)

                val items = mutableListOf<String>().apply {
                    addAll(primerItems)
                    add("**Medidas de control**")
                    addAll(segundoItems)
                }.toTypedArray()

                val checkedItems = BooleanArray(items.size)

                builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                    if (i != primerItems.size && i != items.size - 0) {
                        // Ignora el texto separador y las acciones asociadas

                        if (b) {
                            // Si el elemento está marcado, agrégalo a la lista de selecciones
                            selectedItems.add(items[i])
                        } else {
                            // Si el elemento está desmarcado, remuévelo de la lista de selecciones
                            selectedItems.remove(items[i])
                        }
                    }
                }

                builder.setPositiveButton("Aceptar") { _, _ ->
                    // Acciones a realizar al hacer clic en Aceptar
                    mostrarSelecciones8(selectedItems)
                }

                builder.show()
            }
        }

        private fun mostrarSelecciones(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView23) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }

        private fun mostrarSelecciones3(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView26) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }

        private fun mostrarSelecciones4(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView27) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }

        private fun mostrarSelecciones8(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView31) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }

        private fun exportToPDF() {
            val document = Document()
            val path = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "Peligros_potenciales.pdf").absolutePath
            val file = File(path)
            try {
                val fileOutputStream = FileOutputStream(file)

                PdfWriter.getInstance(document, fileOutputStream)

                document.open()

                // Agrega el primer contenido al documento PDF
                val constraint: ConstraintLayout = findViewById(R.id.constraint2)
                addViewToPDF(document, constraint)

                Toast.makeText(this, "Guardado exitosamente en $path", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG)
                    .show()
            }
            document.close()
            findViewById<Button>(R.id.nextBtn2).visibility = btnNextVisibility

        }


        private fun addViewToPDF(document: Document, view: View) {
            val btnCreatePDF = view.findViewById<Button>(R.id.btnxml2)
            btnCreatePDF.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE

            findViewById<Button>(R.id.nextBtn2).visibility = View.GONE

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

