package com.csdn.jinxu.mahout.recommend.cf;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.recommender.GenericItemBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.ItemSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 实现描述：基于物品的推荐
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-3 下午10:48
 */
public class MahoutItemBased {

    public void process(String fileName,int person, int count) throws IOException, TasteException {
        DataModel model = new FileDataModel(new File(fileName));//文件名一定要是绝对路径
        ItemSimilarity similarity = new PearsonCorrelationSimilarity(model);
        Recommender recommender = new GenericItemBasedRecommender(model, similarity);
        List<RecommendedItem> recommendations = recommender.recommend(person, count);//为用户person推荐count个ItemID
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("ItemBased--" + recommendation);
        }
    }
}
