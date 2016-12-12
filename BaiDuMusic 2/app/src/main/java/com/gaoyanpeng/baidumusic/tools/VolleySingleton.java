package com.gaoyanpeng.baidumusic.tools;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.gaoyanpeng.baidumusic.app.MyApplication;


public class VolleySingleton {
    private static Context mContext;
    private RequestQueue queue;

    private static VolleySingleton ourInstance
            = new VolleySingleton();


    public static VolleySingleton getInstance() {
        return ourInstance;
    }

    private VolleySingleton() {
        mContext = MyApplication.getContext();
        queue = getQueue();
    }


    private RequestQueue getQueue(){

        if (queue == null){

            synchronized (VolleySingleton.class){
                if (queue == null) {
                    queue = Volley.newRequestQueue(mContext);
                }
            }
        }
        return queue;
    }


    public<T> void _addRequest(Request<T> request){
        queue.add(request);
    }

    public <T> void _addRequest(Request<T> request,Object tag){
        request.setTag(tag);
        _addRequest(request);
    }

    public  void _addRequest(String url, Response.Listener<String> listener , Response.ErrorListener errorListener){
        StringRequest stringRequest = new StringRequest(url,listener,errorListener);
        _addRequest(stringRequest);
    }


    public <T> void _addRequest(String url, Class<T> mClass, Response.Listener<T> listener, Response.ErrorListener errorListener){
        GsonRequest<T> gsonRequest = new GsonRequest<T>(Request.Method.GET,url,mClass,listener,errorListener);_addRequest(gsonRequest);
    }



    public static <T> void addRequest(Request<T> request){
        getInstance()._addRequest(request);
    }

    public static <T> void addRequest(Request<T> request, Object tag){
        getInstance()._addRequest(request,tag);
    }

    public static <T> void addRequest(String url, Response.Listener listener, Response.ErrorListener errorListener){
        getInstance()._addRequest(url,listener,errorListener);
    }

    public static <T> void addRequest(String url, Class<T> mClass, Response.Listener listener, Response.ErrorListener errorListener){
        getInstance()._addRequest(url,mClass,listener,errorListener);
    }

    public void removeRequest(Object tag){
        queue.cancelAll(tag);
    }
}
