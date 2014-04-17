package com.example.seaga;

public class board_dimention {
	protected int w, h, paice_w, paice_h, w_margin, h_margin;

	public board_dimention(int w, int h) {
		super();
		this.w = w;
		this.h = h;
		if (w > h) {
			paice_h = h / 7;
			while (paice_h * 8 > w)
				paice_h--;
			paice_w = paice_h;
			h_margin = (h - 7 * paice_h) / 2;
		} else {
			paice_w = w / 7;
			while (paice_w * 8 > w)
				paice_w--;
			paice_h = paice_w;
			w_margin = (w - 7* paice_w) / 2;
		}
		w_margin = (w - 8 * paice_h) / 2;
		h_margin = (h - 7 * paice_h) / 2;
	}
}
