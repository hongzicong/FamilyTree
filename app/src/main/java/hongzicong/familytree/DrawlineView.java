package hongzicong.familytree;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by DELL-PC on 2017/12/4.
 */

public class DrawlineView extends View {
    private int beginX=0;
    private int beginY=0;
    private int endX=0;
    private int endY=0;
    private int offset=0;

    public DrawlineView(Context context, AttributeSet attrs){
        super(context,attrs);
    }

    public DrawlineView(Context context,int beginX,int beginY,int endX,int endY,int offset){
        super(context);
        this.beginX=beginX;
        this.beginY=beginY;
        this.endX=endX;
        this.endY=endY;
        this.offset=offset;
    }

    public int dp2px(Context context,float dp){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(dp*scale+0.5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint whiteLine=new Paint();
        whiteLine.setAntiAlias(true);
        whiteLine.setColor(Color.WHITE);
        whiteLine.setStrokeWidth(5.0f);
        whiteLine.setStyle(Paint.Style.STROKE);

        Path mPath=new Path();
        mPath.reset();
        mPath.moveTo(beginX,beginY);
        //// TODO: 2017/12/4  修改位置
        mPath.cubicTo(beginX,beginY+80,endX,beginY+80,endX,endY-80);
        canvas.drawPath(mPath,whiteLine);
    }
}
