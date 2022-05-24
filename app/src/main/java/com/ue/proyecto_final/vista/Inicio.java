package com.ue.proyecto_final.vista;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.ue.proyecto_final.MainActivity;
import com.ue.proyecto_final.R;

import java.util.Timer;
import java.util.TimerTask;

public class Inicio extends AppCompatActivity {

    public static final int DELAY = 4500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        this.taskBegin();
    }

    private void taskBegin(){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(Inicio.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, DELAY);
    }
}