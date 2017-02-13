package com.qf.sxy.day29_cache.utils;

import com.qf.sxy.day29_cache.bean.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sxy on 2016/10/20.
 */
public class ParseJsonUtils {

    public static List<News> parseJson(String  jsonStr){

//        "subject": "IBM核心业务将是超智能计算机",
//                "summary": "罗睿兰认为IBM未来的核心业务是能够像人一样讲话和推理的超级智能计算机。",
//                "cover": "/Attachs/Article/288231/4d1ca5d13a01421482a87290830db856_padmini.JPG",
        List<News> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONObject jsonObject1 = jsonObject.getJSONObject("paramz");
            JSONArray jsonArray = jsonObject1.getJSONArray("feeds");
            for (int i = 0; i <jsonArray.length() ; i++) {
                JSONObject jsonObject2 = jsonArray.getJSONObject(i).getJSONObject("data");
                String subject = jsonObject2.getString("subject");
                String summary = jsonObject2.getString("summary");
                String cover = "http://litchiapi.jstv.com/"+jsonObject2.getString("cover");
                News news = new News(subject,summary,cover);
                list.add(news);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return list;
    }
}
