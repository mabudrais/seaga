package com.example.seaga;

import java.util.Stack;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class wait_for_p1_move extends main_game_sarf {

	protected boolean ava_move;
	public wait_for_p1_move(int iseasy, game_type type, Stack undo_stack,
			String current_state, Bitmap[] bitmap, int width, int hight,
			Resources resources) {
		super(iseasy, type, undo_stack, current_state, bitmap, width, hight,
				resources);
		state = game_state.WAITING_FOR_P1_MOVE;
		ava_move=can_move('*');
	}

	@Override
	public seagasarface draw(Canvas canvas) {
		if(!ava_move)
			return new wait_for_p2_move(iseasy, type, undo_stack,
					current_state, bitmap, width, hight, res);
		if(get_peace_num('*')<4)
			return new loss_ani(width, hight, res);
		draw_not_selected_peace(canvas);
		draw_button(canvas);
		if (state == game_state.SELECTION_ANIMATION)
			draw_selection_ani(canvas);
		if (state == game_state.MOVING_ANIMATION) {
			if (draw_moving_ani(canvas) > 1000) {
				StringBuffer buf = new StringBuffer(current_state);
				do_eating(buf, current_state.indexOf('m'),true);
				buf.setCharAt(current_state.indexOf('a'), '0');
				buf.setCharAt(current_state.indexOf('m'), '*');
				current_state=buf.toString();
				return new wait_for_p2_move(iseasy, type, undo_stack,
						current_state, bitmap, width, hight, res);
			}
		}
		return this;
	}

	@Override
	public seagasarface touch(int x, int y) {
		int sel_sqr = get_selected(x, y);
		if (sel_sqr > -1) {
			if (state == game_state.SELECTION_ANIMATION) {
				if (current_state.indexOf('a') == sel_sqr) {
					state = game_state.WAITING_FOR_P1_MOVE;
					StringBuffer buf = new StringBuffer(current_state);
					buf.setCharAt(current_state.indexOf('a'), '*');
					current_state = buf.toString();
				}
				if (current_state.charAt(sel_sqr) == '0') {
					set_char_as_move_to(current_state.indexOf('a'), sel_sqr);
					state = game_state.MOVING_ANIMATION;
					timer = java.lang.System.currentTimeMillis();
				}
				return this;
			}
			if (state == game_state.WAITING_FOR_P1_MOVE) {
				if (current_state.charAt(sel_sqr) == '*') {
					set_char_as_selected(sel_sqr);
					state = game_state.SELECTION_ANIMATION;
					timer = java.lang.System.currentTimeMillis();
				}
			}
		}
		return this;
	}

	protected int set_char_as_selected(int i) {
		char k = current_state.charAt(i);
		StringBuffer temp = new StringBuffer(current_state);
		if (k == '*') {
			temp.setCharAt(i, 'a');
			current_state = temp.toString();
			return 1;
		}
		return -1;
	}

	public int set_char_as_move_to(int s, int d) {// source ,destination
		char k = current_state.charAt(d);
		StringBuffer temp = new StringBuffer(current_state);
		if ((k == '0')
				&& ((d == s - 1) || (d == s + 1) || (d == s - 7) || (d == s + 7))) {// you
																					// have
																					// to
																					// check
																					// if
																					// its
																					// a
																					// nighber
																					// place
																					// !!
			temp.setCharAt(d, 'm');
			current_state = temp.toString();
			return 1;
		}
		return -1;
	}

}
