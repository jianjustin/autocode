package org.jerry.light4j.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * execl工具集(通用集)
 * 
 * 备注：
 * execl包含execl2007版本和2003版本
 * HSSF － 提供读写Microsoft Excel XLS格式档案的功能。
 * XSSF － 提供读写Microsoft Excel OOXML XLSX格式档案的功能。
 * 
 * @author admin
 *
 */
public class ExeclUtils {

	/**
	 * 通过文件路径获取execl实体
	 * @param filePath
	 * @return
	 */
	public static Workbook getWorkbook(String filePath){
		Workbook workbook = null;
		try {
			InputStream inputStream = new FileInputStream(filePath);
		    workbook = WorkbookFactory.create(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return workbook;
	}
	
	/**
	 * 通过而execl文件获取sheet列表
	 * @param workbook
	 * @return
	 */
	public static List<Sheet> getSheetList(Workbook workbook){
		List<Sheet> sheetList = new ArrayList<Sheet>();
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			Sheet sheet = workbook.getSheetAt(i);
			sheetList.add(sheet);
		}
		return sheetList;
	}
	
	/**
	 * 获取工作表的数据行列表
	 * @param sheet
	 * @return
	 */
	public static List<Row> getRowList(Sheet sheet){
		List<Row> rowList = new ArrayList<Row>();
		if(null == sheet)return rowList;
		for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if(isRowEmpty(row))continue;
			rowList.add(row);
		}
		return rowList;
	}
	
	/**
	 * 通过数据行信息获取行对应的列数据集合
	 * @param row
	 * @return
	 */
	public static List<Cell> getCellList(Row row){
		List<Cell> cellList = new ArrayList<Cell>();
		if(null == row)return cellList;
		for (int i = 0; i < row.getLastCellNum(); i++) {
			if(null == row.getCell(i))continue;
			cellList.add(row.getCell(i));
			System.out.println(row.getCell(i).toString());
		}
		return cellList;
	}
	
	
	/**
	 * 判断指定行是否为空行
	 * @param row
	 * @return
	 */
	public static boolean isRowEmpty(Row row){
		boolean result = true;
		if(null == row)return result;
		for(int i = row.getFirstCellNum();i<=row.getLastCellNum();i++){
			Cell cell = row.getCell(i);
			if(null != cell && Cell.CELL_TYPE_BLANK != cell.getCellType()){
				result = false;
				return result;
			}
		}
		return result;
		
	}
	
	/**
	 * 获取单元格值
	 * @param cell
	 * @return
	 */
	public static String getCellValue(Cell cell) {
        String value = "";
        if(null == cell)return value;
        // 以下是判断数据的类型
        switch (cell.getCellType()) {
        case Cell.CELL_TYPE_NUMERIC:
            value = cell.getNumericCellValue() + "";
            if (DateUtil.isCellDateFormatted(cell)) {
                Date date = cell.getDateCellValue();
                if (date != null) {
                    value = new SimpleDateFormat("yyyy-MM-dd").format(date);
                } else {
                    value = "";
                }
            } else {
                value = new DecimalFormat("0").format(cell.getNumericCellValue());
            }
            break;
        case Cell.CELL_TYPE_STRING: // 字符串
            value = cell.getStringCellValue();
            break;
        case Cell.CELL_TYPE_BOOLEAN: // Boolean
            value = cell.getBooleanCellValue() + "";
            break;
        case Cell.CELL_TYPE_FORMULA: // 公式
            value = cell.getCellFormula() + "";
            break;
        case Cell.CELL_TYPE_BLANK: // 空值
            value = "";
            break;
        case Cell.CELL_TYPE_ERROR: // 故障
            value = "非法字符";
            break;
        default:
            value = "未知类型";
            break;
        }
        return value;
    }
	
	public static void main(String[] args) {
		Workbook workbook = getWorkbook("C:\\Users\\admin\\Desktop\\用户权限系统表设计文档.xlsx");
		List<Sheet> sheetList = getSheetList(workbook);
		List<Row> rowList = getRowList(sheetList.get(1));
		List<Cell> cellLists = new ArrayList<Cell>();
		for (Row row : rowList) {
			List<Cell> cellList = getCellList(row);
			cellLists.addAll(cellList);
		}
		System.out.println(cellLists.size());
	}
}
