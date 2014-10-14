package com.example.programmingforbegg;

import java.util.ArrayList;
import java.util.HashMap;

import android.util.Log;

public class Keyword {
	static final int FUNCTION = 1;
	private String name;
	private int type;
	private int no_params;
	private ArrayList<Integer> arguments;
	static Keyword curr_command = null;
	static HashMap<String, Keyword> allKeywords = new HashMap<String, Keyword>();
	Keyword(String name, int type, int no_params, int argument)
	{
		this.name = name;
		this.type = type;
		this.no_params = no_params;
	}
	static
	{
		allKeywords.put("init", new Keyword("init", FUNCTION, 3, 0));
		allKeywords.put("fd", new Keyword("fd", FUNCTION, 1, 0));
		allKeywords.put("bk", new Keyword("bk", FUNCTION, 1, 0));
		allKeywords.put("rt", new Keyword("rt", FUNCTION,1, 0));
		allKeywords.put("lt", new Keyword("lt", FUNCTION, 1, 0));
		allKeywords.put("repeat", new Keyword("repeat", FUNCTION, 1, 0));
		allKeywords.put("endrepeat", new Keyword("endrepeat", FUNCTION, 1, 0));
		//allKeywords.put("write", new Keyword("write", FUNCTION,1, 0));
	}
	static boolean parseKeyword(String input)
	{
		
		int loc_1 = input.indexOf('(');
		if( loc_1 < 0)
		{
			return false;
		}
		String k_word = input.substring(0, loc_1);
		Keyword k = allKeywords.get(k_word);
		if(k == null)
		{
			return false;
		}
		
		//String param_str = input.substring(loc_1);
		int loc_2 = input.indexOf(')');
		if(loc_2 < 0)
		{
			return false;
		}
		curr_command = new Keyword(k);
		String params = input.substring(loc_1+1, loc_2);
		
		if(params.equals("") || params == null)
		{
			return false;
		}
		else
		{
			
			String args[] = params.split(",");
			if(args.length != curr_command.getNo_params())
			{
				return false;
			}
			else
			{
				for(String a:args)
				{
					try
					{
						curr_command.getArguments().add(Integer.parseInt(a));
					}
					catch(NumberFormatException nfe)
					{
						return false;
					}
					
				}
				 
			}
		}

		
		return true;
	}
	public static Keyword getCurrent()
	{
		return curr_command;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getNo_params() {
		return no_params;
	}
	public void setNo_params(int no_params) {
		this.no_params = no_params;
	}
	public ArrayList<Integer> getArguments() {
		return arguments;
	}
	public void setArguments(ArrayList<Integer> arguments) {
		this.arguments = arguments;
	}
	public Keyword(Keyword k) {
		this.name = k.name;
		this.no_params = k.no_params;
		this.type = k.type;
		this.arguments = new ArrayList<Integer>();
	}
	
	
	
}
