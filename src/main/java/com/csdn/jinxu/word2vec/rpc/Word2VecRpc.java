package com.csdn.jinxu.word2vec.rpc;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * 实现描述：调用本地的rpc服务
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-29 下午8:40
 */
public class Word2VecRpc {

    private static String command = "/home/jinxu/学习/git/git_project/w2v/trunk/distance";

    private static String params = "/home/jinxu/学习/git/git_project/w2v/trunk/vectors.bin";


    public static void main(String[] args) throws Exception {

        try {
            Process process = Runtime.getRuntime().exec(new String[]{command, params});

         /*   Thread.sleep(10*1000);
            System.out.println("process");
            BufferedWriter output=new BufferedWriter(new OutputStreamWriter(process.getOutputStream(),"UTF-8"));
            output.write("are");
            output.flush();
            output.close();
*/
            System.out.println("output");


            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), "utf-8"));
            String s;
            while ((s = reader.readLine()) != null) {
                System.out.println(s);
            }
            reader.close();
            System.out.println("input");

            int exitCode = process.waitFor();
            process.destroy();
            if (exitCode != 0) {
                throw new Exception("word2vec返回错误码:" + exitCode);
            }

        } catch (Exception e) {
            throw new Exception("word2vec执行失败" + e);
        }

    }
}
