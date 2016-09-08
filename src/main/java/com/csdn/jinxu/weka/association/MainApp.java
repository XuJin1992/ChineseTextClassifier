package com.csdn.jinxu.weka.association;

import weka.associations.AbstractAssociator;
import weka.core.Instances;


/**
 * 实现描述：关联规则入口
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-6 下午3:00
 */
public class MainApp {

    private static String TRAIN_FILE_NAME =MainApp.class.getResource("/supermarket.arff").getPath();

    public static void main(String[] args) throws Exception {

        /**过滤的属性*/
        String[] options = null;
      /*  String[] options = new String[2];
        options[0] = "-R"; // "range"
        options[1] = "1"; //第一个属性*/

        Instances trainInstances = WekaApriori.generate(TRAIN_FILE_NAME, options);

        String params[] =null;

        AbstractAssociator associator = WekaApriori.findAssociationsRules(trainInstances, params);

        System.out.println(associator.toString());

        trainInstances = WekaFPGrowth.generate(TRAIN_FILE_NAME, options);

        associator = WekaFPGrowth.findAssociationsRules(trainInstances, params);

        System.out.println(associator.toString());


    }
}
