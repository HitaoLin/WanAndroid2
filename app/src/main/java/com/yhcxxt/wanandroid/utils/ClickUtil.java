package com.yhcxxt.wanandroid.utils;

/**
 * <pre>
 *     date:20190424
 *     desc:防止Button多次点击
 *     address: https://blog.csdn.net/ecjtuhq/article/details/80101197
 * </pre>
 */
public class ClickUtil {
    private static long mLastClickTime;

    /**
     * 判断当前点击现象是否是快速的点击，若是的则返回true，在客户端中要屏蔽这种现象。
     *
     * @return
     */
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        long deltaTime = time - mLastClickTime;
        if (0 < deltaTime && deltaTime < 800) {
            return true;
        }
        mLastClickTime = time;

        return false;
    }
}
