package com.iwoll.editviewdemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.iwoll.editviewdemo.R;


public class PaymentEditText extends EditText {

    private int lineColor;
    private float lineWidth;
    private int positon;
    private Paint mPaint;

    public PaymentEditText(Context context) {
        this(context, null);
    }

    public PaymentEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PaymentEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public PaymentEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PaymentEditText,defStyleAttr,0);

        int n = typedArray.getIndexCount();

        for (int i = 0; i< n; i++){

            int attr = typedArray.getIndex(i);

            switch (attr){

                case R.styleable.PaymentEditText_lineColor:
                    lineColor = getResources().getColor(typedArray.getResourceId(attr,android.R.color.holo_orange_light));
                    break;
                case R.styleable.PaymentEditText_lineWidth:
                    lineWidth = typedArray.getResourceId(attr,3);
                    break;
            }
        }

        typedArray.recycle();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(lineColor);
        mPaint.setStrokeWidth(lineWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float width = getWidth() / 6;
        float height = getHeight();
        float textPostion = width / 2;

        canvas.drawLine(0.0f,0.0f,0.0f,height,mPaint);
        canvas.drawLine(width ,0.0f,width ,height,mPaint);
        canvas.drawLine(width * 2,0.0f,width * 2,height,mPaint);
        canvas.drawLine(width * 3,0.0f,width * 3,height,mPaint);
        canvas.drawLine(width * 4,0.0f,width * 4,height,mPaint);
        canvas.drawLine(width * 5,0.0f,width * 5,height,mPaint);
        canvas.drawLine(width * 6,0.0f,width * 6,height,mPaint);
        canvas.drawLine(0.0f,height,width * 6,height,mPaint);

        mPaint.setTextSize(20f);
        mPaint.setTextAlign(Paint.Align.CENTER);
        for(int i = 1 ; i < positon + 1  ; i++  ){

            if(i == 1){
                canvas.drawText("●", textPostion, height / 2, mPaint);
            }else  canvas.drawText("●", textPostion * (2*i-2) + textPostion , height / 2, mPaint);

        }
    }

    public void changePostion(int postion){

        this.positon = postion ;
        invalidate();
    }

}
