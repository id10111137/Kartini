package com.example.tatang.myapplication.MainHomes.Profil;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tatang.myapplication.Hellper.ApiConstant;
import com.example.tatang.myapplication.Hellper.Collection.UserModel;
import com.example.tatang.myapplication.Hellper.DataCollection;
import com.example.tatang.myapplication.Hellper.UserModelManager;
import com.example.tatang.myapplication.Hellper.VolleyHellper.VolleyHellper;
import com.example.tatang.myapplication.Hellper.VolleySingleton;
import com.example.tatang.myapplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by tatang.it on 04/07/2018.
 */

public class Activity_Profil extends Fragment {

    UserModel userModel;
    ArrayList<String> arrayListUserAutentication;

    @BindView(R.id.id_name_user)
    TextView id_name_user;
    @BindView(R.id.id_email)
    TextView id_email;
    @BindView(R.id.id_username)
    TextView id_username;
    @BindView(R.id.txt_bidang)
    TextView txt_bidang;
    @BindView(R.id.txt_subid)
    TextView txt_subid;

    JSONObject jsonObject;

    @BindView(R.id.id_ly_txtprofil)
    LinearLayout id_ly_txtprofil;
    @BindView(R.id.id_lv_edt_profil)
    LinearLayout id_lv_edt_profil;
    @BindView(R.id.id_lv_goUpdtae)
    LinearLayout id_lv_goUpdtae;
    @BindView(R.id.id_lv_Update)
    LinearLayout id_lv_Update;

    @BindView(R.id.id_edt_user)
    EditText id_edt_user;
    @BindView(R.id.id_edt_nama)
    EditText id_edt_nama;
    @BindView(R.id.id_edt_email)
    EditText id_edt_email;
    @BindView(R.id.id_edt_username)
    EditText id_edt_username;


    @BindView(R.id.imageView)
    CircleImageView imageView;
    private String selectedFilePath;
    ProgressDialog progressDialog;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1, PICK_FILE_REQUEST = 2;


    public Activity_Profil() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_activity_profil, container, false);
        ButterKnife.bind(this, root);
        userModel = UserModelManager.getInstance(getContext()).getUser();
        id_name_user.setText(userModel.getNama_user());
        id_email.setText(userModel.getEmail());
        id_username.setText(userModel.getUsername());
        txt_bidang.setText(userModel.getNama_bidang());
        txt_subid.setText(userModel.getNama_subid());
        getPhotos(userModel.getId_user());
        jsonObject = new JSONObject();
        GetData(true);

        if (userModel.getPhoto() != null) {
            Picasso.with(this.getContext())
                    .load("http://mistertrainer.com/kartini-api/file_uploads/photo/" + userModel.getPhoto())
                    .centerCrop()
                    .fit()
                    .into(imageView);
        }else{
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.service_desk));
        }


        return root;
    }

    public void getPhotos(final String IdUser) {
        StringRequest ChangeProfil = new StringRequest(Request.Method.POST, ApiConstant.URL_GETCHANGEPTOHO1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status").toString().equals("success")) {
                        JSONArray result = obj.getJSONArray("message");
                        for (int i = 0; i <= result.length(); i++) {
                            JSONObject jsons = result.getJSONObject(i);
                            userModel.setPhoto(jsons.getString("photo"));
                        }
                    } else {
                        Toast.makeText(getContext(), "Username & Password Salah", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error Listener" + error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(DataCollection.KEY_id_user, IdUser);
                return map;
            }
        };
        VolleySingleton.getInstance(getContext()).addToRequestQueue(ChangeProfil);
    }

    public static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            BigInteger md5Data = new BigInteger(1, md.digest(input.getBytes()));
            return String.format("%032X", md5Data);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.imageView)
    public void GetImage() {
        Take_Galley();
    }

    private void Take_Galley() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            onSelectFromGalleryResult(data);
        }

    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        Uri filePath = data.getData();
        ContentResolver contentResolver = null;
        try {
            Bitmap bitmap;
            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
            selectedFilePath = getPathImage(bitmap);
            imageView.setImageBitmap(bitmap);
            saveImage(getFileName(filePath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveImage(String Image) {
        PostUpload_Image(Image, userModel.getId_user().toString(), selectedFilePath);
    }


    private void PostUpload_Image(String name_image, String id_user, String ImageOrFile) {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Change Photo Profil, please wait...");
        progressDialog.show();

        try {
            jsonObject.put("name", name_image);
            jsonObject.put("id_user", id_user);
            jsonObject.put("image", ImageOrFile);
        } catch (JSONException e) {
            Log.e("JSONObject Here", e.toString());
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiConstant.URL_PHOTOPROFILONE, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Log.e("Message from server", jsonObject.toString());
                        progressDialog.dismiss();
                        Toast.makeText(getContext(), "Sukses Ubah Gambar, Silahkan Logout", Toast.LENGTH_SHORT).show();
                        UserModelManager.getInstance(getContext()).LogOut();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.e("Message from server", volleyError.toString());
                progressDialog.dismiss();

            }
        }) {

            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };
        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getContext()).add(jsonObjectRequest);
    }


    private String getPathImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                assert cursor != null;
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }

    @OnClick(R.id.id_go_update)
    public void aktifUpdate() {
        AktifFunction(true);
    }

    @OnClick(R.id.id_go_cancel)
    public void cancelUpdate() {
        AktifFunction(false);
    }

    @OnClick(R.id.id_update_profil)
    public void goUpdateProfil() {
        arrayListUserAutentication = new ArrayList<>(5);
        arrayListUserAutentication.add(id_edt_user.getText().toString());
        arrayListUserAutentication.add(id_edt_nama.getText().toString());
        arrayListUserAutentication.add(id_edt_email.getText().toString());
        arrayListUserAutentication.add(id_edt_username.getText().toString());
        VolleyHellper.getInstance(getContext()).getUpdateProfil(arrayListUserAutentication);
    }

    private void AktifFunction(Boolean choose) {
        if (choose == true) {
            id_ly_txtprofil.setVisibility(View.GONE);
            id_lv_goUpdtae.setVisibility(View.GONE);
            id_lv_edt_profil.setVisibility(View.VISIBLE);
            id_lv_Update.setVisibility(View.VISIBLE);

        } else if (choose == false) {
            id_ly_txtprofil.setVisibility(View.VISIBLE);
            id_lv_goUpdtae.setVisibility(View.VISIBLE);
            id_lv_edt_profil.setVisibility(View.GONE);
            id_lv_Update.setVisibility(View.GONE);
        }
    }

    private void GetData(Boolean GetData) {
        if (GetData == true) {
            id_edt_user.setText(userModel.getId_user());
            id_edt_nama.setText(userModel.getNama_user());
            id_edt_email.setText(userModel.getEmail());
            id_edt_username.setText(userModel.getUsername());
        } else {
            id_name_user.setText(userModel.getNama_user());
            id_email.setText(userModel.getEmail());
            id_username.setText(userModel.getUsername());
        }
    }


}
