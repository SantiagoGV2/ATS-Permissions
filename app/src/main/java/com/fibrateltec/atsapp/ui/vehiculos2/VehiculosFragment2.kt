package com.fibrateltec.atsapp.ui.vehiculos2

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.os.Environment
import android.os.PersistableBundle
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
        private var isCreatePDFButtonVisible = true
        private var btnNextVisibility = View.VISIBLE

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_vehiculo2)

            val nxtboton: Button = findViewById(R.id.button9)
            btnNextVisibility = nxtboton.visibility
            nxtboton.setOnClickListener {
                val intent = Intent(this, PdfFragment5.VehiclePdfActivity::class.java)
                startActivity(intent)
            }

            val btnxml2: Button = findViewById(R.id.button8)
            btnxml2.setOnClickListener {
                isCreatePDFButtonVisible = !isCreatePDFButtonVisible
                btnxml2.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE
                exportToPDF()
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
        private fun exportToPDF() {
            val document = Document()
            val path = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                "Preoperacional_vehiculo2.pdf"
            ).absolutePath
            val file = File(path)
            try {
                val fileOutputStream = FileOutputStream(file)

                PdfWriter.getInstance(document, fileOutputStream)

                document.open()

                // Agrega el primer contenido al documento PDF
                val constraint: ConstraintLayout = findViewById(R.id.constraintv2)
                addViewToPDF(document, constraint)

                document.close()
                findViewById<Button>(R.id.button9).visibility = btnNextVisibility
                Toast.makeText(this, "Guardado exitosamente en $path", Toast.LENGTH_LONG).show()

            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }


        private fun addViewToPDF(document: Document, view: View) {
            val btnCreatePDF = view.findViewById<Button>(R.id.button8)
            btnCreatePDF.visibility = if (isCreatePDFButtonVisible) View.VISIBLE else View.GONE
            findViewById<Button>(R.id.button9).visibility = View.GONE

            // Calcula el margen del documento
            val margin = 0f

            val increasedPageWidth = 550f
            // Calcula el tamaño de la página del documento
            val pageSize = document.pageSize
            val pageWidth = increasedPageWidth - margin * 2.5f
            val pageHeight = pageSize.height - margin * 2.7f

            // Convierte la vista a un bitmap
            val bitmap = convertViewToBitmap(view)

            // Convierte el bitmap a bytes para agregarlo al documento PDF
            val stream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            val image = Image.getInstance(stream.toByteArray())

            // Ajusta el tamaño de la imagen al documento
            val aspectRatio = image.width.toFloat() / image.height.toFloat()
            val newWidth = pageWidth * 1.5f // Ajusta el ancho según tu preferencia
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