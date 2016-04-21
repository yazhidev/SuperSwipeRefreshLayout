package com.yazhi.swipelayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yazhi.superswiperefreshlayout.SuperSwipeRefreshLayout;

import java.util.ArrayList;

/**
 * Created by zyz on 2016/4/19
 * QQ: 344100167
 * Github: https://github.com/yazhi1992
 */
public class MainActivity extends AppCompatActivity {

    private ListView mLv;
    ArrayList<String> list = new ArrayList<>();
    private SuperSwipeRefreshLayout mView;
    public static Handler sHandler = new Handler();
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //测试数据
        //this is data for test
        for (int i = 0; i < 15; i++) {
            list.add("Number  " + (i + 1) + ",  this is Test");
        }
        mView = (SuperSwipeRefreshLayout) findViewById(R.id.myview);
        mLv = (ListView) findViewById(R.id.listview);
        final Adapter adapter = new Adapter();
        mLv.setAdapter(adapter);

        //设置（下拉刷新）小圆圈的位置（刚进入界面时的刷新位置请在xml布局中设置）
        mView.setProgressViewOffset(false, 0, 48);
        //设置小圆圈颜色
        mView.setColorSchemeColors(Color.BLUE);

        mView.setOnRefreshListener(new SuperSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(final int type) {
                sHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        switch (type) {
                            case 0: //进入界面时加载的操作 refresh when created
                                list.add(0, "Refresh when created");
                                //关闭启动加载刷新动画
                                //finish animation
                                mView.setRefreshing(false);
                                break;
                            case 1: //下拉刷新 pull down to refresh
                                list.add(0, "Pull to refresh");
                                //关闭下拉拉刷新动画
                                //finish animation
                                mView.setRefreshing(false);
                                break;
                            case 2: //上拉刷新 pull up to refresh
                                list.add("Up to refresh, number 1");
                                list.add("Up to refresh, number 2");
                                list.add("Up to refresh, number 3");
                                list.add("Up to refresh, number 4");
                                //关闭上拉刷新动画
                                //finish animation
                                mView.setUpRefreshing(false);
                                break;
                            default:
                                break;
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, 3000);
            }
        });

    }


    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View inflate = getLayoutInflater().inflate(R.layout.item, null);
            TextView text = (TextView) inflate.findViewById(R.id.textview);
            text.setText(list.get(position));
            return inflate;
        }
    }
}
