package mateusz.idziejczak.zskclicker.ui.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Locale;

import mateusz.idziejczak.zskclicker.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Animation animation, animation2, animation3, animationTrain;
    private int clicks;
    private long cps=0;
    private SharedPreferences.Editor editor;
    private SharedPreferences sharedPref;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView coinsTV = root.findViewById(R.id.text_home);
        final TextView cpsTV = root.findViewById(R.id.text_home_2);
        final ProgressBar progressBar = root.findViewById(R.id.progressBar);

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.wegielzaklik);
        animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.wegielzaklik);
        animation3 = AnimationUtils.loadAnimation(getContext(), R.anim.wegielzaklik);
        animationTrain = AnimationUtils.loadAnimation(getContext(), R.anim.trainmove);

        Context context = getActivity();
        sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        clicks = sharedPref.getInt(getString(R.string.saved_coins_key), 1);
        int trainNumber = sharedPref.getInt(getString(R.string.saved_train_key), 1);
        coinsTV.setText(String.format(Locale.getDefault(), "coins: %d", clicks, cps));

        ConstraintLayout klikerLayout = root.findViewById(R.id.layoutKlikania);
        final ImageView coalTankIV = root.findViewById(R.id.coalTankHomeIV);
        final ImageView tankIV = root.findViewById(R.id.tankHomeIV);
        final ImageView trainIV = root.findViewById(R.id.trainHomeIV);
        final ImageView coalIV = root.findViewById(R.id.coalHomeIV);
        final ImageView coal2IV = root.findViewById(R.id.coalHome2IV);
        final ImageView coal3IV = root.findViewById(R.id.coalHome3IV);

        if(trainNumber ==1) trainIV.setImageResource(R.drawable.ic_train_svgrepo_com);
        if(trainNumber ==2) trainIV.setImageResource(R.drawable.ic_train_svgrepo_com_03);

        progressBar.setProgress(clicks);

        klikerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clicks % 3 == 0) {
                    coal3IV.setVisibility(View.INVISIBLE);
                    coalIV.setVisibility(View.VISIBLE);
                    coalIV.startAnimation(animation);
                } else if (clicks % 3 == 1) {
                    coalIV.setVisibility(View.INVISIBLE);
                    coal2IV.setVisibility(View.VISIBLE);
                    coal2IV.startAnimation(animation2);
                } else if (clicks % 3 == 2) {
                    coal2IV.setVisibility(View.INVISIBLE);
                    coal3IV.setVisibility(View.VISIBLE);
                    coal3IV.startAnimation(animation3);
                }
                clicks++;
                cps++;
                coinsTV.setText(String.format(Locale.getDefault(), "coins: %d", clicks));
                progressBar.setProgress(clicks);
                progressBar.getProgressDrawable().setColorFilter(
                        Color.BLACK, android.graphics.PorterDuff.Mode.SRC_IN);
            }
        });

        final Handler handler = new Handler();
        final int delay = 1000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cpsTV.setText(String.format(Locale.getDefault(), "cps: %d ",cps));

                if(cps>0){
                    tankIV.startAnimation(animationTrain);
                    trainIV.startAnimation(animationTrain);
                }
                if (clicks >= 2000) {
                    progressBar.setMax(4000);
                }

                cps=0;
                handler.postDelayed(this, delay);
            }
        }, delay);

        return root;
    }

    @Override
    public void onPause() {
        editor.putInt(getString(R.string.saved_coins_key), clicks).commit();
        super.onPause();
    }

    @Override
    public void onStop() {
        editor.putInt(getString(R.string.saved_coins_key), clicks).commit();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        editor.putInt(getString(R.string.saved_coins_key), clicks).commit();
        super.onDestroy();
    }
}