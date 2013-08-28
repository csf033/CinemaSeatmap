package com.csf.seatview;

import java.util.ArrayList;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * The container of the SeatView,contains SeatView HorizontalSeatView and VerticalSeatView
 * @author csf
 * @since 2013-08-27
 * 
 */
public class SeatLayout extends FrameLayout {
	private SeatView mSeatView;
	private HorizontalSeatView mHorizontalSeatView;
	private VerticalSeatView mVerticalSeatView;
	private Bitmap normalSeatBitmap;
	private int seatSpace = 20;
	private int seatHeight;
	private int seatWidth;
	private ArrayList<Seat> seats;
	private int horizontalHeight;
	private int verticalWidth;
	private int margin;

	public SeatLayout(Context context) {
		super(context, null);
	}

	public SeatLayout(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public SeatLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray typedArray = getContext().obtainStyledAttributes(attrs,R.styleable.SeatLayout);
		int normalSeat = typedArray.getResourceId(R.styleable.SeatLayout_normalSeatDrawable, -1);
		normalSeatBitmap = BitmapFactory.decodeResource(getResources(), normalSeat);
		seatHeight = normalSeatBitmap.getHeight()+seatSpace;
		seatWidth = normalSeatBitmap.getWidth()+seatSpace;
		horizontalHeight = verticalWidth = normalSeatBitmap.getHeight();
		margin =  normalSeatBitmap.getWidth();
		typedArray.recycle();
		init();
	}

	private void init() {
		mSeatView = new SeatView(getContext());
		LayoutParams params = new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		params.leftMargin = params.topMargin = margin;
		mSeatView.setLayoutParams(params);
		
		mHorizontalSeatView = new HorizontalSeatView(getContext());
		mHorizontalSeatView.setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.FILL_PARENT,margin));
		mHorizontalSeatView.setSeatSpace(seatSpace);
		mHorizontalSeatView.setSeatWidth(seatWidth - seatSpace);
		
		mVerticalSeatView = new VerticalSeatView(getContext());
		mVerticalSeatView.setLayoutParams(new FrameLayout.LayoutParams(margin,LayoutParams.FILL_PARENT));
		mVerticalSeatView.setSeatSpace(seatSpace);
		mVerticalSeatView.setSeatWidth(margin);
		
		mSeatView.setNormalSeatBitmap(normalSeatBitmap);
		mSeatView.setSeatSpace(seatSpace);
		mSeatView.setOnSeatViewScrollListener(mHorizontalSeatView, mVerticalSeatView);
		addView(mSeatView);
		addView(mHorizontalSeatView);
		addView(mVerticalSeatView);
	}
	public void setSeats(ArrayList<Seat> seats){
		this.seats = seats;
		mSeatView.setSeats(seats);
		Seat lastSeat = seats.get(seats.size()-1);
		int maxRow = lastSeat.row;
		int maxCol = lastSeat.col;
		mHorizontalSeatView.setCols(maxCol);
		mVerticalSeatView.setRows(maxRow);
		mHorizontalSeatView.invalidate();
		
	}

}
