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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import java.util.Locale;

import mateusz.idziejczak.zskclicker.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private Animation animation, animation2, animation3, animationTrain, animationBucket;
    private int clicks = 0, delay = 1000, coins, trainNumber, cps = 0, cpc = 1, secondsBonus;
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
        final ConstraintLayout klikerLayout = root.findViewById(R.id.layoutKlikania);
        final ImageView
                coalTankIV = root.findViewById(R.id.coalTankHomeIV),
                tankIV = root.findViewById(R.id.tankHomeIV),
                trainIV = root.findViewById(R.id.trainHomeIV),
                coalIV = root.findViewById(R.id.coalHomeIV),
                railsIV = root.findViewById(R.id.railsIV),
                bucketIV = root.findViewById(R.id.bucketHomeIV),
                coal2IV = root.findViewById(R.id.coalHome2IV),
                coal3IV = root.findViewById(R.id.coalHome3IV);
        final Context context = getActivity();

        bucketIV.setColorFilter(Color.rgb(21, 21, 150));
        railsIV.setColorFilter(Color.rgb(195,195,195));
        trainIV.setColorFilter(Color.rgb(56, 31, 0));
        tankIV.setColorFilter(Color.rgb(56, 31, 0));

        animation = AnimationUtils.loadAnimation(getContext(), R.anim.wegielzaklik);
        animation2 = AnimationUtils.loadAnimation(getContext(), R.anim.wegielzaklik);
        animation3 = AnimationUtils.loadAnimation(getContext(), R.anim.wegielzaklik);
        animationTrain = AnimationUtils.loadAnimation(getContext(), R.anim.trainmove);
        animationBucket = AnimationUtils.loadAnimation(getContext(), R.anim.bucketanim);
        sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        editor = sharedPref.edit();
        coins = sharedPref.getInt(getString(R.string.saved_coins_key), 1);
        clicks = sharedPref.getInt(getString(R.string.saved_clicks_key), 0);
        trainNumber = sharedPref.getInt(getString(R.string.saved_train_key), 1);
        cpc = sharedPref.getInt(getString(R.string.saved_cpc_key), 1);

        //TODO: Przed wydaniem zamienić tutaj
        if (clicks % 5 == 2/*400*/) {
            trainIV.setColorFilter(Color.rgb(130, 73, 1));
            tankIV.setColorFilter(Color.rgb(130, 73, 1));
        } else if (clicks % 5 == 3/*450*/) {
            trainIV.setColorFilter(Color.rgb(151, 71, 3));
            tankIV.setColorFilter(Color.rgb(151, 71, 3));
        } else if (clicks % 5 == 4/*499*/) {
            trainIV.setColorFilter(Color.rgb(194, 55, 0));
            tankIV.setColorFilter(Color.rgb(194, 55, 0));
            cpc = 0;
        }

        progressBar.setProgress(clicks);
        progressBar.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.colorPrimary), android.graphics.PorterDuff.Mode.SRC_IN);
        coinsTV.setText(String.format(Locale.getDefault(), "coins: %d", coins));

        if (trainNumber == 1) trainIV.setImageResource(R.drawable.ic_train_svgrepo_com);
        if (trainNumber == 2) trainIV.setImageResource(R.drawable.ic_train_svgrepo_com_03);

        klikerLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cpc != 0) {

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
                    coins += cpc;
                    cps++;

                    editor.putInt(getString(R.string.saved_coins_key), coins).apply();
                    editor.putInt(getString(R.string.saved_clicks_key), clicks).apply();
                    coinsTV.setText(String.format(Locale.getDefault(), "coins: %d", coins));
                    progressBar.setProgress(clicks);

                    //TODO: i tu też zamienić
                    if (clicks % 5 == 2/*400*/) {
                        trainIV.setColorFilter(Color.rgb(130, 73, 1));
                        tankIV.setColorFilter(Color.rgb(130, 73, 1));
                    } else if (clicks % 5 == 3/*450*/) {
                        trainIV.setColorFilter(Color.rgb(151, 71, 3));
                        tankIV.setColorFilter(Color.rgb(151, 71, 3));
                    } else if (clicks % 5 == 4/*499*/) {
                        trainIV.setColorFilter(Color.rgb(194, 55, 0));
                        tankIV.setColorFilter(Color.rgb(194, 55, 0));
                        cpc = 0;
                    }

                } else {
                    Toast.makeText(context, "The train is overheated. Please cool it with water bucket", Toast.LENGTH_SHORT).show();
                    bucketIV.startAnimation(animationBucket);
                }

                if (clicks >= progressBar.getMax()) {
                    progressBar.setMax(progressBar.getMax()*2);
                    clicks=0;
                    progressBar.setProgress(clicks);
                }
            }
        });

        bucketIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trainIV.setColorFilter(Color.BLACK);
                tankIV.setColorFilter(Color.BLACK);
                cpc=1;
            }
        });

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                cpsTV.setText(String.format(Locale.getDefault(), "cps: %d ", cps));

                if (cps > 0) {
                    tankIV.startAnimation(animationTrain);
                    trainIV.startAnimation(animationTrain);
                }

                cps = 0;
                handler.postDelayed(this, delay);
            }
        }, delay);

        return root;
    }

    @Override
    public void onPause() {
        editor.putInt(getString(R.string.saved_coins_key), coins).commit();
        editor.putInt(getString(R.string.saved_clicks_key), clicks).commit();
        super.onPause();
    }

    @Override
    public void onStop() {
        editor.putInt(getString(R.string.saved_coins_key), coins).commit();
        editor.putInt(getString(R.string.saved_clicks_key), clicks).commit();
        super.onStop();
    }

    @Override
    public void onDestroy() {
        editor.putInt(getString(R.string.saved_coins_key), coins).commit();
        editor.putInt(getString(R.string.saved_clicks_key), clicks).commit();
        super.onDestroy();
    }
}