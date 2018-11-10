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
import com.example.tatang.myapplication.Hellper.Adapter.Adapter_Eviden_Staft;
import com.example.tatang.myapplication.Hellper.ApiConstant;
import com.example.tatang.myapplication.Hellper.Collection.ModelEvidenStaft;
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
 * Created by Administrator on 7/9/2018.
 */

public class Activity_Eviden_Staft extends Fragment {


    @BindView(R.id.list_eviden_upload)
    ListView list_eviden_upload;
    @BindView(R.id.id_running_text)
    TextView id_running_text;
    @BindView(R.id.date)
    EditText date;
    @BindView(R.id.id_ly_notfound)
    LinearLayout id_ly_notfound;


    private Adapter_Eviden_Staft adapter_eviden_staft;
    ArrayList<ModelEvidenStaft> modelEvidenStafts;
    UserModel userModel;
    View root;

    ProgressDialog progressDialog;


    public Activity_Eviden_Staft() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.home_activity_eviden_upload, container, false);
        ButterKnife.bind(this, root);
        id_running_text.setSelected(true);
        date.setFocusable(false);
        userModel = UserModelManager.getInstance(getContext()).getUser();
        goEviden();

        date.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date.getText().toString().toLowerCase(Locale.getDefault());
                adapter_eviden_staft.filter(text);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = date.getText().toString().toLowerCase(Locale.getDefault());
                adapter_eviden_staft.filter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        return root;
    }


    @OnClick(R.id.date)
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
                        date.setText(KartiniHellper.getInstance(getContext()).getDateStringFormat(year, monthOfYear, dayOfMonth));
                    }
                }, mYear, mMonth, mDay);
        ((ViewGroup) datePickerDialog.getDatePicker()).findViewById(Resources.getSystem().getIdentifier("day", "id", "android")).setVisibility(View.GONE);
        datePickerDialog.setTitle("Pilih Tanggal");
        datePickerDialog.show();
    }


    private void goEviden() {

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Load,Mohon Tunggu...");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, ApiConstant.URL_EVIDEN_STAFT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("menampilkan",""+response);
                try {
                    progressDialog.dismiss();
                    JSONObject obj = new JSONObject(response);


                    if (obj.getString("status").toString().equals("fail")) {
                        id_ly_notfound.setVisibility(View.VISIBLE);
                        list_eviden_upload.setVisibility(View.GONE);
                    } else {
                        JSONArray jsonArray = obj.getJSONArray("message");
                        modelEvidenStafts = new ArrayList<>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject list_info = jsonArray.getJSONObject(i);
                            Log.d("tampilan",""+list_info.toString());
                            modelEvidenStafts.add(new ModelEvidenStaft(
                                    list_info.getString("id_ik"),
                                    list_info.getString("id_prosedur"),
                                    list_info.getString("nomor"),
                                    list_info.getString("nama_ik"),
                                    list_info.getString("bobot"),
                                    list_info.getString("status"),
                                    list_info.getString("dokumen_ik"),
                                    list_info.getString("id_user"),
                                    list_info.getString("nama_prosedur"),
                                    list_info.getString("nomor_pro"),
                                    list_info.getString("dokumen"),
                                    list_info.getString("MLI"),
                                    list_info.getString("info"),
                                    list_info.getString("bulan")+"-"+list_info.getString("tahun"),
                                    list_info.getString("filter_waktu")));
                        }


                        if (getActivity() != null) {
                            adapter_eviden_staft = new Adapter_Eviden_Staft(modelEvidenStafts, getActivity());
                            list_eviden_upload.setAdapter(adapter_eviden_staft);
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
                map.put("tanggal", date.getText().toString());
                map.put(DataCollection.KEY_id_user, userModel.getId_user());
                Log.d("User", "" + userModel.getId_user());
                return map;
            }
        };

        VolleySingleton.getInstance(getContext()).addToRequestQueue(stringRequest);
    }

}
