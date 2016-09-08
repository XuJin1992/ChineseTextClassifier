package com.csdn.jinxu.weka.classifier.decisontree;

import weka.classifiers.trees.J48;
import weka.core.Instances;

/**
 * 实现描述：J48操作入口
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-1 下午6:23
 */
public class MainApp {
    private static String TRAIN_FILE_NAME = MainApp.class.getResource("/weather.nominal.arff").getPath();
    private static String TEST_FILE_NAME = MainApp.class.getResource("/weather.nominal.test.arff").getPath();

    public static void main(String[] args) throws Exception {

        /**过滤的属性*/
        String[] options = null;
      /*  String[] options = new String[2];
        options[0] = "-R"; // "range"
        options[1] = "1"; //第一个属性*/

        Instances trainInstances = WekaJ48TClassifier.generate(TRAIN_FILE_NAME, options);

        String params[] = new String[3];//训练参数数组
        params[0] = "-R";//使用reduced error pruning
        params[1] = "-M";//叶子上的最小实例数
        params[2] = "3";//set叶子上的最小实例数

        J48 j48 = WekaJ48TClassifier.train(trainInstances, params);

        Instances testInstances = WekaJ48TClassifier.generate(TEST_FILE_NAME, options);

        System.out.println(testInstances.instance(0).toString());
        int maxIndex = (int) j48.classifyInstance(testInstances.instance(0));
        System.out.println(trainInstances.attribute(trainInstances.numAttributes() - 1).value(maxIndex));
    }
}
