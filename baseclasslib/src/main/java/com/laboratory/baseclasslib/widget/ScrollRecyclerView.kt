package com.laboratory.baseclasslib.widget

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.util.AttributeSet
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.laboratory.baseclasslib.R


/**
 * Author: 付鑫博
 * Version: 1.15.0
 * Date: 2021/8/31
 * Mender:
 * Modify:
 * Description: RecyclerView滚动条
 */
class ScrollRecyclerView : RecyclerView {

    private var mRangeRectf: RectF = RectF()

    private var mRangPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var scrollHeight: Float = 0f

    private var scrollWidth: Float = 0f

    private var scrollWidthWeight = 50

    private var yScrollOffset = 0f

    private var scrollWidthSpace: Float = 0f

    private var childViewAllHeight = 0f

    private var visualHeight = 0f

    var range = 0f

    private var childWidth = 0

    private var currentChildWidth = 0

    private var showTime: Int = 700

    private var lastHeight: Float = 0f

    private var isHide: Boolean = false

    private var isShow: Boolean = false

    private var scrollColor: Int = 0

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)

    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init(attributeSet)
    }

    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            isHide = false
            ValueAnimator.ofInt(currentChildWidth, measuredWidth).run {
                duration = 300
                addUpdateListener {
                    currentChildWidth = it.animatedValue as Int
                    invalidate()
                }
                start()
            }
        }
    }

    private val onScrollListener = object : OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == 1) {
                handler.removeCallbacksAndMessages(null)
                isHide = true
                invalidate()
            } else if (newState == 0) {
                currentChildWidth = childWidth
                handler.sendMessageDelayed(Message.obtain(), showTime.toLong())
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            yScrollOffset += dy * range
        }
    }

    private fun init(attributeSet: AttributeSet?) {
        mRangPaint.style = Paint.Style.FILL
        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ScrollRecyclerView)
        scrollColor = typedArray.getColor(
            R.styleable.ScrollRecyclerView_scrollColor,
            ContextCompat.getColor(context, R.color.Text_gray)
        )
        isShow = typedArray.getBoolean(R.styleable.ScrollRecyclerView_isShow, true)
        showTime = typedArray.getInt(R.styleable.ScrollRecyclerView_showTime, 700)
        typedArray.recycle()
        mRangPaint.color = scrollColor
        addOnScrollListener(onScrollListener)
    }

    override fun onMeasure(widthSpec: Int, heightSpec: Int) {
        val width = MeasureSpec.getSize(widthSpec)
        scrollWidth = (width / scrollWidthWeight).toFloat()
        scrollWidthSpace = scrollWidth / 10
        childWidth = (width - scrollWidth).toInt()
        currentChildWidth = (width - scrollWidth).toInt()
        scrollWidth -= 2 * scrollWidthSpace
        for (i in 0 until childCount) {
            measureChild(getChildAt(i), childWidth, heightSpec)
            getChildAt(i).layoutParams.width = childWidth
        }
        setMeasuredDimension(width, heightSpec)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        super.onLayout(changed, l, t, r, b)
        visualHeight = measuredHeight.toFloat()
        childViewAllHeight = (getChildAt(0).height * (adapter?.itemCount ?: 0)).toFloat()
        range = 0f
        if (childViewAllHeight != 0f) {
            range = visualHeight / childViewAllHeight
        }
        scrollHeight = range * visualHeight
        if (yScrollOffset > 0) {
            yScrollOffset /= (childViewAllHeight / lastHeight)
        }
        lastHeight = childViewAllHeight
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        if (childViewAllHeight == 0f || !isShow || yScrollOffset == 0f) {
            return
        }
        canvas?.let {
            if (isHide) {
                mRangeRectf.set(
                    childWidth + scrollWidthSpace,
                    yScrollOffset,
                    childWidth + scrollWidth,
                    yScrollOffset + scrollHeight
                )
            } else {
                mRangeRectf.set(
                    currentChildWidth + scrollWidthSpace,
                    yScrollOffset,
                    currentChildWidth + scrollWidth,
                    yScrollOffset + scrollHeight
                )
            }
            it.drawRoundRect(mRangeRectf, 50f, 50f, mRangPaint)
        }
    }

    /**
     * 防止Handler内存泄漏，退出时，请销毁Message
     *
     */
    fun destroy() {
        handler.removeCallbacksAndMessages(null)
    }
}