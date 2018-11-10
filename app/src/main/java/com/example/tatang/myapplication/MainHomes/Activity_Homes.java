package com.example.tatang.myapplication.MainHomes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.tatang.myapplication.Hellper.BottomNavigationViewHelper;
import com.example.tatang.myapplication.Hellper.Collection.UserModel;
import com.example.tatang.myapplication.Hellper.UserModelManager;
import com.example.tatang.myapplication.MainHomes.Eviden.Upload.Activity_Eviden_DM;
import com.example.tatang.myapplication.MainHomes.Eviden.Upload.Activity_Eviden_MB;
import com.example.tatang.myapplication.MainHomes.Eviden.Upload.Activity_Eviden_Staft;
import com.example.tatang.myapplication.MainHomes.Eviden.Validasi.Activity_Validasi_DM;
import com.example.tatang.myapplication.MainHomes.Individu.Activity_Individu;
import com.example.tatang.myapplication.MainHomes.UserGuide.Activity_UserGuide;
import com.example.tatang.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by tatang.it on 04/07/2018.
 */

public class Activity_Homes extends Fragment {

    @BindView(R.id.btn_navigation_mInfo)
    BottomNavigationView btn_navigation_mInfo;
    UserModel userModel;

    Fragment fragment;


    public Activity_Homes() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_activity, container, false);
        ButterKnife.bind(this, root);
        BottomNavigationViewHelper.removeShiftMode(btn_navigation_mInfo);
        userModel = UserModelManager.getInstance(getContext()).getUser();
        setupNavigationView();
        return root;
    }


    private void setupNavigationView() {

        if (btn_navigation_mInfo != null) {

            // Select first menu item by default and show Fragment accordingly.
            Menu menu = btn_navigation_mInfo.getMenu();
            selectmInfo(menu.getItem(0), userModel.getStatus_user());

            // Set action to perform when any menu-item is selected.
            btn_navigation_mInfo.setOnNavigationItemSelectedListener(
                    new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                            selectmInfo(item, userModel.getStatus_user());
                            return false;
                        }
                    });
        }
    }

    protected void selectmInfo(MenuItem item, String getUser) {
        item.setChecked(true);
        switch (item.getItemId()) {
            case R.id.mHomes:
                pushFragment(new Activity_UserGuide());
                break;
            case R.id.mEviden_u:
                pushFragment(Eviden(userModel.getStatus_user()));
                break;

            case R.id.mEviden_v:
                pushFragment(Validasi(userModel.getStatus_user()));
                break;

            case R.id.mML:
                pushFragment(new Activity_Individu());
                break;
        }
    }

    protected void pushFragment(Fragment fragment) {
        if (fragment == null)
            return;

        FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager != null) {
            FragmentTransaction ft = fragmentManager.beginTransaction();
            if (ft != null) {
                ft.replace(R.id.frame_mInfo, fragment);
                ft.commit();
            }
        }
    }

    private Fragment Eviden(String userValidasi){

        if (userValidasi.equalsIgnoreCase("SPV/Staf")){
            fragment = new Activity_Eviden_Staft();
        }else if(userValidasi.equalsIgnoreCase("DM")){
            fragment = new Activity_Eviden_DM();
        }else if(userValidasi.equalsIgnoreCase("MB")){
            fragment =new Activity_Eviden_MB();
        }else{
            return null;
        }

        return fragment;
    }


    private Fragment Validasi(String userValidasi){

        if(userValidasi.equalsIgnoreCase("DM")){
            fragment = new Activity_Validasi_DM();
        }else if(userValidasi.equalsIgnoreCase("MB")){
            fragment = new Activity_Validasi_DM();
        }else if(userValidasi.equalsIgnoreCase("GM")){
            fragment = new Activity_Validasi_DM();
        }else{
            fragment = null;
        }

        return fragment;
    }


}
