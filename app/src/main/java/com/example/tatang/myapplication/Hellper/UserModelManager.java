package com.example.tatang.myapplication.Hellper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.tatang.myapplication.Account.Account_Login;
import com.example.tatang.myapplication.Hellper.Collection.UserModel;
import com.example.tatang.myapplication.MainActivity;


/**
 * Created by tatang.it on 11/24/2017.
 */

public class UserModelManager {

    private static UserModelManager mInstance;
    private static Context mContext;

    private UserModelManager(Context context) {
        mContext = context;
    }

    public static synchronized UserModelManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserModelManager(context);
        }
        return mInstance;
    }

    public void UserLogin(UserModel userModel) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(DataCollection.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(DataCollection.KEY_id_user, userModel.getId_user());
        editor.putString(DataCollection.KEY_nama_unit, userModel.getNama_unit());
        editor.putString(DataCollection.KEY_username, userModel.getUsername());
        editor.putString(DataCollection.KEY_password, userModel.getPassword());
        editor.putString(DataCollection.KEY_nama_user, userModel.getNama_user());
        editor.putString(DataCollection.KEY_email, userModel.getEmail());
        editor.putString(DataCollection.KEY_nama_bidang, userModel.getNama_bidang());
        editor.putString(DataCollection.KEY_id_bid, userModel.getId_bidang());
        editor.putString(DataCollection.KEY_id_subid, userModel.getId_subid());
        editor.putString(DataCollection.KEY_nama_subid, userModel.getNama_subid());
        editor.putString(DataCollection.KEY_status_akun, userModel.getStatus_akun());
        editor.putString(DataCollection.KEY_status_user, userModel.getStatus_user());
        editor.putString(DataCollection.KEY_last_login, userModel.getLast_login());
        editor.putString(DataCollection.KEY_login, userModel.getLogin());
        editor.putString(DataCollection.KEY_deadline, userModel.getDeadline());
        editor.putString(DataCollection.KEY_lock_upload, userModel.getLock_upload());
        editor.putString(DataCollection.KEY_photo, userModel.getPhoto());
        editor.putString(DataCollection.KEY_tujuan_jabatan, userModel.getTujuan_jabatan());
        editor.putString(DataCollection.KEY_cookie, userModel.getCookie());
        editor.apply();
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(DataCollection.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(DataCollection.KEY_id_user, null) != null;
    }

    public UserModel getUser() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(DataCollection.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new UserModel(
                sharedPreferences.getString(DataCollection.KEY_id_user, null),
                sharedPreferences.getString(DataCollection.KEY_nama_unit, null),
                sharedPreferences.getString(DataCollection.KEY_username, null),
                sharedPreferences.getString(DataCollection.KEY_password, null),
                sharedPreferences.getString(DataCollection.KEY_nama_user, null),
                sharedPreferences.getString(DataCollection.KEY_email, null),
                sharedPreferences.getString(DataCollection.KEY_nama_bidang, null),
                sharedPreferences.getString(DataCollection.KEY_id_bid, null),
                sharedPreferences.getString(DataCollection.KEY_id_subid, null),
                sharedPreferences.getString(DataCollection.KEY_nama_subid, null),
                sharedPreferences.getString(DataCollection.KEY_status_akun, null),
                sharedPreferences.getString(DataCollection.KEY_status_user, null),
                sharedPreferences.getString(DataCollection.KEY_last_login, null),
                sharedPreferences.getString(DataCollection.KEY_login, null),
                sharedPreferences.getString(DataCollection.KEY_deadline, null),
                sharedPreferences.getString(DataCollection.KEY_lock_upload, null),
                sharedPreferences.getString(DataCollection.KEY_photo, null),
                sharedPreferences.getString(DataCollection.KEY_tujuan_jabatan, null),
                sharedPreferences.getString(DataCollection.KEY_cookie, null));
    }

    public void LogOut() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(DataCollection.SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        editor.commit();
        Intent intent = new Intent(mContext, Account_Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public void Refresh() {
        mContext.startActivity(new Intent(mContext, MainActivity.class));
        return;
    }

}
