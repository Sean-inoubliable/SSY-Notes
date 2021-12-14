package com.ssycoding.iutils.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Comments: CVS文件操作工具类
 * @Author: Sean
 * @Date: 2020-03-25 17:16
 */
public class CsvUtils {

    private static final Logger logger = LoggerFactory.getLogger(CsvUtils.class);

    /**
     * 私有化构造器
     */
    private CsvUtils() {}

    /**
     * 写出CSV文件，每一行数据为一个Object对象
     * @param data  List<Object>
     * @param header    第一行标题
     * @param filePath  写出文件的绝对路径
     * @param split     CSV文件分隔符，一般为","
     * @throws Exception
     */
    public static void writeCsv(List<?> data, List<String> header, String filePath, String split) throws InvocationTargetException, IllegalAccessException, IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            // 填充对象属性值
            headIsEmpty(header, bw, split);
            // 填充数据
            if (!CollectionUtils.isEmpty(data)) {
                Class<? extends Object> objClass = data.get(0).getClass();
                Field[] fields = objClass.getDeclaredFields();
                for (int i = 0; i < data.size(); i++) {
                    for (int j = 0; j < fields.length; j++) {
                        Method[] methods = objClass.getDeclaredMethods();
                        for (Method method : methods) {
                            if (method.getName().equalsIgnoreCase("get" + fields[j].getName())) {
                                String property = (String) method.invoke(data.get(i), null);
                                bw.append(property == null ? "" : property);
                                break;
                            }
                        }
                        if (j != fields.length - 1) {
                            bw.append(split);
                        }
                    }
                    if (i != data.size() - 1) {
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Write CSV exception------", e);
            throw new IOException(e);
        }
    }

    /**
     * 写出CSV文件，每一行数据为一个List<String>集合
     * @param data  List<String>
     * @param header    第一行标题
     * @param filePath  写出文件的绝对路径
     * @param split     CSV文件分隔符，一般为","
     * @throws Exception
     */
    public static void writeCsvFromList(List<List<String>> data, List<String> header, String filePath,
                                        String split) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), StandardCharsets.UTF_8))) {
            // 填充对象属性值
            headIsEmpty(header, bw, split);
            // 填充数据
            if (!CollectionUtils.isEmpty(data)) {
                for (int i = 0; i < data.size(); i++) {
                    List<String> list = data.get(i);
                    for (int j = 0; j < list.size(); j++) {
                        bw.append(list.get(j));
                        if (j != list.size() - 1) {
                            bw.append(split);
                        }
                    }
                    if (i != data.size() - 1) {
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            logger.error("Write CSV exception------", e);
            throw new IOException(e);
        }
    }

    /**
     * @Author: Sean
     * @Date: 2020/3/25 16:48
     * @Description: 填充对象属性值 - 添加头部（首行）
     * @Param:
     * @Return:
     */
    public static void headIsEmpty(List<String> header, BufferedWriter bw, String split) throws IOException {
        // 填充对象属性值
        if (!CollectionUtils.isEmpty(header)) {
            // 添加头部（首行）
            for (int i = 0; i < header.size(); i++) {
                bw.append(header.get(i));
                if (i != header.size() - 1) {
                    bw.append(split);
                }
            }
            bw.newLine();
        }
    }
}
