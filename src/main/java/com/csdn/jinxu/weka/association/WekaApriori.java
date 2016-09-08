package com.csdn.jinxu.weka.association;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

import java.io.File;

/**
 * 实现描述：Apriori关联规则
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-6 下午3:00
 */
public class WekaApriori {

    public static Apriori findAssociationsRules(Instances instances, String[] options) throws Exception {
        Apriori associator = new Apriori();
        associator.setOptions(options);
        associator.buildAssociations(instances);
        return associator;
    }

    public static Instances generate(String arffFileName, String[] options) throws Exception {
        ArffLoader atf = new ArffLoader();
        File inputFile = new File(arffFileName);
        atf.setFile(inputFile);
        /**格式化的训练数据*/
        Instances instancesTrain = atf.getDataSet();

        Remove remove = new Remove();
        remove.setOptions(options);
        remove.setInputFormat(instancesTrain);
        instancesTrain = Filter.useFilter(instancesTrain, remove);
        return instancesTrain;
    }

}
