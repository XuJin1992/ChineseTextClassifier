package com.csdn.jinxu.weka.classifier.ann;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;

/**
 * 实现描述：多层感知的BP(backpropagation)入口
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-4 下午1:11
 */
public class MainApp {

    private static String TRAIN_FILE_NAME =MainApp.class.getResource("/weather.nominal.arff").getPath();
    private static String TEST_FILE_NAME = MainApp.class.getResource("/weather.nominal.test.arff").getPath();

    public static void main(String[] args) throws Exception {

        /**过滤的属性*/
        String[] options = null;
      /*  String[] options = new String[2];
        options[0] = "-R"; // "range"
        options[1] = "1"; //第一个属性*/

        Instances trainInstances = WekaMPClassifier.generate(TRAIN_FILE_NAME, options);

        String params[] = new String[3];//训练参数数组
        params[0] = "-R";//使用reduced error pruning
        params[1] = "-M";//叶子上的最小实例数
        params[2] = "3";//set叶子上的最小实例数

        MultilayerPerceptron classifier = WekaMPClassifier.train(trainInstances, params);

        Instances testInstances = WekaMPClassifier.generate(TEST_FILE_NAME, options);

        System.out.println(testInstances.instance(0).toString());
        int maxIndex = (int) classifier.classifyInstance(testInstances.instance(0));
        System.out.println(trainInstances.attribute(trainInstances.numAttributes() - 1).value(maxIndex));
    }
}
