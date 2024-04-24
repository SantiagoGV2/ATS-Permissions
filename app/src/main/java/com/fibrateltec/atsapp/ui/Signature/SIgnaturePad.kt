package com.fibrateltec.atsapp.ui.Signature

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.os.Bundle
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity
import com.fibrateltec.atsapp.R


class SignaturePad(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val paint = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 5f
    }

    private val path = Path()
    private val signaturePath = Path()


    private var scrollView: ScrollView? = null

    fun setScrollView(scrollView: ScrollView) {
        this.scrollView = scrollView
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(signaturePath, paint)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                scrollView?.requestDisallowInterceptTouchEvent(true)
                path.moveTo(event.x, event.y)
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                path.lineTo(event.x, event.y)
                signaturePath.reset()
                signaturePath.addPath(path)
                invalidate()
                scrollView?.requestDisallowInterceptTouchEvent(true)
                return true
            }

            MotionEvent.ACTION_UP -> {
                signaturePath.lineTo(event.x, event.y)
                invalidate()
                scrollView?.requestDisallowInterceptTouchEvent(false)
                return true
            }

            else -> return false
        }
    }

    fun clearSignature() {
        path.reset()
        signaturePath.reset()
        invalidate()
    }
    fun clearSignature2() {
        path.reset()
        signaturePath.reset()
        invalidate()
    }

    fun getSignature(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawPath(signaturePath, paint)
        return bitmap
    }
}