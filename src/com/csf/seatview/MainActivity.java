package com.csf.seatview;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ArrayList<Seat> datas = new ArrayList<Seat>();
		for(int i=0;i<30;i++){
			for(int j=0;j<30;j++){
				Seat seat = new Seat();
				seat.row = i;
				seat.col = j;
				datas.add(seat);
			}
		}
		SeatLayout layout = (SeatLayout) findViewById(R.id.seat_layout);
		layout.setSeats(datas);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
