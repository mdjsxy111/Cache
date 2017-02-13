package com.qf.sxy.day29_cache;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.qf.sxy.day29_cache.adapter.MyAdapter;
import com.qf.sxy.day29_cache.asynctask.DownLoadJsonTask;
import com.qf.sxy.day29_cache.bean.News;
import com.qf.sxy.day29_cache.interfaceutils.ImageCallBack;
import com.qf.sxy.day29_cache.interfaceutils.JsonCallBack;
import com.qf.sxy.day29_cache.uri.Cans;
import com.qf.sxy.day29_cache.utils.CacheUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements JsonCallBack,ImageCallBack {

    private ListView mLv;
    private TextView mTv;

    private MyAdapter adapter;
    private  List<News> totalsList = new ArrayList<>();
    private CacheUtils cacheUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化化View  atl+shift+m
        initView();
        //下载数据
        new DownLoadJsonTask(this).execute(Cans.JSON_PATH);

        cacheUtils = new CacheUtils(this);

        adapter = new MyAdapter(cacheUtils,totalsList,this);
        //将数据设置到ListView
        mLv.setAdapter(adapter);
        mLv.setEmptyView(mTv);
    }

    private void initView() {
        mLv = ((ListView) findViewById(R.id.lv));
        mTv = ((TextView) findViewById(R.id.tv));
    }

    //回调接口返回的数据
    @Override
    public void setListData(List<News> list) {
        //有数据了
        totalsList.addAll(list);
        Log.e("AAA","==>"+totalsList.size());
        adapter.notifyDataSetChanged();

    }

    @Override
    public void updataBitmap(Bitmap bitmap, String key) {
        //更新ListView 图片
       ImageView imageView = (ImageView) mLv.findViewWithTag(key);
        if(imageView!= null&& bitmap!=null){
            //展示图片
            imageView.setImageBitmap(bitmap);
        }
    }
}
