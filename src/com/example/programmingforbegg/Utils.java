package com.example.programmingforbegg;

import java.util.ArrayList;

import android.util.Log;

public class Utils {
	private static double curr_angle;
	private static int currX;
	private static int currY;
	private static int initX;
	private static int initY;
	private static ArrayList<Integer> endCoordinatesX;
	private static ArrayList<Integer> endCoordinatesY;
	
	static {
		curr_angle = 0;
		currX = currY = 0;
		endCoordinatesX = new ArrayList<Integer>();
		endCoordinatesY = new ArrayList<Integer>();
	}
	
	
	
	public static int getInitX() {
		return initX;
	}
	public static void setInitX(int initX) {
		Utils.initX = initX;
	}
	public static int getInitY() {
		return initY;
	}
	public static void setInitY(int initY) {
		Utils.initY = initY;
	}
	public static void resetCoords()
	{
		endCoordinatesX = new ArrayList<>();
		endCoordinatesY = new ArrayList<>();
	}
	public static ArrayList<Integer> getEndCoordinatesX() {
		return endCoordinatesX;
	}
	public static void setEndCoordinatesX(ArrayList<Integer> endCoordinatesX) {
		Utils.endCoordinatesX = endCoordinatesX;
	}
	public static ArrayList<Integer> getEndCoordinatesY() {
		return endCoordinatesY;
	}
	public static void setEndCoordinatesY(ArrayList<Integer> endCoordinatesY) {
		Utils.endCoordinatesY = endCoordinatesY;
	}
	public static boolean init(int x, int y, int angle)
	{
		curr_angle = Math.toRadians(angle);
		currX = initX = x;
		currY = initY = y;
		return true;
	}
	public static boolean fd(int amt)
	{
		int x_off =  (int) ((float)amt * Math.cos(curr_angle));
		int y_off =  (int) ((float)amt * Math.sin(curr_angle));
		Log.e("x", Integer.toString(x_off));
		Log.e("y", Integer.toString(y_off));
		endCoordinatesX.add(currX + x_off);
		endCoordinatesY.add(currY + y_off);
		currX += x_off;
		currY += y_off;
		return true;
	}
	public static int repeat(ArrayList<Keyword> keywords, int i, int times)
	{
		ArrayList<Keyword> toRepeat = new ArrayList<>();
		i++;
		while(true)
		{
			Keyword k = keywords.get(i);
			i++;
			if(k.getName().equals("endrepeat"))
			{
				break;
			}
			toRepeat.add(k);
		}
		for(int j = 0; j < times; j++)
		{
			for(Keyword k : toRepeat)
			{
				int amt, x, y, angle;
				switch(k.getName())
				{
					
					case "fd":	amt = k.getArguments().get(0);
								Utils.fd(amt);
								break;
					case "bk":	amt = k.getArguments().get(0);
								Utils.bk(amt);
								break;
					case "rt":	amt = k.getArguments().get(0);
								Utils.rt(amt);
								break;
					case "lt":	amt = k.getArguments().get(0);
								Utils.lt(amt);
								break;
					
				}
			}
		}
		return i;
	}
	public static boolean bk(int amt)
	{
		int x_off =  amt * (int)Math.cos(curr_angle);
		int y_off =  amt * (int)Math.sin(curr_angle);
		endCoordinatesX.add(currX - x_off);
		endCoordinatesY.add(currY - y_off);
		currX -= x_off;
		currY -= y_off;
		return true;
	}
	public static void rt(int angle)
	{
		curr_angle += Math.toRadians(angle);
		Log.e("rt", Double.toString(curr_angle));
	}
	public static void lt(int angle)
	{
		curr_angle -= Math.toRadians(angle);
	}
	

}
