package com.fibrateltec.atsapp.ui.gallery2

import android.Manifest
import android.content.Context
import android.content.Intent
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
import com.fibrateltec.atsapp.R
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.databinding.FragmentGallery2Binding
import com.fibrateltec.atsapp.ui.gallery3.GalleryFragment3
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.ceil
import kotlin.math.min

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
                convertXmlToPdf(showNextButton = false) // Muestra nextBtn en el PDF
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

        private fun convertXmlToPdf(showNextButton: Boolean) {
            // Inflate the XML layout file
            // Oculta el botón btnxml3


            // Oculta el botón nextBtn si showNextButton es falso
            val nextBtn = findViewById<View>(R.id.nextBtn2)
            if (showNextButton == showNextButton) {
                nextBtn.visibility = View.GONE
            }

            val constraint = findViewById<ConstraintLayout>(R.id.constraint2)
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
            val fileName = "Peligros_potenciales.pdf"
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

