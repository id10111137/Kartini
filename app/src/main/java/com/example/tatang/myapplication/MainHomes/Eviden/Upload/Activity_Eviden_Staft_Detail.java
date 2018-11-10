package com.example.tatang.myapplication.MainHomes.Eviden.Upload;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
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
import com.example.tatang.myapplication.Hellper.DataCollection;
import com.example.tatang.myapplication.Hellper.FilePath;
import com.example.tatang.myapplication.Hellper.KartiniHellper;
import com.example.tatang.myapplication.Hellper.VolleySingleton;
import com.example.tatang.myapplication.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Activity_Eviden_Staft_Detail extends AppCompatActivity {

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1, PICK_FILE_REQUEST = 2;
    private static final int STORAGE_PERMISSION_CODE = 123;
    Bitmap bitmap;
    Uri filePath;
    Intent intent;
    JSONObject jsonObject;
    FilePath filePaths;

    ProgressDialog progressDialog;

    @BindView(R.id.id_priview_image)
    ImageView id_priview_image;

    @BindView(R.id.id_nomor_eviden)
    TextView id_nomor_eviden;

    @BindView(R.id.id_status_eviden)
    TextView id_status_eviden;

    @BindView(R.id.id_title_header_detail)
    TextView id_title_header_detail;

    @BindView(R.id.id_description)
    TextView id_description;

    @BindView(R.id.id_nomor_user)
    TextView id_nomor_user;

    @BindView(R.id.txt_file_name)
    TextView txt_file_name;

    @BindView(R.id.txt_eviden_staf_go)
    TextView txt_eviden_staf_go;


    ArrayList<String> list;
    String selectedFilePath;
    String NameImage;
    Integer SizeFile;
    String bobot;
    String id_prosedur;
    String id_kpi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity_eviden_upload_detail);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Upload Eviden");

        list = getIntent().getExtras().getStringArrayList("array_list");

        try {
            for (int i = 0; i <= list.size(); i++) {

                id_nomor_user.setText(list.get(7));
                id_nomor_eviden.setText(list.get(2));
                id_status_eviden.setText(list.get(5));
                id_description.setText(list.get(3));
                id_title_header_detail.setText(list.get(8));
                bobot = list.get(4);
                id_prosedur = list.get(1);
                id_kpi = list.get(0);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }


        getImagePriview(id_kpi);
        jsonObject = new JSONObject();
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        requestStoragePermission();
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


    private void getImagePriview(final String id_kpis) {

        progressDialog = new ProgressDialog(Activity_Eviden_Staft_Detail.this);
        progressDialog.setMessage("Loading, Mencari File");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiConstant.URL_EVIDEN_DOK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status").toString().equals("success")) {
                        JSONArray result = obj.getJSONArray("message");
                        for (int i = 0; i <= result.length(); i++) {
                            JSONObject jsons = result.getJSONObject(i);
                            if (jsons.getString("dokumen").toString().isEmpty() || jsons.getString("dokumen").toString() == null) {
                                Picasso.with(getApplicationContext())
                                        .load("data:image/png;base64,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")
                                        .centerCrop()
                                        .fit()
                                        .into(id_priview_image);
                                progressDialog.dismiss();
                            } else {
                                progressDialog.dismiss();
                                Picasso.with(getApplicationContext())
                                        .load("http://mistertrainer.com/kartini-api/file_uploads/eviden_staf/" + jsons.getString("dokumen").toString())
                                        .centerCrop()
                                        .error(R.drawable.ic_attach_file_black_24dp)
                                        .fit()
                                        .into(id_priview_image);
                                txt_eviden_staf_go.setText("Update");
                                txt_file_name.setVisibility(View.VISIBLE);
                                txt_file_name.setText(jsons.getString("dokumen").toString());
                            }
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Username & Password Salah", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", "" + error);
                progressDialog.dismiss();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put(DataCollection.KEY_KPI, id_kpis.toString());
                return map;
            }
        };

        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

    @OnClick(R.id.id_take_picture)
    public void take_picture() {
        Take_Camera();
    }

    @OnClick(R.id.id_gallery)
    public void take_gallery() {
        Take_Galley();
    }

    @OnClick(R.id.id_file)
    public void getUFile() {
        Take_File();
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE) {
                onSelectFromGalleryResult(data);
            } else if (requestCode == REQUEST_CAMERA) {
                onCaptureImageResult(data);
            } else if (requestCode == PICK_FILE_REQUEST) {
                onSelectFile(data);
            }
        }

    }

    private void Take_File() {
        String[] mimeTypes =
                {"application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document", // .doc & .docx
                        "application/vnd.ms-powerpoint", "application/vnd.openxmlformats-officedocument.presentationml.presentation", // .ppt & .pptx
                        "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", // .xls & .xlsx
                        "text/plain",
                        "application/pdf",
                        "application/zip"};

        intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            intent.setType("*/*");
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        } else {
            StringBuilder mimeTypesStr = new StringBuilder();
            for (String mimeType : mimeTypes) {
                mimeTypesStr.append(mimeType).append("|");
            }
            intent.setType(mimeTypesStr.substring(0, mimeTypesStr.length() - 1));
        }
        startActivityForResult(Intent.createChooser(intent, "ChooseFile"), PICK_FILE_REQUEST);

    }

    private void Take_Galley() {
        intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }

    private void Take_Camera() {
        intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_CAMERA);
    }

    @SuppressWarnings("deprecation")
    private void onSelectFile(Intent data) {
        Uri selectedFileUri = data.getData();
        filePaths = new FilePath();
        selectedFilePath = filePaths.getFileNameByUri(this, selectedFileUri);
        if (selectedFilePath != null && !selectedFilePath.equals("")) {
            return;
        } else Toast.makeText(this, "Cannot upload file to server", Toast.LENGTH_SHORT).show();
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {
        filePath = data.getData();
        try {
            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
            selectedFilePath = getPathImage(bitmap);
            id_priview_image.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        SizeFile = bitmap.getByteCount();
        NameImage = getFileName(filePath);
    }


    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
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

    @SuppressWarnings("deprecation")
    private void onCaptureImageResult(Intent data) {

        filePath = data.getData();

        try {
            bitmap = (Bitmap) data.getExtras().get("data");
            selectedFilePath = getPathImage(bitmap);
            id_priview_image.setImageBitmap(bitmap);
            id_priview_image.setMaxHeight(200);
            id_priview_image.setMaxWidth(200);

        } catch (Exception e) {
            e.printStackTrace();
        }

        String filepath = data.getExtras().get("data").toString();
        String result = filepath.substring(filepath.lastIndexOf("/") + 1, filepath.length());
        NameImage = result;

    }

    private String getPathImage(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, baos);
        byte[] imageBytes = baos.toByteArray();
        return Base64.encodeToString(imageBytes, Base64.DEFAULT);
    }

    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return;
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        //And finally ask for the permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
        }
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }

    @OnClick(R.id.id_uData)
    public void goUpload() {
        PostUpload_Image(id_prosedur, bobot, id_kpi, NameImage, id_nomor_eviden.getText().toString(), id_nomor_user.getText().toString(), selectedFilePath);
        Log.d("TampilingBoss", "ID PROSEDUR : " + id_prosedur + " bobot : " + bobot + " " + id_kpi + "  " + NameImage + "  " + id_nomor_eviden.getText().toString() + "  " + id_nomor_user.getText().toString() + "  " + selectedFilePath);
    }

    private void PostUpload_Image(String id_prosedur, String bobot, String id_kpi, String name_image, String id_eviden, String id_user, String ImageOrFile) {

        progressDialog = new ProgressDialog(Activity_Eviden_Staft_Detail.this);
        progressDialog.setMessage("Uploading, please wait...");
        progressDialog.show();

        try {

            jsonObject.put("name", name_image);
            jsonObject.put("image", ImageOrFile);
            jsonObject.put("id_eviden", id_eviden);
            jsonObject.put("id_user", id_user);
            jsonObject.put("id_kpi", id_kpi);
            jsonObject.put("id_prosedur", id_prosedur);
            jsonObject.put("bobot", bobot);
            jsonObject.put("id_tanggal", KartiniHellper.getInstance(getApplicationContext()).DateFormatOfString());


        } catch (JSONException e) {
            Log.e("JSONObject Here", e.toString());
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiConstant.URL_EVIDEN_STAFT_SAVE, jsonObject,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        progressDialog.dismiss();
                        Log.d("menampilkans", "" + jsonObject);
                        try {
                            if (jsonObject.getString("status").toString().equalsIgnoreCase("fail")) {
                                Toast.makeText(Activity_Eviden_Staft_Detail.this, "Gagal Upload", Toast.LENGTH_SHORT).show();
                            } else if (jsonObject.getString("status").toString().equalsIgnoreCase("success")) {
                                Toast.makeText(Activity_Eviden_Staft_Detail.this, "Saving Data Sukses", Toast.LENGTH_SHORT).show();
                                onBackPressed();
                                finish();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

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
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }
}
