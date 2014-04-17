package com.example.seaga;

import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

public class menu_sarf implements seagasarface {
	protected men menupos; 
	protected int width,hight;
	protected int start_time;
	protected Resources resources;
	public menu_sarf(int width, int hight, Resources resources) {
		super();
		this.width = width;
		this.hight = hight;
		this.resources = resources;
		menupos= new men(this.resources,4,9,width,hight);
	}

	@Override
	public seagasarface draw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		draw_main_menu(canvas);
		return this;
	}

	@Override
	public seagasarface touch(int x, int y) {
		if ((y>menupos.y2 / 5)&&(y<((2 * menupos.y2) / 5)))
		return new menu2_sarf(width, hight, resources);
		if((y>((2 * menupos.y2) / 5)))
			return new about(width, hight, resources);	
		return this;
	}
	protected  void draw_main_menu(Canvas a) {
		int az = 0;
		Rect place = new Rect(menupos.x1 + az, menupos.y1, menupos.x2 + az,
				(menupos.y2) / 5);
		//a.drawBitmap(menupos.bitmap[0], null, place, null);
		place.set(menupos.x1 + az, menupos.y2 / 5, menupos.x2 - az,
				(2 * menupos.y2) / 5);
		a.drawBitmap(menupos.bitmap[0], null, place, null);
		place.set(menupos.x1 + az, (2 * menupos.y2) / 5, menupos.x2 + az,
				(3 * menupos.y2) / 5);
		a.drawBitmap(menupos.bitmap[2], null, place, null);
		place.set(menupos.x1 - az, (3 * menupos.y2) / 5, menupos.x2 - az,
				(4 * menupos.y2) / 5);
		a.drawBitmap(menupos.bitmap[3], null, place, null);
	}
}

class men {// menu
	public Bitmap[] bitmap = new Bitmap[10];// take care number of
	int x1, x2, y1, y2, frame_num, mx = 0, my = 0;
	public int h1, h2, h3, h4, h6;
	public long last_time, end_time, start_time;

	public men(Resources res, int start,int end, int w, int h) {
		int i, x = 0;
		for (i = start; i < end; i++) {
			bitmap[x] = BitmapFactory.decodeResource(
					res,
					res.getIdentifier("com.example.seaga:drawable/m"
							+ new Integer(i + 1).toString(), null, null));// res.getIdentifier(com.example.myandroid,null,null)
			x++;
		}
		this.start_time = java.lang.System.currentTimeMillis();
		for (;;) {
			if ((mx > w) || (my > h))
				break;
			my++;
			mx = (6 * my) / 4;
		}
		this.x1 = (w - mx) / 2;
		this.x2 = w - this.x1;
		this.y1 = (h - my) / 2;
		this.y2 = h - this.y1;
	}
}