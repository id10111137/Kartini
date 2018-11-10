package com.example.tatang.myapplication.MainHomes.Eviden.Upload;

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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tatang.myapplication.Hellper.Adapter.Adapter_Eviden_MB;
import com.example.tatang.myapplication.Hellper.ApiConstant;
import com.example.tatang.myapplication.Hellper.Collection.ModelEvidenMB;
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
 * Created by Administrator on 8/13/2018.
 */

public class Activity_Eviden_MB extends Fragment {

    View root;
    ArrayList<ModelEvidenMB> dataModels;
    private Adapter_Eviden_MB adapter_eviden_mb;
    UserModel userModel;
    ProgressDialog progressDialog;
    @BindView(R.id.date_mb)
    EditText date_mb;
    @BindView(R.id.id_running_text_mb)
    TextView id_running_text_mb;
    @BindView(R.id.id_ly_notfound_mb)
    LinearLayout id_ly_notfound_mb;
    @BindView(R.id.list_eviden_upload_mb)
    ListView list_eviden_upload_mb;
    @BindView(R.id.id_search_Date)
    LinearLayout id_search_Date;

    public Activity_Eviden_MB() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_activity_eviden_mb, container, false);
        ButterKnife.bind(this, root);
        id_running_text_mb.setSelected(true);
        date_mb.setFocusable(false);
        userModel = UserModelManager.getInstance(getContext()).getUser();
        date_mb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date_mb.getText().toString().toLowerCase(Locale.getDefault());
//                adapter_eviden_mb.filter(text);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date_mb.getText().toString().toLowerCase(Locale.getDefault());
//                adapter_eviden_mb.filter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        goEviden();
        return root;
    }

    @OnClick(R.id.date_mb)
    public void goDate() {
        Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), AlertDialog.THEME_HOLO_DARK,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        date_mb.setText(KartiniHellper.getInstance(getContext()).getDateStringFormat(year, monthOfYear, dayOfMonth));
                    }
                }, mYear, mMonth, mDay);
        ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        datePickerDialog.setTitle("Pilih Tanggal");
        datePickerDialog.show();
    }


    private void goEviden() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading, please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiConstant.URL_EVIDEN_MB, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("menampilkan", "" + response);
                try {
                    progressDialog.dismiss();
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status").toString().equals("fail")) {
                        id_ly_notfound_mb.setVisibility(View.VISIBLE);
                        list_eviden_upload_mb.setVisibility(View.GONE);
                    } else {
                        JSONArray jsonArray = obj.getJSONArray("message");
                        dataModels = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject list_info = jsonArray.getJSONObject(i);
                            dataModels.add(new ModelEvidenMB(
                                    list_info.getString("id_apb"),
                                    list_info.getString("id_indikator"),
                                    list_info.getString("id_unit"),
                                    list_info.getString("id_perspektif"),
                                    list_info.getString("id_bidang"),
                                    list_info.getString("bobot"),
                                    list_info.getString("status"),
                                    list_info.getString("indikator"),
                                    list_info.getString("MLI"),
                                    list_info.getString("info"),
                                    list_info.getString("bulan"),
                                    list_info.getString("tahun"),
                                    list_info.getString("filter_waktu"),
                                    list_info.getString("status_response")
                            ));

                        }
                        if (getActivity() != null) {
                            adapter_eviden_mb = new Adapter_Eviden_MB(dataModels, getActivity());
                            list_eviden_upload_mb.setAdapter(adapter_eviden_mb);
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

                map.put("bulantahun", date_mb.getText().toString());
                map.put(DataCollection.KEY_id_user, userModel.getId_user());
                map.put(DataCollection.KEY_id_bid, userModel.getId_bidang());
                return map;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
