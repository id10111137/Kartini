package com.example.tatang.myapplication.Hellper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatang.myapplication.Hellper.Collection.ModelEvidenStaft;
import com.example.tatang.myapplication.MainHomes.Eviden.Upload.Activity_Eviden_Staft_Detail;
import com.example.tatang.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 7/9/2018.
 */

public class Adapter_Eviden_Staft extends ArrayAdapter<ModelEvidenStaft> {

    private Context context;
    private List<ModelEvidenStaft> dataSet = null;
    private ArrayList<ModelEvidenStaft> originDataSet = null;
    Intent intent;
    LayoutInflater inflater;
    ArrayList<String> modeArrays;

    private static class ViewHolder {
        TextView id_title_header;
        TextView id_description;
        TextView id_status_eviden, id_doc_upload, id_tanggal, txt_upload_eviden;
        TextView id_mli_upload;
        LinearLayout id_go_update, id_lv_status_eviden;
    }

    public Adapter_Eviden_Staft(ArrayList<ModelEvidenStaft> data, Context context) {
        super(context, R.layout.home_activity_eviden_upload_row, data);
        this.dataSet = data;
        this.context = context;
        inflater = LayoutInflater.from(getContext());
        this.originDataSet = new ArrayList<ModelEvidenStaft>();
        this.originDataSet.addAll(data);
    }


    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public ModelEvidenStaft getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ModelEvidenStaft dataModel = getItem(position);
        modeArrays = new ArrayList<>();
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.home_activity_eviden_upload_row, parent, false);
            viewHolder.id_title_header = (TextView) convertView.findViewById(R.id.id_title_header);
            viewHolder.id_description = (TextView) convertView.findViewById(R.id.id_description);
            viewHolder.id_status_eviden = (TextView) convertView.findViewById(R.id.id_status_eviden);
            viewHolder.txt_upload_eviden = (TextView) convertView.findViewById(R.id.txt_upload_eviden);
            viewHolder.id_go_update = (LinearLayout) convertView.findViewById(R.id.id_go_update);
            viewHolder.id_mli_upload = (TextView) convertView.findViewById(R.id.id_mli_upload);
            viewHolder.id_doc_upload = (TextView) convertView.findViewById(R.id.id_doc_upload);
            viewHolder.id_tanggal = (TextView) convertView.findViewById(R.id.id_tanggal);
            viewHolder.id_lv_status_eviden = (LinearLayout) convertView.findViewById(R.id.id_lv_status_eviden);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        viewHolder.id_title_header.setText(dataModel.getNomor() + " - " + dataModel.getNama_prosedur());
        viewHolder.id_description.setText(dataModel.getNama_ik());
        viewHolder.id_mli_upload.setText(dataModel.getMLI());
        viewHolder.id_doc_upload.setText(dataModel.getDokumen());


        if (dataModel.getDokumen().equalsIgnoreCase("null")) {
            viewHolder.id_status_eviden.setVisibility(View.VISIBLE);
        } else {
            viewHolder.id_status_eviden.setVisibility(View.GONE);
            viewHolder.id_lv_status_eviden.setVisibility(View.VISIBLE);
            viewHolder.txt_upload_eviden.setText("Update Eviden");
        }

        viewHolder.id_tanggal.setText(dataModel.getTahun());

        modeArrays.add(dataModel.getId_ik());
        modeArrays.add(dataModel.getId_prosedur());
        modeArrays.add(dataModel.getNomor());
        modeArrays.add(dataModel.getNama_ik());
        modeArrays.add(dataModel.getBobot());
        modeArrays.add(dataModel.getStatus());
        modeArrays.add(dataModel.getDokumen_ik());
        modeArrays.add(dataModel.getId_user());
        modeArrays.add(dataModel.getNama_prosedur());
        modeArrays.add(dataModel.getNomor_pro());
        modeArrays.add(dataModel.getMLI());
        modeArrays.add(dataModel.getInfo());
        modeArrays.add(dataModel.getTahun());
        modeArrays.add(dataModel.getFilter_waktu());


        viewHolder.id_go_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), Activity_Eviden_Staft_Detail.class);
                intent.putExtra("array_list", modeArrays);
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
            for (ModelEvidenStaft wp : originDataSet) {
                if (wp.getTahun().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    dataSet.add(wp);
                } else {

                    Toast.makeText(context, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }
        }
        notifyDataSetChanged();
    }
}
