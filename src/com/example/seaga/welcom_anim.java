package com.example.seaga;

import android.content.res.Resources;
import android.graphics.*;

public class welcom_anim implements seagasarface {

	protected Bitmap bitmap;
	protected int width,hight;
	protected long start_time;
	protected Resources resources;
	public welcom_anim(int width, int hight,Resources res) {
		super();
		this.width = width;
		this.hight = hight;
		this.resources=res;
		start_time= java.lang.System.currentTimeMillis();
		bitmap= BitmapFactory.decodeResource(
				res,
				res.getIdentifier("com.example.seaga:drawable/seega", null, null));
	}

	@Override
	public seagasarface draw(Canvas canvas) {
		Rect place = new Rect(0, 0,canvas.getWidth(), canvas.getHeight());
		canvas.drawBitmap(bitmap, null, place, null);
		long ctime=java.lang.System.currentTimeMillis();
		if ((start_time + 3000) <ctime )
			return new menu_sarf(canvas.getWidth(),canvas.getHeight(), resources);
		return this;
	}

	@Override
	public seagasarface touch(int x, int y) {
		// TODO Auto-generated method stub
		return this;
	}

}
