package com.example.seaga;

import java.util.Stack;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class wait_for_p1_put extends main_game_sarf {
	protected int old_peace_num;
	public wait_for_p1_put(int iseasy, game_type type, Stack undo_stack,
			String current_state,Bitmap[] bitmap, int width, int hight,
			Resources resources) {
		super(iseasy, type, undo_stack, current_state, bitmap, width, hight,
				resources);
		old_peace_num=(get_peace_num('*'));
	}

	@Override
	public seagasarface draw(Canvas canvas) {
		draw_not_selected_peace(canvas);
		draw_button(canvas);
		if(old_peace_num==24)
			return new wait_for_p1_move(iseasy, type, undo_stack, current_state, bitmap, width,hight, res);
		return this;
	}

	@Override
	public seagasarface touch(int x, int y) {
		int sel_sqr = get_selected(x, y);
		if (sel_sqr > -1) {
			StringBuffer temp = new StringBuffer(current_state);
			if (temp.charAt(sel_sqr) == '0') {
				temp.setCharAt(sel_sqr, '*');
				current_state = temp.toString();
				int num=get_peace_num('*');
				if (num % 2 == 0 && num>old_peace_num)
					return new wait_for_p2_put(iseasy, type, undo_stack,
							current_state, bitmap, width, hight, res);
			}
		}
		return this;
	}

}
