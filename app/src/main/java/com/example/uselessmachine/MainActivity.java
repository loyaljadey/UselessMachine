package com.example.uselessmachine;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    private Button buttonSelfDestruct;
    private Switch switchUseless;

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

    }

    private void setListeners()
    {
        //Todo self destruct button

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
                else
                {
                    Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
