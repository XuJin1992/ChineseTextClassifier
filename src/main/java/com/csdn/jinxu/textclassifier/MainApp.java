package com.csdn.jinxu.textclassifier;

import com.google.common.collect.Maps;
import org.ansj.util.FilterModifWord;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 实现描述：分类入口
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-12 下午7:22
 */
public class MainApp {
    private static String YES_CLASS_INPUT_DIR = MainApp.class.getResource("/text/yes/").getPath();

    private static String NO_CLASS_INPUT_DIR = MainApp.class.getResource("/text/no/").getPath();

    private static String STOP_WORD_FILE = MainApp.class.getResource("/text/stop_word.txt").getPath();

    private static String INPUT_FILE1 = MainApp.class.getResource("/text/test.txt").getPath();

    public static void main(String[] args) throws IOException {
        HashMap<String, String> stopWordMap = AnsjSpliter.filter(new File(STOP_WORD_FILE));
        FilterModifWord.setUpdateDic(stopWordMap);

        Map<String, Integer> yesDocMap = AnsjSpliter.process(new File(YES_CLASS_INPUT_DIR));
        System.out.println("yesDocMap" + yesDocMap);

        Map<String, Integer> noDocMap = AnsjSpliter.process(new File(NO_CLASS_INPUT_DIR));
        System.out.println("noDocMap" + noDocMap);

        Map<String, Integer> testDocMap = AnsjSpliter.process(new File(INPUT_FILE1));
        System.out.println("testDocMap" + testDocMap);

        Map<String, Double> wordWeightMap = Maps.newHashMap();

        NBClassifier classifier = new NBClassifier(yesDocMap, noDocMap, wordWeightMap);
        double[] classProb = classifier.classify(testDocMap);

        classProb = classifier.normalized(classProb);
        print(classProb);

        classifier.train(testDocMap, false);
        classProb = classifier.classify(testDocMap);

        classProb = classifier.normalized(classProb);
        print(classProb);

    }

    private static void print(double[] classProb) {
        System.out.println("yes 概率" + classProb[0]);
        System.out.println("no 概率" + classProb[1]);
        if (classProb[0] > classProb[1]) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

    }

}
