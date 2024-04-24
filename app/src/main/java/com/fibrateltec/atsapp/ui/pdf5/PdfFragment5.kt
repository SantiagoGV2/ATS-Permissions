package com.fibrateltec.atsapp.ui.pdf5

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.MainActivity
import com.fibrateltec.atsapp.R
import com.fibrateltec.atsapp.databinding.FragmentPdf5Binding
import com.fibrateltec.atsapp.ui.pdf4.PdfViewModel4
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PdfFragment5 : Fragment(){

    private var _binding : FragmentPdf5Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val PdfViewModel =
            ViewModelProvider(this,).get(PdfViewModel4::class.java)

        _binding = FragmentPdf5Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPdf5
        PdfViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }
    class VehiclePdfActivity : AppCompatActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_pdf5)

            val btnDescarga: Button = findViewById(R.id.btnDescarga5)
            btnDescarga.setOnClickListener {
                exportToPDF()
            }

            val linkTextView: TextView = findViewById(R.id.linkTextView5)
            linkTextView.setOnClickListener {
                val url =
                    "https://drive.google.com/drive/folders/1G8AQEhAG4MKW4vaV_QhP8r2nBDJKjJzu" // Reemplaza con tu URL
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

            }
            val btnvolver2: Button = findViewById(R.id.volver5)
            btnvolver2.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        private fun exportToPDF() {
            val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            val currentDateAndTime: String = sdf.format(Date())
            val fileName = "FormularioVehiculo_$currentDateAndTime.pdf"

            val path = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                fileName
            ).absolutePath
            val file = File(path)

            try {
                val fileOutputStream = FileOutputStream(file)
                val writer = PdfWriter(fileOutputStream)
                val document = PdfDocument(writer)
                document.addNewPage()

                // Agrega el contenido de los tres PDFs al documento
                val pdfFiles =
                    listOf("Preoperacional_vehiculo.pdf", "Preoperacional_vehiculo2.pdf")
                pdfFiles.forEach { fileName ->
                    val pdfFile = File(
                        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                        fileName
                    )
                    val pdfReader = PdfReader(pdfFile.absolutePath)
                    val pdfDocument = PdfDocument(pdfReader)
                    pdfDocument.copyPagesTo(1, pdfDocument.numberOfPages, document)
                    pdfReader.close()
                }

                document.close()
                writer.close()

                Toast.makeText(this, "PDF creado exitosamente en $path", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this, "Error al crear el PDF: ${e.message}", Toast.LENGTH_LONG)
                    .show()
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}