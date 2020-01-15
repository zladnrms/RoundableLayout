package com.tistory.zladnrms.roundablelayout

import android.annotation.TargetApi
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.RectF
import android.graphics.drawable.GradientDrawable
import android.os.Build
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import android.view.ViewOutlineProvider
import android.graphics.Outline
import android.view.View
import java.lang.Exception


class RoundableLayout : ConstraintLayout {

    var path: Path? = null

    /** corner radius */
    var cornerLeftTop: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    var cornerRightTop: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    var cornerLeftBottom: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    var cornerRightBottom: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }


    /** side option means top and bottom corner */

    /**
     * if left side value existed,
     * leftTop and leftBottom value is equal to leftSide value.
     * this is made in consideration of the custom attribute of motion layout.
     * because Constraint only has maximum two custom attribute. (2.0.0-beta2)
     */
    var cornerLeftSide: Float = 0F
        set(value) {
            field = value

            if (field != 0F) {
                cornerLeftTop = field
                cornerLeftBottom = field
            }

            postInvalidate()
        }

    var cornerRightSide: Float = 0F
        set(value) {
            field = value

            if (field != 0F) {
                cornerRightTop = field
                cornerRightBottom = field
            }

            postInvalidate()
        }


    /** background color */
    var backgroundColor: Int? = null
        set(value) {
            field = value
            postInvalidate()
        }


    /** stroke & dash options */
    var strokeLineWidth: Int = 0
        set(value) {
            field = value
            postInvalidate()
        }

    var strokeLineColor: Int? = null
        set(value) {
            field = value
            postInvalidate()
        }

    var dashLineGap: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    var dashLineWidth: Float = 0F
        set(value) {
            field = value
            postInvalidate()
        }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
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

            /** set corner radii */
            context.obtainStyledAttributes(it, R.styleable.RoundableLayout).apply {
                cornerLeftTop =
                    this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftTop, 0)
                        .toFloat()
                cornerRightTop =
                    this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightTop, 0)
                        .toFloat()
                cornerLeftBottom =
                    this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftBottom, 0)
                        .toFloat()
                cornerRightBottom =
                    this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightBottom, 0)
                        .toFloat()
                backgroundColor =
                    this.getColor(R.styleable.RoundableLayout_backgroundColor, Color.WHITE)
                strokeLineWidth =
                    this.getDimensionPixelSize(R.styleable.RoundableLayout_strokeLineWidth, 0)
                strokeLineColor =
                    this.getColor(R.styleable.RoundableLayout_strokeLineColor, Color.BLACK)
                dashLineWidth = this.getDimensionPixelSize(R.styleable.RoundableLayout_dashLineWidth, 0)
                    .toFloat()
                dashLineGap =
                    this.getDimensionPixelSize(R.styleable.RoundableLayout_dashLineGap, 0).toFloat()
                cornerLeftSide =
                    this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerLeftSide, 0)
                        .toFloat()
                cornerRightSide =
                    this.getDimensionPixelSize(R.styleable.RoundableLayout_cornerRightSide, 0)
                        .toFloat()
            }.run {
                this.recycle()
            }
        }
    }

    override fun dispatchDraw(canvas: Canvas) {
        /** for outline remake whenenver draw */
        path = null

        if (path == null) {
            path = Path()
        }

        floatArrayOf(
            cornerLeftTop, cornerLeftTop, cornerRightTop, cornerRightTop, cornerRightBottom,
            cornerRightBottom, cornerLeftBottom, cornerLeftBottom
        ).apply {
            clipPathCanvas(canvas, this)
        }

        /** set drawable resource corner & background & stroke */
        GradientDrawable().apply {
            this.cornerRadii = floatArrayOf(
                cornerLeftTop, cornerLeftTop, cornerRightTop, cornerRightTop,
                cornerRightBottom, cornerRightBottom, cornerLeftBottom, cornerLeftBottom
            )

            if (strokeLineWidth != 0 && strokeLineColor != null)
                this.setStroke(strokeLineWidth, strokeLineColor!!, dashLineWidth, dashLineGap)

            backgroundColor?.let {
                /** set background color */
                this.setColor(it)
            } ?: this.setColor(Color.WHITE)

            /** set background color default : WHITE */
            background = this
        }


        outlineProvider = getOutlineProvider()

        clipChildren = false

        super.dispatchDraw(canvas)
    }

    private fun clipPathCanvas(canvas: Canvas, floatArray: FloatArray) {
        path?.let {
            it.addRoundRect(
                RectF(0F, 0F, canvas.width.toFloat(), canvas.height.toFloat()),
                floatArray,
                Path.Direction.CW
            )
            canvas.clipPath(it)
        }
    }

    /** For not showing red underline */
    override fun setOutlineProvider(provider: ViewOutlineProvider?) {
        super.setOutlineProvider(provider)
    }

    /** For not showing red underline */
    override fun setElevation(elevation: Float) {
        super.setElevation(elevation)
    }

    /** For not showing red underline */
    override fun setTranslationZ(translationZ: Float) {
        super.setTranslationZ(translationZ)
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun getOutlineProvider(): ViewOutlineProvider {
        return object : ViewOutlineProvider() {
            override fun getOutline(view: View, outline: Outline) {
                path?.let {
                    outline.setConvexPath(it)
                } ?: throw Exception()
            }
        }
    }


}