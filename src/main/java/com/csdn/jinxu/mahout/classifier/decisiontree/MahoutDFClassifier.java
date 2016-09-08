package com.csdn.jinxu.mahout.classifier.decisiontree;

import com.google.common.collect.Lists;
import org.apache.mahout.classifier.df.DecisionForest;
import org.apache.mahout.classifier.df.builder.DecisionTreeBuilder;
import org.apache.mahout.classifier.df.data.Data;
import org.apache.mahout.classifier.df.data.DataLoader;
import org.apache.mahout.classifier.df.data.Dataset;
import org.apache.mahout.classifier.df.data.DescriptorException;
import org.apache.mahout.classifier.df.node.Node;

import java.util.List;
import java.util.Random;

/**
 * 实现描述：决策森林分类器
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-31 下午8:06
 */
public class MahoutDFClassifier {

    private static Random random = new Random();


    /**
     * 训练数据
     *
     * @param datas 训练数据集
     * @return
     */
    public static DecisionForest train(Data... datas) {
        List<Node> trees = Lists.newArrayList();
        for (Data data : datas) {
            DecisionTreeBuilder builder = new DecisionTreeBuilder();
            builder.setM(data.getDataset().nbAttributes() - 1);
            builder.setMinSplitNum(2);
            builder.setComplemented(false);
            trees.add(builder.build(random, data));
        }
        return new DecisionForest(trees);
    }

    /**
     * 转化数据集
     *
     * @param descriptor 　属性标识
     * @param regression
     * @param strDataSet 　数据集
     * @return
     * @throws DescriptorException
     */
    public static Data generateTrainingData(CharSequence descriptor, boolean regression, String[] strDataSet) throws DescriptorException {

        Dataset dataset = DataLoader.generateDataset(descriptor, regression, strDataSet);

        Data data = DataLoader.loadData(dataset, strDataSet);

        return data;
    }

}
