package com.csdn.jinxu.mahout.classifier.naivebayes;

import org.apache.mahout.classifier.naivebayes.StandardNaiveBayesClassifier;
import org.apache.mahout.math.DenseVector;

/**
 * 实现描述：mahout贝叶斯分类入口
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-31 下午11:40
 */
public class MainApp {

    public static void main(String[] args) {
        StandardNaiveBayesClassifier classifier = MahoutNBClassifier.train();
        System.out.println(MahoutNBClassifier.maxIndex(classifier.classifyFull(new DenseVector(new double[]{1.0, 0.0, 0.0, 0.0}))));

    }

}
