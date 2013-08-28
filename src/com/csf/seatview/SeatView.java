package com.csf.seatview;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class SeatView extends View {
	private Bitmap normalSeatBitmap;
	private int seatSpace;
	private Paint paint;
	private GestureDetector mGestureDetector;
	private ArrayList<Seat> seats;
	private int seatMapWidth;
	private int seatMapHeight;
	private SeatViewSrollListener hSeatViewSrollListener;
	private SeatViewSrollListener vSeatViewSrollListener;

	public SeatView(Context context) {
		this(context, null);
	}

	public SeatView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SeatView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}

	private void init() {
		paint = new Paint();
		mGestureDetector = new GestureDetector(getContext(), new GestureDetectorListener());
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		int bWidth = normalSeatBitmap.getWidth();
		int bHeight = normalSeatBitmap.getHeight();

		if (seats != null) {
			int size = seats.size();
			for (int i = 0; i < size; i++) {
				Seat seat = seats.get(i);
				canvas.drawBitmap(normalSeatBitmap, seat.row * (seatSpace + bWidth), seat.col * (seatSpace + bHeight)
						, paint);
			}
			Seat lastSeat = seats.get(size - 1);
			seatMapWidth = lastSeat.col * (bWidth + seatSpace) - seatSpace;
			seatMapHeight = lastSeat.row * (bHeight + seatSpace) - seatSpace;
		}

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mGestureDetector.onTouchEvent(event);
		return true;
	}

	private void scroll(int distanceX, int distanceY) {
		int x = getScrollX();
		int y = getScrollY();
		System.out.println("y=" + y);
		System.out.println("seatHeight=" + seatMapHeight);
		System.out.println("measureHeight=" + getMeasuredHeight());
		if (y < 0 || y > seatMapHeight - getMeasuredHeight()) {
			distanceY = 0;
		}
		if (x < 0 || x > seatMapWidth - getMeasuredWidth()) {
			distanceX = 0;
		}
		if (distanceX + x < 0) {
			distanceX = -x;
		}
		if (distanceY + y < 0) {
			distanceY = -y;
		}
		if (distanceX + x > seatMapWidth - getMeasuredWidth()) {
			distanceX = seatMapWidth - getMeasuredWidth() - x;
		}
		if (distanceY + y > seatMapHeight - getMeasuredHeight()) {
			distanceY = seatMapHeight - getMeasuredHeight() - y;
		}
		// System.out.println("x="+x+"    y="+y);
		scrollBy(distanceX, distanceY);
		// mHorizontalView.scrollBy(distanceX, 0);
		if(hSeatViewSrollListener !=null){
			hSeatViewSrollListener.scroll(distanceX, distanceY);
		}
		if(vSeatViewSrollListener != null){
			vSeatViewSrollListener.scroll(distanceX, distanceY);
		}
	}

	public void setNormalSeatBitmap(Bitmap bitmap) {
		normalSeatBitmap = bitmap;
	}

	public void setSeatSpace(int seatSpace) {
		this.seatSpace = seatSpace;
	}

	public void setSeats(ArrayList<Seat> seats) {
		this.seats = seats;
	}

	public void setOnSeatViewScrollListener(SeatViewSrollListener hListener,SeatViewSrollListener vListener) {
		hSeatViewSrollListener = hListener;
		vSeatViewSrollListener = vListener;
	}

	private class GestureDetectorListener implements OnGestureListener {

		@Override
		public boolean onDown(MotionEvent e) {
			return false;
		}

		@Override
		public void onShowPress(MotionEvent e) {

		}

		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			return false;
		}

		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
			SeatView.this.scroll((int) distanceX, (int) distanceY);
			return true;
		}

		@Override
		public void onLongPress(MotionEvent e) {

		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
			return false;
		}

	}
}
