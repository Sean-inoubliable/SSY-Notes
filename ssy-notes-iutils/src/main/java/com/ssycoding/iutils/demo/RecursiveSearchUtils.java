package com.ssycoding.iutils.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @Description: 递归搜索 - 某种后缀类型文件 & 文件全路径
 * @Author: Sean
 * @Date: 2020/7/18 10:30
 */
public class RecursiveSearchUtils {

    /**
     * 文件后缀名
     */
    private String suffixName;
    /**
     * 左下角标题
     */
    private String sheetName;
    /**
     * 行 key 值
     */
    private List<String> dataRow;
    /**
     * 标题首行
     */
    private String[] tableStr;
    /**
     * 替换字符
     */
    private static final String SLANT_LINE = "\\";
    private static final String SPOT_STR = ".";

    /**
     * 初始化构造器
     * @param suffixName 文件后缀
     * @param sheetName sheet页码左下角标题
     * @param dataRow 行 key 值
     */
    public RecursiveSearchUtils(String suffixName, String sheetName, List<String> dataRow, String[] tableStr) {
        this.suffixName = suffixName;
        this.sheetName = sheetName;
        this.dataRow = dataRow;
        this.tableStr = tableStr;
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(RecursiveSearchUtils.class);

    private List<Map<String, String>> dataList = new ArrayList<>();

    public void recursiveSearch(String directoryUrl, String writePath) {

        LOGGER.info("\n\n----------- begin 递归处理 -----------\n");

        File file = new File(directoryUrl);

        fileOrDirectoryHandle(file);

        LOGGER.info("\n\n----------- end 递归处理 -----------\n");

        LOGGER.info("\n\n----------- begin 写入文件 -----------\n");

        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(writePath))) {
            writeExcel(fileOutputStream, dataList);
        } catch (IOException e) {
            e.printStackTrace();
        }

        LOGGER.info("\n\n----------- end 写入文件 -----------\n");
        LOGGER.info("\n\n----------- 文件存放路径：" + writePath + " -----------\n");
    }

    /**
     * 写入表格
     * @param fileOutputStream 字节流
     * @param dataList 数据
     */
    private void writeExcel(FileOutputStream fileOutputStream, List<Map<String, String>> dataList) {

        ExcelExporterUtil excelExporterUtil = new ExcelExporterUtil(tableStr, sheetName, ExcelExporterUtil.OfficeVersion.OFFICE_07);

        int lineNum = 1;
        for (Map<String, String> dataMap : dataList) {
            excelExporterUtil.createTableRow(dataMap, dataRow, lineNum);
            lineNum++;
        }

        excelExporterUtil.exportExcel(fileOutputStream);
    }


    /**
     * 文件 or 文件夹处理分支
     * @param file
     */
    private void fileOrDirectoryHandle(File file) {
        if (fileOrDirectory(file) && Objects.requireNonNull(file.listFiles()).length != 0) {
            for (int i = 0; i < Objects.requireNonNull(file.listFiles()).length; i++) {
                fileOrDirectoryHandle(Objects.requireNonNull(file.listFiles())[i]);
            }
        } else {
            isFile(file);
        }
    }

    /**
     * 判断是否是文件夹
     * @param file
     * @return
     */
    private boolean fileOrDirectory(File file) {
        return file.isDirectory();
    }

    /**
     * 如果是文件，则写入集合
     * @param file
     */
    private void isFile(File file) {
        String fileName = file.getName();
        if (fileName.substring(fileName.lastIndexOf(SPOT_STR)).equals(suffixName)) {
            Map<String, String> dataMap = new HashMap<>(3);
            String filePath = file.getPath();
            String versionStr = filePath
                    .substring(
                            filePath.lastIndexOf(SLANT_LINE, filePath.lastIndexOf(SLANT_LINE) - 1) + 1, filePath.lastIndexOf(SLANT_LINE));
            dataMap.put(dataRow.get(0), fileName);
            dataMap.put(dataRow.get(1), versionStr);
            dataMap.put(dataRow.get(2), filePath);
            dataList.add(dataMap);
        }
    }
}
