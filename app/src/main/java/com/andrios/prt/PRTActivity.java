package com.andrios.prt;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.andrios.prt.Classes.Profile;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Observable;
import java.util.Observer;

public class PRTActivity extends Activity implements Observer {

    SegmentedControlButton maleRDO, femaleRDO;

    private static int MAX_PUSHUPS = 100;//max pushups is 92
    private static int MAX_SITUPS = 110;//max situps is 109 (19 yr male)
    private static int MAX_CARDIO = 1260;//max runtime 17:23 (50yr female) 18 min * 60 = 1080


    Spinner ageSpinner;
    SeekBar pushupSeekBar, situpSeekBar, runSeekBar;
    TextView pushupLBL, situpLBL, runLBL, scoreLBL;
    TextView pushupScoreLBL, runScoreLBL, situpScoreLBL, cardioLBL;
    Button secondUpBTN, secondDownBTN;
    Button pushupUpBTN, pushupDownBTN, situpUpBTN, situpDownBTN;
    Button logBTN;
    AndriosData mData;

    boolean pushupchanged = false, situpchanged = false, runchanged = false;
    boolean isPremium = false, isLog = false, alternate = false;
    RelativeLayout bottomBar;
    int age = 18, pushups = 0, situps = 0, runtime = 0, minutes, seconds;
    boolean male;
    CheckBox altitudeCheckBox;
    String cardio = "Run";


    int[] Scores = {45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.prtactivity);

