package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.zhihu.DaypaperLongcommentData;

/**
 * Created by JasonDong on 2017/3/24.
 */

public interface LongCommentContracts {

    interface View extends BaseView {

        void showLongComment(DaypaperLongcommentData daypaperLongcommentData);

    }

    interface Presenter extends BasePresenter<View> {

        void getLongComment(String id);

    }

}
