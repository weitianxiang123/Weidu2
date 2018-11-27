package com.bw.movie.mvp.view;

import android.content.Context;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bw.movie.net.HttpHelper;
import com.bw.movie.net.HttpListener;
import com.google.gson.Gson;

import java.util.Map;



public abstract class AppDelegate implements IDelegate {
    private View rootView;
    private SparseArray<View> views = new SparseArray<>();
    @Override
    public void initData() {

    }

    @Override
    public void create(LayoutInflater inflater, ViewGroup viewGroup, Bundle bundle) {
        rootView = inflater.inflate(getLayout(),viewGroup,false);
    }

    @Override
    public void initContext(Context context) {

    }

    @Override
    public View getRootView() {
        return rootView;
    }
    // 获取布局
    public abstract int getLayout();

    // 返回控件
    public <T extends View> T get(int id){
        T view = (T) views.get(id);
        if (view == null){
            view = rootView.findViewById(id);
            views.put(id,view);
        }
        return view;
    }
    // 设置点击
    public void setClick(View.OnClickListener listener,int...ids){
        if (ids == null){
            return;
        }
        for (int id:ids){
            get(id).setOnClickListener(listener);
        }
    }

    // Get 请求 返回字符串
    public void getString(int type,String url,Map<String,String> map){
        doHttpString("GET",type,url,map);
    }
    // Post 请求 返回字符串
    public void postString(int type,String url,Map<String,String> map){
        doHttpString("POST",type,url,map);
    }
    // Get 请求 返回JavaBean
    public <T> void getBean(int type,String url,Map<String,String> map,Class<T> cls){
        doHttpBean("GET",type,url,map,cls);
    }
    // Post 请求 返回JavaBean
    public <T> void postBean(int type,String url,Map<String,String> map,Class<T> cls){
        doHttpBean("POST",type,url,map,cls);
    }



    // 请求网络 返回String
    private void doHttpString(String method, final int type, String url, Map<String,String> map){
        HttpHelper helper = new HttpHelper();
        if ("GET".equals(method)){
            helper.get(url,map);
        }else {
            helper.post(url,map);
        }
        helper.result(new HttpListener() {
            @Override
            public void success(String data) {
                successString(type,data);
            }

            @Override
            public void fail(String error) {
                error(error);
            }
        });
    }

    // 请求网络 返回 JavaBean
    private <T> void doHttpBean(String method, final int type, String url, Map<String,String> map, final Class<T> cls){
        HttpHelper helper = new HttpHelper();
        if ("GET".equals(method)){
            helper.get(url,map);
        }else {
            helper.post(url,map);
        }
        helper.result(new HttpListener() {
            @Override
            public void success(String data) {
                T bean = new Gson().fromJson(data,cls);
                successBean(type,bean);
            }

            @Override
            public void fail(String error) {
                error(error);
            }
        });
    }

    public void successString(int type,String data){}
    public <T> void successBean(int type,T bean){}
    public void error(String error){}
}

