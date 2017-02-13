package com.qf.sxy.day29_cache.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.qf.sxy.day29_cache.asynctask.DownLoadImageTask;
import com.qf.sxy.day29_cache.interfaceutils.ImageCallBack;

/**
 * Created by sxy on 2016/10/20.
 */
public class CacheUtils {

    private LruCache<String,Bitmap> cache;//声明Lrucache对象
    private Context context;//声明Lrucache对象
    private ImageCallBack imageCallBack;

    public CacheUtils(Context context){

        this.context = context;
        this.imageCallBack = (ImageCallBack) context;

        long MaxMemory =  Runtime.getRuntime().maxMemory();
        int max = (int) (MaxMemory/8);
        cache = new LruCache<String,Bitmap>(max){
            //计算存储图片的大小
            @Override
            protected int sizeOf(String key, Bitmap value) {
               // value.getRowBytes()*value.getHeight();
                return value.getByteCount() ;
            }
        };
    }



    //获取图片
    public  Bitmap getBitmap(String key){

        //从内存中获取图片
        Bitmap bitmap = cache.get(key);
        if(bitmap!=null){
            Log.e("AA","==LRU==");
            return bitmap;
        }else{
            //从SD卡获取
           bitmap =  Utils.getBitmapFromSD(key,context);
            if(bitmap!=null){
                cache.put(key,bitmap);//再次放入缓存
                Log.e("AA","==SD==");
                return bitmap;
            }else{
                Log.e("AA","==NET==");
                //从网络加载
                new DownLoadImageTask(cache,context,imageCallBack).execute(key);

            }
        }
        return null;
    }

    /**
     * 存图片
     * @param imagePath
     * @param bitmap
     */
    public void putBitmap(String imagePath,Bitmap bitmap){
        //存到Lrucache
        cache.put(imagePath,bitmap);
        //存到SD
        Utils.setBitmapToSD(imagePath,bitmap,context);
    }
}
