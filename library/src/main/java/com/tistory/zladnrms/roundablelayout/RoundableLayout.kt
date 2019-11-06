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

    // corner radius
    private var cornerLeftTop: Float = 0F
    private var cornerRightTop: Float = 0F
    private var cornerLeftBottom: Float = 0F
    private var cornerRightBottom: Float = 0F

    // background color
    private var backgroundColor: String? = null

    // stroke
    private var strokeWidth: Int = 0
    private var strokeColor: String? = null
    private var dashGap: Float = 0F
    private var dashWidth: Float = 0F

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        render(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        render(attrs)
    }

    constructor(context: Context) : super(context) {
        render(null)
    }

    private fun render(attrs: AttributeSet?) {
        attrs?.let {

            //set corner radii
            context.obtainStyledAttributes(attrs, R.styleable.RoundableLayout).apply {
                cornerLeftTop = this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftTop,0).toFloat()
                cornerRightTop = this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightTop,0).toFloat()
                cornerLeftBottom = this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftBottom,0).toFloat()
                cornerRightBottom = this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightBottom,0).toFloat()
                backgroundColor = this.getString(R.styleable.RoundableLayout_backgroundColor)
                strokeWidth = this.getDimensionPixelSize(R.styleable.RoundableLayout_strokeWidth,0)
                strokeColor = this.getString(R.styleable.RoundableLayout_strokeColor)
                dashWidth = this.getDimensionPixelSize(R.styleable.RoundableLayout_dashWidth,0).toFloat()
                dashGap = this.getDimensionPixelSize(R.styleable.RoundableLayout_dashGap,0).toFloat()
            }.run {
                this.recycle()
            }

            //set drawable resource corner & background & stroke
            GradientDrawable().apply {
                this.cornerRadii = floatArrayOf(cornerLeftTop, cornerLeftTop, cornerRightTop, cornerRightTop, cornerRightBottom, cornerRightBottom, cornerLeftBottom, cornerLeftBottom)

                if(strokeWidth != 0 && strokeColor != null)
                    this.setStroke(strokeWidth, Color.parseColor(strokeColor), dashWidth, dashGap)

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