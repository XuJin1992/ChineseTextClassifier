package com.csdn.jinxu.word2vec;

import com.csdn.jinxu.ansj.AnsjClient;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainApp {
    private static String DIR = MainApp.class.getResource("/data_set/data1").getPath();
    private static String INPUT = "%s/input.text";
    private static String TMPFILE = "%s/tmpFile.text";
    private static String OUTPUT = "%s/output.bin";
    private static Word2VEC WORD2VEC = new Word2VEC();

    public static void main(String[] args) throws IOException {
        int index=1;
        trainByJava(index);
        String word;
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("请输入数字：");
            word = scanner.nextLine();
            if(null==word){
                break;
            }
            distancesByJava(index, word);
        }
       /* WORD2VEC.loadGoogleModel(MainApp.class.getResource("/data_set").getPath()+"/vectors.bin");
        String word;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请输入数字：");
            word = scanner.nextLine();
            if (null == word) {
                break;
            }
            System.out.println(WORD2VEC.distance(word));
        }*/

    }


    private static void trainByJava(int index) throws IOException {
        String input = String.format(INPUT, DIR, index);
        String tmpFile = String.format(TMPFILE, DIR, index);
        String output = String.format(OUTPUT, DIR, index);

        long start = System.currentTimeMillis();
        try {
            AnsjClient.process(input, tmpFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("分词耗时： " + (System.currentTimeMillis() - start));

        Learn learn = new Learn();
        start = System.currentTimeMillis();
        learn.learnFile(new File(tmpFile));
        System.out.println("训练耗时： " + (System.currentTimeMillis() - start));
        learn.saveModel(new File(output));
    }

    private static void distancesByJava(int index, String word) throws IOException {
        long start = System.currentTimeMillis();
        String output = String.format(OUTPUT, DIR, index);
        WORD2VEC.loadJavaModel(output);
        System.out.println(WORD2VEC.distance(word));
        System.out.println("相似度检索耗时： " + (System.currentTimeMillis() - start));

    }
}
