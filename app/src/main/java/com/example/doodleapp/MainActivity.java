package com.example.doodleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        paintView = (PaintView) findViewById(R.id.paintView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_edit:
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);

                dialog.setTitle("Settings");
                dialog.setMessage("Color & width");

                LinearLayout dialogLayout = new LinearLayout(this);
                dialogLayout.setPadding(20, 8, 20, 8);
                dialogLayout.setOrientation(LinearLayout.VERTICAL);

                final TextView alphaText = new TextView(this);
                alphaText.setText("Alpha - " + paintView.alpha);
                final TextView redText = new TextView(this);
                redText.setText("Red - " + paintView.red);
                final TextView greenText = new TextView(this);
                greenText.setText("Green - " + paintView.green);
                final TextView blueText = new TextView(this);
                blueText.setText("Blue - " + paintView.blue);
                final TextView thicknessText = new TextView(this);
                thicknessText.setText("Thickness - " + paintView.thickness);

                final SeekBar alphaSeakbar = new SeekBar(this);
                final SeekBar redSeeker = new SeekBar(this);
                final SeekBar greenSeeker = new SeekBar(this);
                final SeekBar blueSeeker = new SeekBar(this);
                final SeekBar thichkNessSeeker = new SeekBar(this);

                View colorView = new View(this);
                colorView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, 5 * 4));

                alphaSeakbar.setMax(255);
                alphaSeakbar.setProgress(paintView.alpha);
                alphaSeakbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        colorView.setBackgroundColor(Color.argb(
                                alphaSeakbar.getProgress(),
                                redSeeker.getProgress(),
                                greenSeeker.getProgress(),
                                blueSeeker.getProgress()));
                        alphaText.setText("Alpha - " + paintView.alpha);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                redSeeker.setMax(255);
                redSeeker.setProgress(paintView.red);
                redSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        colorView.setBackgroundColor(Color.argb(
                                alphaSeakbar.getProgress(),
                                redSeeker.getProgress(),
                                greenSeeker.getProgress(),
                                blueSeeker.getProgress()));
                        redText.setText("Red - " + paintView.red);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                greenSeeker.setMax(255);
                greenSeeker.setProgress(paintView.green);
                greenSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        colorView.setBackgroundColor(Color.argb(
                                alphaSeakbar.getProgress(),
                                redSeeker.getProgress(),
                                greenSeeker.getProgress(),
                                blueSeeker.getProgress()));
                        greenText.setText("Green - " + paintView.green);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                blueSeeker.setMax(255);
                blueSeeker.setProgress(paintView.blue);
                blueSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        colorView.setBackgroundColor(Color.argb(
                                alphaSeakbar.getProgress(),
                                redSeeker.getProgress(),
                                greenSeeker.getProgress(),
                                blueSeeker.getProgress()));
                        blueText.setText("Blue - " + paintView.red);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                thichkNessSeeker.setMax(25);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    thichkNessSeeker.setMin(1);
                }
                thichkNessSeeker.setProgress((int) paintView.thickness);
                thichkNessSeeker.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        colorView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, progress * 4));
                        thicknessText.setText("Thickness - " + thichkNessSeeker.getProgress());
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                dialogLayout.addView(alphaText);
                dialogLayout.addView(alphaSeakbar);

                dialogLayout.addView(redText);
                dialogLayout.addView(redSeeker);

                dialogLayout.addView(greenText);
                dialogLayout.addView(greenSeeker);

                dialogLayout.addView(blueText);
                dialogLayout.addView(blueSeeker);

                dialogLayout.addView(thicknessText);
                dialogLayout.addView(thichkNessSeeker);

                View marginView = new View(this);
                marginView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 40));
                colorView.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int) paintView.thickness * 4));
                colorView.setBackgroundColor(Color.rgb(redSeeker.getProgress(),
                        greenSeeker.getProgress(), blueSeeker.getProgress()));

                dialogLayout.addView(marginView);
                dialogLayout.addView(colorView);
                dialog.setView(dialogLayout);

                dialog.setNeutralButton(
                        "SET",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                paintView.setValue(alphaSeakbar.getProgress(), redSeeker.getProgress(),
                                        greenSeeker.getProgress(), blueSeeker.getProgress(), thichkNessSeeker.getProgress());

                            }
                        });
                dialog.show();
                return true;

            case R.id.menu_delete:
                paintView.clearCanvas();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }
}