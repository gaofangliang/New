package com.example.administrator.myapplication.ui.waveview;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2018/5/5 0005.
 */
public class WaveView extends View {
    private Path abovePath;
    private Path belowPath;
    private Paint belowPaint;
    private Paint abovePaint;
    private PaintFlagsDrawFilter filter;
    private float t;

    public WaveView(Context context) {
        super(context);
    }

    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始化路线
        abovePath = new Path();
        belowPath = new Path();
        //创建两个画笔 用来画两条线
        //画上边线
        //创建画笔
        abovePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        abovePaint.setAntiAlias(true);//设置抗锯齿
        abovePaint.setStyle(Paint.Style.FILL);
        abovePaint.setColor(Color.WHITE);//设置颜色白色

        //画下边线
        //创建画笔
        belowPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        belowPaint.setAntiAlias(true);//设置抗锯齿
        belowPaint.setStyle(Paint.Style.FILL);
        belowPaint.setColor(Color.WHITE);//设置颜色白色
        belowPaint.setAlpha(100);//设置透明度

        //抗锯齿
        filter = new PaintFlagsDrawFilter(0, Paint.ANTI_ALIAS_FLAG | Paint.FILTER_BITMAP_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.setDrawFilter(filter);
        //重新设定路线
        abovePath.reset();
        belowPath.reset();

        float y1,y2;
         t-= 0.1f;
         float A =5;
        float w = (float) (2*Math.PI/getWidth());

        //两根线的起始点位置
        abovePath.moveTo(getLeft(),getBottom());
        belowPath.moveTo(getLeft(),getBottom());
        //循环划线
        for (int x = 0; x <= getWidth(); x++) {
            y1 = (float) (8*Math.cos(w*x+t)+8);
            y2 = (float) (8*Math.sin(w*x+t));
            abovePath.lineTo(x,y1);//划线
            belowPath.lineTo(x,y2);

        }
        abovePath.lineTo(getRight(),getBottom());
        belowPath.lineTo(getRight(),getBottom());
        //画两根线
        canvas.drawPath(abovePath,abovePaint);
        canvas.drawPath(belowPath,belowPaint);
        postInvalidateDelayed(20);
    }
}
