package com.csdn.jinxu.textclassifier;

import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.FilterModifWord;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 实现描述：ansj中文分词
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-9-12 下午7:17
 */
public class AnsjSpliter {

    /**
     * 分词　
     * @param file　文档文件或者目录
     * @return
     * @throws IOException
     */
    public static Map<String,Integer> process(File file) throws IOException {
        Map<String, Integer> wordScoreMap = Maps.newHashMap();
        if(file.isDirectory()) {
            for(File tmp:file.listFiles()){
                Map<String,Integer> tmpWordScoreMap=process(tmp);
                for (Iterator iterator = tmpWordScoreMap.entrySet().iterator(); iterator.hasNext(); ) {
                    Map.Entry entry = (Map.Entry) iterator.next();
                    String key=(String)entry.getKey();
                    Integer value=(Integer)entry.getValue();
                    if (wordScoreMap.containsKey(key)) {
                        wordScoreMap.put(key,wordScoreMap.get(key) +value);
                    } else {
                        wordScoreMap.put(key,value);
                    }
                }
            }
        }
        else{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                wordScoreMap=process(tempString,wordScoreMap);
            }
            reader.close();
        }
        return  wordScoreMap;
    }

    /**
     * 分词　
     * @param inputText　文档文本
     * @return
     * @throws IOException
     */
    public static Map<String,Integer> process(String inputText) throws IOException {
        Map<String, Integer> wordScoreMap = Maps.newHashMap();
        process(inputText,wordScoreMap);
        return  wordScoreMap;
    }

    /**
     * 过滤停用词
     * @param file　停用词文件
     * @return
     * @throws IOException
     */
    public static HashMap<String,String> filter(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        HashMap<String,String>stopWordMap= Maps.newHashMap();
        String tempString = null;
        while ((tempString = reader.readLine()) != null) {
            stopWordMap.put(tempString.trim() , "_stop");
        }
        reader.close();
        return  stopWordMap;
    }

    /**
     * 过滤停用词
     * @param inputText　停用词文本，以逗号','隔开
     * @return
     * @throws IOException
     */
    public static HashMap<String,String> filter(String inputText) throws IOException {
        HashMap<String,String>stopWordMap= Maps.newHashMap();
        List<String> wordList= Splitter.on(',').splitToList(inputText);
        if(wordList.size()>0) {
            for (String word : wordList) {
                stopWordMap.put(word, "_stop");
            }
        }
        return  stopWordMap;
    }

    /**
     * 分词　
     * @param inputText 文档文本
     * @param wordScoreMap 单词－频数映射
     * @return
     * @throws IOException
     */
    private static Map<String,Integer> process(String inputText,Map<String,Integer>wordScoreMap) throws IOException {
        for (Term term : FilterModifWord.modifResult(ToAnalysis.parse(inputText))) {
            if(StringUtils.isNotBlank(term.getName().trim())) {
                if (wordScoreMap.containsKey(term.getName())) {
                    wordScoreMap.put(term.getName(), wordScoreMap.get(term.getName()) + 1);
                } else {
                    wordScoreMap.put(term.getName(), 1);
                }
            }
        }
        return  wordScoreMap;
    }
}
