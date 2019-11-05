package mateusz.idziejczak.zskclicker.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.util.Locale;

import mateusz.idziejczak.zskclicker.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Animation animation;
    private long klikow, czasklik1, czasklik2, cps;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        //TODO:zmienić poniższe
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        animation = AnimationUtils.loadAnimation(getContext(),
                R.anim.wegielzaklik);

        ConstraintLayout klikerLayout = root.findViewById(R.id.layoutKlikania);
        final ImageView coalTankIV = root.findViewById(R.id.coalTankHomeIV);
        final ImageView coalIV = root.findViewById(R.id.coalHomeIV);
        final ImageView coal2IV = root.findViewById(R.id.coalHome2IV);
        final ImageView coal3IV = root.findViewById(R.id.coalHome3IV);
        klikerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (klikow % 3 == 0) {
                    coal3IV.clearAnimation();
                    coal3IV.setVisibility(View.INVISIBLE);
                    coalIV.setVisibility(View.VISIBLE);
                    coalIV.startAnimation(animation);
                } else if (klikow % 3 == 1) {
                    coalIV.clearAnimation();
                    coalIV.setVisibility(View.INVISIBLE);
                    coal2IV.setVisibility(View.VISIBLE);
                    coal2IV.startAnimation(animation);
                } else if (klikow % 3 == 2) {
                    coal2IV.clearAnimation();
                    coal2IV.setVisibility(View.INVISIBLE);
                    coal3IV.setVisibility(View.VISIBLE);
                    coal3IV.startAnimation(animation);
                }
                klikow++;
                czasklik2 = czasklik1;
                czasklik1 = System.currentTimeMillis();
                cps = 1000/(czasklik1-czasklik2);
                textView.setText(String.format(Locale.getDefault(), "kasy: %d\ncps: %d ", klikow, cps));
            }
        });

        return root;
    }
}