# SuperSwipeRefreshLayout

## 简介

- 基于Google官网出品[SwipeRrefreshLayout](http://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout.html)优化
- 支持下拉刷新、上拉刷新和一进入页面就加载刷新，并保持加载动画的一致性

![image](https://github.com/yazhi1992/SuperSwipeRefreshLayout/blob/master/app.gif)  

## 使用Gradle构建

在你moudle项目的build.gradle**(Moudle:你的项目)**里添加(注意:不是project的build.gradle)
    
    dependencies {
      compile 'com.yazhi:superswiperefreshlayout:1.1.1
    }

## 使用方法

###XML布局中
 
  **如果使用自定义属性请记住要加上xmlns:app="http://schemas.android.com/apk/res-auto"**
  
  SuperSwipeRefreshLayout布局内只能有一个直接子View，这个子view要求必须是可以滑动的，例如scrollview，listview，recyclerview，如果子view直接放relativelayout等不能滑动的控件时上拉刷新会出现问题。

    
    <com.yazhi.superswiperefreshlayout.SuperSwipeRefreshLayout
        android:id="@+id/layout"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:AllowUpToRefresh="true"         // 是否允许上拉刷新
        app:BottomLocationPercent="0.88"    // 底部刷新动画的位置（控件高度的百分比，当填写的数值大于1时默认为0.88）
        app:RefreshWhenCreate="true"        // 是否一进入布局时就加载刷新
        >

        <ListView
            android:id="@+id/listview"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:scrollbars="none">
        </ListView>
        
    </com.yazhi.superswiperefreshlayout.SuperSwipeRefreshLayout>


###根据回调的参数区分是源自哪类刷新操作
**关闭上拉刷新动画与其他两类不同，是setUpRefreshing(false)**


    SuperSwipeRefreshLayout layout = (SuperSwipeRefreshLayout) findViewById(R.id.layout);

    layout.setOnRefreshListener(new SuperSwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int type) {
                switch (type) {
                    case 0:  //type = 0：进入界面时加载的操作 refresh when activity was created

                        //TODO

                        //业务执行完毕要关闭启动加载刷新动画  finish animation
                        layout.setRefreshing(false);
                        break;
                        
                    case 1:  //type = 1：下拉刷新 pull down to refresh

                        //TODO

                        //业务执行完毕要关闭下拉拉刷新动画  finish animation
                        layout.setRefreshing(false);
                        break;
                        
                    case 2: //type =2: 上拉刷新 pull up to refresh

                        //TODO

                        //业务执行完毕要关闭上拉刷新动画  finish animation
                        layout.setUpRefreshing(false);
                        break;
                        
                    default:
                        break;
                }
            }
        });

###另外提一下两个常用的方法：

        //设置下拉刷新时加载动画的位置
        layout.setProgressViewOffset(false, 0, 48);
        //设置加载动画的颜色，最多可设置四种
        layout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);

因为这个控件是基于官方swiperefreshlayout改动的，所以除了新增的功能和属性、方法，其他与原版一致，更多具体的使用方法请参考
[SwipeRefreshLayout官方文档](http://developer.android.com/reference/android/support/v4/widget/SwipeRefreshLayout.html)。


##最后

我虽热爱分享，但毕竟才疏学浅难免会有错误之处，敬请谅解。欢迎提issues反馈意见，我会尽我所能地去解决问题。谢谢。

互联网精神万岁。
