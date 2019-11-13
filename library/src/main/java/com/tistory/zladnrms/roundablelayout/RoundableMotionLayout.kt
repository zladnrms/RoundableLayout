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
import androidx.constraintlayout.motion.widget.MotionLayout

class RoundableMotionLayout : MotionLayout {

    private var path: Path? = null

    // corner radius
    private var cornerLeftTop: Float = 0F
    private var cornerRightTop: Float = 0F
    private var cornerLeftBottom: Float = 0F
    private var cornerRightBottom: Float = 0F

    // background color
    private var backgroundColor: Int? = null

    // stroke
    private var strokeWidth: Int? = null
    private var strokeColor: Int? = null
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
            context.obtainStyledAttributes(attrs, R.styleable.RoundableMotionLayout).apply {
                cornerLeftTop = this.getDimensionPixelSize(R.styleable.RoundableMotionLayout_mlCornerLeftTop,0).toFloat()
                cornerRightTop = this.getDimensionPixelSize(R.styleable.RoundableMotionLayout_mlCornerRightTop,0).toFloat()
                cornerLeftBottom = this.getDimensionPixelSize(R.styleable.RoundableMotionLayout_mlCornerLeftBottom,0).toFloat()
                cornerRightBottom = this.getDimensionPixelSize(R.styleable.RoundableMotionLayout_mlCornerRightBottom,0).toFloat()
                backgroundColor = this.getColor(R.styleable.RoundableMotionLayout_mlBackgroundColor, Color.WHITE)
                strokeWidth = this.getDimensionPixelSize(R.styleable.RoundableMotionLayout_mlStrokeLineWidth,0)
                strokeColor = this.getColor(R.styleable.RoundableMotionLayout_mlStrokeLineColor, Color.BLACK)
                dashWidth = this.getDimensionPixelSize(R.styleable.RoundableMotionLayout_mlDashLineWidth,0).toFloat()
                dashGap = this.getDimensionPixelSize(R.styleable.RoundableMotionLayout_mlDashLineGap,0).toFloat()
            }.run {
                this.recycle()
            }

            //set drawable resource corner & background & stroke
            GradientDrawable().apply {
                this.cornerRadii = floatArrayOf(cornerLeftTop, cornerLeftTop, cornerRightTop, cornerRightTop, cornerRightBottom, cornerRightBottom, cornerLeftBottom, cornerLeftBottom)

                if(strokeWidth != null && strokeColor != null)
                    this.setStroke(strokeWidth!!, strokeColor!!, dashWidth, dashGap)

                backgroundColor?.let { // set background color
                    this.setColor(it)
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