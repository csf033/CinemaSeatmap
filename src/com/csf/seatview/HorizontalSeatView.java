package com.csf.seatview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class HorizontalSeatView extends View implements SeatViewSrollListener{
	private int seatWidth;
	private int seatSpace;
	private Paint paint;
	private Rect bounds;
	private int cols;
	public HorizontalSeatView(Context context) {
		this(context,null);
	}
	public HorizontalSeatView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}
	public HorizontalSeatView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	
	private void init() {
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setAntiAlias(true);
		bounds = new Rect();
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
//		canvas.drawColor(Color.WHITE);
		paint.setTextSize(seatWidth*3/5);
		for(int i=1;i<=cols;i++){
			String centerText = i+"";
			paint.getTextBounds(centerText, 0,centerText.length(), bounds);
			int x = getWidth()/2 - bounds.centerX();
			int y = getHeight()/2 - bounds.centerY();
			canvas.drawText(i+"",seatWidth+seatWidth/2-bounds.centerX()+(i-1)*(seatWidth+seatSpace),y, paint);
		}
	}
	
	@Override
	public void scroll(int distanceX, int distanceY) {
		scrollBy(distanceX, 0);
	}
	
	public void setSeatWidth(int seatWidth){
		this.seatWidth = seatWidth;
	}
	public void setCols(int cols){
		this.cols = cols;
	}
	public void setSeatSpace(int seatSpace) {
		this.seatSpace = seatSpace;
	}
}
