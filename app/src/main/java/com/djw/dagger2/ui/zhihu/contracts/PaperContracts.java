package com.djw.dagger2.ui.zhihu.contracts;

import com.djw.dagger2.base.BasePresenter;
import com.djw.dagger2.base.BaseView;
import com.djw.dagger2.data.zhihu.paper.PaperBaseData;

import java.util.List;

/**
 * Created by JasonDong on 2017/3/23.
 */

public interface PaperContracts {

    interface View extends BaseView {

        void showPaperData(List<PaperBaseData> daypaperData);

        void showBeforeData(List<PaperBaseData> daypaperBeforeData);

    }

    interface Presenter extends BasePresenter<View> {

        void getPaperData();

        void getBeforeData(String date);

    }

}
