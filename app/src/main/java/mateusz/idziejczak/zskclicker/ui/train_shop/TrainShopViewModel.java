package mateusz.idziejczak.zskclicker.ui.train_shop;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrainShopViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrainShopViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Here, you can buy some other trains");
    }

    public LiveData<String> getText() {
        return mText;
    }
}