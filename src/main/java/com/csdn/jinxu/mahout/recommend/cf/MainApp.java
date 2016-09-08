package com.csdn.jinxu.mahout.recommend.cf;


import org.apache.mahout.cf.taste.common.TasteException;

import java.io.IOException;

/**
 * 实现描述：推荐算法入口
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-3 下午10:35
 */
public class MainApp {

    public static void main(String[] args) throws IOException, TasteException {
        String input = MainApp.class.getResource("/data.txt").getPath();

        int person = 1;
        int count = 2;


        MahoutUserBased userBased = new MahoutUserBased();
        userBased.process(input,person, count);


        MahoutItemBased itemBased = new MahoutItemBased();
        itemBased.process(input,person, count);
    }
}
