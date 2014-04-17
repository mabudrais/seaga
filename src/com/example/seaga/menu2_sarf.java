package com.example.seaga;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

public class menu2_sarf implements seagasarface {
	protected men menupos; 
	protected int width,hight;
	protected int start_time;
	protected Resources resources;
	
	public menu2_sarf(int width, int hight, Resources resources) {
		super();
		this.width = width;
		this.hight = hight;
		this.resources = resources;
		menupos= new men(this.resources,11,13,width,hight);
	}

	@Override
	public seagasarface draw(Canvas canvas) {
		draw_menu(canvas);
		return this;
	}
	protected  void draw_menu(Canvas a) {
		int az = 0;
		a.drawColor(Color.BLACK);
		Rect place = new Rect(menupos.x1 + az, menupos.y1, menupos.x2 + az,
				(menupos.y2) / 5);
		//a.drawBitmap(menupos.bitmap[0], null, place, null);
		place.set(menupos.x1 + az, menupos.y2 / 5, menupos.x2 - az,
				(2 * menupos.y2) / 5);
		a.drawBitmap(menupos.bitmap[0], null, place, null);
		place.set(menupos.x1 + az, (2 * menupos.y2) / 5, menupos.x2 + az,
				(3 * menupos.y2) / 5);
		a.drawBitmap(menupos.bitmap[1], null, place, null);
	}
	@Override
	public seagasarface touch(int x, int y) {
		// TODO Auto-generated method stub
		return new wait_for_p1_put(1, null, null, null, null, width,hight, resources);
	}

}
