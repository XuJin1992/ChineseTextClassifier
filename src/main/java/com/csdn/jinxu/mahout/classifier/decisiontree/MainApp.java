package com.csdn.jinxu.mahout.classifier.decisiontree;

import org.apache.mahout.classifier.df.DecisionForest;
import org.apache.mahout.classifier.df.data.Data;
import org.apache.mahout.classifier.df.data.DescriptorException;
import org.apache.mahout.classifier.df.tools.ForestVisualizer;

/**
 * 实现描述：mahout决策树分类入口
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-31 下午8:54
 */
public class MainApp {
    private static final String[] TRAIN_DATA = {
            "sunny,hot,high,FALSE,no",
            "sunny,hot,high,TRUE,no",
            "overcast,hot,high,FALSE,yes",
            "rainy,mild,high,FALSE,yes",
            "rainy,cool,normal,FALSE,yes",
            "rainy,cool,normal,TRUE,no",
            "overcast,cool,normal,TRUE,yes",
            "sunny,mild,high,FALSE,no",
            "sunny,cool,normal,FALSE,yes",
            "rainy,mild,normal,FALSE,yes",
            "sunny,mild,normal,TRUE,yes",
            "overcast,mild,high,TRUE,yes",
            "overcast,hot,normal,FALSE,yes"}
           /* "rainy,mild,high,TRUE,no"}"sunny,85,85,FALSE,no",
            "sunny,80,90,TRUE,no", "overcast,83,86,FALSE,yes",
            "rainy,70,96,FALSE,yes", "rainy,68,80,FALSE,yes", "rainy,65,70,TRUE,no",
            "overcast,64,65,TRUE,yes", "sunny,72,95,FALSE,no",
            "sunny,69,70,FALSE,yes", "rainy,75,80,FALSE,yes", "sunny,75,70,TRUE,yes",
            "overcast,72,90,TRUE,yes", "overcast,81,75,FALSE,yes",
            "rainy,71,91,TRUE,no"}*/;

    private static final String[] TEST_DATA = {"sunny,hot,high,FALSE,yes"};/*{"overcast,81,75,FALSE,-"}*/
    ;

    private static final String DESCRIPTOR = "C C C C L";


    public static void main(String[] args) throws DescriptorException {

        Data data = MahoutDFClassifier.generateTrainingData(DESCRIPTOR, false, TRAIN_DATA);
        DecisionForest forest = MahoutDFClassifier.train(data);

        Data testData = MahoutDFClassifier.generateTrainingData(DESCRIPTOR, false, TEST_DATA);

        String visualization = ForestVisualizer.toString(forest, data.getDataset(), null);
        System.out.println(visualization);

        double[][] predictions = new double[testData.size()][];
        forest.classify(testData, predictions);
        for (int testId = 0; testId < testData.size(); ++testId) {
            for (int treeId = 0; treeId < predictions[testId].length; ++treeId) {
                int maxIndex = (int) predictions[testId][treeId];
                System.out.println(data.getDataset().labels()[maxIndex]);
                //System.out.println("test dataset " + testId + ": " + predictions[testId][treeId] + ",noValue:" + noValue + ",yesValue:" + yesValue);
            }
        }
    }
}
