package mateusz.idziejczak.zskclicker.ui.upgrades;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UpgradesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UpgradesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is upgrades fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}