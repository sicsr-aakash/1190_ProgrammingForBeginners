package com.example.programmingforbegg;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class DrawActivity extends ActionBarActivity {
	private ArrayList<Integer> endXCoordinates, endYCoordinates;
	private MyCustomPanel view;
	private Canvas canvas;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_draw);
		
		endXCoordinates = new ArrayList<Integer>();
		endYCoordinates = new ArrayList<Integer>();
		setValues();
		view = new MyCustomPanel(this);
	    LinearLayout layout = (LinearLayout) findViewById(R.id.container);
	    layout.addView(view);
		
	    view.setCoordinates(this.endXCoordinates, this.endYCoordinates, Utils.getInitX(), Utils.getInitY());
	    canvas = new Canvas();
	    view.draw(canvas);
	}
	
	void setValues() {
		this.endXCoordinates = Utils.getEndCoordinatesX();
		this.endYCoordinates = Utils.getEndCoordinatesY();
		Utils.resetCoords();
	}
	
	 private class MyCustomPanel extends View {
		 private ArrayList<Integer> endXCoordinates, endYCoordinates;
		 private Paint paint;
		 private Path path;
		 private PathMeasure measure;
		 private Path segmentPath;
		 private Path drawingPath;
		 private float start;
		 private int i=0;
		 private int initX;
		 private int initY;
		  
			
		 
	     public MyCustomPanel(Context context) {
            super(context);
            paint = new Paint();
            path = new Path();
            drawingPath = new Path();
            measure = new PathMeasure();
            segmentPath = new Path();
            start = 0;
            i = 0;
            this.initX = Utils.getInitX();
            this.initY = Utils.getInitY();
            
            this.endXCoordinates = Utils.getEndCoordinatesX();
            this.endYCoordinates = Utils.getEndCoordinatesY();
            
		 }
	     
	    


		public void setCoordinates(ArrayList<Integer> endXCoordinates, ArrayList<Integer> endYCoordinates, int initX, int initY) {
			 this.endXCoordinates = endXCoordinates;
			 this.endYCoordinates = endYCoordinates;
			 //paint.setPathEffect(new DashPathEffect(new float[] { 2, 4 }, 50));

		     //path.moveTo(0,0);
		     final long DRAW_TIME = 10000;
		     CountDownTimer timer = new CountDownTimer(DRAW_TIME, 100) {
		    	@Override
	            public void onTick(long millisUntilFinished) {
	                measure.setPath(path, false);
	                float percent = ((float) (DRAW_TIME - millisUntilFinished))
	                        / (float) DRAW_TIME;
	                float length = measure.getLength() * percent;
	                measure.getSegment(start, length, segmentPath, true);
	                start = length;
	                drawingPath.addPath(segmentPath);
	                invalidate();
	            }

	            @Override
	            public void onFinish() {
	            	if(i<MyCustomPanel.this.endXCoordinates.size())
	            		path.moveTo(MyCustomPanel.this.endXCoordinates.get(i), 
	            				MyCustomPanel.this.endYCoordinates.get(i));
	            }
	        };
	        timer.start();
		 }

		 	
	     @Override
	     public void onDraw(Canvas canvas) {
	    	 	super.onDraw(canvas);
	    	 	plotLines(canvas);
	    	 
	    }
	        
	    void plotLines(Canvas canvas) {
	        paint.setColor(android.graphics.Color.WHITE);
	        canvas.drawPaint(paint);
	        paint.setStrokeWidth(4);
	        
	        path.moveTo(initX, initY);
    	    paint.setColor(android.graphics.Color.rgb(255, 69, 0));
    	    paint.setStyle(Paint.Style.FILL_AND_STROKE);
    	    paint.setAntiAlias(true);
	      
	        for(i=0; i<endXCoordinates.size(); i++) {
	        	
	        	path.lineTo(endXCoordinates.get(i), endYCoordinates.get(i));
	        	canvas.drawPath(drawingPath, paint);
	        }
	    }
	 }
}
