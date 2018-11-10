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

import com.example.tatang.myapplication.Hellper.Collection.ModelEvidenDM;
import com.example.tatang.myapplication.MainHomes.Eviden.Upload.Activity_Eviden_DM_Detail;
import com.example.tatang.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 8/6/2018.
 */

public class Adapter_Eviden_DM extends ArrayAdapter<ModelEvidenDM> {

    private Context context;
    private List<ModelEvidenDM> dataSet = null;
    private ArrayList<ModelEvidenDM> originDataSet = null;
    Intent intent;
    LayoutInflater inflater;
    ArrayList<String> modeArrays;

    private static class ViewHolder {
        TextView id_no_header, id_status_eviden;
        TextView id_title_header_dm, txt_go_update;
        TextView id_description_dm;
        TextView id_status_eviden_dm;
        TextView id_mli_upload_dm, id_dokumen, id_waktu_upload;
        LinearLayout id_upload_dm, id_go_update_dm, id_lv_status_eviden;
    }


    public Adapter_Eviden_DM(ArrayList<ModelEvidenDM> data, Context context) {
        super(context, R.layout.home_activity_eviden_dm_row, data);
        this.dataSet = data;
        this.context = context;
        inflater = LayoutInflater.from(getContext());
        this.originDataSet = new ArrayList<ModelEvidenDM>();
        this.originDataSet.addAll(data);
    }


    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public ModelEvidenDM getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ModelEvidenDM dataModel = getItem(position);
        modeArrays = new ArrayList<>();
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.home_activity_eviden_dm_row, parent, false);

            viewHolder.id_title_header_dm = (TextView) convertView.findViewById(R.id.id_title_header_dm);
            viewHolder.id_description_dm = (TextView) convertView.findViewById(R.id.id_description_dm);
            viewHolder.id_dokumen = (TextView) convertView.findViewById(R.id.id_dokumen);
            viewHolder.id_mli_upload_dm = (TextView) convertView.findViewById(R.id.id_mli_upload_dm);
            viewHolder.txt_go_update = (TextView) convertView.findViewById(R.id.txt_go_update);
            viewHolder.id_status_eviden = (TextView) convertView.findViewById(R.id.id_status_eviden);
            viewHolder.id_waktu_upload = (TextView) convertView.findViewById(R.id.id_waktu_upload);
            viewHolder.id_lv_status_eviden = (LinearLayout) convertView.findViewById(R.id.id_lv_status_eviden);
            viewHolder.id_go_update_dm = (LinearLayout) convertView.findViewById(R.id.id_go_update_dm);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.id_title_header_dm.setText(dataModel.getNomor()+" - "+dataModel.getNama_prosedur());
        viewHolder.id_description_dm.setText(dataModel.getIndikator());
        viewHolder.id_dokumen.setText(dataModel.getDokumen());
        viewHolder.id_mli_upload_dm.setText(dataModel.getMLI());
        viewHolder.id_waktu_upload.setText("Di Upload Pada"+dataModel.getBulan()+"-"+dataModel.getTahun());

        modeArrays.add(dataModel.getId_user());
        modeArrays.add(dataModel.getId_indikator());
        modeArrays.add(dataModel.getIndikator());
        modeArrays.add(dataModel.getId_prosedur());
        modeArrays.add(dataModel.getNomor());
        modeArrays.add(dataModel.getNama_prosedur());
        modeArrays.add(dataModel.getBerlaku());
        modeArrays.add(dataModel.getRevisi());
        modeArrays.add(dataModel.getId_eviden());
        modeArrays.add(dataModel.getDokumen());
        modeArrays.add(dataModel.getBobot());
        modeArrays.add(dataModel.getStatus());
        modeArrays.add(dataModel.getMLI());
        modeArrays.add(dataModel.getInfo());
        modeArrays.add(dataModel.getBulan());
        modeArrays.add(dataModel.getTahun());
        modeArrays.add(dataModel.getFilter_waktu());
        modeArrays.add(dataModel.getStatus_response());


        if (dataModel.getDokumen().equalsIgnoreCase("null")) {
            viewHolder.id_status_eviden.setVisibility(View.VISIBLE);
        } else {
            viewHolder.id_status_eviden.setVisibility(View.GONE);
            viewHolder.id_lv_status_eviden.setVisibility(View.VISIBLE);
            viewHolder.txt_go_update.setText("Update Eviden");
        }


        viewHolder.id_go_update_dm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getContext(), Activity_Eviden_DM_Detail.class);
                intent.putExtra("array_list", modeArrays);
                getContext().startActivity(intent);

            }
        });

        return convertView;
    }

    // Filter Class
    //    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataSet.clear();
        if (charText.length() == 0) {
            dataSet.addAll(originDataSet);
        } else {
            for (ModelEvidenDM wp : originDataSet) {
                if (wp.getTahun().toLowerCase(Locale.getDefault())
                        .contains(charText)) {
                    dataSet.add(wp);
                }else {

                    Toast.makeText(context, "Data Tidak Ada", Toast.LENGTH_SHORT).show();
                }
            }
        }
        notifyDataSetChanged();
    }

}
