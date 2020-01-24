package com.donnicholaus.schoolapp.admin.ui.lecturer;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LecturerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LecturerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is lecturer fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}