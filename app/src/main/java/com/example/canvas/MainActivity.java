package com.example.canvas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBitmap;
    private ImageView mImageView;

    private Rect mRect = new Rect();
    private Rect mBounds = new Rect();

    private static final int OFFSET = 50;
    private int mOffset = OFFSET;
    private static final int MULTIPLIER = 100;

    private int mColorBackground;
    private int mColorRectangle;
    private int mColorCircle;
    private int mColorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mColorBackground = ResourcesCompat.getColor(getResources(), R.color.colorBackground, null);
        mColorRectangle = ResourcesCompat.getColor(getResources(), R.color.colorRectangle, null);
        mColorCircle = ResourcesCompat.getColor(getResources(), R.color.colorAccent, null);
        mColorText = ResourcesCompat.getColor(getResources(), R.color.black, null);

        mPaint.setColor(mColorBackground);
        mPaintText.setColor(mColorText);
        mPaintText.setTextSize(70);

        mImageView = findViewById(R.id.my_image_view);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawSomething(view);
            }
        });

    }

    public void drawSomething(View view){
        int vWidth = view.getWidth();
        int vHeight = view.getHeight();

        int halfWidth = vWidth/2;
        int halfHeight = vHeight/2;

        if (mOffset == OFFSET){
            mBitmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
            mImageView.setImageBitmap(mBitmap);
            mCanvas = new Canvas(mBitmap);
            mCanvas.drawColor(mColorBackground);

            mCanvas.drawText(getString(R.string.keep_tapping), 200, 100, mPaintText);
            mOffset += OFFSET;
        } else {
            if (mOffset < halfWidth && mOffset < halfHeight) {
                mPaint.setColor(mColorCircle - MULTIPLIER*mOffset);
                mCanvas.drawCircle(halfWidth/2, halfHeight/2, halfHeight/5, mPaint);
                mOffset += OFFSET;

                mPaint.setColor(mColorCircle - MULTIPLIER*mOffset);
                mCanvas.drawCircle(halfWidth/2+520, halfHeight/2, halfHeight/5, mPaint);
                mOffset += OFFSET;
            }
            else {
                mPaint.setColor(mColorCircle - MULTIPLIER*mOffset);
                mCanvas.drawCircle(halfWidth, halfHeight, halfHeight/2, mPaint);
                mOffset += OFFSET;

                mPaint.setColor(mColorCircle - MULTIPLIER*mOffset);
                mCanvas.drawCircle(halfWidth/2+80, halfHeight/2+300, halfHeight/7, mPaint);
                mOffset += OFFSET;

                mPaint.setColor(mColorCircle - MULTIPLIER*mOffset);
                mCanvas.drawCircle(halfWidth/2+480, halfHeight/2+300, halfHeight/7, mPaint);
                mOffset += OFFSET;

                mPaint.setColor(mColorCircle - MULTIPLIER*mOffset);
                mCanvas.drawCircle(halfWidth, halfHeight+100, halfHeight/12, mPaint);
                mOffset += OFFSET;
            }
        }

        view.invalidate();
    }
}