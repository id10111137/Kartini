package com.example.tatang.myapplication.MainHomes.Eviden.Validasi;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tatang.myapplication.Hellper.Adapter.Adapter_Eviden_Validasi;
import com.example.tatang.myapplication.Hellper.ApiConstant;
import com.example.tatang.myapplication.Hellper.Collection.ModelValidasi;
import com.example.tatang.myapplication.Hellper.Collection.UserModel;
import com.example.tatang.myapplication.Hellper.DataCollection;
import com.example.tatang.myapplication.Hellper.KartiniHellper;
import com.example.tatang.myapplication.Hellper.UserModelManager;
import com.example.tatang.myapplication.Hellper.VolleySingleton;
import com.example.tatang.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 7/11/2018.
 */

public class Activity_Validasi_DM extends Fragment {

    @BindView(R.id.list_eviden_validasi)
    ListView list_eviden_validasi;

    ArrayList<ModelValidasi> modelEvidenDM;
    UserModel userModel;
    private Adapter_Eviden_Validasi adapter_Eviden_Validasi;

    @BindView(R.id.date_validasi)
    EditText date_validasi;

    @BindView(R.id.id_running_text_validasi)
    TextView id_running_text_validasi;

    DatePickerDialog datePickerDialog;

//    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1888;

    ProgressDialog progressDialog;


    View root;

    public Activity_Validasi_DM() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_activity_eviden_validasi, container, false);
        ButterKnife.bind(this, root);
        date_validasi.setFocusable(false);
        id_running_text_validasi.setSelected(true);

        userModel = UserModelManager.getInstance(getContext()).getUser();
        getEvidenValidasi();
        date_validasi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date_validasi.getText().toString().toLowerCase(Locale.getDefault());
                adapter_Eviden_Validasi.filter(text);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date_validasi.getText().toString().toLowerCase(Locale.getDefault());
                adapter_Eviden_Validasi.filter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return root;
    }


    private void getEvidenValidasi() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading, please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiConstant.URL_EVIDENKUMPULANPEGAWAI1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status").toString().equals("fail")) {
                        progressDialog.dismiss();
                        KartiniHellper.getInstance(getContext()).getAttchmentPop(getContext(), root);
                    } else {
                        progressDialog.dismiss();
                        JSONArray jsonArray = obj.getJSONArray("message");
                        modelEvidenDM = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject list_info = jsonArray.getJSONObject(i);
                            modelEvidenDM.add(new ModelValidasi(
                                    list_info.getString("id_user"),
                                    list_info.getString("nama"),
                                    list_info.getString("photo"),
                                    list_info.getString("jumlah_ik"),
                                    list_info.getString("bobot"),
                                    list_info.getString("capai"),
                                    list_info.getString("total_kpi"),
                                    list_info.getString("pencapaian"),
                                    list_info.getString("IP"),
                                    list_info.getString("IPK"),
                                    list_info.getString("nama_subid"),
                                    list_info.getString("status"),
                                    list_info.getString("verifikasi"),
                                    list_info.getString("deadline"),
                                    list_info.getString("lock")
                            ));
                        }

                        if (getActivity() != null) {
                            adapter_Eviden_Validasi = new Adapter_Eviden_Validasi(modelEvidenDM, getContext());
                            list_eviden_validasi.setAdapter(adapter_Eviden_Validasi);
                        }
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
                map.put(DataCollection.KEY_nama_user, userModel.getNama_user());
                map.put(DataCollection.KEY_id_user, userModel.getId_user());
                map.put(DataCollection.KEY_id_subid, userModel.getId_subid());
                return map;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }


    @OnClick(R.id.date_validasi)
    public void godate_validasi() {

        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        date_validasi.setText(KartiniHellper.getInstance(getContext()).getDateStringFormat(year, monthOfYear, dayOfMonth));
                    }
                }, mYear, mMonth, mDay);
        ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        datePickerDialog.setTitle("Pilih Tanggal");
        datePickerDialog.show();

    }


//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        // super.onActivityResult(requestCode, resultCode, data);
//        try {
//            if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
//                if (resultCode == Activity.RESULT_OK && data != null) {
//
//                }
//            }
//        } catch (Exception e) {
//            Toast.makeText(this.getActivity(), e + "Something went wrong", Toast.LENGTH_LONG).show();
//
//        }
//    }


}
