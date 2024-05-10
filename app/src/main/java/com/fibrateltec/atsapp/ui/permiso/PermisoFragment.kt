package com.fibrateltec.navbar.ui.permiso

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
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat

import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentPermisoBinding
import com.fibrateltec.atsapp.ui.permiso2.PermisoFragment2
import com.itextpdf.text.Document
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.ceil
import kotlin.math.min


class PermisoFragment : Fragment() {

    private var _binding: FragmentPermisoBinding? = null
    private var btnNextVisibility = View.VISIBLE
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPermisoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val permisoViewModel = ViewModelProvider(this).get(PermisoViewModel::class.java)
        askPermissions()
        val textView: TextView = binding.Permiso
        permisoViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        // Llama a la función de selección aquí
        seleccion(root.context)


        val nxtboton: Button = root.findViewById(R.id.next)
        btnNextVisibility = nxtboton.visibility
        nxtboton.setOnClickListener {
            val intent = Intent(requireContext(), PermisoFragment2.FourteenthActivity::class.java)
            startActivity(intent)
            convertXmlToPdf(showNextButton = false) // Muestra nextBtn en el PDF
        }

        val etBirthDate: TextView = root.findViewById(R.id.fecha_Realizacion)

        val fechaActual = obtenerFechaActual()

        etBirthDate.text = "Fecha Actual:$fechaActual"

        val spinner19: Spinner = root.findViewById(R.id.nombresyapellidos)

        // Define los elementos del Spinner
        //val items18 = listOf("Bueno", "Regular", "Para cambio")
        val items19 = arrayOf("Tecnico", *resources.getStringArray(R.array.tecnicos))
        // Crea un adaptador y establece los elementos en el Spinner
        val adapter19 =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items19)
        adapter19.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
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

        val spinner20: Spinner = root.findViewById(R.id.cedula)

        // Define los elementos del Spinner
        //val items18 = listOf("Bueno", "Regular", "Para cambio")
        val items20 = arrayOf("Cedula Técnico", *resources.getStringArray(R.array.cedulas))

        // Crea un adaptador y establece los elementos en el Spinner
        val adapter20 = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, items20)
        adapter20.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
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

        return root
    }


    private fun obtenerFechaActual(): String {
        val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val fecha = Date()
        return dateFormat.format(fecha)
    }



    private fun seleccion(context: Context) {
        val button: Button = binding.btnHerramientas // Usa la referencia de la vista inflada
        val selectedItems = mutableListOf<String>()
        button.setOnClickListener {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Herramientas a Utilizar")

            val primerItems = resources.getStringArray(R.array.spinnerHerramienta)

            val items = mutableListOf<String>().apply {
                addAll(primerItems)
            }.toTypedArray()

            val checkedItems = BooleanArray(items.size)

            builder.setMultiChoiceItems(items, checkedItems) { _, i, b ->
                if (i != primerItems.size && i != items.size - 0) {
                    if (b) {
                        selectedItems.add(items[i])
                    } else {
                        selectedItems.remove(items[i])
                    }
                }
            }

            builder.setPositiveButton("Aceptar") { _, _ ->
                mostrarSelecciones(selectedItems)
            }

            builder.show()
        }
    }

    private fun mostrarSelecciones(selecciones: List<String>) {
        val textView: TextView = binding.textHerramientas // Reemplaza con tu ID de TextView
        textView.text = "Selecciones: ${selecciones.joinToString(", ")}"
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
            binding.next.visibility = View.GONE
        }

        val constraint: ConstraintLayout= binding.constraint12

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
        val fileName = "DatosBasicos.pdf"
        val filePath = File(downloadsDir, fileName)
        try {
            // Save the document to a file
            val fos = FileOutputStream(filePath)
            document.writeTo(fos)
            document.close()

            // Restablece la visibilidad del botón btnxml3

            // Restablece la visibilidad del botón nextBtn si showNextButton es true

            binding.next.visibility = View.VISIBLE


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