package com.fibrateltec.atsapp.ui.vehiculos2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.pdf.PdfDocument
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentVehiculo2Binding
import com.fibrateltec.atsapp.ui.pdf5.PdfFragment5
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.math.ceil
import kotlin.math.min


class VehiculosFragment2 : Fragment(){

    private var _binding: FragmentVehiculo2Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val vehiculosViewModel2 = ViewModelProvider(this).get(VehiculosViewModel2::class.java)

        _binding = FragmentVehiculo2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textvehiculo2
        vehiculosViewModel2.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    class VehicleActivity : AppCompatActivity(){
        private var btnNextVisibility = View.VISIBLE

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_vehiculo2)

            val nxtboton: Button = findViewById(R.id.button9)
            btnNextVisibility = nxtboton.visibility
            nxtboton.setOnClickListener {
                val intent = Intent(this, PdfFragment5.VehiclePdfActivity::class.java)
                startActivity(intent)
                convertXmlToPdf(showNextButton = false)
            }



            val spinner: Spinner = findViewById(R.id.spinner50)

            // Define los elementos del Spinner
            val items = resources.getStringArray(R.array.preoperacional_vehiculos)

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
                    Toast.makeText(this@VehicleActivity, selectedItem, Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner.onItemSelectedListener = null

            val spinner1: Spinner = findViewById(R.id.spinner51)

            // Define los elementos del Spinner
            val items1 = resources.getStringArray(R.array.preoperacional_vehiculos)

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
                    Toast.makeText(this@VehicleActivity, "$selectedItem1", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner1.onItemSelectedListener = null

            val spinner2: Spinner = findViewById(R.id.spinner52)

            // Define los elementos del Spinner
            val items2 = resources.getStringArray(R.array.preoperacional_vehiculos)

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
                    Toast.makeText(this@VehicleActivity, "$selectedItem2", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner2.onItemSelectedListener = null

            val spinner3: Spinner = findViewById(R.id.spinner53)

            // Define los elementos del Spinner
            val items3 = resources.getStringArray(R.array.preoperacional_vehiculos)

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
                    Toast.makeText(this@VehicleActivity, "$selectedItem3", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner3.onItemSelectedListener = null

            val spinner4: Spinner = findViewById(R.id.spinner54)

            // Define los elementos del Spinner
            val items4 = resources.getStringArray(R.array.preoperacional_vehiculos)

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
                    Toast.makeText(this@VehicleActivity, "$selectedItem4", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner4.onItemSelectedListener = null

            val spinner5: Spinner = findViewById(R.id.spinner55)
            // Define los elementos del Spinner
            val items5 = resources.getStringArray(R.array.preoperacional_vehiculos)

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
                    Toast.makeText(this@VehicleActivity, "$selectedItem5", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner5.onItemSelectedListener = null

            val spinner6: Spinner = findViewById(R.id.spinner56)

            // Define los elementos del Spinner
            val items6 =resources.getStringArray(R.array.preoperacional_vehiculos)

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
                    Toast.makeText(this@VehicleActivity, "$selectedItem6", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner6.onItemSelectedListener = null

            val spinner7: Spinner = findViewById(R.id.spinner57)

            // Define los elementos del Spinner
            val items7 = resources.getStringArray(R.array.preoperacional_vehiculos)

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
                    Toast.makeText(this@VehicleActivity, "$selectedItem7", Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // No se hace nada en caso de que no se seleccione ningún elemento.
                }
            }
            spinner7.onItemSelectedListener = null
        }
        private fun convertXmlToPdf(showNextButton: Boolean) {
            // Inflate the XML layout file
            // Oculta el botón btnxml3


            // Oculta el botón nextBtn si showNextButton es falso
            val nextBtn = findViewById<View>(R.id.button9)
            if (showNextButton == showNextButton) {
                nextBtn.visibility = View.GONE
            }

            val constraint = findViewById<ConstraintLayout>(R.id.constraintv2)
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
            val fileName = "Preoperacional_vehiculo2.pdf"
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