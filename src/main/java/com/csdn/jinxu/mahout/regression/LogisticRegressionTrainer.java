package com.csdn.jinxu.mahout.regression;

import com.google.common.base.Splitter;
import org.apache.mahout.classifier.sgd.AdaptiveLogisticRegression;
import org.apache.mahout.classifier.sgd.CrossFoldLearner;
import org.apache.mahout.classifier.sgd.L1;
import org.apache.mahout.classifier.sgd.PriorFunction;
import org.apache.mahout.math.RandomAccessSparseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.vectorizer.encoders.ConstantValueEncoder;
import org.apache.mahout.vectorizer.encoders.FeatureVectorEncoder;
import org.apache.mahout.vectorizer.encoders.StaticWordValueEncoder;

import java.util.List;

/**
 * 实现描述：回归demo
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-28 下午1:59
 */
public class LogisticRegressionTrainer {

    private final AdaptiveLogisticRegression learner;
    private CrossFoldLearner classifier;


    private static final FeatureVectorEncoder BIAS_ENCODER = new ConstantValueEncoder("bias");
    private static final FeatureVectorEncoder TEXT_ENCODER = new StaticWordValueEncoder("text");


    public LogisticRegressionTrainer(int numCategories, int numFeatures, PriorFunction prior, int threadCount, int poolSize) {
        this.learner = new AdaptiveLogisticRegression(numCategories, numFeatures, new L1(), threadCount, poolSize);
        this.learner.setInterval(2);
        this.learner.setAveragingWindow(2);
    }


    public void train(List<String> dataSet) {
        double prevLearningRate = 1.0, prevLambda = 1.0;
        while (true) {
            for (String data : dataSet) {
                learner.train(extractCategory(data), extractVector(data));
            }
            if (Math.abs(prevLambda - learner.getBest().getMappedParams()[0]) < 1e-12 && Math.abs(prevLearningRate - learner.getBest().getMappedParams()[1]) < 1e-12) {
                break;
            }
            prevLambda = learner.getBest().getMappedParams()[0];
            prevLearningRate = learner.getBest().getMappedParams()[1];
        }
        this.classifier = learner.getBest().getPayload().getLearner();
    }

    public String classify(String data) {
        String category = "";
        if (this.classifier != null) {
            Vector result = this.classifier.classifyFull(extractVector(data));
            category = transforCategory(result.maxValueIndex());
        }
        return category;
    }

    private int extractCategory(String data) {
        List<String> items = Splitter.on(",").splitToList(data);
        switch (items.get(items.size() - 1)) {
            case "中国":
                return 0;
            case "美国":
                return 1;
            case "俄罗斯":
                return 2;
            case "英国":
                return 3;
            default:
                return 4;
        }
    }

    private String transforCategory(int index) {
        switch (index) {
            case 0:
                return "中国";
            case 1:
                return "美国";
            case 2:
                return "俄罗斯";
            case 3:
                return "英国";
            default:
                return "";
        }
    }

    private Vector extractVector(String data) {
        List<String> items = Splitter.on(",").splitToList(data);
        items = items.subList(0, items.size() - 1);
        Vector vector = new RandomAccessSparseVector(3);

        BIAS_ENCODER.addToVector("", vector); //
        for (String word : items) {
            TEXT_ENCODER.addToVector(word, vector);
        }
        return vector;
    }


}
