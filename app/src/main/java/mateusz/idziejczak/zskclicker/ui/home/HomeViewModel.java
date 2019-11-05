package mateusz.idziejczak.zskclicker.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("kasa: 0\ncps: 0");
    }

    public LiveData<String> getText() {
        return mText;
    }
}