package com.andrios.prt;

import android.content.Context;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Observable;


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
			
	
	int[] pushupMale17 = {42, 46, 49, 51, 60, 68, 76, 79, 82, 86, 91, 92};
	int[] pushupMale20 = {37, 42, 45, 47, 55, 64, 71, 74, 77, 81, 86, 87};
	int[] pushupMale25 = {34, 38, 41, 44, 51, 60, 67, 69, 73, 77, 82, 84};
	int[] pushupMale30 = {31, 35, 38, 41, 48, 57, 64, 67, 69, 74, 78, 80};
	int[] pushupMale35 = {27, 33, 35, 37, 44, 53, 60, 63, 65, 70, 74, 76};
	int[] pushupMale40 = {24, 29, 32, 34, 41, 50, 56, 59, 61, 67, 70, 72};
	int[] pushupMale45 = {21, 25, 28, 32, 37, 46, 52, 54, 57, 63, 66, 68};
	int[] pushupMale50 = {19, 23, 25, 30, 34, 43, 49, 51, 53, 59, 62, 64};
	int[] pushupMale55 = {10, 12, 14, 16, 32, 38, 46, 48, 52, 56, 59, 60};
	int[] pushupMale60 = { 8, 10, 12, 14, 23, 32, 44, 46, 48, 52, 56, 57};
	int[] pushupMale65 = { 4,  6,  8, 10, 18, 25, 36, 39, 41, 44, 46, 48};
	
	int[] situpMale17 = {50, 54, 59, 62, 71, 81, 90, 93, 98, 102, 107, 109};
	int[] situpMale20 = {46, 50, 54, 58, 66, 78, 87, 90, 94, 98, 103, 105};
	int[] situpMale25 = {43, 47, 50, 54, 62, 75, 84, 87, 91, 95, 100, 101};
	int[] situpMale30 = {40, 44, 47, 51, 59, 73, 81, 85, 88, 92, 97, 98};
	int[] situpMale35 = {37, 40, 43, 47, 55, 70, 78, 83, 85, 88, 93, 95};
	int[] situpMale40 = {35, 37, 39, 44, 51, 68, 76, 80, 83, 85, 90, 92};
	int[] situpMale45 = {31, 33, 35, 40, 47, 65, 73, 78, 80, 81, 86, 88};
	int[] situpMale50 = {29, 30, 32, 37, 44, 63, 71, 76, 77, 78, 84, 85};
	int[] situpMale55 = {26, 28, 30, 36, 40, 54, 62, 66, 70, 74, 80, 81};
	int[] situpMale60 = {20, 22, 24, 26, 32, 40, 56, 62, 66, 70, 74, 75};
	int[] situpMale65 = {10, 13, 17, 20, 28, 36, 44, 50, 55, 60, 64, 65};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] runMale17 = {765,     735,     720,    660,    630,    600,    585,    570,    555,    540,    525,    495};
	int[] runMale20 = {810, 795, 765, 720, 690, 645, 630, 600, 585, 555, 540, 510};
	int[] runMale25 = {840, 825, 803, 773, 735, 683, 652, 630, 615, 578, 563, 535};
	int[] runMale30 = {870, 855, 840, 825, 780, 720, 675, 660, 630, 600, 585, 560};
	int[] runMale35 = {900, 885, 863, 848, 803, 743, 683, 668, 638, 608, 593, 565};
	int[] runMale40 = {930, 915, 885, 870, 825, 765, 705, 675, 645, 615, 600, 570};
	int[] runMale45 = {968, 945, 915, 893, 848, 780, 728, 698, 668, 630, 608, 573};
	int[] runMale50 = {1005, 975, 945, 915, 870, 795, 750, 720, 690, 645, 615, 595};
	int[] runMale55 = {1029, 1011, 993, 975, 914, 853, 792, 749, 717, 685, 669, 642};
	int[] runMale60 = {1132, 1100, 1067, 1034, 967, 900, 833, 796, 760, 724, 708, 681};
	int[] runMale65 = {1235, 1187, 1140, 1093, 1020, 947, 874, 837, 800, 753, 733, 701};
	
	// 5000+ feet altitude
	
	int[] altRunMale17 = {820,	800,	785,	720,	685,	655,	640,	620,	605,	590,	570,	540};
	int[] altRunMale20 = {885,	865,	835,	785,	750,	705,	685,	655,	640,	605,	590,	555};
	int[] altRunMale25 = {918,	898,	875,	843,	800,	745,	710,	688,	673,	630,	615,	583};
	int[] altRunMale30 = {950,	930,	915,	900,	850,	785,	735,	720,	705,	655,	640,	610};
	int[] altRunMale35 = {983,	965,	940,	925,	875,	810,	753,	728,	705,	663,	648,	615};
	int[] altRunMale40 = {1015,	1000,	965,	950,	900,	835,	770,	735,	705,	670,	655,	620};
	int[] altRunMale45 = {1055,	1033,	998,	975,	925,	850,	795,	760,	728,	688,	663,	623};
	int[] altRunMale50 = {1095,	1065,	1030,	1000,	950,	865,	820,	785,	750,	705,	670,	625};
	int[] altRunMale55 = {1132,	1112,	1092,	1073,	1005,	938,	871,	824,	789,	754,	736,	706};
	int[] altRunMale60 = {1245,	1210,	1174,	1137,	1064,	990,	916,	876,	836,	796,	779,	749};
	int[] altRunMale65 = {1359,	1306,	1254,	1202,	1122,	1042,	961,	921,	880,	839,	806,	771};
	
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] swim500Male17 = {765,	    735,	705,	675,	630,	555,	510,	495,	465,	435,	405,	390};
	int[] swim500Male20 = {780,	    735,	720,	690,	630,	570,	525,	495,	480,	450,	420,	390};
	int[] swim500Male25 = {788,	    743,	728,	698,	638,	578,	533,	503,	488,	458,	428,	398};
	int[] swim500Male30 = {795,	    750,	735,	705,	645,	585,	540,	510,	495,	465,	435,	405};
	int[] swim500Male35 = {803,	    758,	743,	713,	653,	593,	548,	518,	503,	473,	443,	413};
	int[] swim500Male40 = {810,	    765,	750,	720,	660,	600,	555,	525,	510,	480,	450,	420};
	int[] swim500Male45 = {818,	    773,	758,	728,	668,	608,	563,	533,	518,	488,	458,	428};
	int[] swim500Male50 = {825,	    780,	765,	735,	675,	615,	570,	540,	525,	495,	465,	435};
	int[] swim500Male55 = {835,	    805,	780,	753,	695,	640,	587,	555,	530,	497,	467,	437};
	int[] swim500Male60 = {845,	    820,	795,	770,	715,	660,	605,	570,	535,	500,	470,	440};
	int[] swim500Male65 = {855,	    840,	820,	800,	740,	685,	630,	585,	545,    505,	475,	445};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] swim450Male17 = {755,	    725,	695,	665,	620,	545,	500,	485,	455,	425,	395,	380};
	int[] swim450Male20 = {770,	    725,	710,	680,	620,	560,	515,	485,	470,	440,	410,	380};
	int[] swim450Male25 = {778,	    733,	718,	688,	628,	568,	523,	493,	478,	448,	418,	388};
	int[] swim450Male30 = {785,	    740,	725,	695,	635,	575,	530,	500,	485,	455,	425,	395};
	int[] swim450Male35 = {793,	    748,	733,	703,	643,	583,	538,	508,	493,	463,	433,	403};
	int[] swim450Male40 = {800,	    755,	740,	710,	650,	590,	545,	515,	500,	470,	440,	410};
	int[] swim450Male45 = {808,	    763,	748,	718,	658,	598,	553,	523,	508,	478,	448,	418};
	int[] swim450Male50 = {815,	    770,	755,	725,	665,	605,	560,	530,	515,	485,	455,	425};
	int[] swim450Male55 = {825,	    795,	770,	743,	685,	630,	577,	545,	520,	487,	457,	457};
	int[] swim450Male60 = {835,	    810,	785,    760,	705,	650,	595,	560,	525,	490,	460,	430};
	int[] swim450Male65 = {845,	    830,	810,	790,	730,	675,	620,	575,	535,	495,	465,	435};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altSwim500Male17 = {835,	    800,	770,	735,	685,	605,	555,	540,	505,	475,	440,	425};
	int[] altSwim500Male20 = {850,	    800,	785,	750,	685,	620,	570,	540,	484,	490,	460,	425};
	int[] altSwim500Male25 = {858,	    810,	793,    760,	695,	630,	580,	548,	533,	498,	468,	433};
	int[] altSwim500Male30 = {865,	    820,	800,	770,	705,	640,	590,	555,	540,	505,	475,	440};
	int[] altSwim500Male35 = {875,	    828,	810,	778,	713,	648,	598,	563,	548,	515,	483,	450};
	int[] altSwim500Male40 = {885,	    835,	820,	785,	720,	655,	605,	570,	555,	525,	490,	460};
	int[] altSwim500Male45 = {893,	    843,	828,	793,	728,	663,	613,	580,	563,	533,	498,	468};
	int[] altSwim500Male50 = {900,	    850,	835,	800,	735,	670,	620,	590,	570,	540,	505,	475};
	int[] altSwim500Male55 = {919,	    886,	858,	828,	765,	704,	646,	611,	583,	547,	514,	481};
	int[] altSwim500Male60 = {930,	    902,	875,	847,	787,	726,	666,	627,	589,	550,	517,	484};
	int[] altSwim500Male65 = {941,	    924,	902,	880,	814,	754,	693,	644,	600,	556,	523,	490};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altSwim450Male17 = {820,	    790,	760,	725,	674,	584,	545,	530,	495,	465,	430,	415};
	int[] altSwim450Male20 = {840,	    790,	775,	740,	675,	610,	560,	530,	510,	480,	445,	415};
	int[] altSwim450Male25 = {844,	    797,	780,	748,	684,	620,	571,	539,	524,	490,	460,	426};
	int[] altSwim450Male30 = {855,	    805,	790,	760,	690,	625,	580,	545,	530,	495,	465,	430,};
	int[] altSwim450Male35 = {861,	    814,	797,	765,	701,	637,	588,	554,	539,	507,	475,	443};
	int[] altSwim450Male40 = {870,	    825,	805,	775,	710,	645,	595,	560,	545,	510,	480,	445};
	int[] altSwim450Male45 = {878,	    829,	814,	779,	716,	652,	603,	571,	554,	524,	490,	460};
	int[] altSwim450Male50 = {890,	    840,	825,	790,	725,	660,	610,	580,	560,	530,	495,	465};
	int[] altSwim450Male55 = {908,	    875,	847,	817,	754,	693,	635,	600,    572,	536,	503,	470};
	int[] altSwim450Male60 = {919,	    891,	864,	836,	776,	715,	655,	616,	578,	539,	506,	473};
	int[] altSwim450Male65 = {930,	    913,	891,	869,	803,	743,	682,	633,	589,	545,	512,	479};
	
	
	//Female
	int[] weightFemale = {102, 106, 110, 114, 118, 123, 127, 131, 136, 141, 145,
			149, 152, 156, 160, 163, 167, 170, 174, 177, 181, 185, 189, 194, 200,
			205, 277, 216, 222, 227, 233, 239, 245, 251, 257, 263};
	
	
	int[] pushupFemale17 = {19, 20, 22, 24, 30, 36, 42, 43, 45, 47, 50, 51};
	int[] pushupFemale20 = {16, 17, 20, 21, 28, 33, 39, 40, 43, 44, 47, 48};
	int[] pushupFemale25 = {13, 15, 18, 19, 26, 30, 37, 39, 41, 43, 45, 46};
	int[] pushupFemale30 = {11, 13, 15, 17, 24, 28, 35, 37, 39, 41, 43, 44};
	int[] pushupFemale35 = { 9, 11, 13, 14, 22, 26, 34, 35, 37, 39, 42, 43};
	int[] pushupFemale40 = { 7,  9, 11, 12, 20, 24, 32, 33, 35, 37, 40, 41};
	int[] pushupFemale45 = { 5,  7,  8, 11, 18, 22, 30, 32, 33, 35, 39, 40};
	int[] pushupFemale50 = { 2,  5,  6, 10, 16, 20, 28, 30, 31, 33, 37, 38};
	int[] pushupFemale55 = { 2,  3,  5,  6, 10, 16, 20, 22, 24, 26, 28, 30};
	int[] pushupFemale60 = { 2,  3,  4,  5,  8, 12, 16, 18, 20, 22, 24, 26};
	int[] pushupFemale65 = { 1,  2,  3,  4,  6,  9, 12, 14, 16, 18, 20, 22};

    int[] situpFemale17 = {50, 54, 59, 62, 71, 81, 90, 93, 98, 102, 107, 109};
    int[] situpFemale20 = {46, 50, 54, 58, 66, 78, 87, 90, 94, 98, 103, 105};
    int[] situpFemale25 = {43, 47, 50, 54, 62, 75, 84, 87, 91, 95, 100, 101};
    int[] situpFemale30 = {40, 44, 47, 51, 59, 73, 81, 85, 88, 92, 97, 98};
    int[] situpFemale35 = {37, 40, 43, 47, 55, 70, 78, 83, 85, 88, 93, 95};
    int[] situpFemale40 = {35, 37, 39, 44, 51, 68, 76, 80, 83, 85, 90, 92};
    int[] situpFemale45 = {31, 33, 35, 40, 47, 65, 73, 78, 80, 81, 86, 88};
    int[] situpFemale50 = {29, 30, 32, 37, 44, 63, 71, 76, 77, 78, 84, 85};
    int[] situpFemale55 = {26, 28, 30, 36, 40, 54, 62, 66, 70, 74, 80, 81};
    int[] situpFemale60 = {20, 22, 24, 26, 32, 40, 56, 62, 66, 70, 74, 75};
    int[] situpFemale65 = {10, 13, 17, 20, 28, 36, 44, 50, 55, 60, 64, 65};
	
	
	int[] runFemale17 = {900, 	885, 	855, 	810, 	780, 	765, 	750, 	720, 	705, 	690, 	675, 	569};
	int[] runFemale20 = {930, 	915, 	900,	855,	825, 	810, 	795, 	765, 	735, 	690, 	675, 	587};
	int[] runFemale25 = {968, 	945, 	923, 	893, 	870, 	840, 	803, 	780, 	750, 	705, 	690, 	617};
	int[] runFemale30 = {1005,  975, 	945, 	930, 	915, 	870, 	810, 	795, 	765, 	720, 	705, 	646};
	int[] runFemale35 = {1020, 	998, 	975, 	953, 	930, 	878, 	825, 	803, 	773, 	728, 	713, 	651};
	int[] runFemale40 = {1035, 	1020, 	1005, 	975, 	945, 	885, 	840, 	810, 	780, 	735, 	720, 	656};
	int[] runFemale45 = {1043, 	1028, 	1013, 	990, 	953, 	900, 	848, 	825, 	795, 	750, 	728, 	658};
	int[] runFemale50 = {1050, 	1035, 	1020, 	1005, 	960, 	915, 	855, 	840, 	810, 	765, 	735, 	660};
	int[] runFemale55 = {1114, 	1098, 	1083, 	1068, 	1018, 	969, 	920, 	893, 	865, 	837, 	819, 	743};
	int[] runFemale60 = {1183, 	1165, 	1148, 	1131, 	1086, 	1037, 	985, 	960, 	934, 	908, 	890, 	814};
	int[] runFemale65 = {1252, 	1231, 	1213, 	1194, 	1146, 	1098, 	1050, 	1027, 	1003, 	979, 	961, 	885};
	
	// 5000+ feet altitude
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altRunFemale17 = {980,	965,	930,	885,	850,	835,	820,	785,	770,	750,	735,	620};
	int[] altRunFemale20 = {1015,	1000,	980,	930,	900,	885,	865,	835,	800,	750,	735,	640};
	int[] altRunFemale25 = {1055,	1033,	1005,	973,	950,	918,	875,	850,	818,	768,	753,	673};
	int[] altRunFemale30 = {1095,	1065,	1030,	1015,	1000,	950,	885,	865,	835,	785,	770,	705};
	int[] altRunFemale35 = {1113,	1088,	1063,	1040,	1015,	958,	900,	875,	843,	793,	778,	710};
	int[] altRunFemale40 = {1130,	1110,	1095,	1065,	1030,	965,	915,	885,	850,	800,	785,	715};
	int[] altRunFemale45 = {1138,	1120,	1103,	1080,	1038,	983,	923,	840,	748,	818,	793,	718};
	int[] altRunFemale50 = {1145,	1130,	1110,	1095,	1045,	1000,	930,	915,	885,	835,	800,	720};
	int[] altRunFemale55 = {1225,	1208,	1191,	1175,	1120,	1066,	1012,	982,	952,	921,	901,	817};
	int[] altRunFemale60 = {1301,	1282,	1263,	1244,	1195,	1141,	1084,	1056,	1027,	999,	979,	895};
	int[] altRunFemale65 = {1377,	1354,	1334,	1313,	1261,	1208,	1155,	1130,	1103,	1077,	1057,	974};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] swim500Female17 = {855,	825,	795,	780,	720,	645,	585,	570,	540,	510,	465,	405};
	int[] swim500Female20 = {870,	840,	825,	795,	735,	660,	600,	585,	555,	525,	480,	435};
	int[] swim500Female25 = {885,	855,	833,	810,	750,	675,	615,	600,	570,	540,	495,	443};
	int[] swim500Female30 = {900,	870,	840,	825,	765,	690,	630,	615,	585,	555,	510,	450};
	int[] swim500Female35 = {915,	878,	855,	780,	773,	705,	645,	623,	540,	570,	518,	465};
	int[] swim500Female40 = {930,	885,	870,	855,	780,	720,	660,	630,	615,	585,	525,	480};
	int[] swim500Female45 = {938,	900,	885,	870,	795,	735,	668,	645,	623,	593,	540,	495};
	int[] swim500Female50 = {945,	915,	900,	885,	810,	750,	675,	660,	630,	600,	555,	510};
	int[] swim500Female55 = {960,	930,	915,	900,	825,	765,	685,	675,	637,	607,	570,	525};
	int[] swim500Female60 = {975,	945,	930,	915,	840,	780,	695,	690,	645,	615,	585,	540};
	int[] swim500Female65 = {990,	960,	945,	930,	855,	795,	710,	705,	652,	623,	600,	555};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] swim450Female17 = {855,	825,	795,	780,	720,	645,	585,	570,	540,	510,	465,	405};
	int[] swim450Female20 = {860,	830,	815,	785,	725,	650,	590,	575,	545,	515,	470,	425};
	int[] swim450Female25 = {875,	845,	823,	800,	740,	665,	605,	590,	560,	530,	478,	433};
	int[] swim450Female30 = {890,	860,	830,	815,	755,	680,	620,	605,	575,	545,	500,	440};
	int[] swim450Female35 = {905,	868,	845,	830,	763,	695,	635,	613,	590,	560,	508,	455};
	int[] swim450Female40 = {920,	875,	860,	845,	770,	710,	650,	620,	605,	575,	515,	470};
	int[] swim450Female45 = {928,	890,	875,	860,	785,	725,	658,	635,	613,	583,	530,	485};
	int[] swim450Female50 = {935,	905,	890,	875,	800,	740,	665,	650,	620,	590,	545,	500};
	int[] swim450Female55 = {950,	920,	905,	890,	815,	755,	675,	665,	627,	597,	560,	515};
	int[] swim450Female60 = {976,	915,	915,	915,	793,	732,	671,	671,	610,	610,	549,	488};
	int[] swim450Female65 = {980,	950,	935,	920,	845,	785,	700,	695,	642,	613,	590,	545};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altSwim500Female17 = {930,	900,	865,	850,	785,	705,	640,	620,	590,	555,	505,	440};
	int[] altSwim500Female20 = {950,	915,	900,	865,	800,	720,	655,	640,	605,	570,	525,	475};
	int[] altSwim500Female25 = {965,	933,	908,	883,	818,	735,	670,	655,	623,	588,	540,	483};
	int[] altSwim500Female30 = {980,	950,	915,	900,	835,	750,	685,	670,	640,	605,	555,	490};
	int[] altSwim500Female35 = {958,	940,	933,	915,	843,	768,	703,	678,	655,	623,	563,	508};
	int[] altSwim500Female40 = {1015,	965,	950,	930,	850,	785,	720,	685,	670,	640,	570,	525};
	int[] altSwim500Female45 = {1023,	983,	965,	948,	868,	803,	728,	703,	678,	648,	588,	540};
	int[] altSwim500Female50 = {1030,	1000,	980,	965,	885,	820,	735,	720,	685,	655,	605,	555};
	int[] altSwim500Female55 = {1056,	1023,	1007,	990,	908,	842,	754,	743,	701,	668,	627,	578};
	int[] altSwim500Female60 = {1073,	1040,	1023,	1007,	924,	858,	765,	759,	710,	677,	644,	594};
	int[] altSwim500Female65 = {1089,	1056,	1040,	1023,	941,	875,	781,	776,	717,	685,	660,	611};
	
	// 1  2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18   19    20   21
	//60 120 180 240 300 360 420 480 540 600 660 720 780 840 900 960 1020 1080 1140 1200 1260
	int[] altSwim450Female17 = {920,	890,	855,	720,	715,	630,	625,	610,	580,	545,	495,	430};
	int[] altSwim450Female20 = {940,	905,	890,	855,	790,	710,	645,	625,	595,	560,	510,	465};
	int[] altSwim450Female25 = {950,	918,	893,	868,	804,	723,	659,	645,	613,	578,	531,	475};
	int[] altSwim450Female30 = {970,	940,	905,	890,	825,	740,	675,	660,	625,	595,	545,	480};
	int[] altSwim450Female35 = {982,	942,	918,	900,	829,	755,	691,	667,	645,	613,	554,	499};
	int[] altSwim450Female40 = {1000,	955,	940,	920,	840,	775,	710,	675,	660,	625,	560,	510};
	int[] altSwim450Female45 = {1006,	967,	950,	932,	854,	790,	716,	691,	667,	637,	578,	531};
	int[] altSwim450Female50 = {1020,	986,	970,	955,	870,	805,	725,	710,	675,	645,	595,	545};
	int[] altSwim450Female55 = {1045,	1012,	996,	979,	897,	831,	743,	732,	690,	657,	616,	567};
	int[] altSwim450Female60 = {1062,	1029,	1012,	996,	913,	847,	754,	748,	699,	666,	633,	583};
	int[] altSwim450Female65 = {1078,	1045,	1029,	1012,	930,	864,	770,	765,	706,	674,	649,	600};
	
	
	
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


