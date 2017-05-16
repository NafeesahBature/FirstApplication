package com.example.renato.firstapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etext = null;
    boolean firstStart = true;

    void startOtherActivity(){

        String message = null;

        try {

            message = etext.getText().toString();

            Intent intent = new Intent(this, OtherActivity.class);
            intent.putExtra("MainActivity_content", message);
            startActivity(intent);

        } catch (Exception exc){
            Toast.makeText(this, "Some error happened",
                    Toast.LENGTH_LONG).show();
        }
    }

    //Still not okay: changing orientation will mess this up.
    //You need to fix this!
    void startMyService(String msg){

        Intent intent = new Intent(this, BackgroundService.class);
        intent.putExtra("msg_to_service", msg);
        startService(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (firstStart){
            Intent intent = new Intent(this, BackgroundService.class);
            intent.putExtra("msg_to_service", "First start");
            startService(intent);
            firstStart = false;
        }

        etext = (EditText) findViewById(R.id.editText); //remove this to present NullPointerException

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Snackbar.make(view, "Button anything", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();
                startMyService("Button pressed in MainActivity");
                startOtherActivity();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, BackgroundService.class));
        super.onDestroy();
    }
}
