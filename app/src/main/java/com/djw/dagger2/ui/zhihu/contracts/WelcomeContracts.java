package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;

/**
 * Created by JasonDong on 2017/4/26.
 */

public interface WelcomeContracts {

    interface View extends BaseView {

        void showWelcome(String url);

    }

    interface Presenter extends BasePresenter<View> {

        void getWelcome();

    }

}
