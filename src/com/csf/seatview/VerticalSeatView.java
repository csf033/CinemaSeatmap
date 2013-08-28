package com.csf.seatview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class VerticalSeatView extends View implements SeatViewSrollListener {
	private int seatWidth;
	private int seatSpace;
	private Paint paint;
	private Rect bounds;
	private int rows;

	public VerticalSeatView(Context context) {
		this(context,null);
	}

	public VerticalSeatView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
	}

	public VerticalSeatView(Context context, AttributeSet attrs, int defStyle) {
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
//		Paint paint1 = new Paint();
//		paint1.setColor(Color.BLUE);
//		paint1.setAntiAlias(true);
//		paint1.setStyle(Paint.Style.FILL);
//		
//		canvas.drawRect(0,seatWidth, seatWidth,1000, paint1 );
		paint.setTextSize(seatWidth*3/5);
		for(int i=1;i<=rows;i++){
			String centerText = i+"";
			paint.getTextBounds(centerText, 0,centerText.length(), bounds);
			int x = getWidth()/2 - bounds.centerX();
			int y = getHeight()/2 - bounds.centerY();
			canvas.drawText(i+"",x,seatWidth+seatWidth/2-bounds.centerY()+(i-1)*(seatWidth+seatSpace),paint);
		}
		
	}
	
	@Override
	public void scroll(int distanceX, int distanceY) {
		scrollBy(0, distanceY);
	}
	public void setSeatWidth(int seatWidth) {
		this.seatWidth = seatWidth;
	}
	
	public void setSeatSpace(int seatSpace) {
		this.seatSpace = seatSpace;
	}
	public void setRows(int rows){
		this.rows = rows;
	}
}
