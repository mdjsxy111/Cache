package com.qf.sxy.day29_cache.asynctask;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.qf.sxy.day29_cache.interfaceutils.JsonCallBack;
import com.qf.sxy.day29_cache.utils.ParseJsonUtils;
import com.qf.sxy.day29_cache.utils.Utils;

/**
 * Created by sxy on 2016/10/20.
 */
public class DownLoadJsonTask extends AsyncTask<String,Void,String> {

    private JsonCallBack jsonCallBack;
    public DownLoadJsonTask(JsonCallBack jsonCallBack){
        this.jsonCallBack = jsonCallBack;
    }
    @Override
    protected String doInBackground(String... params) {
        //下载数据
        String  jsonStr =  Utils.getJsonFromNet(params[0]);

        return jsonStr;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        //返回数据
        if(!TextUtils.isEmpty(s)){
            //解析后的数据
            jsonCallBack.setListData(ParseJsonUtils.parseJson(s));
        }


    }
}
