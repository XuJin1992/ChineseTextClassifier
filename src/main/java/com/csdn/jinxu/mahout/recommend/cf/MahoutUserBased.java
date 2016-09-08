package com.csdn.jinxu.mahout.recommend.cf;

import org.apache.mahout.cf.taste.common.TasteException;
import org.apache.mahout.cf.taste.impl.model.file.FileDataModel;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.PearsonCorrelationSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 实现描述：基于用户的推荐
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-3 下午10:45
 */
public class MahoutUserBased {

    public void process(String fileName,int person, int count) throws IOException, TasteException {
        DataModel model = new FileDataModel(new File(fileName));
        UserSimilarity similarity = new PearsonCorrelationSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(2, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);
        List<RecommendedItem> recommendations = recommender.recommend(person, count);//为用户person推荐count个ItemID
        for (RecommendedItem recommendation : recommendations) {
            System.out.println("UserBased--" + recommendation);
        }
    }
}
