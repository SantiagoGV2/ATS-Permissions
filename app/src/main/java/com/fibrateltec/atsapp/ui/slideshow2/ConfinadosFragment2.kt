package com.fibrateltec.atsapp.ui.slideshow2

import android.Manifest
import android.content.Context
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
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentConfinados2Binding
import com.fibrateltec.atsapp.ui.gallery3.GalleryFragment3
import com.fibrateltec.atsapp.ui.slideshow3.ConfinadosFragment3
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.ceil
import kotlin.math.min

class ConfinadosFragment2 : Fragment() {

    private var _binding: FragmentConfinados2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val galleryViewModel2 = ViewModelProvider(this).get(ConfinadosViewModel::class.java)

        _binding = FragmentConfinados2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textFragmentConfinados
        galleryViewModel2.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    class FourActivity : AppCompatActivity() {

        private var btnNextVisibility = View.VISIBLE

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_confinados2)
            seleccion(this)
            seleccion5(this)
            seleccion6(this)



            val nxtboton5: Button = findViewById(R.id.nextBtn6)
            btnNextVisibility = nxtboton5.visibility
            nxtboton5.setOnClickListener {
                val intent = Intent(this, ConfinadosFragment3.FifthActivity::class.java)
                startActivity(intent)
                convertXmlToPdf(showNextButton = false) // Muestra nextBtn en el PDF
            }



        }

        fun seleccion(context: Context) {
            val button: Button = findViewById(R.id.btnbiologico)
            val selectedItems = mutableListOf<String>()
            button.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.Biologico)
                val segundoItems = resources.getStringArray(R.array.Biologicos2)

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


        fun seleccion5(context: Context) {
            val selectedItems = mutableListOf<String>()
            val button5: Button = findViewById(R.id.btnbiomecanicos)
            button5.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.biomecanicos3)
                val segundoItems = resources.getStringArray(R.array.biomecanicos4)

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
        fun seleccion6(context: Context) {
            val selectedItems = mutableListOf<String>()

            val button5: Button = findViewById(R.id.btnelectrico)
            button5.setOnClickListener {
                val builder = AlertDialog.Builder(context)
                builder.setTitle("Factor y agente de riesgo")

                val primerItems = resources.getStringArray(R.array.electrico4)
                val segundoItems = resources.getStringArray(R.array.electrico5)

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
                    mostrarSelecciones5(selectedItems)
                }

                builder.show()
            }
        }


        private fun mostrarSelecciones(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView23) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }



        private fun mostrarSelecciones4(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView27) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }
        private fun mostrarSelecciones5(selecciones: List<String>) {
            val textView: TextView =
                findViewById(R.id.textView28) // Reemplaza con tu ID de TextView
            textView.text = "Selecciones: ${selecciones.joinToString(", ")}"

        }



        private fun convertXmlToPdf(showNextButton: Boolean) {
            // Inflate the XML layout file
            // Oculta el botón btnxml3


            // Oculta el botón nextBtn si showNextButton es falso
            val nextBtn = findViewById<View>(R.id.nextBtn6)
            if (showNextButton == showNextButton) {
                nextBtn.visibility = View.GONE
            }

            val constraint = findViewById<ConstraintLayout>(R.id.constraint5)
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
            val fileName = "Peligros_potenciales3.pdf"
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
                Toast.makeText(this, "XML to PDF Conversion Successful", Toast.LENGTH_LONG).show()
            } catch (e: IOException) {
                e.printStackTrace()
                // Error occurred while converting to PDF
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}