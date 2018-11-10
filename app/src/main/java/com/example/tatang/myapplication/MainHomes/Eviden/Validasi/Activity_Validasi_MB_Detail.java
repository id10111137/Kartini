//package com.example.tatang.myapplication.MainHomes.Eviden.Validasi;
//
//import android.app.AlertDialog;
//import android.app.DatePickerDialog;
//import android.app.ProgressDialog;
//import android.content.res.Resources;
//import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.util.Log;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.DatePicker;
//import android.widget.EditText;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//import android.widget.TextView;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.example.tatang.myapplication.Hellper.Adapter.Adapter_Eviden_Validasi_Eviden;
//import com.example.tatang.myapplication.Hellper.Adapter.Adapter_Eviden_Validasi_MB_Eviden;
//import com.example.tatang.myapplication.Hellper.ApiConstant;
//import com.example.tatang.myapplication.Hellper.Collection.UserGuideMBCollection;
//import com.example.tatang.myapplication.Hellper.Collection.UserGuideUploadCollection;
//import com.example.tatang.myapplication.Hellper.Collection.UserModel;
//import com.example.tatang.myapplication.Hellper.DataCollection;
//import com.example.tatang.myapplication.Hellper.KartiniHellper;
//import com.example.tatang.myapplication.Hellper.UserModelManager;
//import com.example.tatang.myapplication.Hellper.VolleySingleton;
//import com.example.tatang.myapplication.R;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.HashMap;
//import java.util.Locale;
//import java.util.Map;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//
///**
// * Created by Administrator on 8/15/2018.
// */
//
//public class Activity_Validasi_MB_Detail  extends AppCompatActivity {
//
//    @BindView(R.id.date_validasi_eviden)
//    EditText date_validasi_eviden;
//
//    @BindView(R.id.id_running_text_validasi_eviden)
//    TextView id_running_text_validasi_eviden;
//
//    @BindView(R.id.list_eviden_validasi_eviden)
//    ListView list_eviden_validasi_eviden;
//
//    ArrayList<UserGuideMBCollection> dataModels;
//    private Adapter_Eviden_Validasi_MB_Eviden adapter_eviden_validasi_mb_eviden;
//    private PopupWindow mPopupWindow;
//    UserModel userModel;
//    View root;
//    String id_user_validasi;
//    DatePickerDialog datePickerDialog;
//    ProgressDialog progressDialog;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.home_eviden_validasi_mb);
//        ButterKnife.bind(this);
//        activity_Eviden_Validasi_Eviden();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Validasi Eviden");
//        userModel = UserModelManager.getInstance(getApplicationContext()).getUser();
//        id_user_validasi = getIntent().getExtras().getString("id_user_validasi");
//        Log.d("menampilkan",""+id_user_validasi);
//        ListValidasiPenilian();
//        date_validasi_eviden.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String text = date_validasi_eviden.getText().toString().toLowerCase(Locale.getDefault());
//                adapter_eviden_validasi_mb_eviden.filter(text);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                String text = date_validasi_eviden.getText().toString().toLowerCase(Locale.getDefault());
//                adapter_eviden_validasi_mb_eviden.filter(text);
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//    }
//
//    private void activity_Eviden_Validasi_Eviden() {
//        date_validasi_eviden.setFocusable(false);
//        id_running_text_validasi_eviden.setSelected(true);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case android.R.id.home:
//                super.onBackPressed();
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }
//
//
//    private void ListValidasiPenilian() {
//        progressDialog = new ProgressDialog(Activity_Validasi_MB_Detail.this);
//        progressDialog.setMessage("Loading, please wait...");
//        progressDialog.show();
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiConstant.URL_EVIDENKUMPULANPEGAWAIPENILAIANMB, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Log.d("menampilkan", " "+response);
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    if (obj.getString("status").toString().equals("fail")) {
//                        Log.d("Tmpilings", "Zonks");
//                        progressDialog.dismiss();
//                    } else {
//                        progressDialog.dismiss();
//                        JSONArray jsonArray = obj.getJSONArray("message");
//                        dataModels = new ArrayList<>();
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject list_info = jsonArray.getJSONObject(i);
//                            dataModels.add(new UserGuideMBCollection(
//                                    list_info.getString("id_user"),
//                                    list_info.getString("id_prosedur"),
//                                    list_info.getString("id_indikator"),
//                                    list_info.getString("nomor"),
//                                    list_info.getString("nama_prosedur"),
//                                    list_info.getString("berlaku"),
//                                    list_info.getString("revisi"),
//                                    list_info.getString("dokumen"),
//                                    list_info.getString("status"),
//                                    list_info.getString("bobot")));
//                        }
//
//                        if (getApplication() != null) {
//                            adapter_eviden_validasi_mb_eviden = new Adapter_Eviden_Validasi_MB_Eviden(dataModels, getApplicationContext());
//                            list_eviden_validasi_eviden.setAdapter(adapter_eviden_validasi_mb_eviden);
//                        }
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("VolleyError", "" + error);
//                progressDialog.dismiss();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<>();
//                map.put(DataCollection.KEY_id_user, id_user_validasi);
//                return map;
//            }
//        };
//        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
//    }
//
//    @OnClick(R.id.date_validasi_eviden)
//    public void godate_validasi() {
//        Calendar c = Calendar.getInstance();
//        int mYear = c.get(Calendar.YEAR);
//        int mMonth = c.get(Calendar.MONTH);
//        int mDay = c.get(Calendar.DAY_OF_MONTH);
//
//        DatePickerDialog datePickerDialog = new DatePickerDialog(Activity_Validasi_MB_Detail.this, AlertDialog.THEME_HOLO_DARK,
//                new DatePickerDialog.OnDateSetListener() {
//
//                    @Override
//                    public void onDateSet(DatePicker view, int year,
//                                          int monthOfYear, int dayOfMonth) {
//                        date_validasi_eviden.setText(KartiniHellper.getInstance(Activity_Validasi_MB_Detail.this).getDateStringFormat(year, monthOfYear, dayOfMonth));
//                    }
//                }, mYear, mMonth, mDay);
//        ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
//        datePickerDialog.setTitle("Pilih Tanggal");
//        datePickerDialog.show();
//    }
//
//
//}
