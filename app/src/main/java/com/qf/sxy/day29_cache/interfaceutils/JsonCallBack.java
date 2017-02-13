package com.qf.sxy.day29_cache.interfaceutils;

import com.qf.sxy.day29_cache.bean.News;

import java.util.List;

/**
 * Created by sxy on 2016/10/20.
 * 回调接口 为了返回  Json数据
 */
public interface JsonCallBack {

    public void setListData(List<News> list);
}
