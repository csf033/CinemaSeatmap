package com.csf.seatview;

public class Seat {
	public static final int STATE_NORMAL = 0;
	public static final int STATE_USED = 2;
	public static final int STATE_CHOOSED = 1;
	public int row;
	public int col;
	public int state = STATE_NORMAL;
}
