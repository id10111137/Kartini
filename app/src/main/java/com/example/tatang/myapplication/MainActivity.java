package com.example.tatang.myapplication;

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
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.tatang.myapplication.Account.Account_Login;
import com.example.tatang.myapplication.Hellper.Adapter.SqlLiteAdapter;
import com.example.tatang.myapplication.Hellper.ApiConstant;
import com.example.tatang.myapplication.Hellper.Collection.UserModel;
import com.example.tatang.myapplication.Hellper.UserModelManager;
import com.example.tatang.myapplication.MainHomes.Activity_Homes;
import com.example.tatang.myapplication.MainHomes.Profil.Activity_Profil;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    Fragment fragment;

    private String selectedFilePath;
    ProgressDialog progressDialog;
    JSONObject jsonObject;
    UserModel userModel;
    TextView txt_namesheader;

    private int REQUEST_CAMERA = 0, SELECT_FILE = 1, PICK_FILE_REQUEST = 2;
    ImageView imgProfile;
    SqlLiteAdapter sqlLiteAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jsonObject = new JSONObject();
        userModel = UserModelManager.getInstance(MainActivity.this).getUser();

        sqlLiteAdapter = new SqlLiteAdapter(this);


        try {
            if (savedInstanceState == null) {
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.container_activity_homes, new Activity_Homes());
                fragmentTransaction.commit();
            }

            if (!UserModelManager.getInstance(getApplicationContext()).isLoggedIn()) {
                startActivity(new Intent(this, Account_Login.class));
                finish();
            }

            getActionBar().setTitle("Kartini 99 V.2.0");
        } catch (Exception e) {
            e.printStackTrace();
        }


        dl = (DrawerLayout) findViewById(R.id.drawerLayout);
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_close);
        NavigationView nv = findViewById(R.id.nv);


        View navHeader = nv.getHeaderView(0);
        txt_namesheader = (TextView) navHeader.findViewById(R.id.id_nameheader);
        txt_namesheader.setText(userModel.getNama_user());
        imgProfile = (ImageView) navHeader.findViewById(R.id.imageView);
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Take_Galley();
            }
        });


        if (userModel.getPhoto() != null) {
            Picasso.with(this)
                    .load("http://mistertrainer.com/kartini-api/file_uploads/photo/" + userModel.getPhoto())
                    .centerCrop()
                    .fit()
                    .into(imgProfile);
        } else {
            imgProfile.setImageDrawable(getResources().getDrawable(R.drawable.service_desk));
        }


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                fragment = null;

                switch (id) {
                    case R.id.menu_home:
                        fragment = new Activity_Homes();
                        break;
                    case R.id.menu_profil:
                        fragment = new Activity_Profil();
                        break;
                    case R.id.menu_logout:
                        UserModelManager.getInstance(getApplicationContext()).LogOut();
                        break;
                    default:
                        fragment = new Activity_Homes();
                        break;
                }

                goDestination(fragment);
                dl = findViewById(R.id.drawerLayout);
                dl.closeDrawer(GravityCompat.START);
                return true;

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return t.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    private void goDestination(Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction =
                    getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_activity_homes, fragment);
            fragmentTransaction.commit();
        }
    }

    private Fragment gomaung(Fragment maung) {
        return maung;
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
            bitmap = MediaStore.Images.Media.getBitmap(MainActivity.this.getContentResolver(), filePath);
            selectedFilePath = getPathImage(bitmap);
            imgProfile.setImageBitmap(bitmap);
            saveImage(getFileName(filePath));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveImage(String Image) {
        PostUpload_Image(Image, userModel.getId_user().toString(), selectedFilePath);
    }


    private void PostUpload_Image(final String name_image, final String id_user, String ImageOrFile) {
        progressDialog = new ProgressDialog(MainActivity.this);
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
                        Toast.makeText(MainActivity.this, "Sukses Ubah Gambar", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        long id = sqlLiteAdapter.insertData(id_user, name_image);
                        if (id <= 0) {
                            Toast.makeText(MainActivity.this, "Insertion Unsuccessful", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Insertion successful", Toast.LENGTH_SHORT).show();
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
        Volley.newRequestQueue(MainActivity.this).add(jsonObjectRequest);
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
            Cursor cursor = MainActivity.this.getContentResolver().query(uri, null, null, null, null);
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

}
