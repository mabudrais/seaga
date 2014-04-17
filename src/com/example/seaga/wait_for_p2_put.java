package com.example.seaga;

import java.util.Stack;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class wait_for_p2_put extends main_game_sarf {

	protected int old_peace_num;

	public wait_for_p2_put(int iseasy, game_type type, Stack undo_stack,
			String current_state, Bitmap[] bitmap, int width, int hight,
			Resources resources) {
		super(iseasy, type, undo_stack, current_state, bitmap, width, hight,
				resources);
		old_peace_num = (get_peace_num('#'));
	}

	public seagasarface draw(Canvas canvas) {
		draw_not_selected_peace(canvas);
		draw_button(canvas);
		do_com_put(canvas);
		int num = get_peace_num('#');
		if (num % 2 == 0 && num > old_peace_num)
			return new wait_for_p1_put(iseasy, type, undo_stack, current_state,
					bitmap, width, hight, res);
		if(num==24&&num == old_peace_num)
			return new wait_for_p1_move(iseasy, type, undo_stack, current_state, bitmap,width,hight, res);
		return this;
	}

	@Override
	public seagasarface touch(int x, int y) {
		return this;
	}

	protected void do_com_put(Canvas a) {
		StringBuffer temp = new StringBuffer(current_state);
		int x = get_puts();
		temp.setCharAt(x, '#');
		current_state = temp.toString();
	}

	protected int get_put_result(int i) {
		int result = 0;
		if (i > 20)
			result = result + get_write_up_result(i - 7);
		if (i < 28)
			result = result + get_write_up_result(i + 7);
		if (i % 7 < 4)
			result = result + get_write_up_result(i + 1);
		if (i % 7 > 2)
			result = result + get_write_up_result(i - 1);
		return result;
	}

	protected int get_puts() {
		int i = 0, result = -1, best = -1, x;
		for (i = 0; i < 49; i++) {
			if (current_state.charAt(i) == '0') {
				x = get_put_result(i);
				if (x > result) {
					result = x;
					best = i;
				}
			}
		}

		return best;
	}

	protected int get_write_up_result(int i) {
		int result = 0;
		if (i > 13) {
			if ((current_state.charAt(i - 7) == '0')
					&& (current_state.charAt(i - 14) == '0'))
				result = result + 1;
			if ((current_state.charAt(i - 7) == '0')
					&& (current_state.charAt(i - 14) == '#')
					|| (current_state.charAt(i - 7) == '0')
					&& (current_state.charAt(i - 14) == '#'))
				result = result + 80;
			if ((current_state.charAt(i - 7) == '*')
					&& (current_state.charAt(i - 14) == '#'))
				result = result + 4000;
		}
		if (i < 35) {
			if ((current_state.charAt(i + 7) == '0')
					&& (current_state.charAt(i + 14) == '0'))
				result = result + 1;
			if ((current_state.charAt(i + 7) == '0')
					&& (current_state.charAt(i + 14) == '#')
					|| (current_state.charAt(i + 7) == '0')
					&& (current_state.charAt(i + 14) == '#'))
				result = result + 80;
			if ((current_state.charAt(i + 7) == '*')
					&& (current_state.charAt(i + 14) == '#'))
				result = result + 4000;
		}
		if (i % 7 < 5) {
			if ((current_state.charAt(i + 1) == '0')
					&& (current_state.charAt(i + 2) == '0'))
				result = result + 1;
			if ((current_state.charAt(i + 1) == '0')
					&& (current_state.charAt(i + 2) == '#')
					|| (current_state.charAt(i + 1) == '0')
					&& (current_state.charAt(i + 2) == '#'))
				result = result + 80;
			if ((current_state.charAt(i + 1) == '*')
					&& (current_state.charAt(i + 2) == '#'))
				result = result + 4000;
		}
		if (i % 7 > 1) {
			if ((current_state.charAt(i - 1) == '0')
					&& (current_state.charAt(i - 2) == '0'))
				result = result + 1;
			if ((current_state.charAt(i - 1) == '0')
					&& (current_state.charAt(i - 2) == '#')
					|| (current_state.charAt(i - 1) == '0')
					&& (current_state.charAt(i - 2) == '#'))
				result = result + 80;
			if ((current_state.charAt(i - 1) == '*')
					&& (current_state.charAt(i - 2) == '#'))
				result = result + 4000;
		}
		return result;
	}
}
