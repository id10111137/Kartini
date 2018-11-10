package com.example.tatang.myapplication.Hellper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatang.myapplication.Hellper.Collection.ModelValidasi;
import com.example.tatang.myapplication.MainHomes.Eviden.Validasi.Activity_Validasi_DM_Detail;
import com.example.tatang.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Handler;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 7/11/2018.
 */

public class Adapter_Eviden_Validasi extends ArrayAdapter<ModelValidasi> {

    private Context context;
    private List<ModelValidasi> dataSet = null;
    private ArrayList<ModelValidasi> originDataSet = null;
    Intent intent;
    LayoutInflater inflater;

    private Handler handler;

    private int progresStatus = 0;

    private static class ViewHolder {

        LinearLayout id_lv_validasi, id_lyt_dm_;
        TextView id_status_validasi;
        TextView id_no_status_validasi;
        CircleImageView imageView_validasi;
        TextView id_jabatan_validasi;
        TextView id_name_user_validasi;
        TextView id_subid_validasi;
        TextView id_ip_validasi, id_bobot;
        TextView id_ipk_validasi;
        TextView id_sumik_validasi;
        TextView id_pencapaian_bobot_validasi;
        ProgressBar id_progresrbar_validasi;
    }

    public Adapter_Eviden_Validasi(ArrayList<ModelValidasi> data, Context context) {
        super(context, R.layout.home_activity_eviden_validasi_row, data);
        this.dataSet = data;
        this.context = context;
        inflater = LayoutInflater.from(getContext());
        this.originDataSet = new ArrayList<ModelValidasi>();
        this.originDataSet.addAll(data);
    }


    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public ModelValidasi getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ModelValidasi dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.home_activity_eviden_validasi_row, parent, false);
            viewHolder.id_lv_validasi = (LinearLayout) convertView.findViewById(R.id.id_lv_validasi);
            viewHolder.id_lyt_dm_ = (LinearLayout) convertView.findViewById(R.id.id_lyt_dm_);
            viewHolder.id_status_validasi = (TextView) convertView.findViewById(R.id.id_status_validasi);
            viewHolder.id_no_status_validasi = (TextView) convertView.findViewById(R.id.id_no_status_validasi);
            viewHolder.imageView_validasi = (CircleImageView) convertView.findViewById(R.id.imageView_validasi);
            viewHolder.id_jabatan_validasi = (TextView) convertView.findViewById(R.id.id_jabatan_validasi);
            viewHolder.id_bobot = (TextView) convertView.findViewById(R.id.id_bobot);
            viewHolder.id_name_user_validasi = (TextView) convertView.findViewById(R.id.id_name_user_validasi);
            viewHolder.id_subid_validasi = (TextView) convertView.findViewById(R.id.id_subid_validasi);
            viewHolder.id_ip_validasi = (TextView) convertView.findViewById(R.id.id_ip_validasi);
            viewHolder.id_ipk_validasi = (TextView) convertView.findViewById(R.id.id_ipk_validasi);
            viewHolder.id_sumik_validasi = (TextView) convertView.findViewById(R.id.id_sumik_validasi);
            viewHolder.id_pencapaian_bobot_validasi = (TextView) convertView.findViewById(R.id.id_pencapaian_bobot_validasi);
            viewHolder.id_progresrbar_validasi = (ProgressBar) convertView.findViewById(R.id.id_progresrbar_validasi);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.id_name_user_validasi.setText(dataModel.getNama());
        viewHolder.id_subid_validasi.setText(dataModel.getNama_subid());
        viewHolder.id_ip_validasi.setText(dataModel.getIP());
        viewHolder.id_ipk_validasi.setText(dataModel.getIPK());
        viewHolder.id_sumik_validasi.setText(dataModel.getJumlah_ik());
        viewHolder.imageView_validasi.setImageBitmap(StringToBitMap(dataModel.getPhoto()));
        viewHolder.id_jabatan_validasi.setText(dataModel.getNama_subid());
        viewHolder.id_pencapaian_bobot_validasi.setText(dataModel.getCapai());
        viewHolder.id_bobot.setText(dataModel.getBobot());


        viewHolder.id_lv_validasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_Validasi_DM_Detail.class);
                intent.putExtra("id_user_validasi", dataModel.getId_user());
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }


    //    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataSet.clear();
        if (charText.length() == 0) {
            dataSet.addAll(originDataSet);
        } else {
            for (ModelValidasi wp : originDataSet) {
                if (wp.getJumlah_ik().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    dataSet.add(wp);
                } else {

                    Toast.makeText(context, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }
        }
        notifyDataSetChanged();
    }


    public Bitmap StringToBitMap(String encodedString){
        try{
            byte [] encodeByte= Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        }catch(Exception e){
            e.getMessage();
            return null;
        }
    }
}
