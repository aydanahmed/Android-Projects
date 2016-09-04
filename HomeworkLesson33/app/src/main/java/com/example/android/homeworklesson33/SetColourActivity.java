package com.example.android.homeworklesson33;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class SetColourActivity extends AppCompatActivity {
    private SeekBar seekBar1;
    private SeekBar seekBar2;
    private SeekBar seekBar3;
    private TextView seekBar1TV1;
    private TextView seekBar2TV2;
    private TextView seekBar3TV3;
    private Button confirm;
    private LinearLayout layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_colour);
        seekBar1 = (SeekBar) findViewById(R.id.seekBar);
        seekBar2 = (SeekBar) findViewById(R.id.seekBar2);
        seekBar3 = (SeekBar) findViewById(R.id.seekBar3);
        seekBar1TV1 = (TextView) findViewById(R.id.seekBar1TV);
        seekBar2TV2 = (TextView) findViewById(R.id.seekBar2TV);
        seekBar3TV3 = (TextView) findViewById(R.id.seekBar3TV);
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBar1TV1.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBar2TV2.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        seekBar3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                seekBar3TV3.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
        confirm =(Button) findViewById(R.id.confirm);
        layout = (LinearLayout) findViewById(R.id.layout);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layout.setBackgroundColor(
                        0xff000000
                                + seekBar1.getProgress() * 0x10000
                                + seekBar2.getProgress() * 0x100
                                + seekBar3.getProgress()
                );
            }
        });
    }
}






