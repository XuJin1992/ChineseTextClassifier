package com.csdn.jinxu.ansj;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.io.*;

/**
 * 实现描述：AnsjClient处理器
 *
 * @author jin.xu
 * @version v1.0.0
 * @see
 * @since 16-8-27 上午10:08
 */
public class AnsjClient {

    public static void process(String input, String output) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(input)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(new File(output), true));
        String tempString = null;
        int line = 0;
        while ((tempString = reader.readLine()) != null) {
            StringBuilder newLine = new StringBuilder();
            for (Term term : ToAnalysis.parse(tempString)) {
                newLine.append(term.toString()).append(" ");
            }
            writer.write(newLine.toString());
            ++line;
            System.out.println("line:" + line + "...");
        }
        reader.close();
        writer.close();
    }
}