public static ArrayList<Instruction> getInstructions(){
    ArrayList<Instruction> instructionList = new ArrayList<>();

    Calendar jan2016 = new GregorianCalendar(2016, 00, 15);

    jan2016.set(2011, 6, 11);
    instructionList.add(
            new Instruction(
                    "Physical Readiness Program",
                    "OPNAVINST 6110.1J",
                    "http://www.jag.navy.mil/distrib/instructions/OPNAV6110.1JPRTprogram.pdf",
                    jan2016.getTimeInMillis()
            )
    );
    jan2016.set(2016, 0, 15);
    instructionList.add(
            new Instruction(
                    "Command Fitness Leader Administrative Duties and Responsibilities",
            "Guide 1",
            "http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%201-%20CFLAdministrative%20Duties%20and%20Responsibilities%202016.pdf",
                    jan2016.getTimeInMillis()
            )
    );
    instructionList.add(
            new Instruction(
                    "Command Inspection Self - Assessment Checklist",
                    "Guide 2",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%202-%20Inspection%20and%20Command%20Self%20Assessment%20Checklist%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "PFA Checklist 2016",
                    "Guide 3",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%203-PFA%20Checklist%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "Body Composition Assessment (BCA)",
                    "Guide 4",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%204-%20Body%20Composition%20Assessment%20(BCA)%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "Physical Readiness Test 2016",
                    "Guide 5",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%205-%20Physical%20Readiness%20Test%20%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "PFA Medical Clearance/Waiver",
                    "Guide 6",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%206-%20PFA%20Medical%20Waiver%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    " PFA Administrative Actions/Administrative Separation",
                    "Guide 7",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%207-%20Adminstrative%20Separation%20(ADSEP)%202016%20(F).pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "Managing PFA Records for Pregnant Service Women",
                    "Guide 8",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%208-%20Managing%20PFA%20Records%20for%20Pregnant%20Service%20Women%202016%20(F).pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "Managing PFA Records for IA/OSA/GSA/PEP/Mobilized Reservist",
                    "Guide 9",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%209-%20Managing%20PFA%20Records%20for%20IA%20GSA%20OSA%20PEP%20and%20Mobilized%20Reservists%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "Alternate Cardio Options Procedures",
                    "Guide 10",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2010-%20Alternate%20Cardio%20Options%20Procedures%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "Member's Responsibilities 2016.pdf",
                    "Guide 11",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2011-%20Member%27s%20Responsibilities%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "Glossary of Physical Readiness Program Related Terms",
                    "Guide 12",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2012-%20Glossary%20of%20Physical%20Readiness%20Program%20Related%20Terms%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "Command Fitness and Fitness Enhancement Program (FEP) Guide",
                    "Guide 13",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2013-%20Command%20PT%20and%20FEP%20Guide%202016%20(F).pdf",
                    jan2016.getTimeInMillis()            )
    );
    instructionList.add(
            new Instruction(
                    "Nutrition Resource Guide",
                    "Guide 14",
"http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Documents/Guide%2014-%20Nutrition%20Resource%20Guide%202016.pdf",
                    jan2016.getTimeInMillis()            )
    );
	jan2016.set(2016,4,26);
	instructionList.add(
			new Instruction(
					"Physical Readiness Program Update #3",
					"NAVADMIN 124/16 ",
					"http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2016/NAV16124.txt",
					jan2016.getTimeInMillis()            )
	);
    jan2016.set(2016,2,9);
    instructionList.add(
            new Instruction(
                    "Implementation of Physical Readiness Program Policy Changes Update #2",
                    "NAVADMIN 061/16",
                    "http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2016/NAV16061.txt",
                    jan2016.getTimeInMillis()            )
    );
    jan2016.set(2015,9,7);
    instructionList.add(
            new Instruction(
                    "Physical Readiness Program Policy Changes Update #1: Enlisted Policies",
                    "NAVADMIN 233/15",
                    "http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2015/NAV15233.txt",
                    jan2016.getTimeInMillis()            )
    );
    jan2016.set(2015,7,3);
    instructionList.add(
            new Instruction(
                    "Physical Readiness Program Policy Changes",
                    "NAVADMIN 178/15",
                    "http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2015/NAV15178.txt",
                    jan2016.getTimeInMillis()            )
    );
    jan2016.set(2013,8,12);
    instructionList.add(
            new Instruction(
                    "Physical Readiness Program Policy Changes",
                    "NAVADMIN 231/13",
                    "http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2013/NAV13231.txt",
                    jan2016.getTimeInMillis()            )
    );
    jan2016.set(2011,6,11);
    instructionList.add(
            new Instruction(
                    "OPNAVINST 6110.1J Physical Readiness Program Policy Changes",
                    "NAVADMIN 203/11",
                    "http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2011/NAV11203.txt",
                    jan2016.getTimeInMillis()            )
    );
    jan2016.set(2011,3,6);
    instructionList.add(
            new Instruction(
                    "Command Fitness Certification Course Update",
                    "NAVADMIN ",
                    "http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2011/NAV11118.txt",
                    jan2016.getTimeInMillis()            )
    );
    jan2016.set(2010,4,28);
    instructionList.add(
            new Instruction(
                    "Change to Physical Fitness Assessment Documentation on Fitness Reports",
                    "NAVADMIN ",
                    "http://www.public.navy.mil/bupers-npc/reference/messages/Documents/NAVADMINS/NAV2010/NAV10193.txt",
                    jan2016.getTimeInMillis()            )
    );
    jan2016.set(2016,10,22);
    instructionList.add(
            new Instruction(
                    "Navy Physical Readiness Program Website",
                    "Official Website",
                    "http://www.public.navy.mil/bupers-npc/support/21st_Century_Sailor/physical/Pages/default2.aspx",
                    jan2016.getTimeInMillis()            )
    );

    Collections.sort(instructionList);




    return instructionList;

}


	public int[] getWeightMale() {
		return weightMale;
	}

	public int[] getWeightFemale() {
		return weightFemale;
	}
}
