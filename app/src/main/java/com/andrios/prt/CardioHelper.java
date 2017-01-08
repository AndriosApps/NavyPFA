package com.andrios.prt;

import android.content.Context;

/**
 * Created by Corey on 1/7/2017.
 */

public class CardioHelper {
    int cardioOption;
    String[][] cardioArray;

    public CardioHelper(Context context){
        cardioOption = 8;
        cardioArray = new String[9][2];
        cardioArray[0][0] = context.getString(R.string.cardiorun);
        cardioArray[0][1] = "1";
        cardioArray[1][0]  = context.getString(R.string.cardioswim500);
        cardioArray[1][1] = "2";
        cardioArray[2][0] = context.getString(R.string.cardioswim450);
        cardioArray[2][1] = "2";
        cardioArray[3][0] = context.getString(R.string.cardiobike);
        cardioArray[3][1] = "3";
        cardioArray[4][0] =context.getString(R.string.cardioellipefx556);
        cardioArray[4][1] = "4";
        cardioArray[5][0] =context.getString(R.string.cardioellipct9500hr);
        cardioArray[5][1] = "4";
        cardioArray[6][0] =context.getString(R.string.cardioellipct9500);
        cardioArray[6][1] = "4";
        cardioArray[7][0] =context.getString(R.string.cardioellip95xi);
        cardioArray[7][1] = "4";
        cardioArray[8][0] =context.getString(R.string.cardioellipe916);
        cardioArray[8][1] = "4";
    }

    public String[] getNextCardioOptions(){

        cardioOption++;
        if(cardioOption > 8){
            cardioOption = 0;
        }
        return cardioArray[cardioOption];
    }

    public String[] getCurrentCardioOption(){

        return cardioArray[cardioOption];
    }




}
