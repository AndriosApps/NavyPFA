package com.andrios.prt.Activities;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.andrios.prt.Adapters.InstructionListViewAdapter;
import com.andrios.prt.AndriosData;
import com.andrios.prt.Classes.Instruction;
import com.andrios.prt.R;

import java.io.File;
import java.util.ArrayList;

public class InstructionsActivity extends Activity {

	private ListView instructionListView;
	private InstructionListViewAdapter adapter;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.instructionsactivity);
        
        getExtras();
        setConnections();
		adapter.add(AndriosData.getInstructions());
		adapter.notifyDataSetChanged();

    }
    

	private void getExtras() {

		
	}


	private void setConnections() {

		instructionListView = (ListView) findViewById(R.id.instruction_list_view);

		// Create a new {@link ArrayAdapter} of earthquakes
		adapter = new InstructionListViewAdapter(
				this, new ArrayList<Instruction>());
		// Set the adapter on the {@link ListView}
		// so the list can be populated in the user interface
		instructionListView.setAdapter(adapter);

		instructionListView.setClickable(true);
		instructionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
				Instruction instruction = (Instruction) (instructionListView.getItemAtPosition(position));
				String url = instruction.getUrl();
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});
		
	}


	
	public void onResume(){
		super.onResume();
	}
	
	public void onPause(){
		super.onPause();
	}
	
	private void open(String filename){
		
		String PATH = Environment.getExternalStorageDirectory()
                + "/download/";
		
		File file = new File(PATH + filename);
		if (file.exists()) {
			 System.out.println("file exists");
            Uri path = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");

            try {
            	System.out.println("Start Activity");
                startActivity(intent);
            } 
            catch (ActivityNotFoundException e) {
                Toast.makeText(InstructionsActivity.this, 
                    "No Application Available to View PDF", 
                    Toast.LENGTH_SHORT).show();
            }
		}else{
			float f = 1/0;
		}
	}

}
