package com.example.seaga;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class about implements seagasarface {
	protected int width,hight;
	protected long start_time;
	protected Resources resources;
	
	public about(int width, int hight, Resources resources) {
		super();
		this.width = width;
		this.hight = hight;
		this.resources = resources;
	}

	@Override
	public seagasarface draw(Canvas canvas) {
		Paint mBigCharPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBigCharPaint.setTextSize(2);
		//mBigCharPaint.setTextAlign(Paint.Align.CENTER);
		mBigCharPaint.setARGB(250, 200, 0, 0);
		mBigCharPaint.setTextSize((float) 20.0);
		canvas.drawText("game developer:", (float) 15, (float) 50,
				mBigCharPaint);
		canvas.drawText("mohammed elemam", (float) 15, (float) 100, mBigCharPaint);
		canvas.drawText("abudrais", (float) 15, (float) 150, mBigCharPaint);
		canvas.drawText("m.abudrais@", (float) 15, (float) 200, mBigCharPaint);
		canvas.drawText("gmail.com", (float) 15, (float) 220, mBigCharPaint);
		return this;
	}

	@Override
	public seagasarface touch(int x, int y) {
		// TODO Auto-generated method stub
		return new menu_sarf(width, hight, resources);
	}

}
