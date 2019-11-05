package com.tistory.zladnrms.roundablelayout

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import android.util.TypedValue

class RoundableLayout : ConstraintLayout {

    private var path: Path? = null
    private var cornerLeftTop: Float = 0F
    private var cornerRightTop: Float = 0F
    private var cornerLeftBottom: Float = 0F
    private var cornerRightBottom: Float = 0F
    private var backgroundColor: String? = null

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        setBackgroundWithDrawable(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setBackgroundWithDrawable(attrs)
    }

    constructor(context: Context) : super(context) {

    }

    private fun setBackgroundWithDrawable(attrs: AttributeSet?) {
        attrs?.let {
            context.obtainStyledAttributes(attrs, R.styleable.RoundableLayout).apply {
                cornerLeftTop = this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftTop,0).toFloat()
                cornerRightTop = this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightTop,0).toFloat()
                cornerLeftBottom = this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftBottom,0).toFloat()
                cornerRightBottom = this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightBottom,0).toFloat()
                backgroundColor = this.getString(R.styleable.RoundableLayout_backgroundColor)
            }.run {
                this.recycle()
            }

            //set background
            GradientDrawable().apply {
                this.cornerRadii = floatArrayOf(cornerLeftTop, cornerLeftTop, cornerRightTop, cornerRightTop, cornerRightBottom, cornerRightBottom, cornerLeftBottom, cornerLeftBottom)

                backgroundColor?.let { // set background color
                    this.setColor(Color.parseColor(it))
                } ?: this.setColor(Color.WHITE) // set background color default : WHITE
                background = this
            }

            clipChildren = false
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        if(path == null) {
            path = Path()
        }

        floatArrayOf(cornerLeftTop, cornerLeftTop, cornerRightTop, cornerRightTop, cornerRightBottom, cornerRightBottom, cornerLeftBottom, cornerLeftBottom).apply {
            path?.let {
                it.addRoundRect(RectF(0F,0F,canvas.width.toFloat(), canvas.height.toFloat()), this, Path.Direction.CW)
                canvas.clipPath(it)
            }
        }
        super.dispatchDraw(canvas)
    }
}