package com.bwie.caolei.myapp.ui.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.bwie.caolei.myapp.R;
import com.bwie.caolei.myapp.ui.adapter.MianViewPagerAdapter;
import com.bwie.caolei.myapp.ui.fragment.FragmentOne;
import com.bwie.caolei.myapp.ui.fragment.FragmentThree;
import com.bwie.caolei.myapp.ui.fragment.FragmentTwo;

import java.util.ArrayList;
import java.util.List;

/**
 * autour: 曹磊
 * date: 2017/1/5 8:32
 * update: 2017/1/5
 */

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TabLayout mMain_tab;
    private Toolbar mToolbar;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private ActionBarDrawerToggle mToggle;
    FloatingActionButton mFloatingActionButton;

    private List<Fragment> fragment_list = new ArrayList<Fragment>();
    private ArrayList<String> fm_tab = new ArrayList<>();
    private ViewPager mMain_vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏标题栏以及状态栏
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }


    private void initView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mMain_tab = (TabLayout) findViewById(R.id.main_tab);
        mMain_vp = (ViewPager) findViewById(R.id.main_vp);
        mFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        setSupportActionBar(mToolbar);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mToggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(mToggle);
        mToggle.syncState();


        mNavigationView.setNavigationItemSelectedListener(this);

    }

    private void initData() {
        //添加标题
        fm_tab.add("日报");
        fm_tab.add("专栏");
        fm_tab.add("微信");

        fragment_list.add(new FragmentOne());
        fragment_list.add(new FragmentTwo());
        fragment_list.add(new FragmentThree());

        MianViewPagerAdapter adapter = new MianViewPagerAdapter(getSupportFragmentManager(), fragment_list, fm_tab);
        //ViewPager加载布局
        mMain_vp.setAdapter(adapter);
        //TabLayout加载viewpager
        mMain_tab.setupWithViewPager(mMain_vp);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
