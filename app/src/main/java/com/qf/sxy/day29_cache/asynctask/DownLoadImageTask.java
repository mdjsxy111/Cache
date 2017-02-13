package com.qf.sxy.day29_cache.asynctask;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.LruCache;

import com.qf.sxy.day29_cache.interfaceutils.ImageCallBack;
import com.qf.sxy.day29_cache.utils.Utils;

/**
 * Created by sxy on 2016/10/20.
 */
public class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {

    private  String imagePath = "";
    private LruCache<String,Bitmap> lrucache;
    private Context context;
    private ImageCallBack imageCallBack;

    public DownLoadImageTask(LruCache<String,Bitmap> lrucache, Context context,ImageCallBack imageCallBack){
        this.lrucache = lrucache;
        this.context = context;
        this.imageCallBack = imageCallBack;
    }
    @Override
    protected Bitmap doInBackground(String... params) {
        //下载图片
        imagePath = params[0];
        Bitmap bitmap =   Utils.getBitmapFromNet(imagePath);

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        //处理图片
        if(bitmap!=null){
            //存到Lrucache
            lrucache.put(imagePath,bitmap);
            //存到SD
            Utils.setBitmapToSD(imagePath,bitmap,context);
            //展示
            imageCallBack.updataBitmap(bitmap,imagePath);
        }
    }
}
