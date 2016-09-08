package com.csdn.jinxu.mahout.classifier.naivebayes;

import org.apache.mahout.classifier.naivebayes.NaiveBayesModel;
import org.apache.mahout.classifier.naivebayes.StandardNaiveBayesClassifier;
import org.apache.mahout.math.DenseMatrix;
import org.apache.mahout.math.DenseVector;
import org.apache.mahout.math.Vector;
import org.apache.mahout.math.Vector.Element;


/**
 * 实现描述：mahout贝叶斯分类器
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-31 下午11:32
 */
public class MahoutNBClassifier {

    public static StandardNaiveBayesClassifier train() {
        NaiveBayesModel model = createStandardNaiveBayesModel();
        return new StandardNaiveBayesClassifier(model);
    }

    private static NaiveBayesModel createStandardNaiveBayesModel() {
        double[][] matrix = {
                {0.7, 0.1, 0.1, 0.3},
                {0.4, 0.4, 0.1, 0.1},
                {0.1, 0.0, 0.8, 0.1},
                {0.1, 0.1, 0.1, 0.7}};

        double[] labelSumArray = {1.2, 1.0, 1.0, 1.0};
        double[] featureSumArray = {1.3, 0.6, 1.1, 1.2};

        DenseMatrix weightMatrix = new DenseMatrix(matrix);
        DenseVector labelSum = new DenseVector(labelSumArray);
        DenseVector featureSum = new DenseVector(featureSumArray);

        // now generate the model
        return new NaiveBayesModel(weightMatrix, featureSum, labelSum, null, 1.0f);
    }


    protected static int maxIndex(Vector instance) {
        int maxIndex = -1;
        double maxScore = Integer.MIN_VALUE;
        for (Element label : instance.all()) {
            if (label.get() >= maxScore) {
                maxIndex = label.index();
                maxScore = label.get();
            }
        }
        return maxIndex;
    }

}
