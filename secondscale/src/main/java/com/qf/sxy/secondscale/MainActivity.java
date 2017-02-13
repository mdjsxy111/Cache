package com.qf.sxy.secondscale;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;

    //进行操作的图片
    private Bitmap resouceBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = ((ImageView) findViewById(R.id.iv));

        //获取处理的图片
        resouceBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.a);
    }

    //点击按钮进行缩小图片
    public void MyClick(View view) {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        //将图片转成outputStream
        resouceBitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);
        //获取缩放之后的图片
        Bitmap bitmap = getImageScale(outputStream.toByteArray(),100,100);
        iv.setImageBitmap(bitmap);

    }

    //对图片二次采样
    public Bitmap getImageScale(byte[] bytes,int newWidth ,int newHeight){
        //获取options 对象  为了获取边缘区域
        BitmapFactory.Options options = new BitmapFactory.Options();
        //为了仅仅获取图片的边缘解码区
        options.inJustDecodeBounds = true;
        //返回图片null
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);

        //获取宽和高  边缘
        int w =  options.outWidth;
        int h =  options.outHeight;

        int scaleWidth = w/newWidth;
        int scaleHeight = h/newHeight;

        //获取最终的缩放比例   获取最大的
        int scale = scaleWidth>scaleHeight?scaleWidth:scaleHeight;

        //设置缩放的比例  (缩小)比例
        options.inSampleSize = scale;

        //为了仅仅获取图片的边缘解码区
        options.inJustDecodeBounds = false;
        Bitmap bitmap =  BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        return bitmap;
    }
    //对图片二次采样
    public Bitmap getImageScale2(byte[] bytes,int newWidth ,int newHeight){
        //获取options 对象  为了获取边缘区域
        BitmapFactory.Options options = new BitmapFactory.Options();
        //为了仅仅获取图片的边缘解码区
        options.inJustDecodeBounds = true;
        //返回图片null
        BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);

        //获取宽和高  边缘
        int w =  options.outWidth;
        int h =  options.outHeight;

//        int scaleWidth = w/newWidth;
//        int scaleHeight = h/newHeight;
//
//        //获取最终的缩放比例   获取最大的
//        int scale = scaleWidth>scaleHeight?scaleWidth:scaleHeight;
        int scale = 1;
        while(w/scale>newWidth||h/scale>newHeight){
            scale *= 2;
        }

        //设置缩放的比例  (缩小)比例
        options.inSampleSize = scale;

        //为了仅仅获取图片的边缘解码区
        options.inJustDecodeBounds = false;
        Bitmap bitmap =  BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        return bitmap;
    }
}
