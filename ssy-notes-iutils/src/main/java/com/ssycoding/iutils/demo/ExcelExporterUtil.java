package com.ssycoding.iutils.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Description: 使用SXSSFWorkbook向Excel中写入数据
 * @Author: Sean
 * @Date: 2020/5/20 10:34
 */
@Slf4j
public class ExcelExporterUtil {

    public enum OfficeVersion {
        /**
         * office版本
         */
        OFFICE_03,
        /**
         * office版本
         */
        OFFICE_07;
    }

    private String[] title;
    public Workbook workBook;
    private Sheet sheet;

    /**
     *
     * @param title
     *            表头
     * @param sheetName
     *            左下角标题
     * @param officeVersion
     *            excel版本
     */
    public ExcelExporterUtil(String[] title, String sheetName, OfficeVersion officeVersion) {
        this.title = title;
        // 创建一个工作薄
        if (OfficeVersion.OFFICE_07.equals(officeVersion)) {
            // workBook = new XSSFWorkbook();//处理07版本excel
            // 处理07版本，但是适用于大数据量，导出之后数据不会占用内存
            workBook = new SXSSFWorkbook();
        } else if (OfficeVersion.OFFICE_03.equals(officeVersion)) {
            workBook = new HSSFWorkbook();
        }
        // 创建一个工作表sheet
        sheet = workBook.createSheet(sheetName);
        // 首行冻结
        sheet.createFreezePane(0,1,0,1);
        initHeader();
    }

    /**
     * 初始化表头信息
     */
    private void initHeader() {
        // 创建第一行
        Row row = sheet.createRow(0);
        row.setHeightInPoints(25);
        Cell cell = null;
        // 创建表头
        for (int i = 0; i < title.length; i++) {
            cell = row.createCell(i);
            cell.setCellValue(title[i]);
            setCellStyle(cell);
            // 根据表头信息锁定列宽度, 适用于中文
            sheet.setColumnWidth(i, title[i].getBytes().length * 256);
        }
    }

    /**
     * 设置单元格样式
     *
     * @param cell
     *            单元格
     */
    public void setCellStyle(Cell cell) {
        // 设置样式
        CellStyle cellStyle = workBook.createCellStyle();
        // 边框边线
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        // 设置字体居中
        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        // 设置字体
        Font font = workBook.createFont();
        font.setFontName("微软雅黑");
        // 字体加粗
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        font.setFontHeightInPoints((short) 13);
        cellStyle.setFont(font);
        cell.setCellStyle(cellStyle);
    }

    /**
     * 创建行内容(每一行的数据装在list中)
     *
     * @param dataMap
     *            每一行的数据
     * @param dataRow
     *            行 key 值
     * @param rowIndex
     *            行号(从1开始)
     */
    public void createTableRow(Map<String, String> dataMap, List<String> dataRow, int rowIndex) {
        // 创建第i行
        Row row = sheet.createRow(rowIndex);
        row.setHeightInPoints(20);
        Cell cell = null;
        // 写入数据
        for (int index = 0, length = dataRow.size(); index < length; index++) {
            // 参数代表第几列
            cell = row.createCell(index);
            cell.setCellType(Cell.CELL_TYPE_STRING);
            cell.setCellValue(String.valueOf(dataMap.get(dataRow.get(index))));
            CellStyle cellStyle = workBook.createCellStyle();
            // 边框边线
            cellStyle.setBorderRight(CellStyle.BORDER_THIN);
            cellStyle.setBorderTop(CellStyle.BORDER_THIN);
            cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
            cell.setCellStyle(cellStyle);
        }
    }

    /**
     *
     * @param datas
     *            数据,每一个map都是一行
     * @param keys
     *            key[i]代表从map中获取keys[i]的值作为第i列的值,如果传的是null默认取表头
     */
    public void createTableRows(List<Map<String, Object>> datas, String[] keys) {
        for (int i = 0, length1 = datas.size(); i < length1; i++) {
            if (ArrayUtils.isEmpty(keys)) {
                keys = title;
            }
            // 创建行(从第二行开始)
            Map<String, Object> data = datas.get(i);
            Row row = sheet.createRow(i + 1);
            Cell cell = null;
            for (int j = 0, length2 = keys.length; j < length2; j++) {
                // 单元格获取map中的key
                String key = keys[j];
                String value = MapUtils.getString(data, key, "");
                cell = row.createCell(j);
                cell.setCellType(Cell.CELL_TYPE_STRING);
                cell.setCellValue(value);
            }

        }
    }

    /**
     * 根据表头自动调整列宽度
     */
    public void autoAllSizeColumn() {
        // 如果是SXSSFSheet，需要调用trackAllColumnsForAutoSizing方法一次
//        if (sheet instanceof SXSSFSheet) {
//            SXSSFSheet tmpSheet = (SXSSFSheet) sheet;
//            tmpSheet.trackAllColumnsForAutoSizing();
//        }
        for (int i = 0, length = title.length; i < length; i++) {
            sheet.autoSizeColumn(i, true);
        }
    }

    /**
     * 将数据写出到excel中
     *
     * @param outputStream
     */
    public void exportExcel(OutputStream outputStream) {
        // 导出之前先自动设置列宽, 根据每行数据调整列宽
        this.autoAllSizeColumn();
        try {
            workBook.write(outputStream);
        } catch (IOException e) {
            log.error(" exportExcelUtil error", e);
        } finally {
            IOUtils.closeQuietly(outputStream);
        }
    }

    /**
     * 将数据写出到 byte[] 中
     * @return
     */
    public byte[] exportByte(Workbook workBook) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            workBook.write(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            log.error(" exportExcelUtil error", e);
        }
        return null;
    }

    /**
     * 合并单元格(起始行列都包括在里面)
     *
     * @param startRow
     *            起始行
     * @param endRow
     *            结束行
     * @param startCol
     *            起始列
     * @param endCol
     *            结束列
     */
    public void mergeCell(int startRow, int endRow, int startCol, int endCol) {
        CellRangeAddress region = new CellRangeAddress(startRow, endRow, startCol, endCol);
        sheet.addMergedRegion(region);
    }

    /**
     * 将数据写出到excel中
     *
     * @param outputFilePath
     */
    public void exportExcel(String outputFilePath) {
        // 导出之前先自动设置列宽
        this.autoAllSizeColumn();
        try (FileOutputStream outputStream = new FileOutputStream(outputFilePath)) {
            workBook.write(outputStream);
        } catch (IOException e) {
            log.error(" exportExcelUtil error", e);
        }
    }
}
