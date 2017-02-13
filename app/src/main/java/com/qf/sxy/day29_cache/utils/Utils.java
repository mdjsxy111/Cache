package com.qf.sxy.day29_cache.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Utils {

	// 下载json
	public static String getJsonFromNet(String path) {

		HttpURLConnection conn = null;
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();
			conn.setConnectTimeout(1000 * 5);
			conn.setReadTimeout(1000 * 5);
			reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String str;
			while ((str = reader.readLine()) != null) {
				buffer.append(str);
			}
			return buffer.toString();

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return null;
	}

	//网络下载图片
	public static Bitmap getBitmapFromNet(String path) {

		HttpURLConnection conn = null;
		try {
			URL url = new URL(path);
			conn = (HttpURLConnection) url.openConnection();

			conn.setConnectTimeout(1000 * 5);
			conn.setReadTimeout(1000 * 5);
			return BitmapFactory.decodeStream(conn.getInputStream());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
		}

		return null;

	}

	// 从SD卡获取图片
	public static Bitmap getBitmapFromSD(String key, Context mContext) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			Bitmap map = BitmapFactory.decodeFile(mContext.getExternalCacheDir().getAbsolutePath() + File.separator
					+ key.substring(key.lastIndexOf("/") + 1));
			return map;
		}
		return null;
	}

	// 图片保存到SD
	public static void setBitmapToSD(String key, Bitmap map, Context mContext) {
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
			File file = null;
			file = new File(mContext.getExternalCacheDir().getAbsolutePath() + File.separator
					+ key.substring(key.lastIndexOf("/") + 1));
			FileOutputStream outputStream = null;
			try {
				file.createNewFile();
				outputStream = new FileOutputStream(file);

				map.compress(CompressFormat.JPEG, 100, outputStream);
				outputStream.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				if (outputStream!=null) {
					try {
						outputStream.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

	}

}
