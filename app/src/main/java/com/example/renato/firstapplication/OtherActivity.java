package com.example.renato.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OtherActivity extends AppCompatActivity {

    TextView tview = null;
    Intent intent = null;
    String message = null;

    void startMyService(String msg){

        Intent intent = new Intent(this, BackgroundService.class);
        intent.putExtra("msg_to_service", msg);
        startService(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (tview == null)
            tview = (TextView) findViewById(R.id.textView);

        if (intent == null) {
            intent = getIntent();
        }

        if (message == null) {
            message = intent.getStringExtra("MainActivity_content");
        }

        tview.setText(message);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Button anything", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                startMyService("Button pressed in OtherActivity");
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is the other activity", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
