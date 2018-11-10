package com.example.tatang.myapplication.Hellper.VolleyHellper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tatang.myapplication.Hellper.ApiConstant;
import com.example.tatang.myapplication.Hellper.Collection.UserModel;
import com.example.tatang.myapplication.Hellper.DataCollection;
import com.example.tatang.myapplication.Hellper.UserModelManager;
import com.example.tatang.myapplication.Hellper.VolleySingleton;
import com.example.tatang.myapplication.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VolleyHellper {

    private Context mContext;
    private static VolleyHellper mInstance;
    UserModel userModel;
    ProgressDialog progressDialog;


//    public static final String URL_EVIDENDM= ROOT_URL + "/Eviden_DM";


//    public TextView id_no_header, id_title_header, notfoundList;
//    TextView id_title_header, notfoundList;
//    TextView id_description;
//    TextView id_status_eviden, id_doc_upload, id_tanggal, txt_upload_eviden;
//    TextView id_mli_upload, id_berlaku;
//    LinearLayout id_upload, id_go_update, id_lv_status_eviden;





    private VolleyHellper(Context context) {
        mContext = context;
    }

    public static synchronized VolleyHellper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new VolleyHellper(context);
        }
        return mInstance;
    }

    public void getUserAutentication(final ArrayList<String> arrayList) {

        StringRequest LoginArea = new StringRequest(Request.Method.POST, ApiConstant.URL_LOGIN1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Respos",""+response);
                try {
                    JSONObject obj = new JSONObject(response);

                    if (obj.getString("status").toString().equals("success")) {
                        JSONArray result = obj.getJSONArray("message");
//                        progressDialog.dismiss();
                        for (int i = 0; i <= result.length(); i++) {
                            JSONObject jsons = result.getJSONObject(i);
                            userModel = new UserModel(
                                    jsons.getString("id_user"),
                                    jsons.optString("nama_unit"),
                                    jsons.getString("username"),
                                    jsons.getString("password"),
                                    jsons.getString("nama_user"),
                                    jsons.getString("email"),
                                    jsons.optString("nama_bidang"),
                                    jsons.optString("id_bidang"),
                                    jsons.optString("id_subid"),
                                    jsons.optString("nama_subid"),
                                    jsons.getString("status_akun"),
                                    jsons.getString("status_user"),
                                    jsons.getString("last_login"),
                                    jsons.getString("login"),
                                    jsons.getString("deadline"),
                                    jsons.getString("lock_upload"),
                                    jsons.getString("photo"),
                                    jsons.getString("tujuan_jabatan"),
                                    jsons.getString("cookie"));



                            UserModelManager.getInstance(mContext.getApplicationContext()).UserLogin(userModel);
                            Intent intent = new Intent(mContext, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    } else {
                        Toast.makeText(mContext, "Username & Password Salah", Toast.LENGTH_SHORT).show();
//                        progressDialog.dismiss();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error Listener" + error);
//                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                int sArrayList = arrayList.size();
                for (int i = 0; i < sArrayList; i++) {
                    map.put(DataCollection.KEY_username, arrayList.get(0).toString());
                    map.put(DataCollection.KEY_password, arrayList.get(1).toString());
                }
                return map;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(LoginArea);
    }

    public void getUpdateProfil(final ArrayList<String> arrayList) {
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading, Mohon Tunggu...");
        progressDialog.show();
        StringRequest UpdateProfil = new StringRequest(Request.Method.PUT, ApiConstant.URL_UPDATEPROFIL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status").toString().equals("success")) {
                        if (obj.getString("message").toString().equals("true")) {
//                            UserModelManager.getInstance(mContext.getApplicationContext()).UserLogin(userModel);
                            UserModelManager.getInstance(mContext.getApplicationContext()).LogOut();
                        }
                    } else {
                        Toast.makeText(mContext, "Email Tidak Terdaftar", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("UP", "ERROR LISTERNER" + error);
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                int sArrayList = arrayList.size();
                for (int i = 0; i < sArrayList; i++) {
                    map.put(DataCollection.KEY_id_user, arrayList.get(0).toString());
                    map.put(DataCollection.KEY_nama_user, arrayList.get(1).toString());
                    map.put(DataCollection.KEY_email, arrayList.get(2).toString());
                    map.put(DataCollection.KEY_username, arrayList.get(3).toString());
                    map.put(DataCollection.KEY_password, arrayList.get(4).toString());
                }
                return map;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(UpdateProfil);
    }

    public void getChangePassword(final String emailUser){
        progressDialog = new ProgressDialog(mContext);
        progressDialog.setMessage("Loading, Mohon Tunggu...");
        progressDialog.show();
        StringRequest changePassword = new StringRequest(Request.Method.PUT, ApiConstant.URL_REPASSWORD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status").toString().equals("success")) {
                        if (obj.getString("message").toString().equals("true")) {
                            Toast.makeText(mContext, "Password Berhasil Di Buat Check Email", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("UP", "DATA NOT FOUND");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("UP", "ERROR LISTERNER" + error);
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                    map.put(DataCollection.KEY_email, emailUser.toString());
                return map;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(changePassword);
    }
}
