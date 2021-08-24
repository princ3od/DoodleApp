package com.example.doodleapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import java.util.ArrayList;

public class PaintView extends View {
    Path path = new Path();
    Paint brush = new Paint();
    ArrayList<Path> paths = new ArrayList<Path>();
    ArrayList<Paint> brushes = new ArrayList<Paint>();
    public int alpha = 255, red = 0, green = 0, blue = 255;
    float thickness = 5f;

    public PaintView(Context context) {
        this(context, null);

    }

    public PaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(Color.WHITE);
        brush.setAntiAlias(true);
        brush.setColor(Color.BLUE);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setStrokeWidth(5.0f);
        paths.add(path);
        brushes.add(brush);
    }

    public PaintView(Context context, AttributeSet attrs, int defStyle) {
        this(context, attrs);
    }

    public void clearCanvas() {
        paths.clear();
        brushes.clear();
        path = new Path();
        brush = new Paint();
        int color = Color.argb(alpha, red, green, blue);
        brush.setAntiAlias(true);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setColor(color);
        brush.setStrokeWidth(thickness);
        paths.add(path);
        brushes.add(brush);
        postInvalidate();
    }

    public void setValue(int alpha, int red, int green, int blue, float thickness) {
        path = new Path();
        brush = new Paint();
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.thickness = thickness;
        int color = Color.argb(alpha, red, green, blue);
        brush.setAntiAlias(true);
        brush.setStyle(Paint.Style.STROKE);
        brush.setStrokeJoin(Paint.Join.ROUND);
        brush.setColor(color);
        brush.setStrokeWidth(thickness);
        paths.add(path);
        brushes.add(brush);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float pointX = event.getX();
        float pointY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(pointX, pointY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(pointX, pointY);
                break;
            default:
                return false;
        }
        postInvalidate();
        return false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (int i = 0; i < paths.size(); i++)
            canvas.drawPath(paths.get(i), brushes.get(i));
    }
}
