package com.sauga960am.musicradioapp;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button play, pause , stop;
    MediaPlayer mediaPlayer;
    int paused_position;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ExpandableListView listView = (ExpandableListView) findViewById(R.id.list_drop_down);
        EditText filter = (EditText) findViewById(R.id.search_filter);

        ArrayList<String> names = new ArrayList<>();
        names.add("The Braying Mule");

        adapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, names);
        listView.setAdapter(adapter);
        filter.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.this.adapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        play=(Button)findViewById(R.id.play_btn);
        pause=(Button)findViewById(R.id.pause_btn);
        stop=(Button)findViewById(R.id.stop_btn);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch(view.getId()){
            case R.id.play_btn:
                if(mediaPlayer==null){
                    mediaPlayer=MediaPlayer.create(getApplicationContext(), R.raw.mulesong);
                    mediaPlayer.start();
                }

                else if(!mediaPlayer.isPlaying()){
                    mediaPlayer.seekTo(paused_position);
                    mediaPlayer.start();
                }
                break;
            case R.id.pause_btn:
                if(mediaPlayer != null){
                    mediaPlayer.pause();
                    paused_position = mediaPlayer.getCurrentPosition();
                }
                break;
            case R.id.stop_btn:
                if(mediaPlayer != null){
                    mediaPlayer.stop();
                    mediaPlayer = null;
                }
                break;
        }
    }
}
