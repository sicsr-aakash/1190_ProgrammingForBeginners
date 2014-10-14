package com.example.programmingforbegg;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	Button btn;
	Button howitworks;
	AlertDialog.Builder alertDialog;
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		
		 alertDialog = new AlertDialog.Builder(this);
		 alertDialog.setTitle("Want to Quit? ");
		 alertDialog.setMessage("Are you sure you want to close this?");
		 alertDialog.setCancelable(false);
		 alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MainActivity.this.finish();
			}
			
		}); 
		 alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				
			}
		});
		 
		 AlertDialog alert = alertDialog.create();
		 alert.setCancelable(true);
		 alert.show();
		 
	}	
			
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        howitworks = (Button)findViewById(R.id.example_button);
        howitworks.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(getApplicationContext(), HowItWorksActivity.class);
				startActivity(i);
				
				
			}
		});
        btn= (Button)findViewById(R.id.new_btn);
        OnClickListener newListener = new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				//explicit intent
				Intent intent = new Intent(getApplicationContext(),WorkspaceActivity.class);
				startActivity(intent);
				
				
			}
		};
		
		btn.setOnClickListener(newListener);
        
    }

}
