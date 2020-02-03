package com.donnicholaus.schoolapp.db;


public interface QueryResponse<T> {
    void onSuccess(T data);
    void onFailure(String message);
}
