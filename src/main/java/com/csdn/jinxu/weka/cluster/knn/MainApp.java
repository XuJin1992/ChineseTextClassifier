package com.csdn.jinxu.weka.cluster.knn;

import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

/**
 * 实现描述：KNN聚类
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-4 下午1:34
 */
public class MainApp {

    private static String TRAIN_FILE_NAME =MainApp.class.getResource("/glass.arff").getPath();
    private static String TEST_FILE_NAME = MainApp.class.getResource("/glass.arff").getPath();

    public static void main(String[] args) throws Exception {

        String[] options = new String[2];
        options[0] = "-R"; // "range"
        options[1] = "10"; //第一个属性*/

        Instances trainInstances = WekaSKMeansCluster.generate(TRAIN_FILE_NAME, null);

        SimpleKMeans cluster = WekaSKMeansCluster.train(trainInstances, null);

        Instances testInstances = WekaSKMeansCluster.generate(TEST_FILE_NAME, null);

        /**cluster聚类*/
      /*  System.out.println(testInstances.instance(0).toString());
        int maxIndex = (int) cluster.clusterInstance(testInstances.instance(0));
        System.out.println(trainInstances.attribute(trainInstances.numAttributes() - 1).value(maxIndex));*/


        Instances resultInstences = cluster.getClusterCentroids();
        System.out.println("CentroIds: " + resultInstences);
    }
}
