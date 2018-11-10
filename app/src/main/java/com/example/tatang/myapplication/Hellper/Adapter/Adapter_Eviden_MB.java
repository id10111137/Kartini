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

import com.example.tatang.myapplication.Hellper.Collection.ModelEvidenMB;
import com.example.tatang.myapplication.MainHomes.Eviden.Upload.Activity_Eviden_MB_Detail;
import com.example.tatang.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 8/13/2018.
 */

public class Adapter_Eviden_MB extends ArrayAdapter<ModelEvidenMB> {


    private Context context;
    private List<ModelEvidenMB> dataSet = null;
    private ArrayList<ModelEvidenMB> originDataSet = null;
    Intent intent;
    LayoutInflater inflater;
    ArrayList<String> modeArrays;


    private static class ViewHolder {
        LinearLayout lv_row_upload;
        LinearLayout lv_go_update;
        LinearLayout lv_status_eviden;

        TextView id_title_header_mb;
        TextView id_description_mb;
        TextView id_bobotmb;
        TextView id_mlimb;
        TextView id_berlakumb;
        TextView id_docuploadmb;
        TextView id_tanggalmb;
        TextView id_statusevidenmb;
        TextView txt_upload_eviden;
    }

    public Adapter_Eviden_MB(ArrayList<ModelEvidenMB> data, Context context) {
        super(context, R.layout.home_activity_eviden_mb_row, data);
        this.dataSet = data;
        this.context = context;
        inflater = LayoutInflater.from(getContext());
        this.originDataSet = new ArrayList<ModelEvidenMB>();
        this.originDataSet.addAll(data);
    }


    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public ModelEvidenMB getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ModelEvidenMB dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;
        modeArrays = new ArrayList<>();

        if (convertView == null) {

            viewHolder = new ViewHolder();

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.home_activity_eviden_mb_row, parent, false);
            viewHolder.id_description_mb = (TextView) convertView.findViewById(R.id.id_descriptionmb);
            viewHolder.id_bobotmb = (TextView) convertView.findViewById(R.id.id_bobotmb);
            viewHolder.id_mlimb = (TextView) convertView.findViewById(R.id.id_mlimb);
            viewHolder.id_title_header_mb = (TextView) convertView.findViewById(R.id.id_title_header_mb);
            viewHolder.id_berlakumb = (TextView) convertView.findViewById(R.id.id_berlakumb);
            viewHolder.id_docuploadmb = (TextView) convertView.findViewById(R.id.id_docuploadmb);
            viewHolder.id_tanggalmb = (TextView) convertView.findViewById(R.id.id_tanggalmb);
            viewHolder.id_statusevidenmb = (TextView) convertView.findViewById(R.id.id_statusevidenmb);
            viewHolder.txt_upload_eviden = (TextView) convertView.findViewById(R.id.txt_upload_eviden);
            viewHolder.lv_row_upload = (LinearLayout) convertView.findViewById(R.id.lv_row_upload);
            viewHolder.lv_go_update = (LinearLayout) convertView.findViewById(R.id.lv_go_update);
            viewHolder.lv_status_eviden = (LinearLayout) convertView.findViewById(R.id.lv_status_eviden);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.id_description_mb.setText(dataModel.getIndikator());
        viewHolder.id_bobotmb.setText(dataModel.getBobot());
        viewHolder.id_mlimb.setText(dataModel.getMLI());
        viewHolder.id_berlakumb.setText(dataModel.getBulan()+" - "+dataModel.getTahun());
        viewHolder.id_title_header_mb.setText(dataModel.getIndikator());
//        viewHolder.id_docuploadmb.setText(dataModel.);
//        viewHolder.id_tanggalmb = (TextView) convertView.findViewById(R.id.id_tanggalmb);
        viewHolder.id_statusevidenmb.setText(dataModel.getStatus());
//        viewHolder.txt_upload_eviden = (TextView) convertView.findViewById(R.id.txt_upload_eviden);
//        viewHolder.lv_row_upload = (LinearLayout) convertView.findViewById(R.id.lv_row_upload);
//        viewHolder.lv_go_update = (LinearLayout) convertView.findViewById(R.id.lv_go_update);
//        viewHolder.lv_status_eviden = (LinearLayout) convertView.findViewById(R.id.lv_status_eviden);


        modeArrays.add(dataModel.getId_apb());
        modeArrays.add(dataModel.getId_indikator());
        modeArrays.add(dataModel.getId_unit());
        modeArrays.add(dataModel.getId_perspektif());
        modeArrays.add(dataModel.getId_bidang());
        modeArrays.add(dataModel.getBobot());
        modeArrays.add(dataModel.getStatus());
        modeArrays.add(dataModel.getIndikator());
        modeArrays.add(dataModel.getMLI());
        modeArrays.add(dataModel.getInfo());
        modeArrays.add(dataModel.getBulan());
        modeArrays.add(dataModel.getTahun());
        modeArrays.add(dataModel.getFilter_waktu());
        modeArrays.add(dataModel.getStatus_response());




        viewHolder.lv_go_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 intent = new Intent(getContext(), Activity_Eviden_MB_Detail.class);
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
            for (ModelEvidenMB wp : originDataSet) {
                if (wp.getBulan().toLowerCase(Locale.getDefault())
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
