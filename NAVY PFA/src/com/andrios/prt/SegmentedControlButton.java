package com.andrios.prt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.util.AttributeSet;
import android.widget.RadioButton;

public class SegmentedControlButton extends RadioButton {

    private float mX;

    public SegmentedControlButton(Context context) {
        super(context);
    }

    public SegmentedControlButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SegmentedControlButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private static final float TEXT_SIZE = 16.0f;

    @Override
    public void onDraw(Canvas canvas) {

        String text = this.getText().toString();
        Paint textPaint = new Paint();
        textPaint.setAntiAlias(true);
        float currentWidth = textPaint.measureText(text);
        float currentHeight = textPaint.measureText("x");

        // final float scale =
        // getContext().getResources().getDisplayMetrics().density;
        // float textSize = (int) (TEXT_SIZE * scale + 0.5f);
        textPaint.setTextSize(this.getTextSize());
        textPaint.setTextAlign(Paint.Align.CENTER);

        float canvasWidth = canvas.getWidth();
        float textWidth = textPaint.measureText(text);

        if (isChecked()) {
            GradientDrawable grad = new GradientDrawable(Orientation.TOP_BOTTOM, new int[] { 0xffdcdcdc, 0xff111111 });
            grad.setBounds(0, 0, this.getWidth(), this.getHeight());
            grad.draw(canvas);
            textPaint.setColor(Color.WHITE);
        } else {
            GradientDrawable grad = new GradientDrawable(Orientation.TOP_BOTTOM, new int[] { 0xffa5a5a5, 0xff000000 });
            grad.setBounds(0, 0, this.getWidth(), this.getHeight());
            grad.draw(canvas);
            textPaint.setColor(0xffcccccc);
        }

        float w = (this.getWidth() / 2) - currentWidth;
        float h = (this.getHeight() / 2) + currentHeight;
        canvas.drawText(text, mX, h, textPaint);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Style.STROKE);
        Rect rect = new Rect(0, 0, this.getWidth(), this.getHeight());
        canvas.drawRect(rect, paint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int ow, int oh) {
        super.onSizeChanged(w, h, ow, oh);
        mX = w * 0.5f; // remember the center of the screen
    }

}
