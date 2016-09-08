package com.csdn.jinxu.weka.classifier.ann;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;

/**
 * 实现描述：多层感知的BP(backpropagation)
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-4 下午1:10
 */
public class WekaMPClassifier {
    public static MultilayerPerceptron train(Instances instances, String[] options) throws Exception {

        MultilayerPerceptron classifier = new MultilayerPerceptron();
        classifier.setOptions(options);
        classifier.buildClassifier(instances);
        return classifier;
    }

    public static Instances generate(String arffFileName, String[] options) throws Exception {
        ArffLoader atf = new ArffLoader();
        File inputFile = new File(arffFileName);
        atf.setFile(inputFile);
        /**格式化的训练数据*/
        Instances instancesTrain = atf.getDataSet();

        /**设置分类属性所在行（第一行为0号）,instancesTrain.numAttributes()可以取得属性总数*/
        instancesTrain.setClassIndex(instancesTrain.numAttributes() - 1);

        Remove remove = new Remove();
        remove.setOptions(options);
        remove.setInputFormat(instancesTrain);
        instancesTrain = Filter.useFilter(instancesTrain, remove);
        return instancesTrain;
    }

}
