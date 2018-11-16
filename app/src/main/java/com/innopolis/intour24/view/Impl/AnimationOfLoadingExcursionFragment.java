package com.innopolis.intour24.view.Impl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.innopolis.intour24.R;
import com.innopolis.intour24.presenter.Impl.GroupLoadingAnimationPresenterImpl;
import com.innopolis.intour24.presenter.LoadingExcursionPresenter;
import com.innopolis.intour24.view.LoadingExcursionAnimation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Timkabor on 6/6/2017.
 */

public class AnimationOfLoadingExcursionFragment extends android.support.v4.app.Fragment implements LoadingExcursionAnimation
{
    @BindView(R.id.busIcon) ImageView busIcon;
    private Animation runBusRight,runBusLeft;
    private Animation.AnimationListener animationListenerRight,animationListenerLeft;
    private LoadingExcursionPresenter presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_loading_excursions, container, false);
        ButterKnife.bind(this, view);
        presenter = new GroupLoadingAnimationPresenterImpl(this);
        presenter.initAnim();

        return view;
    }
    @Override
    public void initAnim() {
        runBusRight = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.bus_going_right);
        runBusLeft = AnimationUtils.loadAnimation(getActivity().getApplicationContext(),
                R.anim.bus_going_left);
        initListeners();
    }

    @Override
    public void startAnim() {
        busIcon.startAnimation(runBusRight);
        runBusRight.setAnimationListener(animationListenerRight);
        runBusLeft.setAnimationListener(animationListenerLeft);
    }

    @Override
    public void appear() {
        try {((MainActivity)getActivity()).showFragment(this);}
        catch (Exception ignored) {}
    }

    @Override
    public void dissapear() {
       try  {((MainActivity)getActivity()).hideFragment(this);}
       catch (Exception ignored) {}
    }

    @Override
    public void stopAnim() {
        busIcon.clearAnimation();
    }

    public void initListeners()
    {
        animationListenerRight = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                busIcon.startAnimation(runBusLeft);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
        animationListenerLeft  = new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                busIcon.startAnimation(runBusRight);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

}
