package com.example.programmingforbegg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class DrawView extends View {
	Paint paint;
	int to_x, to_y;
	
	public DrawView(Context context, int to_x, int to_y) {
		super(context);
		// TODO Auto-generated constructor stub
		paint = new Paint();
		paint.setColor(Color.BLACK);
		this.to_x = to_x;
		this.to_y = to_y;
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{
		int height = canvas.getHeight();
		int width = canvas.getWidth();
		int cen_y = height/2;
		int cen_x = width/2;
		canvas.drawLine(cen_x, cen_y, to_x, to_y, paint);
	}
	
	

}
