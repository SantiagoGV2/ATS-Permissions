package com.fibrateltec.atsapp.ui.pdf





import com.itextpdf.kernel.pdf.PdfReader
import java.io.File
import java.io.FileOutputStream
import com.itextpdf.kernel.pdf.PdfDocument
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fibrateltec.atsapp.databinding.FragmentPdfBinding
import com.itextpdf.kernel.pdf.PdfWriter
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import com.fibrateltec.atsapp.MainActivity
import com.fibrateltec.atsapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale




class PdfFragment : Fragment() {

    private var _binding: FragmentPdfBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val PdfViewModel =
            ViewModelProvider(this).get(PdfViewModel::class.java)

        _binding = FragmentPdfBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textPdf1
        PdfViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    class TentthActivity : AppCompatActivity() {

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.fragment_pdf)


            val btnDescarga: Button = findViewById(R.id.btnDescarga)
            btnDescarga.setOnClickListener {
                exportToPDF()
            }

            val btnvolver: Button = findViewById(R.id.btnvolver)
            btnvolver.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }

            val linkTextView: TextView = findViewById(R.id.linkTextView1)
            linkTextView.setOnClickListener {
                val url =
                    "https://drive.google.com/drive/folders/1G8AQEhAG4MKW4vaV_QhP8r2nBDJKjJzu" // Reemplaza con tu URL
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(url)
                startActivity(intent)

            }

        }



        private fun exportToPDF() {
            val sdf = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            val currentDateAndTime: String = sdf.format(Date())

            val fileName = "FormularioRiesgoElectrico_$currentDateAndTime.pdf"
            val path = File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName
            ).absolutePath
            val file = File(path)

            try {
                val fileOutputStream = FileOutputStream(file)
                val writer = PdfWriter(fileOutputStream)
                val document = PdfDocument(writer)
                document.addNewPage()

                // Agrega el contenido de los tres PDFs al documento
                val pdfFiles =
                    listOf("Riesgo.pdf", "Peligros_potenciales2.pdf", "Tareas_criticas2.pdf", "Verificacion_distancias.pdf")
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





