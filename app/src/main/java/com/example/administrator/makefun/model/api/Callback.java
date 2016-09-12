package com.example.administrator.makefun.model.api;

public interface Callback<T> {
    public void onSccuss(T data);
    public void onLoading();
    public void onFaild();
}