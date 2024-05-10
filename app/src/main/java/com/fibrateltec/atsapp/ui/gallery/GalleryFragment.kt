package com.fibrateltec.atsapp.ui.gallery



import android.Manifest
import java.text.SimpleDateFormat
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
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentGalleryBinding
import com.fibrateltec.atsapp.ui.gallery2.GalleryFragment2
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.Locale
import java.util.Date
import kotlin.math.ceil
import kotlin.math.min
import android.content.Intent





class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private var btnNextVisibility = View.VISIBLE




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        askPermissions()
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        val spinner: Spinner = root.findViewById(R.id.mySpinner1)

        // Define los elementos del Spinner
        val items = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items)
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
                Toast.makeText(requireContext(), "$selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner.onItemSelectedListener = null
        // Encuentra el Spinner por su ID
        val spinner1: Spinner = root.findViewById(R.id.spinner2)

        // Define los elementos del Spinner
        val items1 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter1 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items1)
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
                Toast.makeText(requireContext(), "$selectedItem1", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner1.onItemSelectedListener = null
        val spinner2: Spinner = root.findViewById(R.id.spinner3)

        // Define los elementos del Spinner
        val items2 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter2 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items2)
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
                Toast.makeText(requireContext(), "$selectedItem2", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner2.onItemSelectedListener = null
        val spinner3: Spinner = root.findViewById(R.id.spinner4)

        // Define los elementos del Spinner
        val items3 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter3 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items3)
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
                Toast.makeText(requireContext(), "$selectedItem3", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner3.onItemSelectedListener = null
        val spinner4: Spinner = root.findViewById(R.id.spinner5)

        // Define los elementos del Spinner
        val items4 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter4 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items4)
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
                Toast.makeText(requireContext(), "$selectedItem4", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner4.onItemSelectedListener = null
        val spinner5: Spinner = root.findViewById(R.id.spinner6)
        // Define los elementos del Spinner
        val items5 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter5 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items5)
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
                Toast.makeText(requireContext(), "$selectedItem5", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner5.onItemSelectedListener = null
        val spinner6: Spinner = root.findViewById(R.id.spinner7)

        // Define los elementos del Spinner
        val items6 =resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter6 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items6)
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
                Toast.makeText(requireContext(), "$selectedItem6", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner6.onItemSelectedListener = null
        val spinner7: Spinner = root.findViewById(R.id.spinner8)

        // Define los elementos del Spinner
        val items7 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter7 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items7)
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
                Toast.makeText(requireContext(), "$selectedItem7", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner7.onItemSelectedListener = null
        val spinner8: Spinner = root.findViewById(R.id.spinner9)


        // Define los elementos del Spinner
        val items8 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter8 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items8)
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
                Toast.makeText(requireContext(), "$selectedItem8", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner8.onItemSelectedListener = null
        val spinner9: Spinner = root.findViewById(R.id.spinner10)

        // Define los elementos del Spinner
        val items9 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter9 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items9)
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
                Toast.makeText(requireContext(), "$selectedItem9", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner9.onItemSelectedListener = null
        val spinner10: Spinner = root.findViewById(R.id.spinner11)

        // Define los elementos del Spinner
        val items10 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter10 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items10)
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
                Toast.makeText(requireContext(), "$selectedItem10", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner10.onItemSelectedListener = null
        val spinner11: Spinner = root.findViewById(R.id.spinner12)

        // Define los elementos del Spinner
        val items11 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter11 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items11)
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
                Toast.makeText(requireContext(), "$selectedItem11", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner11.onItemSelectedListener = null
        val spinner12: Spinner = root.findViewById(R.id.spinner13)

        // Define los elementos del Spinner
        val items12 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter12 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items12)
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
                Toast.makeText(requireContext(), "$selectedItem12", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner12.onItemSelectedListener = null
        val spinner13: Spinner = root.findViewById(R.id.spinner14)

        // Define los elementos del Spinner
        val items13 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter13 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items13)
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
                Toast.makeText(requireContext(), "$selectedItem13", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner13.onItemSelectedListener = null
        val spinner14: Spinner = root.findViewById(R.id.spinner15)

        // Define los elementos del Spinner
        val items14 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter14 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items14)
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
                Toast.makeText(requireContext(), "$selectedItem14", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner14.onItemSelectedListener = null
        val spinner15: Spinner = root.findViewById(R.id.spinner16)

        // Define los elementos del Spinner
        val items15 = resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter15 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items15)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner15.adapter = adapter15

        spinner15.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem15 = items15[position]
                Toast.makeText(requireContext(), "$selectedItem15", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner15.onItemSelectedListener = null
        val spinner16: Spinner = root.findViewById(R.id.spinner17)

        // Define los elementos del Spinner
        val items16 =  resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter16 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items16)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner16.adapter = adapter16

        spinner16.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem16 = items16[position]
                Toast.makeText(requireContext(), "$selectedItem16", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner16.onItemSelectedListener = null
        val spinner17: Spinner = root.findViewById(R.id.spinner18)

        // Define los elementos del Spinner
        val items17 =  resources.getStringArray(R.array.spinners)

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter17 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items17)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner17.adapter = adapter17

        spinner17.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem17 = items17[position]
                Toast.makeText(requireContext(), "$selectedItem17", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }
        spinner17.onItemSelectedListener = null

        val spinner19: Spinner = root.findViewById(R.id.NombreyApellido)

        // Define los elementos del Spinner
        //val items18 = listOf("Bueno", "Regular", "Para cambio")
        val items19 = arrayOf("Tecnico", *resources.getStringArray(R.array.tecnicos))
        // Crea un adaptador y establece los elementos en el Spinner
        val adapter19 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items19)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner19.adapter = adapter19

        spinner19.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) { // Si no es el pretexto
                    val selectedItem19 = items19[position]
                    Toast.makeText(requireContext(), "$selectedItem19", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }

        spinner19.onItemSelectedListener = null

        val spinner20: Spinner = root.findViewById(R.id.supervisor)

        // Define los elementos del Spinner
        //val items18 = listOf("Bueno", "Regular", "Para cambio")
        val items20 = arrayOf("Supervisor", *resources.getStringArray(R.array.supervisores))
        // Crea un adaptador y establece los elementos en el Spinner
        val adapter20 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items20)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner20.adapter = adapter20

        spinner20.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position != 0) { // Si no es el pretexto
                    val selectedItem20 = items20[position]
                    Toast.makeText(requireContext(), "$selectedItem20", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }

        spinner20.onItemSelectedListener = null

        val spinner18: Spinner = root.findViewById(R.id.spinner19)

        // Define los elementos del Spinner
        //val items18 = listOf("Bueno", "Regular", "Para cambio")
        val items18 = resources.getStringArray(R.array.spinners)
        // Crea un adaptador y establece los elementos en el Spinner
        val adapter18 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items18)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner18.adapter = adapter18

        spinner18.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem18 = items18[position]
                Toast.makeText(requireContext(), "$selectedItem18", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // No se hace nada en caso de que no se seleccione ningún elemento.
            }
        }

        spinner18.onItemSelectedListener = null



        val nxtboton: Button = root.findViewById(R.id.nextBtn)
        btnNextVisibility = nxtboton.visibility
        nxtboton.setOnClickListener {
            val intent = Intent(requireContext(), GalleryFragment2.SecondActivity::class.java)
            startActivity(intent)
            convertXmlToPdf(showNextButton = false) // Muestra nextBtn en el PDF
        }


        val etBirthDate: TextView= root.findViewById(R.id.fecha)

        val fechaActual = obtenerFechaActual()

        etBirthDate.text = "Fecha Actual:$fechaActual"

        return root
    }


    private fun obtenerFechaActual(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fecha = Date()
        return dateFormat.format(fecha)
    }

    private fun askPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
            REQUEST_CODE
        )
    }

    private fun convertXmlToPdf(showNextButton: Boolean) {
        // Oculta el botón btnxml3

        // Oculta el botón nextBtn si showNextButton es falso
        if (showNextButton == showNextButton) {
            binding.nextBtn.visibility = View.GONE
        }

        val constraint: ConstraintLayout= binding.constraint3

        val displayMetrics = DisplayMetrics()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireContext().display!!.getRealMetrics(displayMetrics)
        } else {
            requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)
        }

        // Measure the view with UNSPECIFIED height
        constraint.measure(
            View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY),
            View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED)
        )

        // Calculate total number of pages needed based on view height
        val totalHeight = constraint.measuredHeight
        val totalPages = ceil((totalHeight.toFloat() / displayMetrics.heightPixels).toDouble()).toInt()

        // Create a new PdfDocument instance
        val document = PdfDocument()

        // Obtain the width and height of the view
        val viewWidth = constraint.measuredWidth
        for (i in 0 until totalPages) {
            // Create a PageInfo object specifying the page attributes
            val pageInfo = PdfDocument.PageInfo.Builder(viewWidth, displayMetrics.heightPixels, i + 1).create()

            // Start a new page
            val page = document.startPage(pageInfo)

            // Get the Canvas object to draw on the page
            val canvas = page.canvas

            // Calculate the portion of the view to be drawn on this page
            val start = i * displayMetrics.heightPixels
            val end = min((start + displayMetrics.heightPixels).toDouble(), totalHeight.toDouble()).toInt()

            // Translate the canvas to draw the correct portion of the view
            canvas.translate(0f, -start.toFloat())

            // Draw the portion of the view on the canvas
            constraint.layout(0, -start, viewWidth, end)
            constraint.draw(canvas)

            // Finish the page
            document.finishPage(page)
        }

        // Specify the path and filename of the output PDF file
        val downloadsDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
        val fileName = "Alturas.pdf"
        val filePath = File(downloadsDir, fileName)
        try {
            // Save the document to a file
            val fos = FileOutputStream(filePath)
            document.writeTo(fos)
            document.close()

            // Restablece la visibilidad del botón btnxml3

            // Restablece la visibilidad del botón nextBtn si showNextButton es true

            binding.nextBtn.visibility = View.VISIBLE


            fos.close()
            // PDF conversion successful
            Toast.makeText(requireContext(), "Guardado", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            // Error occurred while converting to PDF
            Toast.makeText(requireContext(), "Error al guardar: ${e.message}", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val REQUEST_CODE = 1232
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}










