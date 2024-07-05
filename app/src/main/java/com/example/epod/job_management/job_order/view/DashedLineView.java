package com.example.epod.job_management.job_order.view;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.epod.R;

public class DashedLineView extends View {

    private Paint paint;
    private Path path;
    private PathEffect pathEffect;

    public DashedLineView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        paint.setColor(ContextCompat.getColor(context, R.color.darkGrey));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        pathEffect = new DashPathEffect(new float[]{10, 20}, 0);
        paint.setPathEffect(pathEffect);
        path = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.moveTo(0, 0);
        path.lineTo(getWidth(), 0);
        canvas.drawPath(path, paint);
    }
}
