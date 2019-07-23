package com.util;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.util.List;

import okhttp3.Call;

public class Net {
    public static void post(String url, Params params, List<File> map, final HttpListener httpListener) {
        PostFormBuilder params2 = OkHttpUtils.post().url(url).params(params);
        if (map != null) {
            for (int i = 0; i < map.size(); i++) {
                params2.addFile("" + i + '\001', ((File) map.get(i)).getName(), (File) map.get(i));
            }
        }

        params2.build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {
                httpListener.OnError(e.getMessage());
            }

            @Override
            public void onResponse(String response, int id) {
                httpListener.OnSuccess(response);
            }


        });
    }


    public static void post(String url, String header, Params params, List<File> map, final HttpListener httpListener) {

        PostFormBuilder params2 = OkHttpUtils.post().url(url).params(params);

        if (map != null) {

            for (int i = 0; i < map.size(); i++) {

                params2.addFile("" + i + '\001', ((File) map.get(i)).getName(), (File) map.get(i));
            }
        }

        if (header != null) {

            params2.addHeader("token", header);
        }

        params2.build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        httpListener.OnError(e.getMessage());
                        Log.e("结果", "错误" + e.getMessage());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("结果", response);
                        httpListener.OnSuccess(response);
                    }
                });
    }

    public static interface HttpListener {
        void OnSuccess(String param1String);

        void OnError(String param1String);
    }
}
