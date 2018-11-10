package com.example.tatang.myapplication.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;

import com.example.tatang.myapplication.Hellper.UserModelManager;
import com.example.tatang.myapplication.Hellper.VolleyHellper.VolleyHellper;
import com.example.tatang.myapplication.MainActivity;
import com.example.tatang.myapplication.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by tatang.it on 02/07/2018.
 */

public class Account_Login extends AppCompatActivity {

    @BindView(R.id.edt_username)
    EditText edt_username;
    @BindView(R.id.edt_password)
    EditText edt_password;
    Intent intent;

    ArrayList <String> arrayListUserAutentication;

    private static final String TAG;

    static {
        TAG = "MainLogin";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        void_MainLogin();
        ButterKnife.bind(this);
    }

    private void void_MainLogin() {
        if (UserModelManager.getInstance(getApplicationContext()).isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }
    }

    @OnClick(R.id.id_login)
    public void goLogin() {
        if (TextUtils.isEmpty(edt_username.getText().toString())) {
            edt_username.setError("username kosong");
            edt_username.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(edt_password.getText().toString())) {
            edt_password.setError("password kosong");
            edt_password.requestFocus();
            return;
        }
        arrayListUserAutentication =new ArrayList<>(2);
        arrayListUserAutentication.add(edt_username.getText().toString());
        arrayListUserAutentication.add(edt_password.getText().toString());
        VolleyHellper.getInstance(getApplicationContext()).getUserAutentication(arrayListUserAutentication);

    }

    @OnClick(R.id.go_repassword)
    public void goRepassword() {
        intent = new Intent(this, Account_Repassword.class);
        startActivity(intent);
    }

}
