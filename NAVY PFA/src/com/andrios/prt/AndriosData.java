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
	
	// 5000+ feet altitude
	
	int[] altRunMale17 = {820, 720, 640, 590, 500};
	int[] altRunMale20 = {885, 785, 685, 615, 515};
	int[] altRunMale25 = {918, 843, 710, 640, 543};
	int[] altRunMale30 = {950, 900, 735, 665, 570};
	int[] altRunMale35 = {983, 925, 753, 673, 575};
	int[] altRunMale40 = {1015, 950, 770, 680, 580};
	int[] altRunMale45 = {1055, 975, 795, 698, 583};
	int[] altRunMale50 = {1095, 1000, 820, 705, 625};
	int[] altRunMale55 = {1132, 1073, 871, 754, 706};
	int[] altRunMale60 = {1245, 1137, 916, 796, 749};
	int[] altRunMale65 = {1359, 1202, 961, 839, 771};
	
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] swim500Male17 = {765, 675, 510, 435, 390};
	int[] swim500Male20 = {780, 690, 525, 450, 390};
	int[] swim500Male25 = {788, 698, 533, 558, 398};
	int[] swim500Male30 = {803, 705, 540, 465, 405};
	int[] swim500Male35 = {803, 713, 548, 473, 413};
	int[] swim500Male40 = {810, 720, 555, 480, 420};
	int[] swim500Male45 = {818, 728, 563, 488, 428};
	int[] swim500Male50 = {825, 735, 570, 495, 435};
	int[] swim500Male55 = {835, 753, 587, 497, 437 };
	int[] swim500Male60 = {845, 770, 605, 500, 440};
	int[] swim500Male65 = {855, 800, 630, 505, 445};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] swim450Male17 = {755, 665, 500, 425, 380};
	int[] swim450Male20 = {770, 680, 515, 440, 380};
	int[] swim450Male25 = {778, 688, 523, 548, 388};
	int[] swim450Male30 = {793, 695, 530, 455, 395};
	int[] swim450Male35 = {793, 703, 538, 463, 403};
	int[] swim450Male40 = {800, 720, 555, 480, 410};
	int[] swim450Male45 = {808, 718, 553, 478, 418};
	int[] swim450Male50 = {815, 725, 560, 485, 425};
	int[] swim450Male55 = {825, 743, 577, 487, 427 };
	int[] swim450Male60 = {835, 760, 595, 490, 430};
	int[] swim450Male65 = {845, 790, 620, 495, 445};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altSwim500Male17 = {835, 735, 555, 475, 425};
	int[] altSwim500Male20 = {850, 750, 570, 490, 425};
	int[] altSwim500Male25 = {858, 760, 580, 498, 433};
	int[] altSwim500Male30 = {865, 770, 590, 505, 440};
	int[] altSwim500Male35 = {875, 778, 598, 515, 450};
	int[] altSwim500Male40 = {885, 785, 605, 525, 460};
	int[] altSwim500Male45 = {893, 793, 613, 533, 468};
	int[] altSwim500Male50 = {900, 800, 620, 540, 475};
	int[] altSwim500Male55 = {919, 828, 646, 547, 481};
	int[] altSwim500Male60 = {930, 847, 666, 550, 484};
	int[] altSwim500Male65 = {941, 880, 693, 556, 490};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altSwim450Male17 = {820, 725, 505, 465, 415};
	int[] altSwim450Male20 = {840, 740, 560, 480, 415};
	int[] altSwim450Male25 = {844, 748, 571, 490, 426};
	int[] altSwim450Male30 = {855, 760, 580, 495, 430};
	int[] altSwim450Male35 = {861, 768, 588, 507, 443};
	int[] altSwim450Male40 = {870, 775, 595, 510, 445};
	int[] altSwim450Male45 = {878, 770, 603, 524, 460};
	int[] altSwim450Male50 = {890, 790, 610, 530, 465};
	int[] altSwim450Male55 = {908, 817, 635, 536, 470};
	int[] altSwim450Male60 = {919, 836, 655, 539, 473};
	int[] altSwim450Male65 = {930, 869, 682, 545, 479};
	
	
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
	
	// 5000+ feet altitude
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altRunFemale17 = {980, 885, 820, 750, 620};
	int[] altRunFemale20 = {1015, 930, 865, 750, 640};
	int[] altRunFemale25 = {1055, 973, 875, 768, 673};
	int[] altRunFemale30 = {1095, 1015, 885, 785, 705};
	int[] altRunFemale35 = {1088, 1040, 900, 793, 710};
	int[] altRunFemale40 = {1130, 1065, 915, 800, 715};
	int[] altRunFemale45 = {1138, 1080, 923, 818, 718};
	int[] altRunFemale50 = {1145, 1095, 930, 835, 720};
	int[] altRunFemale55 = {1225, 1175, 1012, 921, 817};
	int[] altRunFemale60 = {1301, 1244, 1084, 999, 895};
	int[] altRunFemale65 = {1377, 1313, 1155, 1077, 954};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] swim500Female17 = {855, 780, 585, 510, 405};
	int[] swim500Female20 = {870, 795, 600, 525, 435};
	int[] swim500Female25 = {885, 810, 615, 540, 443};
	int[] swim500Female30 = {900, 825, 630, 555, 450};
	int[] swim500Female35 = {915, 840, 645, 570, 465};
	int[] swim500Female40 = {930, 855, 660, 585, 480};
	int[] swim500Female45 = {938, 870, 668, 593, 495};
	int[] swim500Female50 = {945, 885, 675, 600, 510};
	int[] swim500Female55 = {960, 900, 685, 607, 525};
	int[] swim500Female60 = {975, 915, 695, 615, 540};
	int[] swim500Female65 = {990, 930, 710, 623, 555};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] swim450Female17 = {845, 770, 575, 500, 395};
	int[] swim450Female20 = {860, 785, 590, 515, 425};
	int[] swim450Female25 = {875, 800, 605, 530, 433};
	int[] swim450Female30 = {890, 815, 620, 545, 440};
	int[] swim450Female35 = {905, 830, 635, 560, 455};
	int[] swim450Female40 = {920, 845, 650, 575, 470};
	int[] swim450Female45 = {928, 860, 658, 583, 485};
	int[] swim450Female50 = {935, 875, 665, 590, 500};
	int[] swim450Female55 = {950, 890, 675, 597, 515};
	int[] swim450Female60 = {965, 905, 685, 605, 530};
	int[] swim450Female65 = {980, 920, 700, 613, 545};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altSwim500Female17 = {930, 850, 640, 555, 475};
	int[] altSwim500Female20 = {950, 905, 655, 570, 475};
	int[] altSwim500Female25 = {965, 883, 670, 588, 483};
	int[] altSwim500Female30 = {980, 900, 685, 605, 490};
	int[] altSwim500Female35 = {933, 915, 703, 623, 508};
	int[] altSwim500Female40 = {1015, 930, 720, 640, 525};
	int[] altSwim500Female45 = {1023, 948, 728, 648, 540};
	int[] altSwim500Female50 = {1030, 965, 735, 655, 555};
	int[] altSwim500Female55 = {1056, 990, 754, 668, 578};
	int[] altSwim500Female60 = {1073, 1007, 765, 677, 594};
	int[] altSwim500Female65 = {1089, 1023, 781, 685, 611};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altSwim450Female17 = {920, 840, 625, 545, 430};
	int[] altSwim450Female20 = {940, 855, 645, 560, 465};
	int[] altSwim450Female25 = {950, 868, 659, 578, 475};
	int[] altSwim450Female30 = {970, 890, 675, 595, 480};
	int[] altSwim450Female35 = {982, 900, 691, 613, 499};
	int[] altSwim450Female40 = {1000, 920, 710, 625, 510};
	int[] altSwim450Female45 = {1006, 932, 716, 637, 531};
	int[] altSwim450Female50 = {1020, 955, 725, 645, 545};
	int[] altSwim450Female55 = {1045, 979, 743, 657, 567};
	int[] altSwim450Female60 = {1062, 995, 754, 666, 583};
	int[] altSwim450Female65 = {1078, 1012, 770, 674, 600};
	
	
	
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
