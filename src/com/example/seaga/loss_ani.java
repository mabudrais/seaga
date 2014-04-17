package com.example.seaga;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public class loss_ani implements seagasarface {
	protected int width,hight;
	protected long start_time;
	protected Resources resources;
	
	public loss_ani(int width, int hight, Resources resources) {
		super();
		this.width = width;
		this.hight = hight;
		this.resources = resources;
	}

	@Override
	public seagasarface draw(Canvas canvas) {
		Paint mBigCharPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		mBigCharPaint.setTextSize(15);
		//mBigCharPaint.setTextAlign(Paint.Align.CENTER);
		mBigCharPaint.setARGB(250, 200, 0, 0);
		mBigCharPaint.setTextSize((float) 50.0);
		canvas.drawText("you loss", (float) 0.0,
				(float) 20.0, mBigCharPaint);
		return this;
	}

	@Override
	public seagasarface touch(int x, int y) {
		return new menu_sarf(width, hight, resources);
	}

}
