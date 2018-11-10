package com.example.tatang.myapplication.Hellper.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tatang.myapplication.Hellper.Collection.ModelValidasiDetail;
import com.example.tatang.myapplication.Hellper.KartiniHellper;
import com.example.tatang.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Administrator on 7/13/2018.
 */

public class Adapter_Eviden_Validasi_Eviden extends ArrayAdapter<ModelValidasiDetail> {

    private Context context;
    private List<ModelValidasiDetail> dataSet = null;
    private ArrayList<ModelValidasiDetail> originDataSet = null;
    private ArrayList arrayList;
    LayoutInflater inflater;

    private static class ViewHolder {
        TextView id_title_header_validasi, id_bobot, id_description_validasi, id_doc_validasi, id_mli_upload_validasi, id_status_eviden_validasi, id_Date_status_eviden;
        LinearLayout lv_row_upload_validasi, id_validasi, id_lv_status_eviden_validasi;
    }

    public Adapter_Eviden_Validasi_Eviden(ArrayList<ModelValidasiDetail> data, Context context) {
        super(context, R.layout.home_eviden_validasi_eviden_row, data);
        this.dataSet = data;
        this.context = context;
        inflater = LayoutInflater.from(getContext());
        this.originDataSet = new ArrayList<ModelValidasiDetail>();
        this.originDataSet.addAll(data);
    }


    @Override
    public int getCount() {
        return dataSet.size();
    }

    @Override
    public ModelValidasiDetail getItem(int position) {
        return dataSet.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ModelValidasiDetail dataModel = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.home_eviden_validasi_eviden_row, parent, false);
            viewHolder.id_title_header_validasi = convertView.findViewById(R.id.id_title_header_validasi);
            viewHolder.id_description_validasi = convertView.findViewById(R.id.id_description_validasi);
            viewHolder.id_mli_upload_validasi = convertView.findViewById(R.id.id_mli_upload_validasi);
            viewHolder.id_bobot = convertView.findViewById(R.id.id_bobot);
            viewHolder.id_doc_validasi = convertView.findViewById(R.id.id_doc_validasi);
            viewHolder.id_Date_status_eviden = convertView.findViewById(R.id.id_Date_status_eviden);
            viewHolder.lv_row_upload_validasi = convertView.findViewById(R.id.lv_row_upload_validasi);
            viewHolder.id_validasi = convertView.findViewById(R.id.id_validasi);
            viewHolder.id_lv_status_eviden_validasi = convertView.findViewById(R.id.id_lv_status_eviden_validasi);

            result = convertView;
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }


        viewHolder.id_title_header_validasi.setText(dataModel.getNomor() + " " + dataModel.getNama_prosedur());
        viewHolder.id_description_validasi.setText(dataModel.getNama_ik());
        viewHolder.id_bobot.setText("Bobot : " + dataModel.getBobot());

        viewHolder.id_mli_upload_validasi.setText(dataModel.getStatus());

        if (dataModel.getStatus() != null) {
            viewHolder.id_mli_upload_validasi.setText("MLI : Sudah Di Niali");
        } else {
            viewHolder.id_mli_upload_validasi.setText("MLI : Belum Di Niali");
        }


        if (dataModel.getDokumen_ik() == null && dataModel.getBerlaku() == null) {
            viewHolder.id_lv_status_eviden_validasi.setVisibility(View.GONE);
        } else {
            viewHolder.id_doc_validasi.setText(dataModel.getDokumen_ik());
            viewHolder.id_Date_status_eviden.setText("Di Upload Pada " + dataModel.getBerlaku());
        }



        arrayList = new ArrayList();
        arrayList.add(dataModel.getId_user());
        arrayList.add(dataModel.getId_prosedur());
        arrayList.add(dataModel.getId_ik());
        arrayList.add(dataModel.getBobot());
        arrayList.add(dataModel.getTanggal());

        viewHolder.id_validasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KartiniHellper.getInstance(getContext()).getValidasi(getContext(), result, arrayList);
            }
        });

        return convertView;
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        dataSet.clear();
        if (charText.length() == 0) {
            dataSet.addAll(originDataSet);
        } else {
            for (ModelValidasiDetail wp : originDataSet) {
                if (wp.getBerlaku().toLowerCase(Locale.getDefault())
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