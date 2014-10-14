package com.example.programmingforbegg;

import java.util.ArrayList;
import java.util.Arrays;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WorkspaceActivity extends ActionBarActivity {

	EditText edttxt;
	LinearLayout thisLayout;
	Button run;
	ArrayList<String> linesOfCode;
	ArrayList<Keyword> keywords;
	
	@Override
	public void onBackPressed() {
		
		super.onBackPressed();
		finish();
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textinput);
		thisLayout = (LinearLayout) findViewById(R.layout.textinput);
		edttxt = (EditText)findViewById(R.id.code_area);
		run = (Button) findViewById(R.id.run);
		//keywords = new ArrayList<Keyword>();
		run.setOnClickListener(new OnClickListener() {
			
			
			
			@Override
			public void onClick(View arg0) {
				interpretCode();
				Utils.resetCoords();
				Log.e("line 1", linesOfCode.get(0));
				boolean flag = true;
				keywords = new ArrayList<Keyword>();
				for(int i = 0; i < linesOfCode.size(); i++)
				{
					String codeline = linesOfCode.get(i);
					flag = Keyword.parseKeyword(codeline);
					if(!flag)
					{
						Toast.makeText(getApplicationContext(), "Error in parsing...", Toast.LENGTH_SHORT).show();
						TextView error_display = (TextView) findViewById(R.id.error_display);
						error_display.setText("Syntax error on line no: " + i);
						error_display.setTextColor(getResources().getColor(R.color.error_color));
						break;
					}
//					Log.e("flag", Boolean.toString(flag));
					Keyword k = Keyword.getCurrent();
					keywords.add(k);
					String text = k.getName() + " " + k.getArguments();
					//Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
				}
				if(flag)
				{
					runCommands();
					for(int i = 0; i < Utils.getEndCoordinatesX().size(); i++)
					{
						Log.e("x_coord", Integer.toString(Utils.getEndCoordinatesX().get(i)));
			        	Log.e("y_coord", Integer.toString(Utils.getEndCoordinatesY().get(i)));
					}
					Intent intent = new Intent(getApplicationContext(), DrawActivity.class);
					startActivity(intent);
				}
				
				
				
				
			}
		});
		edttxt.addTextChangedListener(new TextWatcher() {
			 
			   public void afterTextChanged(Editable s) {
			   }
			 
			   public void beforeTextChanged(CharSequence s, int start,
			     int count, int after) {
			   }
			 
			   public void onTextChanged(CharSequence s, int start,
			     int before, int count) {
				   boolean flag = true;

					   
						   
						   
				   
			   TextView myOutputBox = (TextView) findViewById(R.id.error_display);
			   myOutputBox.setText(s);
			   if(flag == false){
				   myOutputBox.setTextColor(getResources().getColor(R.color.error_color));
			   		}
			   else
			   {
				   myOutputBox.setTextColor(getResources().getColor(R.color.no_error_color));
			   }
			   }
			  });
		
	}
	
	void interpretCode() {
		linesOfCode = new ArrayList<String>();
		String programText = edttxt.getText().toString();
		ArrayList<String> lines = new ArrayList<String>(Arrays.asList(programText.split("\\r?\\n")));
		
		for(int i=0; i<lines.size(); i++) {
			if(!lines.get(i).trim().equals("")) {
				String line = lines.get(i).replaceAll("\\s+","");
				if(!line.trim().equals("")) {
					linesOfCode.add(line);
				}
			}
		}
		
	}
	void runCommands()
	{
		for(int i = 0; i < keywords.size(); i++)
		{
			Keyword k = keywords.get(i);
			int amt, x, y, angle;
			switch(k.getName())
			{
				case "init":x = k.getArguments().get(0);
							y = k.getArguments().get(1);
							angle = k.getArguments().get(2);
							Utils.init(x, y, angle);
							break;
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
				case "repeat": amt = k.getArguments().get(0);
							i = Utils.repeat(keywords, i, amt);
							break;
			}
		}
	}
	
	
}
