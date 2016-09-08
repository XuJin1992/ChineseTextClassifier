package com.csdn.jinxu.weka.classifier.naivebayes;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
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
    private static String TRAIN_FILE_NAME =MainApp.class.getResource("/weather.nominal.arff").getPath();
    private static String TEST_FILE_NAME = MainApp.class.getResource("/weather.nominal.test.arff").getPath();

    public static void main(String[] args) throws Exception {

        Instances trainInstances = WekaNBClassifier.generate(TRAIN_FILE_NAME, null);

        NaiveBayes naiveBayes = WekaNBClassifier.train(trainInstances, null);

        Instances testInstances = WekaNBClassifier.generate(TEST_FILE_NAME, null);

        System.out.println(testInstances.instance(0).toString());
        int maxIndex = (int) naiveBayes.classifyInstance(testInstances.instance(0));
        System.out.println(trainInstances.attribute(trainInstances.numAttributes() - 1).value(maxIndex));


        Instance testInst;
        Evaluation testingEvaluation = new Evaluation(trainInstances);
        int length = trainInstances.numInstances();
        for (int i = 0; i < length; i++) {
            testInst = trainInstances.instance(i);
            testingEvaluation.evaluateModelOnceAndRecordPrediction(naiveBayes, testInst);
        }

        // 打印分类结果
        System.out.println("分类的正确率" + (1 - testingEvaluation.errorRate()));
    }
}
