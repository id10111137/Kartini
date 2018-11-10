package com.example.tatang.myapplication.Account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tatang.myapplication.R;

import butterknife.ButterKnife;

/**
 * Created by tatang.it on 04/07/2018.
 */

public class NoConnection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noconnection);
        ButterKnife.bind(this);
        getSupportActionBar().hide();
    }
}
