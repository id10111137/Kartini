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
import com.example.tatang.myapplication.Hellper.Adapter.Adapter_Eviden_DM;
import com.example.tatang.myapplication.Hellper.ApiConstant;
import com.example.tatang.myapplication.Hellper.Collection.ModelEvidenDM;
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
 * Created by Administrator on 8/6/2018.
 */

public class Activity_Eviden_DM extends Fragment {

    View root;
    ArrayList<ModelEvidenDM> modelEvidenDM;
    private Adapter_Eviden_DM adapter_eviden_dm;
    UserModel userModel;
    ProgressDialog progressDialog;
    @BindView(R.id.date_dm)
    EditText date_dm;
    @BindView(R.id.id_running_text_dm)
    TextView id_running_text_dm;
    @BindView(R.id.id_ly_notfound_dm)
    LinearLayout id_ly_notfound_dm;
    @BindView(R.id.list_eviden_upload_dm)
    ListView list_eviden_upload_dm;


    public Activity_Eviden_DM() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_activity_eviden_dm, container, false);
        ButterKnife.bind(this, root);

        id_running_text_dm.setSelected(true);
        date_dm.setFocusable(false);
        userModel = UserModelManager.getInstance(getContext()).getUser();
        date_dm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date_dm.getText().toString().toLowerCase(Locale.getDefault());
                adapter_eviden_dm.filter(text);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date_dm.getText().toString().toLowerCase(Locale.getDefault());
                adapter_eviden_dm.filter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        goEviden();
        return root;
    }

    @OnClick(R.id.date_dm)
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
                        date_dm.setText(KartiniHellper.getInstance(getContext()).getDateStringFormat(year, monthOfYear, dayOfMonth));
                    }
                }, mYear, mMonth, mDay);
        ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        datePickerDialog.setTitle("Pilih Tanggal");
        datePickerDialog.show();
    }

    private void goEviden()  {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Uploading, please wait...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiConstant.URL_EVIDEN_DM, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("mMenampilkan",""+response);
                try {
                    progressDialog.dismiss();
                    JSONObject obj = new JSONObject(response);
                    if (obj.getString("status").toString().equals("fail")) {
                        id_ly_notfound_dm.setVisibility(View.VISIBLE);
                        list_eviden_upload_dm.setVisibility(View.GONE);
                    } else {
                        JSONArray jsonArray = obj.getJSONArray("message");
                        modelEvidenDM = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject list_info = jsonArray.getJSONObject(i);
                            modelEvidenDM.add(new ModelEvidenDM(
                                    list_info.getString("id_user"),
                                    list_info.getString("id_indikator"),
                                    list_info.getString("indikator"),
                                    list_info.getString("id_prosedur"),
                                    list_info.getString("nomor"),
                                    list_info.getString("nama_prosedur"),
                                    list_info.getString("berlaku"),
                                    list_info.getString("revisi"),
                                    list_info.getString("id_eviden"),
                                    list_info.getString("dokumen"),
                                    list_info.getString("bobot"),
                                    list_info.getString("status"),
                                    list_info.getString("MLI"),
                                    list_info.getString("info"),
                                    list_info.getString("bulan"),
                                    list_info.getString("tahun"),
                                    list_info.getString("filter_waktu"),
                                    list_info.getString("status_response")
                                    ));
                        }

                        if (getActivity() != null) {
                            adapter_eviden_dm = new Adapter_Eviden_DM(modelEvidenDM, getContext());
                            list_eviden_upload_dm.setAdapter(adapter_eviden_dm);
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
                map.put("bulantahun", date_dm.getText().toString());
                map.put(DataCollection.KEY_id_user, userModel.getId_user());
                map.put(DataCollection.KEY_id_bid, userModel.getId_bidang());

                return map;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }
}
