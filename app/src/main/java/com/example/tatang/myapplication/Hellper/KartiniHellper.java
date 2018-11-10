package com.example.tatang.myapplication.Hellper;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tatang.myapplication.R;

import net.steamcrafted.materialiconlib.MaterialIconView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 7/11/2018.
 */

public class KartiniHellper {

    private PopupWindow mPopupWindow;
    private static KartiniHellper mInstance;
    private Context mContext;
    private LinearLayout id_upload_attach, lv_choose_question02, lv_choose_question03, id_upload_cancel;
    private ImageButton closeButton, internet_close;
    private MaterialIconView btn_take_picture, btn_take_gallery, btn_file;
    private RadioGroup rg_no1, rg_no2, rg_no3;
    private RadioButton rb;
    int selectedId, indeks;
    int nValues;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    SimpleDateFormat sdf;
    String dates;

    String id_user;
    String id_prosedur;
    String id_ik;
    String bobot;
    String SelectedRb;

    ProgressDialog progressDialog;
    JSONObject jsonObject;

    private KartiniHellper(Context context) {
        mContext = context;
    }

    public static synchronized KartiniHellper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new KartiniHellper(context);
        }
        return mInstance;
    }

    public void ViewHolder(){
        TextView id_no_header;
        TextView id_title_header, notfoundList;
        TextView id_description;
        TextView id_status_eviden, id_doc_upload, id_tanggal, txt_upload_eviden;
        TextView id_mli_upload, id_berlaku;
        LinearLayout id_upload, id_go_update, id_lv_status_eviden;
    }


    public String getDateStringFormat(final int year, final int monthOfYear, final int dayOfMonth) {
        String FinalDateFormat = null;
        String MonthOFYear = null;
        if (monthOfYear < 10) {
            MonthOFYear = "0" + Integer.valueOf(monthOfYear + 1);
        }
        return FinalDateFormat = Integer.toString(year) + '-' + MonthOFYear;
    }

    public String DateFormatOfString() {
        String currentDateandTime = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return currentDateandTime = sdf.format(new Date());
    }

    public void getAttchmentPop(final Context mContext, View root) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.activity_upload_pop, null);

        mPopupWindow = new PopupWindow(
                customView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        closeButton = (ImageButton) customView.findViewById(R.id.ib_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });


        mPopupWindow.showAtLocation(root, Gravity.CENTER, 0, 0);
    }

    public void choosePhotoFromGallary() {
        mContext.startActivity(new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
    }

    public void chooseTakeFromGallary() {

    }

    public void getValidasi(final Context mContext, View root, final ArrayList arrayList) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.activity_upload_validasi, null);
        mPopupWindow = new PopupWindow(
                customView,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        closeButton = (ImageButton) customView.findViewById(R.id.iclose);
        lv_choose_question02 = (LinearLayout) customView.findViewById(R.id.lv_choose_question02);
        lv_choose_question03 = (LinearLayout) customView.findViewById(R.id.lv_choose_question03);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        id_upload_cancel = (LinearLayout) customView.findViewById(R.id.id_upload_cancel);
        id_upload_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopupWindow.dismiss();
            }
        });

        rg_no1 = (RadioGroup) customView.findViewById(R.id.rg_no1);
        rg_no1.clearCheck();
        rg_no1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb = (RadioButton) radioGroup.findViewById(i);
                SelectedRb = rb.getText().toString();
                if (null != rb) {
                    if (SelectedRb.equals(DataCollection.KEY_CHOOSE1A)) {
                        lv_choose_question02.setVisibility(View.GONE);
                        lv_choose_question03.setVisibility(View.GONE);
                    }
                    if (SelectedRb.equals(DataCollection.KEY_CHOOSE1B)) {
                        lv_choose_question02.setVisibility(View.VISIBLE);
                    }
                    nValues = 50;
                    indeks = 1;
                }
            }
        });


        rg_no2 = (RadioGroup) customView.findViewById(R.id.rg_no2);
        rg_no2.clearCheck();
        rg_no2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                rb = (RadioButton) radioGroup.findViewById(i);
                SelectedRb = rb.getText().toString();
                selectedId = radioGroup.getCheckedRadioButtonId();
                Log.i("INDONESIA2", "" + rb.getText().toString());
                if (null != rb) {
                    if (SelectedRb.equals(DataCollection.KEY_CHOOSE2A)) {
                        lv_choose_question03.setVisibility(View.GONE);
                    } else if (SelectedRb.equals(DataCollection.KEY_CHOOSE2B)) {
                        lv_choose_question03.setVisibility(View.GONE);
                    } else if (SelectedRb.equals(DataCollection.KEY_CHOOSE2C)) {
                        lv_choose_question03.setVisibility(View.VISIBLE);
                    }
                    nValues = 60;
                    indeks = 2;
                }

            }
        });

        rg_no3 = (RadioGroup) customView.findViewById(R.id.rg_no3a);
        rg_no3.clearCheck();
        rg_no3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                selectedId = radioGroup.getCheckedRadioButtonId();
                rb = (RadioButton) radioGroup.findViewById(i);
                SelectedRb = rb.getText().toString();
                Log.i("INDONESIA3", "" + SelectedRb);
                if (null != rb) {
                    if (SelectedRb.equals(DataCollection.KEY_CHOOSE3A)) {
                        nValues = 80;
                        indeks = 3;
                    } else if (SelectedRb.equals(DataCollection.KEY_CHOOSE3B)) {
                        nValues = 90;
                        indeks = 4;
                    }
                }
            }
        });

        id_upload_attach = (LinearLayout) customView.findViewById(R.id.id_upload_attach);
        id_upload_attach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDM(arrayList);
            }
        });

        mPopupWindow.showAtLocation(root, Gravity.CENTER, 0, 0);
    }

    private void getDM(final ArrayList arrayList) {
        StringRequest LoginArea = new StringRequest(Request.Method.POST, ApiConstant.URL_EVIDENKUMPULANPEGAWAIPENILAIANDETAIL1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("Respos", "" + response);
                try {
                    JSONObject obj = new JSONObject(response);

                    if (obj.getString("status").toString().equals("success")) {
                        if (obj.getString("message").toString().equals(true)) ;
                        {
                            Toast.makeText(mContext, "Terima Kasih, Data Sukses", Toast.LENGTH_SHORT).show();
                            mPopupWindow.dismiss();
                        }

                    } else {
                        Toast.makeText(mContext, "Username & Password Salah", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error", "Error Listener" + error);
                Toast.makeText(mContext, "Error Server", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();

                for (int a = 0; a <= arrayList.size(); a++) {
                    map.put(DataCollection.KEY_id_user, arrayList.get(0).toString());
                    map.put("id_prosedur", arrayList.get(1).toString());
                    map.put(DataCollection.KEY_id_ik, arrayList.get(2).toString());
                    map.put("bobot", arrayList.get(3).toString());
                    map.put("tanggal", arrayList.get(4).toString());
                }
                map.put("nilai", String.valueOf(nValues));
                map.put("verivikasi", "Y");
                map.put("indeks", "Y");
                map.put("tanggal_verivikasi", DateFormatOfString());
                return map;
            }
        };
        VolleySingleton.getInstance(mContext).addToRequestQueue(LoginArea);
    }
}
