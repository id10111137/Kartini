package com.example.tatang.myapplication.MainHomes.UserGuide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tatang.myapplication.Hellper.Collection.UserModel;
import com.example.tatang.myapplication.Hellper.UserModelManager;
import com.example.tatang.myapplication.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 7/9/2018.
 */

public class Activity_UserGuide extends Fragment {


    UserModel userModel;

    @BindView(R.id.id_name_user)
    TextView id_name_user;
    @BindView(R.id.id_jabatan)
    TextView id_jabatan;
    @BindView(R.id.id_bidang)
    TextView id_bidang;
    @BindView(R.id.id_subid)
    TextView id_subid;


    @BindView(R.id.imageView)
    CircleImageView imageView;


    public Activity_UserGuide() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_activity_user_guide, container, false);
        ButterKnife.bind(this, root);
        userModel = UserModelManager.getInstance(getContext()).getUser();
        activity_UserGuide();

        return root;
    }

    private void activity_UserGuide() {
        id_name_user.setText(userModel.getNama_user());
        id_jabatan.setText(userModel.getStatus_user());
        id_bidang.setText(userModel.getNama_bidang());
        id_subid.setText(userModel.getNama_subid());

        if (userModel.getPhoto() != null) {
            Log.d("Gambar", "" + userModel.getPhoto());
            Picasso.with(this.getContext())
                    .load("http://mistertrainer.com/kartini-api/file_uploads/photo/" + userModel.getPhoto())
                    .centerCrop()
                    .fit()
                    .into(imageView);
        }else{
            imageView.setImageDrawable(getResources().getDrawable(R.drawable.service_desk));
        }



//        expandableListDetail = UserGuideExpandleViewListView.getData();
//        expandableListTitle = new ArrayList<>(expandableListDetail.keySet());
//        expandableListAdapter = new AdapterUserGuide(getContext(), expandableListTitle, expandableListDetail);
//        expandableListView.setAdapter(expandableListAdapter);


//        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            @Override
//            public void onGroupExpand(int i) {
//                Toast.makeText(getContext(),
//                        expandableListTitle.get(i) + " List Expanded.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            @Override
//            public void onGroupCollapse(int i) {
//                Toast.makeText(getContext(),
//                        expandableListTitle.get(i) + " List Collapsed.",
//                        Toast.LENGTH_SHORT).show();
//            }
//        });
//
//
//        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//            @Override
//            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
//                Toast.makeText(
//                        getContext(),
//                        expandableListTitle.get(i)
//                                + " -> "
//                                + expandableListDetail.get(
//                                expandableListTitle.get(i)).get(
//                                i1), Toast.LENGTH_SHORT
//                ).show();
//                return false;
//            }
//        });


    }
}
