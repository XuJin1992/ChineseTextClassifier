package com.csdn.jinxu.textclassifier;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


/**
 * 实现描述：${todo} 类描述待完成
 *
 * @author jin.xu
 * @version v1.0.0
 * 1.先验概率P(y)= 类y下单词总数/整个训练样本的单词总数
 * 2.类条件概率P(xi|y)=(类y下单词xi在各个文档中出现过的次数之和+1)/(类y下单词总数+向量的维度)
 * 3.P(y|xi)=P(y)*P(xi|y)/P(xi)等价于P(y)*P(xi|y)
 * @see
 * @since 16-9-13 下午5:44
 */
public class NBClassifier {
    private

    public static  double[] classify(Map<String,Integer>wordFreMap){
        double yesPrioriProb=1.0*yesDocMap.size()/(yesDocMap.size()+noDocMap.size());
        double noPrioriProb=1.0*noDocMap.size()/(yesDocMap.size()+noDocMap.size());

        double [][]conditionalProb=new double[2][wordFreMap.size()];
        conditionalProb[0]=getProbMatrix(yesDocMap,wordFreMap);
        conditionalProb[1]=getProbMatrix(noDocMap,wordFreMap);

        double yesProb=yesPrioriProb;
        double noProb=noPrioriProb;
        for(int i=0;i<wordFreMap.size();++i){
            yesProb*=conditionalProb[0][i];
            noProb*=conditionalProb[1][i];
        }

        return conditionalProb;
    }


    public static double[]getProbMatrix(Map<String,Integer>docMap,Map<String,Integer>wordFreMap){
        double []probMatrixPerClass=new double[wordFreMap.size()];
        Integer totalCount=0;
        for(Iterator iter=wordFreMap.entrySet().iterator();iter.hasNext();) {
            Map.Entry entry = (Map.Entry) iter.next();
            totalCount+=(Integer) entry.getValue();
        }

        int index=0;
        for(Iterator iter=wordFreMap.entrySet().iterator();iter.hasNext();){
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Integer tmpCount=1;
            if(docMap.containsKey(key)){
                tmpCount+=docMap.get(key);
            }
            probMatrixPerClass[index++]=1.0*(tmpCount+1)/(totalCount+1000);//????????/固定总维度为1000
        }
        return probMatrixPerClass;
    }
}
