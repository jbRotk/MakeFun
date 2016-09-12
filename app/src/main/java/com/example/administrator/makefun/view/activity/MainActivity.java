package com.example.administrator.makefun.view.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.administrator.makefun.R;
import com.example.administrator.makefun.utils.ScreenUtils;
import com.example.administrator.makefun.view.Adapter.Main_tab_Adapter;
import com.example.administrator.makefun.view.fragment.TodayNews;
import com.example.administrator.makefun.view.fragment.TuCaoPic;
import com.example.administrator.makefun.view.fragment.VideoRecommendation;
import com.example.administrator.makefun.view.fragment.WaidiReport;
import com.example.administrator.makefun.view.fragment.WeekColum;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.support.design.widget.TabLayout.MODE_FIXED;
import static android.support.design.widget.TabLayout.OnClickListener;

public class MainActivity extends BaseActivity implements OnClickListener{
    /*控件*/
    @InjectView(R.id.tabs)
    protected TabLayout tab;
    @InjectView(R.id.viewpager)
    protected ViewPager pagers;
    @InjectView(R.id.SlidebarToggle)
    protected ImageView slidebarToggle;

    protected SlidingMenu slidingMenu;

    /*子界面的属性集合*/
    ArrayList<Fragment> fragments;
    ArrayList<String> titles;

    /*初始化各个子界面*/
    WeekColum weekColum;
    TodayNews todayNews;
    WaidiReport waidiReport;
    VideoRecommendation videoRecommendation;

    /*Adapter*/
    Main_tab_Adapter pagers_adapter;

    /*侧滑栏的items*/
    int item = 0;
    LinearLayout YaoWen;
    LinearLayout TuKu;
    LinearLayout CePing;
    LinearLayout Setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        //startActivity(new Intent(MainActivity.this, SettingActivity.class));
    }

    private void init()
    {
        /*初始化必要值*/
        ButterKnife.inject(this);
        fragments = new ArrayList<Fragment>();
        titles = new ArrayList<String>();

        /*初始化子界面的数据*/
        weekColum = new WeekColum();
        todayNews = new TodayNews();
        waidiReport = new WaidiReport();
        videoRecommendation = new VideoRecommendation();

        TuCaoPic tuCaoPic = new TuCaoPic();
        fragments.add(tuCaoPic);
        //fragments.add(weekColum);
        fragments.add(todayNews);
        fragments.add(waidiReport);
        fragments.add(videoRecommendation);

        /*从String.xml里面取出数组值并且赋值*/
        String[] ary = getResources().getStringArray(R.array.News);
        for(int i=0;i<ary.length;i++)
        {
            titles.add(ary[i]);
            tab.addTab(tab.newTab().setTag(ary[i]));
        }
        /*tablayout属性初始化*/
        tab.setTabMode(MODE_FIXED);

        /*初始化Adapter*/
        pagers_adapter = new Main_tab_Adapter(MainActivity.this.getSupportFragmentManager(),fragments,titles);

        /*设置Adapter*/
        pagers.setAdapter(pagers_adapter);
        tab.setupWithViewPager(pagers);

        /*初始化SlidingMenu*/
        initSlidingMenu();
    }

    private void initSlidingMenu(){
        slidingMenu=new SlidingMenu(this);//创建侧边栏
        //设置菜单模式，LEFT(仅左侧边栏) RIGHT(仅右侧边栏)
//LEFT_RIGHT(左右侧边栏)
        slidingMenu.setMode(SlidingMenu.LEFT);
        slidingMenu.setMenu(R.layout.slide_layout);//设置第一个（左）侧边栏
        //设置第二个(右)侧边栏,若设置LEFT_RIGHT模式使用该方法设置右侧边栏
        //slidingMenu.setSecondaryMenu(R.layout.fragment_pretty_laydy_pic);
        //将侧边栏粘连到Activity上
        slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        //设置侧边栏阴影大小
        slidingMenu.setShadowWidth(50);
        //设置偏离距离
       // slidingMenu.setBehindOffsetRes(R.dimen.LargerSize);
        slidingMenu.setBehindWidth(ScreenUtils.getInstance(MainActivity.this).getWidth()*2/3);
        //全屏模式，全屏滑动都可打开
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);


        /*Iem的选项实例*/
        YaoWen = (LinearLayout)slidingMenu.findViewById(R.id.YaoWen);
        TuKu = (LinearLayout) slidingMenu.findViewById(R.id.TuKu);
        CePing = (LinearLayout)slidingMenu.findViewById(R.id.CePing);
        Setting = (LinearLayout)slidingMenu.findViewById(R.id.Setting);
        setBtnListener();
    }

    private void setBtnListener()
    {
        /*设置item监听*/
        YaoWen.setOnClickListener(this);
        TuKu.setOnClickListener(this);
        CePing.setOnClickListener(this);
        Setting.setOnClickListener(this);

        /*设置显示侧边栏*/
        slidebarToggle.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.YaoWen:
                item = 0;
                YaoWen.setBackgroundResource(R.color.Slide_item_clicked);
                TuKu.setBackgroundResource(R.drawable.button_selector);
                CePing.setBackgroundResource(R.drawable.button_selector);
                Setting.setBackgroundResource(R.drawable.button_selector);
                slidingMenu.toggle();
                break;
            case R.id.TuKu:
                item = 1;
                YaoWen.setBackgroundResource(R.drawable.button_selector);
                TuKu.setBackgroundResource(R.color.Slide_item_clicked);
                CePing.setBackgroundResource(R.drawable.button_selector);
                Setting.setBackgroundResource(R.drawable.button_selector);
                slidingMenu.toggle();
                break;
            case R.id.CePing:
                item = 2;
                YaoWen.setBackgroundResource(R.drawable.button_selector);
                TuKu.setBackgroundResource(R.drawable.button_selector);
                CePing.setBackgroundResource(R.color.Slide_item_clicked);
                Setting.setBackgroundResource(R.drawable.button_selector);
                slidingMenu.toggle();
                break;
            case R.id.Setting:
                item = 3;
                YaoWen.setBackgroundResource(R.drawable.button_selector);
                TuKu.setBackgroundResource(R.drawable.button_selector);
                CePing.setBackgroundResource(R.drawable.button_selector);
                Setting.setBackgroundResource(R.color.Slide_item_clicked);
                slidingMenu.toggle();
                break;
            case R.id.SlidebarToggle:
                slidingMenu.toggle();
                break;
        }
    }
}

