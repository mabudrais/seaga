package com.example.seaga;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;


public class mainview extends View {
	protected seagasarface sarf;
	protected long pretime;
	public mainview(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		sarf=new welcom_anim(getWidth(),getHeight(),getResources());
	}

	@Override
	public void draw(Canvas canvas) {
		// TODO Auto-generated method stub
		/*Paint cpaint=new Paint();
		cpaint.setARGB(0,0,0,255);
		getResources();
		canvas.drawCircle((float)0.0, (float)0.0,(float)200.0,cpaint);
		Paint mBigCharPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBigCharPaint.setTextSize(15);
		//mBigCharPaint.setTextAlign(Paint.Align.CENTER);
		mBigCharPaint.setARGB(250, 200, 0, 0);
		mBigCharPaint.setTextSize((float) 50.0);
		canvas.drawText(new Integer(canvas.getHeight()).toString(), (float) 0.0,
				(float) 20.0, mBigCharPaint);*/
		//super.draw(canvas);
		sarf=sarf.draw(canvas);
		invalidate();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		long ctim=java.lang.System.currentTimeMillis();
		if(ctim-pretime>500){
		sarf=sarf.touch((int)event.getX(),(int)event.getY());
		pretime=ctim;
		}
		return true;
	}

}