        getExtras();
        setConnections();
        setOnClickListeners();
        finishSetup();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.prtmenu, menu);
        return true;
    }


    private void finishSetup() {
        if (age <= 19) {
            ageSpinner.setSelection(0);
        } else if (age <= 24) {
            ageSpinner.setSelection(1);
        } else if (age <= 29) {
            ageSpinner.setSelection(2);
        } else if (age <= 34) {
            ageSpinner.setSelection(3);
        } else if (age <= 39) {
            ageSpinner.setSelection(4);
        } else if (age <= 44) {
            ageSpinner.setSelection(5);
        } else if (age <= 49) {
            ageSpinner.setSelection(6);
        } else if (age <= 54) {
            ageSpinner.setSelection(7);
        } else if (age <= 59) {
            ageSpinner.setSelection(8);
        } else if (age <= 64) {
            ageSpinner.setSelection(9);
        } else if (age >= 65) {
            ageSpinner.setSelection(10);
        }

        if (!male) {
            femaleRDO.setChecked(true);
        }
        if (isLog) {
            maleRDO.setEnabled(false);
            femaleRDO.setEnabled(false);
            ageSpinner.setEnabled(false);
        }


    }


    private void getExtras() {
        Intent intent = this.getIntent();
        isLog = intent.getBooleanExtra("log", false);
        isPremium = intent.getBooleanExtra("premium", false);
        mData = (AndriosData) intent.getSerializableExtra("data");
        if (mData == null) {
            readData();
        }
        mData.addObserver(this);
        age = mData.getAge();
        male = mData.getGender();


    }

    private void setConnections() {
        String[] array_spinner = new String[11];

        array_spinner[0] = "17-19";
        array_spinner[1] = "20-24";
        array_spinner[2] = "25-29";
        array_spinner[3] = "30-34";
        array_spinner[4] = "35-39";
        array_spinner[5] = "40-44";
        array_spinner[6] = "45-49";
        array_spinner[7] = "50-54";
        array_spinner[8] = "55-59";
        array_spinner[9] = "60-64";
        array_spinner[10] = "65+";
        ageSpinner = (Spinner) findViewById(R.id.prtActivityAgeSpinner);
        ArrayAdapter adapter = new ArrayAdapter(this,
                R.layout.my_spinner_item, array_spinner);
        ageSpinner.setAdapter(adapter);

        altitudeCheckBox = (CheckBox) findViewById(R.id.prtActivityAltitudeCheckBox);

        logBTN = (Button) findViewById(R.id.prtActivityLogBTN);
        cardioLBL = (TextView) findViewById(R.id.prtActivityCardioLBL);

        maleRDO = (SegmentedControlButton) findViewById(R.id.prtActivityrMaleRDO);
        femaleRDO = (SegmentedControlButton) findViewById(R.id.prtActivityrFemaleRDO);

        pushupSeekBar = (SeekBar) findViewById(R.id.calculatorPushupSeekBar);
        situpSeekBar = (SeekBar) findViewById(R.id.calculatorSitUpSeekBar);
        runSeekBar = (SeekBar) findViewById(R.id.calculatorRunTimeSeekBar);

        scoreLBL = (TextView) findViewById(R.id.calculatorScoreLBL);
        pushupLBL = (TextView) findViewById(R.id.calculatorPushUpLBL);
        situpLBL = (TextView) findViewById(R.id.calculatorSitUpLBL);
        runLBL = (TextView) findViewById(R.id.calculatorRunLBL);


        pushupScoreLBL = (TextView) findViewById(R.id.prtActivityPushupScoreLBL);
        situpScoreLBL = (TextView) findViewById(R.id.prtActivitySitupScoreLBL);
        runScoreLBL = (TextView) findViewById(R.id.prtActivityRunScoreActivity);


        pushupUpBTN = (Button) findViewById(R.id.calculatorPushupsUpBTN);
        situpUpBTN = (Button) findViewById(R.id.calculatorSitupsUpBTN);
        secondUpBTN = (Button) findViewById(R.id.calculatorSecondsUpBTN);

        pushupDownBTN = (Button) findViewById(R.id.calculatorPushupsDownBTN);
        situpDownBTN = (Button) findViewById(R.id.calculatorSitupsDownBTN);
        secondDownBTN = (Button) findViewById(R.id.calculatorSecondsDownBTN);


        pushupSeekBar.setMax(MAX_PUSHUPS);
        situpSeekBar.setMax(MAX_SITUPS);
        runSeekBar.setMax(MAX_CARDIO);

        //adView = (AdView)this.findViewById(R.id.homeAdView);

        //request = new AdRequest();
        //request.setTesting(false);

        //Removing Ad View For everyone.
        if (!isPremium) {
            //adView = (AdView)this.findViewById(R.id.homeAdView);

            // request = new AdRequest();
            //request.setTesting(false);
            //adView.loadAd(request);
            bottomBar = (RelativeLayout) findViewById(R.id.prtActivityBottomBar);
            bottomBar.setVisibility(View.GONE);
        } else {
            //adView.setVisibility(View.INVISIBLE);
            if (!isLog) {

                bottomBar = (RelativeLayout) findViewById(R.id.prtActivityBottomBar);
                bottomBar.setVisibility(View.GONE);

            } else {
                logBTN.setVisibility(View.VISIBLE);
            }
        }


    }

    private void setOnClickListeners() {

        altitudeCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                calculateScore();

            }

        });

        ageSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                                       long arg3) {
                int posit = ageSpinner.getSelectedItemPosition();
                if (posit == 0) {
                    age = 19;
                } else if (posit == 1) {
                    age = 24;
                } else if (posit == 2) {
                    age = 29;
                } else if (posit == 3) {
                    age = 34;
                } else if (posit == 4) {
                    age = 39;
                } else if (posit == 5) {
                    age = 44;
                } else if (posit == 6) {
                    age = 49;
                } else if (posit == 7) {
                    age = 54;
                } else if (posit == 8) {
                    age = 59;
                } else if (posit == 9) {
                    age = 64;
                } else if (posit == 10) {
                    age = 65;
                }

                mData.setAge(age);
                calculateScore();


            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });


        pushupSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                pushups = arg1;
                pushupLBL.setText(Integer.toString(pushups));
                pushupchanged = true;
                calculateScore();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        situpSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                situps = arg1;
                situpLBL.setText(Integer.toString(situps));
                situpchanged = true;
                calculateScore();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });

        runSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                runtime = arg1;
                minutes = (Integer) runtime / 60;
                seconds = runtime % 60;
                formatTimer();
                runchanged = true;
                calculateScore();

            }

            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });


        pushupUpBTN.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                pushups += 1;
                if (pushups > 100) {
                    pushups = 100;
                }

                pushupSeekBar.setProgress(pushups);

                calculateScore();
            }

        });

        situpUpBTN.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                situps += 1;
                if (situps > 110) {
                    situps = 110;
                }

                situpSeekBar.setProgress(situps);

                calculateScore();
            }

        });

        secondUpBTN.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                runtime += 1;
                if (runtime > 1080) {
                    runtime = 1080;
                }
                minutes = (Integer) runtime / 60;
                seconds = runtime % 60;
                formatTimer();
                runSeekBar.setProgress(runtime);
                runchanged = true;
                calculateScore();
            }

        });


        pushupDownBTN.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                pushups -= 1;
                if (pushups < 0) {
                    pushups = 0;
                }

                pushupSeekBar.setProgress(pushups);

                calculateScore();
            }

        });

        situpDownBTN.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                situps -= 1;
                if (situps < 0) {
                    situps = 0;
                }

                situpSeekBar.setProgress(situps);

                calculateScore();
            }

        });

        secondDownBTN.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                runtime -= 1;
                if (runtime < 0) {
                    runtime = 0;
                }
                minutes = (Integer) runtime / 60;
                seconds = runtime % 60;
                formatTimer();
                runSeekBar.setProgress(runtime);
                runchanged = true;
                calculateScore();
            }

        });

        maleRDO.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
                calculateScore();
                mData.setGender(maleRDO.isChecked());
            }

        });

        logBTN.setOnClickListener(new OnClickListener() {

            public void onClick(View v) {
                if (changed()) {
                    PrtEntry p = new PrtEntry(
                            Integer.toString(pushups),
                            Integer.toString(situps),
                            formatTimer(),
                            pushupScoreLBL.getText().toString(),
                            situpScoreLBL.getText().toString(),
                            runScoreLBL.getText().toString(),
                            scoreLBL.getText().toString()
                    );
                    p.setAge(age);
                    p.setAlternateCardio(cardio);
                    if (altitudeCheckBox.isChecked()) {
                        p.setAltitude(true);
                    } else {
                        p.setAltitude(false);
                    }

                    Intent intent = new Intent();


                    intent.putExtra("entry", p);
                    PRTActivity.this.setResult(RESULT_OK, intent);
                    PRTActivity.this.finish();
                } else {
                    Toast.makeText(PRTActivity.this, "Enter Required Metrics", Toast.LENGTH_SHORT).show();
                }


            }

        });


    }


    private String formatTimer() {
        String minutesTXT, secondsTXT;
        if (minutes < 10) {
            minutesTXT = "0" + Integer.toString(minutes);
        } else {
            minutesTXT = Integer.toString(minutes);
        }
        if (seconds < 10) {
            secondsTXT = "0" + Integer.toString(seconds);
        } else {
            secondsTXT = Integer.toString(seconds);
        }
        String s = minutesTXT + ":" + secondsTXT;
        runLBL.setText(s);

        return s;
    }

    private void calculateScore() {
        if (maleRDO.isChecked()) {
            if (altitudeCheckBox.isChecked()) {
                if (cardio.equals("500m Swim")) {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.altSwim500Male17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.altSwim500Male20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.altSwim500Male25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.altSwim500Male30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.altSwim500Male35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.altSwim500Male40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.altSwim500Male45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.altSwim500Male50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.altSwim500Male55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.altSwim500Male60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.altSwim500Male65);
                    }
                } else if (cardio.equals("450m Swim")) {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.altSwim450Male17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.altSwim450Male20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.altSwim450Male25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.altSwim450Male30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.altSwim450Male35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.altSwim450Male40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.altSwim450Male45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.altSwim450Male50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.altSwim450Male55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.altSwim450Male60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.altSwim450Male65);
                    }
                } else {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.altRunMale17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.altRunMale20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.altRunMale25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.altRunMale30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.altRunMale35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.altRunMale40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.altRunMale45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.altRunMale50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.altRunMale55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.altRunMale60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.altRunMale65);
                    }
                }
            } else {
                if (cardio.equals("500m Swim")) {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.swim500Male17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.swim500Male20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.swim500Male25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.swim500Male30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.swim500Male35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.swim500Male40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.swim500Male45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.swim500Male50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.swim500Male55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.swim500Male60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.swim500Male65);
                    }
                } else if (cardio.equals("450m swim")) {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.swim450Male17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.swim450Male20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.swim450Male25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.swim450Male30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.swim450Male35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.swim450Male40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.swim450Male45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.swim450Male50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.swim450Male55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.swim450Male60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.swim450Male65);
                    }
                } else {
                    if (age < 20) {
                        calculateMale(mData.pushupMale17, mData.situpMale17, mData.runMale17);
                    } else if (age < 25) {
                        calculateMale(mData.pushupMale20, mData.situpMale20, mData.runMale20);
                    } else if (age < 30) {
                        calculateMale(mData.pushupMale25, mData.situpMale25, mData.runMale25);
                    } else if (age < 35) {
                        calculateMale(mData.pushupMale30, mData.situpMale30, mData.runMale30);
                    } else if (age < 40) {
                        calculateMale(mData.pushupMale35, mData.situpMale35, mData.runMale35);
                    } else if (age < 45) {
                        calculateMale(mData.pushupMale40, mData.situpMale40, mData.runMale40);
                    } else if (age < 50) {
                        calculateMale(mData.pushupMale45, mData.situpMale45, mData.runMale45);
                    } else if (age < 55) {
                        calculateMale(mData.pushupMale50, mData.situpMale50, mData.runMale50);
                    } else if (age < 60) {
                        calculateMale(mData.pushupMale55, mData.situpMale55, mData.runMale55);
                    } else if (age < 65) {
                        calculateMale(mData.pushupMale60, mData.situpMale60, mData.runMale60);
                    } else {
                        calculateMale(mData.pushupMale65, mData.situpMale65, mData.runMale65);
                    }
                }
            }


        } else {
            if (altitudeCheckBox.isChecked()) {
                if (cardio.equals("500m Swim")) {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.altSwim500Female17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.altSwim500Female20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.altSwim500Female25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.altSwim500Female30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.altSwim500Female35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.altSwim500Female40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.altSwim500Female45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.altSwim500Female50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.altSwim500Female55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.altSwim500Female60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.altSwim500Female65);
                    }
                } else if (cardio.equals("450m Swim")) {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.altSwim450Female17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.altSwim450Female20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.altSwim450Female25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.altSwim450Female30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.altSwim450Female35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.altSwim450Female40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.altSwim450Female45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.altSwim450Female50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.altSwim450Female55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.altSwim450Female60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.altSwim450Female65);
                    }
                } else {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.altRunFemale17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.altRunFemale20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.altRunFemale25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.altRunFemale30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.altRunFemale35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.altRunFemale40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.altRunFemale45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.altRunFemale50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.altRunFemale55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.altRunFemale60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.altRunFemale65);
                    }
                }
            } else {
                if (cardio.equals("500m Swim")) {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.swim500Female17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.swim500Female20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.swim500Female25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.swim500Female30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.swim500Female35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.swim500Female40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.swim500Female45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.swim500Female50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.swim500Female55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.swim500Female60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.swim500Female65);
                    }
                } else if (cardio.equals("450m swim")) {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.swim450Female17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.swim450Female20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.swim450Female25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.swim450Female30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.swim450Female35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.swim450Female40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.swim450Female45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.swim450Female50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.swim450Female55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.swim450Female60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.swim450Female65);
                    }
                } else {
                    if (age < 20) {
                        calculateFemale(mData.pushupFemale17, mData.situpFemale17, mData.runFemale17);
                    } else if (age < 25) {
                        calculateFemale(mData.pushupFemale20, mData.situpFemale20, mData.runFemale20);
                    } else if (age < 30) {
                        calculateFemale(mData.pushupFemale25, mData.situpFemale25, mData.runFemale25);
                    } else if (age < 35) {
                        calculateFemale(mData.pushupFemale30, mData.situpFemale30, mData.runFemale30);
                    } else if (age < 40) {
                        calculateFemale(mData.pushupFemale35, mData.situpFemale35, mData.runFemale35);
                    } else if (age < 45) {
                        calculateFemale(mData.pushupFemale40, mData.situpFemale40, mData.runFemale40);
                    } else if (age < 50) {
                        calculateFemale(mData.pushupFemale45, mData.situpFemale45, mData.runFemale45);
                    } else if (age < 55) {
                        calculateFemale(mData.pushupFemale50, mData.situpFemale50, mData.runFemale50);
                    } else if (age < 60) {
                        calculateFemale(mData.pushupFemale55, mData.situpFemale55, mData.runFemale55);
                    } else if (age < 65) {
                        calculateFemale(mData.pushupFemale60, mData.situpFemale60, mData.runFemale60);
                    } else {
                        calculateFemale(mData.pushupFemale65, mData.situpFemale65, mData.runFemale65);
                    }
                }
            }

        }


    }


    private void calculateMale(int[] pushupMale, int[] situpMale, int[] runMale) {
        int totalScore = 0;
        int pushupScore = 0;
        int situpScore = 0;
        int cardioScore = 0;
        boolean pushupsFailed = true;
        boolean situpsFailed = true;
        boolean cardioFailed = true;

        if (pushupchanged) {
            for (int i = 11; i >= 0; i--) {
                if (pushups >= pushupMale[i]) {
                    totalScore += Scores[i];
                    pushupScore = Scores[i];
                    pushupScoreLBL.setText(getCategory(pushupScore));
                    pushupsFailed = false;
                    break;
                }
            }
            if (pushupsFailed) {
                pushupScoreLBL.setText(R.string.score_failure);
            }
        }

        if (situpchanged) {
            for (int i = 11; i >= 0; i--) {
                if (situps >= situpMale[i]) {
                    totalScore += Scores[i];
                    situpScore = Scores[i];
                    situpScoreLBL.setText(getCategory(situpScore));
                    situpsFailed = false;
                    break;
                }
            }
            if (situpsFailed) {
                situpScoreLBL.setText(R.string.score_failure);
            }
        }

        if (runchanged) {
            for (int i = 11; i >= 0; i--) {
                if (runtime <= runMale[i]) {
                    totalScore += Scores[i];
                    cardioScore = Scores[i];
                    runScoreLBL.setText(getCategory(cardioScore));
                    cardioFailed = false;
                    break;
                }
            }
            if (cardioFailed) {
                runScoreLBL.setText(R.string.score_failure);
            }
        }


        if (!pushupsFailed && !situpsFailed && !cardioFailed && changed()) {

            if ((totalScore / 3) < 45) {
                scoreLBL.setBackgroundColor(Color.RED);
                scoreLBL.setTextColor(Color.BLACK);
                scoreLBL.getBackground().setAlpha(100);
            } else {
                scoreLBL.setBackgroundColor(Color.GREEN);
                scoreLBL.setTextColor(Color.BLACK);
                scoreLBL.getBackground().setAlpha(100);
            }

            scoreLBL.setText(getCategory(totalScore / 3));

        } else if (changed()) {
            scoreLBL.setText(R.string.score_failure);
            scoreLBL.setBackgroundColor(Color.RED);
            scoreLBL.setTextColor(Color.BLACK);
            scoreLBL.getBackground().setAlpha(100);
        }


    }


    private void calculateFemale(int[] pushupFemale, int[] situpFemale, int[] runFemale) {
        int totalScore = 0;
        int pushupScore = 0;
        int situpScore = 0;
        int runScore = 0;
        boolean fail0 = true;//pushups
        boolean fail1 = true;//situps
        boolean fail2 = true;//run

        if (pushupchanged) {


            for (int i = 11; i >= 0; i--) {
                if (pushups >= pushupFemale[i]) {
                    totalScore += Scores[i];
                    pushupScore = Scores[i];
                    pushupScoreLBL.setText(getCategory(pushupScore));
                    fail0 = false;
                    break;
                }
            }
            if (fail0) {
                pushupScoreLBL.setText(getString(R.string.score_failure));
            }
        }

        if (situpchanged) {
            for (int i = 11; i >= 0; i--) {
                if (situps >= situpFemale[i]) {
                    totalScore += Scores[i];
                    situpScore = Scores[i];
                    situpScoreLBL.setText(getCategory(situpScore));
                    fail1 = false;
                    break;
                }
            }
            if (fail1) {
                situpScoreLBL.setText(R.string.score_failure);
            }
        }

        if (runchanged) {
            for (int i = 11; i >= 0; i--) {
                if (runtime <= runFemale[i]) {
                    totalScore += Scores[i];
                    runScore = Scores[i];
                    runScoreLBL.setText(getCategory(runScore));
                    fail2 = false;
                    break;
                }
            }
            if (fail2) {
                runScoreLBL.setText(R.string.score_failure);
            }
        }

        if (!fail0 && !fail1 && !fail2 && changed()) {

            if ((totalScore / 3) < 45) {
                scoreLBL.setBackgroundColor(Color.RED);
                scoreLBL.setTextColor(Color.BLACK);
                scoreLBL.getBackground().setAlpha(100);
            } else {
                scoreLBL.setBackgroundColor(Color.GREEN);
                scoreLBL.setTextColor(Color.BLACK);
                scoreLBL.getBackground().setAlpha(100);
            }
            scoreLBL.setText(getCategory(totalScore / 3));

        } else if (changed()) {
            scoreLBL.setText(R.string.score_failure);
            scoreLBL.setBackgroundColor(Color.RED);
            scoreLBL.setTextColor(Color.BLACK);
            scoreLBL.getBackground().setAlpha(100);
        }
    }

    private boolean changed() {
        boolean changed = false;
        if (runchanged && situpchanged && pushupchanged) {
            changed = true;
        }
        return changed;
    }

    public void onResume() {
        super.onResume();
    }

    public void onPause() {
        super.onPause();
    }

    public void update(Observable observable, Object data) {
        age = mData.getAge();
        if (age == 19) {
            ageSpinner.setSelection(0);
        } else if (age == 24) {
            ageSpinner.setSelection(1);
        } else if (age == 29) {
            ageSpinner.setSelection(2);
        } else if (age == 34) {
            ageSpinner.setSelection(3);
        } else if (age == 39) {
            ageSpinner.setSelection(4);
        } else if (age == 44) {
            ageSpinner.setSelection(5);
        } else if (age == 49) {
            ageSpinner.setSelection(6);
        } else if (age == 54) {
            ageSpinner.setSelection(7);
        } else if (age == 59) {
            ageSpinner.setSelection(8);
        } else if (age == 64) {
            ageSpinner.setSelection(9);
        } else if (age == 65) {
            ageSpinner.setSelection(10);
        }

        femaleRDO.setChecked(!mData.getGender());
        maleRDO.setChecked(mData.getGender());


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menuRunBTN:
                cardioLBL.setText(R.string.label_cardio_run_time);
                alternate = false;
                cardio = "1.5 Mile Run";
                calculateScore();
                return true;

            case R.id.menuBikeBTN:
                cardioLBL.setText(R.string.label_cardio_bike_time);
                alternate = true;
                cardio = "Bike";
                calculateScore();
                return true;

            case R.id.menuEllipticalBTN:
                cardioLBL.setText(R.string.label_cardio_elliptical_time);
                alternate = true;
                cardio = "Elliptical";
                calculateScore();
                return true;

            case R.id.menuSwim500BTN:
                cardioLBL.setText(R.string.label_500_swim_time);
                alternate = true;
                cardio = "500m Swim";
                calculateScore();
                return true;

            case R.id.menuSwim450BTN:
                cardioLBL.setText(R.string.label_450_swim);
                alternate = true;
                cardio = "450m Swim";
                calculateScore();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public String getCategory(int score) {
        String scoreString = getString(R.string.score_failure);

        if ((score) < 45) {
            scoreString = getString(R.string.score_failure);
        } else if ((score) < 50) {
            scoreString = getString(R.string.score_probationary);
        } else if ((score) < 55) {
            scoreString = getString(R.string.score_sat_med);
        } else if ((score) < 60) {
            scoreString = getString(R.string.score_sat_high);
        } else if ((score) < 65) {
            scoreString = getString(R.string.score_good_low);
        } else if ((score) < 70) {
            scoreString = getString(R.string.score_good_med);
        } else if ((score) < 75) {
            scoreString = getString(R.string.score_good_high);
        } else if ((score) < 80) {
            scoreString = getString(R.string.score_excellent_low);
        } else if ((score) < 85) {
            scoreString = getString(R.string.score_excellent_med);
        } else if ((score) < 90) {
            scoreString = getString(R.string.score_excellent_high);
        } else if ((score) < 95) {
            scoreString = getString(R.string.score_outstanding_low);
        } else if ((score) < 100) {
            scoreString = getString(R.string.score_outstanding_med);
        } else if ((score) >= 100) {
            scoreString = getString(R.string.score_outstanding_high);
        }


        return scoreString;

    }

    private void readData() {

        try {
            FileInputStream fis = openFileInput("profile");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Profile profile = (Profile) ois.readObject();
            ois.close();
            fis.close();

            mData = new AndriosData();
            mData.setAge(profile.getAge());
            mData.setGender(profile.isMale());


        } catch (Exception e) {
            Profile profile = new Profile();
            mData = new AndriosData();
        }
    }

}
