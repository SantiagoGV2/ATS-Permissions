package com.fibrateltec.atsapp.ui.permiso3

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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ScrollView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentPermiso3Binding
import com.fibrateltec.atsapp.ui.Signature.SignaturePad
import com.fibrateltec.atsapp.ui.pdf4.PdfFragment4
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.ceil
import kotlin.math.min


class PermisoFragment3 : Fragment() {

    private var _binding: FragmentPermiso3Binding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val permisoViewModel =
            ViewModelProvider(this).get(PermisoViewModel3::class.java)

        _binding = FragmentPermiso3Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.Permiso3
        permisoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    class FifteenthActivity : AppCompatActivity() {

        private var btnNextVisibility = View.VISIBLE

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_permiso3)

            val scrollView = findViewById<ScrollView>(R.id.scroll2)
            val signaturePad = findViewById<SignaturePad>(R.id.signaturePad)
            val signaturePad2 = findViewById<SignaturePad>(R.id.signaturePad2)
            signaturePad.setScrollView(scrollView)
            signaturePad2.setScrollView(scrollView)

            val nxtboton2: Button = findViewById(R.id.button5)
            btnNextVisibility = nxtboton2.visibility
            nxtboton2.setOnClickListener {
                val intent = Intent(this, PdfFragment4.SixteenthActivity::class.java)
                startActivity(intent)
                convertXmlToPdf(showNextButton = false) // Muestra nextBtn en el PDF

            }


            val clearButton = findViewById<Button>(R.id.clear1)
            clearButton.setOnClickListener {
                val signaturePad = findViewById<SignaturePad>(R.id.signaturePad)
                signaturePad.clearSignature()
            }
            val clearButton2 = findViewById<Button>(R.id.clear2)
            clearButton2.setOnClickListener {
                val signaturePad22 = findViewById<SignaturePad>(R.id.signaturePad2)
                signaturePad22.clearSignature2()
            }
            val spinner: Spinner = findViewById(R.id.spinner)

            // Define los elementos del Spinner
            val items = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem = items[position]
                    Toast.makeText(this@FifteenthActivity, selectedItem, Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner.onItemSelectedListener = null
            // Encuentra el Spinner por su ID
            val spinner1: Spinner = findViewById(R.id.spinner2)

            // Define los elementos del Spinner
            val items1 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items1)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner1.adapter = adapter1

            spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem1 = items1[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem1", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner1.onItemSelectedListener = null
            val spinner2: Spinner = findViewById(R.id.spinner3)

            // Define los elementos del Spinner
            val items2 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items2)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner2.adapter = adapter2

            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem2 = items2[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem2", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner2.onItemSelectedListener = null
            val spinner3: Spinner = findViewById(R.id.spinner21)

            // Define los elementos del Spinner
            val items3 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items3)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner3.adapter = adapter3

            spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem3 = items3[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem3", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner3.onItemSelectedListener = null
            val spinner4: Spinner = findViewById(R.id.spinner22)

            // Define los elementos del Spinner
            val items4 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter4 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items4)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner4.adapter = adapter4

            spinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem4 = items4[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem4", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner4.onItemSelectedListener = null
            val spinner5: Spinner = findViewById(R.id.spinner23)
            // Define los elementos del Spinner
            val items5 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter5 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items5)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner5.adapter = adapter5

            spinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem5 = items5[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem5", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner5.onItemSelectedListener = null
            val spinner6: Spinner = findViewById(R.id.spinner24)

            // Define los elementos del Spinner
            val items6 =resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter6 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items6)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner6.adapter = adapter6

            spinner6.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem6 = items6[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem6", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner6.onItemSelectedListener = null
            val spinner7: Spinner = findViewById(R.id.spinner25)

            // Define los elementos del Spinner
            val items7 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter7 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items7)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner7.adapter = adapter7

            spinner7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem7 = items7[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem7", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner7.onItemSelectedListener = null
            val spinner8: Spinner = findViewById(R.id.spinner26)


            // Define los elementos del Spinner
            val items8 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter8 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items8)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner8.adapter = adapter8


            spinner8.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem8 = items8[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem8", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner8.onItemSelectedListener = null
            val spinner9: Spinner = findViewById(R.id.spinner27)

            // Define los elementos del Spinner
            val items9 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter9 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items9)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner9.adapter = adapter9

            spinner9.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem9 = items9[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem9", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner9.onItemSelectedListener = null
            val spinner10: Spinner = findViewById(R.id.spinner28)

            // Define los elementos del Spinner
            val items10 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter10 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items10)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner10.adapter = adapter10

            spinner10.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem10 = items10[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem10", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner10.onItemSelectedListener = null
            val spinner11: Spinner = findViewById(R.id.spinner29)

            // Define los elementos del Spinner
            val items11 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter11 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items11)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner11.adapter = adapter11

            spinner11.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem11 = items11[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem11", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner11.onItemSelectedListener = null
            val spinner12: Spinner = findViewById(R.id.spinner30)

            // Define los elementos del Spinner
            val items12 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter12 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items12)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner12.adapter = adapter12

            spinner12.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem12 = items12[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem12", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner12.onItemSelectedListener = null
            val spinner13: Spinner = findViewById(R.id.spinner31)

            // Define los elementos del Spinner
            val items13 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter13 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items13)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner13.adapter = adapter13

            spinner13.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem13 = items13[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem13", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner13.onItemSelectedListener = null
            val spinner14: Spinner = findViewById(R.id.spinner32)

            // Define los elementos del Spinner
            val items14 = resources.getStringArray(R.array.spinners2)

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter14 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items14)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner14.adapter = adapter14

            spinner14.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val selectedItem14 = items14[position]
                    Toast.makeText(this@FifteenthActivity, "$selectedItem14", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner14.onItemSelectedListener = null

            val spinner15: Spinner = findViewById(R.id.nombre_supervisor)

            // Define los elementos del Spinner
            val items15 = arrayOf("Supervisor", *resources.getStringArray(R.array.supervisores))

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter15 = ArrayAdapter(this, android.R.layout.simple_spinner_item, items15)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner15.adapter = adapter15

            spinner15.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) { // Si no es el pretexto
                        val selectedItem15 = items15[position]
                        Toast.makeText(this@FifteenthActivity, "$selectedItem15", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner15.onItemSelectedListener = null
            val spinner16: Spinner = findViewById(R.id.cedula)

            // Define los elementos del Spinner
            val items16 = arrayOf("Cedula Supervisor", *resources.getStringArray(R.array.cedula_supervisor))

            // Crea un adaptador y establece los elementos en el Spinner
            val adapter16 =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, items16)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner16.adapter = adapter16

            spinner16.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position != 0) { // Si no es el pretexto
                        val selectedItem16 = items16[position]
                        Toast.makeText(this@FifteenthActivity, "$selectedItem16", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner16.onItemSelectedListener = null



        }

        private fun convertXmlToPdf(showNextButton: Boolean) {
            // Inflate the XML layout file
            // Oculta el botón btnxml3


            // Oculta el botón nextBtn si showNextButton es falso
            val nextBtn = findViewById<View>(R.id.button5)
            if (showNextButton == showNextButton) {
                nextBtn.visibility = View.GONE
            }
            val clearButton = findViewById<Button>(R.id.clear1)
            clearButton.visibility = View.GONE
            val clearButton2 = findViewById<Button>(R.id.clear2)
            clearButton2.visibility = View.GONE

            val constraint = findViewById<ConstraintLayout>(R.id.constraint14)
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
            val fileName = "MedidasPrevencion2.pdf"
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