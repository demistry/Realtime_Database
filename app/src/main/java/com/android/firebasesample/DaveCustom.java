package com.android.firebasesample;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ILENWABOR DAVID on 16/02/2018.
 */

public class DaveCustom extends View {
    private int circleColor, labelColor;
    private String circleLabel;
    private Paint paint;
    public DaveCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        this.measure(96, 96);
        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DaveCustom, 0,0);
        try{
            circleLabel = typedArray.getString(R.styleable.DaveCustom_circleLabel);
            circleColor = typedArray.getInteger(R.styleable.DaveCustom_circleColor,0);
            labelColor = typedArray.getInteger(R.styleable.DaveCustom_labelColor, 0);
        }
        finally{
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int viewWidthHalf = this.getMeasuredWidth()/2;
        int viewHeightHalf = this.getMeasuredHeight()/2;
        int radius = 0;
        if(viewWidthHalf>viewHeightHalf)
            radius=viewHeightHalf-10;
        else
            radius=viewWidthHalf-10;
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(circleColor);
        canvas.drawCircle(viewWidthHalf, viewHeightHalf, radius, paint);
        paint.setColor(labelColor);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        canvas.drawText(circleLabel, viewWidthHalf, viewHeightHalf, paint);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public int getCircleColor() {
        return circleColor;
    }

    public void setCircleColor(int circleColor) {
        this.circleColor = circleColor;
    }

    public int getLabelColor() {
        return labelColor;
    }

    public void setLabelColor(int labelColor) {
        this.labelColor = labelColor;
    }

    public String getCircleLabel() {
        return circleLabel;
    }

    public void setCircleLabel(String circleLabel) {
        this.circleLabel = circleLabel;
        invalidate();
        requestLayout();
    }
}
