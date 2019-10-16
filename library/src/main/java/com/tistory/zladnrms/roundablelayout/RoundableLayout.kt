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
        setCornerRound(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setBackgroundWithDrawable(attrs)
        setCornerRound(context, attrs)
    }

    constructor(context: Context) : super(context) {
        setCornerRound(context, null)
    }

    private fun setBackgroundWithDrawable(attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundableLayout)
            cornerLeftTop = typedArray.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftTop,0).toFloat()
            cornerRightTop = typedArray.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightTop,0).toFloat()
            cornerLeftBottom = typedArray.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftBottom,0).toFloat()
            cornerRightBottom = typedArray.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightBottom,0).toFloat()
            backgroundColor = typedArray.getString(R.styleable.RoundableLayout_backgroundColor)
            typedArray.recycle()

            val drawable = GradientDrawable()
            drawable.cornerRadii = floatArrayOf(cornerRightBottom, cornerRightBottom, cornerRightTop, cornerRightTop, cornerLeftTop, cornerLeftTop, cornerLeftBottom, cornerLeftBottom)

            backgroundColor?.let {
                drawable.setColor(Color.parseColor(it))
            } ?: drawable.setColor(Color.WHITE)
            background = drawable

            clipChildren = false
        }
    }

    private fun setCornerRound(context: Context, attrs: AttributeSet?) {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.RoundableLayout)
            cornerLeftTop = typedArray.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightBottom,0).toFloat()
            cornerRightTop = typedArray.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightTop,0).toFloat()
            cornerLeftBottom = typedArray.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftBottom,0).toFloat()
            cornerRightBottom = typedArray.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftTop,0).toFloat()

            typedArray.recycle()
        }
    }

    private fun dpToFloat(context: Context, value: Float): Float {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.resources.displayMetrics)
    }

    override fun dispatchDraw(canvas: Canvas) {
        if(path == null) {
            path = Path()
        }

        val radii = floatArrayOf(cornerLeftTop, cornerLeftTop, cornerRightTop, cornerRightTop, cornerRightBottom, cornerRightBottom, cornerLeftBottom, cornerLeftBottom)

        path?.let {
            it.addRoundRect(RectF(0F,0F,canvas.width.toFloat(), canvas.height.toFloat()), radii, Path.Direction.CW)
            canvas.clipPath(it)
        }
        super.dispatchDraw(canvas)
    }
}