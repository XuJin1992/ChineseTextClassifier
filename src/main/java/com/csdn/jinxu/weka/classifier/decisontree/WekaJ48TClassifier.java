package com.csdn.jinxu.weka.classifier.decisontree;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;

/**
 * 实现描述：wekaJ48决策树
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-1 下午6:14
 */
public class WekaJ48TClassifier {

    public static J48 train(Instances instances, String[] options) throws Exception {
        /**J48用以建立一个剪枝或不剪枝的c4.5决策树*/
        J48 classifier = new J48();
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
