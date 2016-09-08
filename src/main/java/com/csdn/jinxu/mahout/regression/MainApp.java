package com.csdn.jinxu.mahout.regression;

import com.google.common.collect.Lists;
import org.apache.mahout.classifier.sgd.L1;

import java.util.List;

/**
 * 实现描述：回归测试
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-28 下午2:28
 */
public class MainApp {

    public static void main(String[] args) {
        LogisticRegressionTrainer trainer = new LogisticRegressionTrainer(5, 3, new L1(), 1, 1);
        List<String> wordList = Lists.newArrayList();
        wordList.add("伦敦,唐宁街,英吉利,英国");
        wordList.add("陶瓷,茶叶,丝绸,中国");
        wordList.add("篮球,自由,美利坚,美国");

        trainer.train(wordList);

        String data = "茶叶,陶瓷,丝绸,**";
        String key = trainer.classify(data);
        System.out.println(key);
    }
}
