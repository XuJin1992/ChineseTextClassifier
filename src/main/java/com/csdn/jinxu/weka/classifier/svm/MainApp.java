package com.csdn.jinxu.weka.classifier.svm;


import weka.classifiers.functions.LibSVM;
import weka.classifiers.meta.AttributeSelectedClassifier;
import weka.core.Instances;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现描述：J48操作入口
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-1 下午6:23
 */
public class MainApp {
    private static String TRAIN_FILE_NAME =MainApp.class.getResource("/soybean.arff").getPath();
    private static String TEST_FILE_NAME = MainApp.class.getResource("/soybean.arff").getPath();

    public static void main(String[] args) throws Exception {

        Instances trainInstances= WekaSVMClassifier.generate(TRAIN_FILE_NAME, null);

        LibSVM classifier = WekaSVMClassifier.train(trainInstances, null);

        Instances testInstances= WekaSVMClassifier.generate(TEST_FILE_NAME, null);

        int maxIndex = (int) classifier.classifyInstance(testInstances.instance(0));
        System.out.println(trainInstances.attribute(trainInstances.numAttributes() - 1).value(maxIndex));

    }
}
