package com.djw.retrofit_rxjava_mvp_dagger.http;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by JasonDong on 2017/3/25.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface WxUrl {

}
