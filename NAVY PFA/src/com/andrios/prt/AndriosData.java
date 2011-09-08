package com.andrios.prt;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Observable;

import android.content.Context;
import android.widget.Toast;


public class AndriosData extends Observable implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6149357292077176082L;

	boolean isMale;
	int age;
	int runtime;
	
	
	//Male
	int[] weightMale = {97, 102, 107, 112, 117, 122, 127, 131, 136, 141, 145, 150, 155,
			160, 165, 170, 175, 181, 186, 191, 196, 201, 206, 211, 216, 221, 226, 231,
			236, 241, 246, 251, 256, 261, 266, 271, 271};
			
	
	int[] pushupMale17 = {42, 51, 76, 86, 92};
	int[] pushupMale20 = {37, 47, 71, 81, 87};
	int[] pushupMale25 = {34, 44, 67, 77, 84};
	int[] pushupMale30 = {31, 41, 64, 74, 80};
	int[] pushupMale35 = {27, 37, 60, 70, 76};
	int[] pushupMale40 = {24, 34, 56, 67, 72};
	int[] pushupMale45 = {21, 32, 52, 63, 68};
	int[] pushupMale50 = {19, 30, 49, 59, 64};
	int[] pushupMale55 = {10, 16, 46, 56, 60};
	int[] pushupMale60 = { 8, 14, 44, 52, 57};
	int[] pushupMale65 = { 4, 10, 36, 44, 48};
	
	int[] situpMale17 = {50, 62, 90, 102, 109};
	int[] situpMale20 = {46, 58, 87, 98, 105};
	int[] situpMale25 = {43, 54, 84, 95, 101};
	int[] situpMale30 = {40, 51, 81, 92, 98};
	int[] situpMale35 = {37, 47, 78, 88, 95};
	int[] situpMale40 = {35, 44, 76, 85, 92};
	int[] situpMale45 = {31, 40, 73, 81, 88};
	int[] situpMale50 = {29, 37, 71, 78, 85};
	int[] situpMale55 = {26, 36, 62, 74, 81};
	int[] situpMale60 = {20, 26, 56, 70, 75};
	int[] situpMale65 = {10, 20, 44, 60, 65};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] runMale17 = {750, 660, 585, 540, 495};
	int[] runMale20 = {810, 720, 630, 555, 510};
	int[] runMale25 = {840, 773, 652, 573, 535};
	int[] runMale30 = {870, 825, 675, 600, 560};
	int[] runMale35 = {900, 848, 683, 608, 565};
	int[] runMale40 = {930, 870, 705, 615, 570};
	int[] runMale45 = {968, 893, 728, 630, 573};
	int[] runMale50 = {1005, 915, 750, 645, 575};
	int[] runMale55 = {1029, 975, 792, 685, 642};
	int[] runMale60 = {1132, 1034, 833, 724, 681};
	int[] runMale65 = {1252, 1194, 1050, 979, 885};
	
	//Female
	int[] weightFemale = {102, 106, 110, 114, 118, 123, 127, 131, 136, 141, 145,
			149, 152, 156, 160, 163, 167, 170, 174, 177, 181, 185, 189, 194, 200,
			205, 277, 216, 222, 227, 233, 239, 245, 251, 257, 263};
	
	
	int[] pushupFemale17 = {19, 24, 42, 47, 51};
	int[] pushupFemale20 = {16, 21, 39, 44, 48};
	int[] pushupFemale25 = {13, 19, 37, 43, 46};
	int[] pushupFemale30 = {11, 17, 35, 41, 44};
	int[] pushupFemale35 = { 9, 14, 34, 39, 43};
	int[] pushupFemale40 = { 7, 12, 32, 37, 41};
	int[] pushupFemale45 = {5, 11, 30, 35, 40};
	int[] pushupFemale50 = {2, 10, 28, 33, 38};
	int[] pushupFemale55 = {2, 6, 20, 26, 30};
	int[] pushupFemale60 = {2, 5, 16, 22, 26};
	int[] pushupFemale65 = {1, 4, 12, 18, 22};
	
	int[] situpFemale17 = {50, 62, 90, 102, 109};
	int[] situpFemale20 = {46, 58, 87, 98, 105};
	int[] situpFemale25 = {13, 54, 84, 95, 101};
	int[] situpFemale30 = {40, 51, 81, 92, 98};
	int[] situpFemale35 = {37, 47, 78, 88, 95};
	int[] situpFemale40 = {35, 44, 76, 85, 92};
	int[] situpFemale45 = {31, 40, 73, 81, 88};
	int[] situpFemale50 = {29, 37, 71, 78, 85};
	int[] situpFemale55 = {26, 36, 62, 74, 81};
	int[] situpFemale60 = {20, 26, 56, 70, 75};
	int[] situpFemale65 = {10, 20, 44, 60, 65};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] runFemale17 = {900, 810, 750, 690, 569};
	int[] runFemale20 = {930, 855, 795, 690, 587};
	int[] runFemale25 = {968, 893, 803, 705, 617};
	int[] runFemale30 = {1005, 930, 810, 720, 646};
	int[] runFemale35 = {1020, 953, 825, 728, 651};
	int[] runFemale40 = {1035, 975, 840, 735, 656};
	int[] runFemale45 = {1043, 990, 848, 750, 658};
	int[] runFemale50 = {1050, 1005, 855, 765, 660};
	int[] runFemale55 = {1123, 1068, 920, 837, 743};
	int[] runFemale60 = {1183, 1131, 985, 908, 814};
	int[] runFemale65 = {1252, 1194, 1050, 979, 885};
	
	
	public AndriosData(){
		isMale = true;
		age = 19;
		
	}
	

	/**
	 * Getter Methods
	 */
	
	public boolean getGender(){
		return isMale;
	}
	
	public int getAge(){
		return age;
	}
	
	public int getRunTime(){
		return runtime;
	}
	
	
	
	/**
	 * Setter Methods
	 */
	
	public void setGender(boolean isMale){
		this.isMale = isMale;
		setChanged();
		notifyObservers();
		System.out.println("MODEL UPDATE GENDER");
	}
	
	public void setAge(int age){
		this.age = age;

		setChanged();
		notifyObservers();
		System.out.println("MODEL UPDATE AGE");
	}
	
	public void setRunTime(int runtime){
		this.runtime = runtime;
		setChanged();
		notifyObservers();
		System.out.println("MODEL UPDATE RUNTIME");
	}
	
	


	
	public void write(Context ctx){
		AndriosData writableData = null;
		try {
			writableData = (AndriosData) this.clone();
		} catch (CloneNotSupportedException e1) {
			e1.printStackTrace();
		}
		
		
		try {
		
			FileOutputStream fos;
			fos = ctx.openFileOutput("data", Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(writableData);

			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			Toast.makeText(ctx, "Error: Writing to file",
					Toast.LENGTH_SHORT).show();
		}
	}













	
}
