package com.andrios.prt;

import java.util.ArrayList;
import java.util.Calendar;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LogAdapter extends ArrayAdapter<LogEntry>{

    private ArrayList<LogEntry> items;
	
   
	public LogAdapter(Context context, int textViewResourceId,
			ArrayList<LogEntry> items) {
		super(context, textViewResourceId, items);
		this.items = items;
		
		// TODO Auto-generated constructor stub
	}
	
	
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	LogEntry b;
        b = items.get(position);   
    	View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(b.getLayout(), null);
        }
            
      
            if (b != null) {
                    TextView titleTXT = (TextView) v.findViewById(R.id.journal_entry_list_item_titleLBL);
                   //LinearLayout background = (LinearLayout) v.findViewById(R.id.baby_name_list_item_backgroundLL);
                    LinearLayout dateLL = (LinearLayout) v.findViewById(R.id.journal_entry_list_item_dateLL);
                    TextView dateLBL = (TextView) v.findViewById(R.id.journal_entry_list_item_dateLBL);
                    TextView yearLBL = (TextView) v.findViewById(R.id.journal_entry_list_item_yearLBL);
                    ImageView importantIMG = (ImageView) v.findViewById(R.id.journal_entry_list_item_importantIMG);
                    TextView scoreTXT = (TextView) v.findViewById(R.id.journal_entry_list_item_scoreLBL);
                    
                    
                    if (titleTXT != null) {
                          titleTXT.setText(b.getName());                            
                    }
                   
                    
                    
                    if(dateLL != null){
                    	
                    
                    	Calendar c = b.getDate();
                    	dateLBL.setText(Integer.toString(c.get(Calendar.DAY_OF_MONTH)));
                    	yearLBL.setText(Integer.toString(c.get(Calendar.YEAR)));
	                    if(c.get(Calendar.MONTH) == 0){
	                    	dateLL.setBackgroundResource(R.drawable.cal_0);
	                    }else if(c.get(Calendar.MONTH) == 1){
	                    	dateLL.setBackgroundResource(R.drawable.cal_1);
	                    }else if(c.get(Calendar.MONTH) == 2){
	                    	dateLL.setBackgroundResource(R.drawable.cal_2);
	                    }else if(c.get(Calendar.MONTH) == 3){
	                    	dateLL.setBackgroundResource(R.drawable.cal_3);
	                    }else if(c.get(Calendar.MONTH) == 4){
	                    	dateLL.setBackgroundResource(R.drawable.cal_4);
	                    }else if(c.get(Calendar.MONTH) == 5){
	                    	dateLL.setBackgroundResource(R.drawable.cal_5);
	                    }else if(c.get(Calendar.MONTH) == 6){
	                    	dateLL.setBackgroundResource(R.drawable.cal_6);
	                    }else if(c.get(Calendar.MONTH) == 7){
	                    	dateLL.setBackgroundResource(R.drawable.cal_7);
	                    }else if(c.get(Calendar.MONTH) == 8){
	                    	dateLL.setBackgroundResource(R.drawable.cal_8);
	                    }else if(c.get(Calendar.MONTH) == 9){
	                    	dateLL.setBackgroundResource(R.drawable.cal_9);
	                    }else if(c.get(Calendar.MONTH) == 10){
	                    	dateLL.setBackgroundResource(R.drawable.cal_10);
	                    }else if(c.get(Calendar.MONTH) == 111){
	                    	dateLL.setBackgroundResource(R.drawable.cal_11);
	                    }
                    
                    }
                    
                    if(b.isOfficial()){
                    	importantIMG.setImageResource(R.drawable.icon_official);
                    }else{
                    	importantIMG.setImageResource(R.drawable.nothing);
                    }
                    
                    
                    try{
                    	
                    	scoreTXT.setText(b.getScoreString());
                    }catch(Exception e){
                    		e.printStackTrace();
                    }
                    	 
                    
                    
            }
            return v;
    }
    
	

}
