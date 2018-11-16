package com.innopolis.intour24.presenter.Impl;

import android.content.Context;

import com.innopolis.intour24.presenter.LoadingExcursionPresenter;
import com.innopolis.intour24.view.Impl.AnimationOfLoadingExcursionFragment;

/**
 * Created by Timkabor on 6/6/2017.
 */

public class GroupLoadingAnimationPresenterImpl implements LoadingExcursionPresenter {
    private AnimationOfLoadingExcursionFragment view;
    private Context context;
    public GroupLoadingAnimationPresenterImpl(AnimationOfLoadingExcursionFragment view)
    {
        this.view = view;
        context = view.getActivity().getApplicationContext();
    }
    @Override
    public void initAnim() {
        view.initAnim();
    }

    @Override
    public void startAnim() {
        view.startAnim();
    }

    @Override
    public void stopAnim() {

    }
}
