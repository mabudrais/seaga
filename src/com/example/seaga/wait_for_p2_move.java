package com.example.seaga;

import java.util.Stack;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.Log;

public class wait_for_p2_move extends main_game_sarf {
	protected think think_thread;
	protected boolean ava_move;
	public wait_for_p2_move(int iseasy, game_type type, Stack undo_stack,
			String current_state, Bitmap[] bitmap, int width, int hight,
			Resources resources) {
		super(iseasy, type, undo_stack, current_state, bitmap, width, hight,
				resources);
		if (this.type == game_type.SINGL_PLAYER)
			think_thread = new think(current_state, iseasy);
		think_thread.start();
		Log.d("cstate",current_state);
		state=game_state.WAITING_FOR_P2_MOVE;
		ava_move=can_move('#');
		// TODO Auto-generated constructor stub
	}

	@Override
	public seagasarface draw(Canvas canvas) {
		if(!ava_move)
			return new wait_for_p1_move(iseasy, type, undo_stack,
					current_state, bitmap, width, hight, res);
		if(get_peace_num('#')<4)
			return new win_ani(width, hight, res);
		draw_not_selected_peace(canvas);
		switch (state) {
		case WAITING_FOR_P2_MOVE:
			check_thinking(canvas);
			break;
		case MOVING_ANIMATION:
			if (draw_moving_ani(canvas) > 1000) {
				StringBuffer buf = new StringBuffer(current_state);
				do_eating(buf, current_state.indexOf('m'),false);
				buf.setCharAt(current_state.indexOf('c'), '0');
				buf.setCharAt(current_state.indexOf('m'), '#');
				current_state=buf.toString();
				return new wait_for_p1_move(iseasy, type, undo_stack,
						current_state, bitmap, width, hight, res);
			}
			break;
		}
		return this;
	}

	@Override
	public seagasarface touch(int x, int y) {
		// TODO Auto-generated method stub
		return this;
	}
	public void check_thinking(Canvas a) {
		int i;
		String result = new String();
		try {
			think_thread.join((long) 1000);
		} catch (Exception e) {
		}
		if (think_thread.isAlive() == false) {
			if (think_thread.result.length() > 0) {
				//Log.d("result", think_thread.result);
				int move = Integer.parseInt(think_thread.result.substring(0, 2));
				StringBuffer temp = new StringBuffer(current_state);
				temp.setCharAt(move, 'c');
				switch (think_thread.result.charAt(2)) {
				case 'r':
					temp.setCharAt(move + 1, 'm');
					break;
				case 'l':
					temp.setCharAt(move - 1, 'm');
					break;
				case 'a':
					temp.setCharAt(move - 7, 'm');
					break;
				case 'd':
					temp.setCharAt(move + 7, 'm');
					break;
				}
				current_state = temp.toString();
				state=game_state.MOVING_ANIMATION;
				timer=java.lang.System.currentTimeMillis();
			} else
				state =game_state.WAITING_FOR_P1_MOVE;
		}
	}


}

class think extends Thread {
	public logic b;
	public String result;
	public String board;
	public int e;

	public think(String s, int easy) {
		b = new logic();
		board = s;
		// b.ini(s);
		e = easy;
	}

	public void run() {
		// result=new String(b.do_simble_logic());
		if (e == 1) {
			b.set_max_depth(3);
			result = new String(b.do_logic(board));
		} else {
			b.set_max_depth(6);
			result = new String(b.do_logic(board));
		}
	}
}
