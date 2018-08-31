package com.example.uselessmachine;

import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private Button buttonSelfDestruct;
    private Switch switchUseless;
    private ConstraintLayout constraintLayout;

    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Todo wire widgets

        wireWidgets();
        setListeners();
    }

    private void wireWidgets()
    {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
        constraintLayout = findViewById(R.id.constraintLayout_main);

    }

    private void setListeners()
    {
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                startSelfDestructSequence();
            }
        });


        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(isChecked)
                {
                    startSwitchOffTimer();
                    //Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startSelfDestructSequence()
    {
        /**TODO
         * diable the button
         * start a 10 second countdown timer that updates every 10 seconds
         * Want the button to show the countdown
         * Destruct in 10...
         * Destruct in 9...
         * At the end, we're going to close the activity
         * call the finish() method
         * **/
        buttonSelfDestruct.setEnabled(false);
        startSelfDestructTimer();

    }

    private void startSelfDestructTimer()
    {
        new CountDownTimer(10000, 1000)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                Toast.makeText(MainActivity.this, "Disable in " + (millisUntilFinished + 1000)/1000 + "..." , Toast.LENGTH_SHORT).show();
                buttonSelfDestruct.setText("Disable in " + (millisUntilFinished + 1000)/1000 + "...");

                constraintLayout.setBackgroundColor(Color.rgb(255,0,0));
            }

            @Override
            public void onFinish()
            {
                finish();
            }
        }.start();
    }


    private void startSwitchOffTimer()
    {
        new CountDownTimer(5000, 100)
        {
            @Override
            public void onTick(long millisUntilFinished)
            {
                if(!switchUseless.isChecked())
                {
                    //Log.d(TAG, "onTick: cancelling timer");
                    cancel();
                }
            }

            @Override
            public void onFinish()

            {
                //Log.d(TAG, "onFinish: switch set to false");
                switchUseless.setChecked(false);
            }
        }.start();
    }

}
