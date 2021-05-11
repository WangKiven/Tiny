package com.zxy.tiny.test;

import android.app.Application;
import android.util.Log;

import com.kiven.kutils.tools.KContext;
import com.kiven.kutils.tools.KUtil;
import com.zxy.recovery.callback.RecoveryCallback;
import com.zxy.recovery.core.Recovery;
import com.zxy.tiny.Tiny;

/**
 * Created by zhengxiaoyong on 2017/3/14.
 */
public class App extends KContext {
    @Override
    protected void init() {
        super.init();

        KUtil.Config config = new KUtil.Config();
        config.setDebug(true);
        KUtil.init(this, config);

        Tiny.getInstance().debug(true).init(this);

        Recovery.getInstance().debug(true).callback(new RecoveryCallback() {
            @Override
            public void stackTrace(String stackTrace) {
                Log.e("zxy", "stackTrace:" + stackTrace);
            }

            @Override
            public void cause(String cause) {
                Log.e("zxy", "cause:" + cause);
            }

            @Override
            public void exception(String throwExceptionType, String throwClassName, String throwMethodName, int throwLineNumber) {

            }

            @Override
            public void throwable(Throwable throwable) {

            }
        }).init(this);
    }
}
