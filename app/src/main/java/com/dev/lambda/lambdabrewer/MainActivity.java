package com.dev.lambda.lambdabrewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.dev.lambda.lambdabrewer.Fragments.Alarmas;
import com.dev.lambda.lambdabrewer.Fragments.Dashboard;
import com.dev.lambda.lambdabrewer.Fragments.Recetas;
import com.dev.lambda.lambdabrewer.Data.BJCPStyles;
import com.dev.lambda.lambdabrewer.Model.Estilo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.pager) ViewPager viewPager;
    @BindView(R.id.tab_layout) TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_recetas);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_dashboard);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_alarmas);

        //INICIALIZO BJCPStyles para obtener context
        try {
            InputStream is = this.getAssets().open("styleguideBJCP.json");
            BJCPStyles.getInstance().init(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Estilo prueba = BJCPStyles.getInstance().getEstiloBjcpPorNombre("1B - American Lager");

        Log.d("INFO",prueba.IBUmin);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new Recetas(), "Recetas");
        adapter.addFragment(new Dashboard(), "Dashboard");
        adapter.addFragment(new Alarmas(), "Alarmas");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            //return mFragmentTitleList.get(position);
            return "";
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void altaReceta(View v) {
        startActivity(new Intent(this,AltaReceta.class));
    }
}
