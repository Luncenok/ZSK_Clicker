package mateusz.idziejczak.zskclicker.ui.upgrades;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import mateusz.idziejczak.zskclicker.R;

public class UpgradesFragment extends Fragment {

    private UpgradesViewModel upgradesViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        upgradesViewModel =
                ViewModelProviders.of(this).get(UpgradesViewModel.class);
        View root = inflater.inflate(R.layout.fragment_upgrades, container, false);

        Button
                cpsBtn = root.findViewById(R.id.cpsBtn),
                cpcBtn = root.findViewById(R.id.cpcBtn),
                grznieBtn = root.findViewById(R.id.grzanieBtn);




        return root;
    }
}