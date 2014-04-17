package com.example.seaga;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;

import com.example.seaga.seagasarface;
import java.util.*;

public class main_game_sarf implements seagasarface {
	public enum game_state {
		WAITING_FOR_APP, EATING_ANIMATION, SELECTION_ANIMATION, WAITING_FOR_P1_MOVE, MOVING_ANIMATION, WAITING_FOR_P2_MOVE;
	}

	public enum game_type {
		SINGL_PLAYER, BLUETOOTH, INTERNET;
	}

	protected long timer;
	protected int iseasy = 1;
	protected game_state state;
	protected game_type type;
	protected Stack undo_stack;
	public String current_state = new String(
			"0000000000000000000000000000000000000000000000000");
	protected board_dimention dimen;
	protected Bitmap[] bitmap = new Bitmap[10];
	protected int width, hight;
	protected Resources res;

	@Override
	public seagasarface draw(Canvas canvas) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public seagasarface touch(int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

	public main_game_sarf(int iseasy, game_type type, Stack undo_stack,
			String current_state, Bitmap[] bitmap, int width, int hight,
			Resources resources) {
		super();
		this.iseasy = iseasy;
		this.type = game_type.SINGL_PLAYER;
		if (undo_stack != null)
			this.undo_stack = undo_stack;
		else
			undo_stack = new Stack();
		if (current_state != null)
			this.current_state = current_state;
		else
			this.current_state = new String(
					"0000000000000000000000000000000000000000000000000");
		this.dimen = new board_dimention(width, hight);
		this.res = resources;
		if (bitmap != null)
			this.bitmap = bitmap;
		else
			load_bitmaps();
		this.width = width;
		this.hight = hight;
	}

	protected void load_bitmaps() {
		int i, x = 0;
		for (i = 0; i < 6; i++) {
			try {
				bitmap[x] = BitmapFactory.decodeResource(
						res,
						res.getIdentifier("com.example.seaga:drawable/b"
								+ new Integer(i).toString(), null, null));// res.getIdentifier(com.example.myandroid,null,null)
			} catch (Exception e) {
				Log.d("exep", e.toString());
			}
			x++;
		}
	}

	public void draw_not_selected_peace(Canvas a) {
		int i;
		a.drawColor(Color.BLACK);
		Rect place = new Rect(0, 0, a.getWidth() - 200, a.getHeight() - 200);
		for (i = 0; i < 49; i++) {
			place.set((int) dimen.paice_w * (i % 7), (int) dimen.paice_w
					* (i / 7), (int) dimen.paice_w * ((i) % 7) + dimen.paice_w,
					(int) dimen.paice_h * ((i) / 7) + dimen.paice_h);
			if (i % 2 == 0)
				a.drawBitmap(bitmap[4], null, place, null);
			if (i % 2 == 0)
				a.drawBitmap(bitmap[5], null, place, null);
			if (current_state.charAt(i) == '*')
				a.drawBitmap(bitmap[0], null, place, null);
			if (current_state.charAt(i) == '#')
				a.drawBitmap(bitmap[1], null, place, null);
		}
	}

	public void draw_button(Canvas a) {
		if (hight > width) {
			Rect place = new Rect(0, a.getHeight() - dimen.paice_w,
					dimen.paice_w, a.getHeight());// a.getWidth()
			a.drawBitmap(bitmap[2], null, place, null);
			place.set(a.getWidth() - dimen.paice_w, a.getHeight()
					- dimen.paice_w, a.getWidth(), a.getHeight());
			a.drawBitmap(bitmap[3], null, place, null);
		} else {
			Rect place = new Rect(a.getWidth() - dimen.paice_w, 0,
					a.getWidth(), dimen.paice_w);
			a.drawBitmap(bitmap[2], null, place, null);
			place.set(a.getWidth() - dimen.paice_w, a.getHeight()
					- dimen.paice_w, a.getWidth(), a.getHeight());
			// the undo button should apear even in two player mode
			a.drawBitmap(bitmap[3], null, place, null);

		}
	}

	protected int get_selected(int x, int y) {
		int i;
		for (i = 0; i < 49; i++) {
			if ((x > dimen.paice_w * (i % 7))
					&& (x < dimen.paice_w * (i % 7) + dimen.paice_w)
					&& (y > dimen.paice_h * (i / 7))
					&& (y < dimen.paice_h * (i / 7) + dimen.paice_h))
				break;
		}
		if (i < 49)
			return i;
		return -50;
	}

	protected int get_peace_num(char tar) {
		int i, k = 0;
		for (i = 0; i < 49; i++) {
			if (current_state.charAt(i) == tar)
				k++;
		}
		return k;
	}

	protected long draw_moving_ani(Canvas canvas) {
		long ctime = java.lang.System.currentTimeMillis();
		int x1, y1, x2, y2, bitmap_index = 0;
		boolean isplayer1;
		int sourc = current_state.indexOf('a');
		if (sourc == -1) {
			sourc = current_state.indexOf('c');
			bitmap_index = 1;
		}
		int dist = current_state.indexOf('m');
		x1 = (int) dimen.paice_w * (sourc % 7);
		x2 = (int) dimen.paice_w * (dist % 7);
		y1 = (int) dimen.paice_w * (sourc / 7);
		y2 = (int) dimen.paice_w * (dist / 7);
		float delta = (float) (((float) (ctime - timer)) / 1000.0);
		Log.d("delta11", new Float(delta).toString());
		int x = (int) (x1 + (x2 - x1) * delta);
		int y = (int) (y1 + (y2 - y1) * delta);
		Rect place = new Rect(x, y, x + dimen.paice_w, y + dimen.paice_h);
		canvas.drawBitmap(bitmap[bitmap_index], null, place, null);
		return ctime - timer;
	}

	protected void draw_selection_ani(Canvas canvas) {
		int index = current_state.indexOf('a');
		long ctime = java.lang.System.currentTimeMillis();
		if (((ctime - timer) / 500) % 2 == 0) {
			Rect place = new Rect((int) dimen.paice_w * (index % 7),
					(int) dimen.paice_w * (index / 7), (int) dimen.paice_w
							* ((index) % 7) + dimen.paice_w,
					(int) dimen.paice_h * ((index) / 7) + dimen.paice_h);
			canvas.drawBitmap(bitmap[0], null, place, null);
		}
	}

	protected void do_eating(StringBuffer temp, int eating_target,
			Boolean isplayerone) {
		// print_state(st);
		if (isplayerone) {
			// eating down
			if ((eating_target > 13) && (temp.charAt(eating_target - 7) == '#')
					&& (temp.charAt(eating_target - 14) == '*')) {
				temp.setCharAt(eating_target - 7, '0');
			}
			// eating up
			if ((eating_target < 35) && (temp.charAt(eating_target + 7) == '#')
					&& (temp.charAt(eating_target + 14) == '*')) {// not sure
																	// about 37
				temp.setCharAt(eating_target + 7, '0');
			}
			// eating write
			if ((eating_target % 7 < 5)
					&& (temp.charAt(eating_target + 1) == '#')
					&& (temp.charAt(eating_target + 2) == '*')) {
				temp.setCharAt(eating_target + 1, '0');
			}
			// eating left
			if ((eating_target % 7 > 1)
					&& (temp.charAt(eating_target - 1) == '#')
					&& (temp.charAt(eating_target - 2) == '*')) {
				temp.setCharAt(eating_target - 1, '0');
			}
		}
		if (!isplayerone) {
			// eating down
			if ((eating_target > 13) && (temp.charAt(eating_target - 7) == '*')
					&& (temp.charAt(eating_target - 14) == '#')) {
				temp.setCharAt(eating_target - 7, '0');
			}
			// eating up
			if ((eating_target < 35) && (temp.charAt(eating_target + 7) == '*')
					&& (temp.charAt(eating_target + 14) == '#')) {// not sure
																	// about 37
				temp.setCharAt(eating_target + 7, '0');
			}
			// eating write
			if ((eating_target % 7 < 5)
					&& (temp.charAt(eating_target + 1) == '*')
					&& (temp.charAt(eating_target + 2) == '#')) {
				temp.setCharAt(eating_target + 1, '0');
			}
			// eating left
			if ((eating_target % 7 > 1)
					&& (temp.charAt(eating_target - 1) == '*')
					&& (temp.charAt(eating_target - 2) == '#')) {
				temp.setCharAt(eating_target - 1, '0');
			}
		}
		current_state = temp.toString();
	}

	protected boolean can_move(char peace) {
		int x, y;
		for (y = 0; y < 7; y++) {
			for (x = 0; x < 7; x++) {
				if (x > 0 && current_state.charAt(y * 7 + x - 1) == '0'
						&& current_state.charAt(y * 7 + x) == peace)
					return true;
				if (x <6 && current_state.charAt(y * 7 + x + 1) == '0'
						&& current_state.charAt(y * 7 + x) == peace)
					return true;
				if (y <6 && current_state.charAt((y+1) * 7 + x) == '0'
						&& current_state.charAt(y * 7 + x) == peace)
					return true;
				if (y >0 && current_state.charAt((y-1) * 7 + x) == '0'
						&& current_state.charAt(y * 7 + x) == peace)
					return true;
			}
		}
		return false;
	}
}
