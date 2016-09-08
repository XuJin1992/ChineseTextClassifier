package com.csdn.jinxu.ansj;

import java.io.IOException;

/**
 * 实现描述：ansj入口
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-27 上午10:25
 */
public class MainApp {
    public static void main(String[] args) {
        String input = MainApp.class.getResource("/datavw.txt").getPath();
        String output =MainApp.class.getResource("/datavw_result.txt").getPath();
        try {
            AnsjClient.process(input, output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("stop");
    }
}
