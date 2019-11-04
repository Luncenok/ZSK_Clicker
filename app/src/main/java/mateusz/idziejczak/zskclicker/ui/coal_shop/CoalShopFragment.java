package mateusz.idziejczak.zskclicker.ui.coal_shop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import mateusz.idziejczak.zskclicker.R;

public class CoalShopFragment extends Fragment {

    private CoalShopViewModel coalShopViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        coalShopViewModel =
                ViewModelProviders.of(this).get(CoalShopViewModel.class);
        View root = inflater.inflate(R.layout.fragment_coal_shop, container, false);
        final TextView textView = root.findViewById(R.id.text_tools);
        coalShopViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}