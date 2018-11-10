package com.example.tatang.myapplication.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.tatang.myapplication.Hellper.VolleyHellper.VolleyHellper;
import com.example.tatang.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tatang.it on 02/07/2018.
 */

public class Account_Repassword extends AppCompatActivity {

    @BindView(R.id.edt_email)
    EditText edt_email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repassword);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Reset Password");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                super.onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @OnClick(R.id.id_repassword)
    public void goRepassword() {
        VolleyHellper.getInstance(getApplicationContext()).getChangePassword(edt_email.getText().toString());
    }

}
