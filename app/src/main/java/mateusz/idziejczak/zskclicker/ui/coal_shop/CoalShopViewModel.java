package mateusz.idziejczak.zskclicker.ui.coal_shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CoalShopViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CoalShopViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is coal shop fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}