package com.qf.sxy.day29_cache.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.qf.sxy.day29_cache.R;
import com.qf.sxy.day29_cache.bean.News;
import com.qf.sxy.day29_cache.utils.CacheUtils;

import java.util.List;

/**
 * Created by sxy on 2016/10/20.
 */
public class MyAdapter extends BaseAdapter {

    private List<News> list;
    private Context context;
    private CacheUtils cacheUtils;

    public MyAdapter(CacheUtils cacheUtils,List<News> list, Context context){
        this.context = context;
        this.list = list;
        this.cacheUtils = cacheUtils;
    }


    @Override
    public int getCount() {
        return list!=null?list.size():0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.itemIv = (ImageView) convertView.findViewById(R.id.item_iv);
            viewHolder.itemTitle = (TextView) convertView.findViewById(R.id.item_tv_title);
            viewHolder.itemContent = (TextView) convertView.findViewById(R.id.item_tv_content);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //展示数据
        News news = list.get(position);
        if(news!=null){
            viewHolder.itemTitle.setText(news.getSubject());
            viewHolder.itemContent.setText(news.getSummary());
            //图片展示  用三级缓存
            viewHolder.itemIv.setTag(news.getCover());
            //通过三级缓存展示图片
            viewHolder.itemIv.setImageBitmap(cacheUtils.getBitmap(news.getCover()));

        }
        return convertView;
    }

    class ViewHolder{
        ImageView itemIv;
        TextView itemTitle,itemContent;

    }
}
