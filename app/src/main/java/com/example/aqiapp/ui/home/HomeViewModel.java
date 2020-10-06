package com.example.aqiapp.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aqiapp.api.object.Aqi;
import com.example.aqiapp.repository.AqiRepository;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<List<Aqi>> currentAqis;
    private AqiRepository aqiRepository = new AqiRepository();
    private MutableLiveData<String> mText;

    public HomeViewModel() {
    }

    public LiveData<List<Aqi>> getCurrentAqi() {
        if (currentAqis == null) {
            currentAqis = aqiRepository.getCurrentAqi();
        }
        return currentAqis;
    }
}