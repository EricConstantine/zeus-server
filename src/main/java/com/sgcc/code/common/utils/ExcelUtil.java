package com.sgcc.code.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sgcc.code.common.exception.CommonException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ExcelUtil {

	//创建Excel导出模板
	public static Workbook createExcelTemplate(String[] titleNameArray,String titleName) throws CommonException {
		Workbook wb = null;
		OutputStream os = null;
		try {
			//不使用Excel模板
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();

			//设置标题样式
			CellStyle style = wb.createCellStyle();
			style.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			//标题置灰
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			style.setAlignment(HorizontalAlignment.CENTER);
			//设置边框
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());

			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 14);
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setFontName("宋体");
			style.setFont(font);//选择需要用到的字体格式
			int rowIndex = 0;
			if(StringUtil.isNotEmpty(titleName)){
				Row row = sheet.createRow(rowIndex);
				CellRangeAddress cellRange =new CellRangeAddress(0,0,0,titleNameArray.length-1);
				//绑定大标题
				sheet.addMergedRegion(cellRange);
				//为合并单元格添加边框
				RegionUtil.setBorderTop(1, cellRange, sheet);
				RegionUtil.setBorderBottom(1, cellRange, sheet);
				RegionUtil.setBorderLeft(1, cellRange, sheet);
				RegionUtil.setBorderRight(1, cellRange, sheet);
				Cell nowCell = row.createCell(0);
				nowCell.setCellValue(titleName);
				nowCell.setCellStyle(style);
				rowIndex += 1;
			}
			Row row = sheet.getRow(rowIndex);
			if (row == null) {
				row = sheet.createRow(rowIndex);
			}
			for (int i = 0; i < titleNameArray.length; i++) {
				Cell cell = row.getCell(i);
				if (cell == null) {
					cell = row.createCell(i);
				}
				cell.setCellValue(titleNameArray[i]);
				cell.setCellStyle(style);
			}
			os = new ByteArrayOutputStream();
			wb.write(os);
		} catch (Exception e) {
			throw new CommonException(500,e.getMessage());
		} finally {
//			is.close();
			try{
				os.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return wb;
	}
	//创建Excel导出模板
	public static Workbook createExcelTemplate(String[] titleNameArray,Integer[] columnWidthArray,String titleName) throws CommonException {
		Workbook wb = null;
		OutputStream os = null;
		try {
			//不使用Excel模板
			wb = new HSSFWorkbook();
			Sheet sheet = wb.createSheet();

			//设置标题样式
			CellStyle style = wb.createCellStyle();
//			style.setFillForegroundColor(IndexedColors..getIndex());
			style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			//style.setFillBackgroundColor(IndexedColors.YELLOW.index);
			style.setVerticalAlignment(VerticalAlignment.CENTER);
			style.setAlignment(HorizontalAlignment.CENTER);
			Font font = wb.createFont();
			font.setFontHeightInPoints((short) 14);
			font.setColor(IndexedColors.BLACK.getIndex());
			font.setFontName("宋体");
			style.setFont(font);//选择需要用到的字体格式
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());

			if(titleName!=null){
				Row row = sheet.createRow(0);
				CellRangeAddress cellRange =new CellRangeAddress(0,0,0,titleNameArray.length-1);
				//绑定大标题
				sheet.addMergedRegion(cellRange);
				//为合并单元格添加边框
				RegionUtil.setBorderTop(1, cellRange, sheet);
				RegionUtil.setBorderBottom(1, cellRange, sheet);
				RegionUtil.setBorderLeft(1, cellRange, sheet);
				RegionUtil.setBorderRight(1, cellRange, sheet);
				Cell nowCell = row.createCell(0);
				nowCell.setCellValue(titleName);
				nowCell.setCellStyle(style);

			}
			Row row = sheet.getRow(1);
			if (row == null) {
				row = sheet.createRow(1);
			}

			for (int i = 0; i < titleNameArray.length; i++) {
				sheet.setColumnWidth(i,columnWidthArray[i]);
				Cell cell = row.getCell(i);
				if (cell == null) {
					cell = row.createCell(i);
				}
				cell.setCellValue(titleNameArray[i]);
				cell.setCellStyle(style);
			}
			os = new ByteArrayOutputStream();
			wb.write(os);
		} catch (Exception e) {
			throw new CommonException(500,e.getMessage());
		} finally {
//			is.close();
			try{
				os.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return wb;
	}


	/**
	 * 导出Excel
	 */
	public static ResponseEntity<byte[]> exportExcelContent(List data,String[] titleNameArray,String[] fieldNameArray,String titleName) throws CommonException {
		InputStream is = null;
		ByteArrayOutputStream os = null;
		ResponseEntity<byte[]> entity = null;
		int startRowIndex = 1,startCellIndex = 0;
		int rowIndex = startRowIndex;
		Workbook wb = createExcelTemplate(titleNameArray,titleName);

		if(StringUtil.isNotEmpty(titleName)){
			rowIndex = 2;
		}

		//单元格颜色变黑
		CellStyle cellStyle = wb.createCellStyle();
		//设置边框
		cellStyle.setBorderBottom(BorderStyle.THIN);
		cellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderLeft(BorderStyle.THIN);
		cellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderRight(BorderStyle.THIN);
		cellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
		cellStyle.setBorderTop(BorderStyle.THIN);
		cellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
		//自动换行
		cellStyle.setWrapText(true);
		try {
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 0; i < data.size(); i++) {
				Map map = JSON.parseObject(JSON.toJSONStringWithDateFormat(data.get(i),"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat),HashMap.class);
				Row row = sheet.getRow(rowIndex);
				if (row == null) {
					row = sheet.createRow(rowIndex);
				}
				for (int j = 0; j < fieldNameArray.length; j++) {
					Cell cell = row.getCell(startCellIndex + j);
					if (cell == null) {
						cell = row.createCell(startCellIndex + j);
					}
					if (fieldNameArray[j] != ""
							&& map.containsKey(fieldNameArray[j])) {
						cell.setCellValue(toString(map.get(fieldNameArray[j])));
					} else {
						cell.setCellValue("");
					}
					cell.setCellStyle(cellStyle);
				}
				rowIndex++;
			}
			//设置自动列宽
			for (int i = 0; i < titleNameArray.length; i++) {
				sheet.autoSizeColumn(i);
				//最大列宽限制
				if (sheet.getColumnWidth(i)*2 > 15000) {
					sheet.setColumnWidth(i,15000);
				}else {
					sheet.setColumnWidth(i,sheet.getColumnWidth(i)*2);
				}
			}
			os = new ByteArrayOutputStream();
			wb.write(os);
			is = new ByteArrayInputStream(os.toByteArray());
			os = new ByteArrayOutputStream();
			wb.write(os);
			is = new ByteArrayInputStream(os.toByteArray());
			byte[] arr = new byte[is.available()];
			is.read(arr);
			HttpHeaders headers = new HttpHeaders();
			String exportFilename = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()) + ".xls";
			headers.add("Content-Disposition", "attchement;filename=" + exportFilename);
			HttpStatus statusCode = HttpStatus.OK;
			entity = new ResponseEntity<byte[]>(arr, headers, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != os) {
					os.close();
				}
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}


	public static ResponseEntity<byte[]> exportExcelContent(List data,String[] titleNameArray,String[] fieldNameArray){
		return exportExcelContent(data,titleNameArray,fieldNameArray,null);
	}
	/**
	 * 导出Excel
	 * List list:导出内容数据
	 * String[] titleNameArray:中文标题数组
	 * String[] fieldNameArray:字段名数组
	 * String[]
	 */
	public static ResponseEntity<byte[]> exportExcelContent(List data,String[] titleNameArray,String[] fieldNameArray,Integer[] columnWidthArray,String titleName) throws CommonException {
		InputStream is = null;
		ByteArrayOutputStream os = null;
		ResponseEntity<byte[]> entity = null;
		int startRowIndex = 2,startCellIndex = 0;
		int rowIndex = startRowIndex;
		Workbook wb = createExcelTemplate(titleNameArray,columnWidthArray,titleName);
		try {
			CellStyle style = wb.createCellStyle();
			style.setBorderBottom(BorderStyle.THIN);
			style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderLeft(BorderStyle.THIN);
			style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderRight(BorderStyle.THIN);
			style.setRightBorderColor(IndexedColors.BLACK.getIndex());
			style.setBorderTop(BorderStyle.THIN);
			style.setTopBorderColor(IndexedColors.BLACK.getIndex());
			Sheet sheet = wb.getSheetAt(0);
			for (int i = 0; i < data.size(); i++) {
				Map map = JSON.parseObject(JSON.toJSONStringWithDateFormat(data.get(i),"yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat),HashMap.class);
				Row row = sheet.getRow(rowIndex);
				if (row == null) {
					row = sheet.createRow(rowIndex);
				}
				for (int j = 0; j < fieldNameArray.length; j++) {
					Cell cell = row.getCell(startCellIndex + j);
					if (cell == null) {
						cell = row.createCell(startCellIndex + j);
					}
					if (fieldNameArray[j] != ""
							&& map.containsKey(fieldNameArray[j])) {
						cell.setCellValue(toString(map.get(fieldNameArray[j])));
						cell.setCellStyle(style);
					} else {
						cell.setCellValue("");
						cell.setCellStyle(style);
					}
				}
				rowIndex++;
			}

			os = new ByteArrayOutputStream();
			wb.write(os);
			is = new ByteArrayInputStream(os.toByteArray());
			os = new ByteArrayOutputStream();
			wb.write(os);
			is = new ByteArrayInputStream(os.toByteArray());
			byte[] arr = new byte[is.available()];
			is.read(arr);
			HttpHeaders headers = new HttpHeaders();
			String exportFilename = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()) + ".xls";
			headers.add("Content-Disposition", "attchement;filename=" + exportFilename);
			HttpStatus statusCode = HttpStatus.OK;
			entity = new ResponseEntity<byte[]>(arr, headers, statusCode);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return entity;
	}

	/**
	 * 获取Excel内容数据 用于从Excel导入
	 * @param fieldNameArray
	 * @throws Exception
	 */
	public static List<Map<String,String>> getExcelContent(MultipartFile file, String[] fieldNameArray) throws CommonException {
		return getExcelContent(file,fieldNameArray,0);
	}

	public static List<Map<String,String>> getExcelContent(MultipartFile file, String[] fieldNameArray,Integer sheetIndex) throws CommonException {
		int startRowIndex = 1;//开始读取的行索引
		int startCellIndex = 0;//开始读取的列索引
//		int endCellIndex = fieldNameArray.length;//读取结束的列索引

		Workbook wb = null;
		try {
			InputStream inp = file.getInputStream();
			wb = WorkbookFactory.create(inp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}

		//  Sheet sheet = wb.createSheet();
		Sheet sheet = wb.getSheetAt(sheetIndex);

		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		Row row = sheet.getRow(startRowIndex);
		Cell cell = row.getCell(startCellIndex);
		int rowindex = 0;
		cell.setCellType(CellType.STRING);
		while(row!=null && cell.getStringCellValue()!=null){
			Map<String,String> map = new HashMap<String,String>();
			for(int i=0;i<fieldNameArray.length;i++){
				cell = row.getCell(startCellIndex+i);
				String cellvalue = "";
				if(cell!=null){
					cell.setCellType(CellType.STRING);
					cellvalue = cell.getStringCellValue();
				}
				map.put(fieldNameArray[i],cellvalue);
			}
			list.add(map);
			rowindex++;
			row = sheet.getRow(startRowIndex+rowindex);
			if(row!=null){
				cell = row.getCell(startCellIndex);
				if(cell==null){
					break;
				}else{
					cell.setCellType(CellType.STRING);
					if(cell.getStringCellValue()==null || "".equals(cell.getStringCellValue())){
						break;
					}
				}
			}
		}
		return list;
	}
	/**
	 * 获取Excel内容数据 用于从Excel导入
	 * @param fieldNameArray
	 * @throws Exception
	 */
	public static <V> List<V> getExcelContentIoList(MultipartFile file, String[] fieldNameArray, Class<V> clazz) throws CommonException {
		return getExcelContentIoList(file,fieldNameArray,clazz,0);
	}

	public static <V> List<V> getExcelContentIoList(MultipartFile file, String[] fieldNameArray, Class<V> clazz,Integer sheetIndex) throws CommonException {
		List<Map<String,String>> list = getExcelContent(file, fieldNameArray,sheetIndex);
		List<V> result = new ArrayList<>();
		for(Map<String,String> map:list){
			result.add(JSON.parseObject(JSON.toJSONString(map),clazz));
		}
		return result;
	}

	/**
	 * 获取Excel内容数据 用于从Excel导入
	 * @param file
	 */
	public static String isValid(MultipartFile file){
		if (file==null) {
			return "请选择上传的文件";
		}
		String fileName = file.getOriginalFilename();
		if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
			return "上传文件格式错误，请上传后缀为.xls或.xlsx的文件";
		}
		return "true";
	}


	public static String toString(Object obj){
		return obj==null?"":obj.toString();
	}

	//提取异常信息中的关键描述内容
	public static String getPointOfException(String msg,String[] fieldNameArray,String[] titleNameArray){
		if(msg!=null && msg.length()>30){
			String[] msg_arr = msg.split(": ");
			msg = msg_arr[msg_arr.length-1];
		}
		if(msg!=null && msg.length()>0) {
			for(int i=0;i<fieldNameArray.length;i++){
				String fieldName = fieldNameArray[i];
				String titleName = titleNameArray[i];
				msg = msg.replace("'"+fieldName+"'","'"+titleName+"'");
			}
		}
		return msg;
	}

	public static <V> List<V> getExcelContentIoListMerge(MultipartFile file, String[] fieldNameArray, Class<V> clazz) throws CommonException {
		List<Map<String, String>> list = getExcelContentMerge(file, fieldNameArray);
		List<V> result = new ArrayList();
		Iterator var6 = list.iterator();

		while(var6.hasNext()) {
			Map<String, String> map = (Map)var6.next();
			result.add(JSON.parseObject(JSON.toJSONString(map), clazz));
		}

		return result;
	}


	/**
	 * 获取Excel内容数据 用于从Excel导入
	 * @param fieldNameArray
	 * @throws Exception
	 */
	public static List<Map<String,String>> getExcelContentMerge(MultipartFile file, String[] fieldNameArray) throws CommonException {
		Workbook wb = null;
		try {
			InputStream inp = file.getInputStream();
			wb = WorkbookFactory.create(inp);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		Sheet sheet = wb.getSheetAt(0);
		Row row = null;
		List<Map<String,String>> result = new ArrayList<>();
		for(int i=1; i<sheet.getLastRowNum()+1; i++) {
			row = sheet.getRow(i);
			Map<String,String> tempMap = new HashMap<>();
			int j = 0;
			for(Cell c : row) {
				String value = "";
				boolean isMerge = isMergedRegion(sheet, i, c.getColumnIndex());
				//判断是否具有合并单元格
				if(isMerge) {
					value = getMergedRegionValue(sheet, row.getRowNum(), c.getColumnIndex());
				}else {
					value = c.getRichStringCellValue()+"";
				}
				tempMap.put(fieldNameArray[j],value);
				j++;
			}
			result.add(tempMap);
		}
		return result;
	}
	/**
	 * 获取合并单元格的值
	 * @param sheet
	 * @param row
	 * @param column
	 * @return
	 */
	private static String getMergedRegionValue(Sheet sheet ,int row , int column){
		int sheetMergeCount = sheet.getNumMergedRegions();

		for(int i = 0 ; i < sheetMergeCount ; i++){
			CellRangeAddress ca = sheet.getMergedRegion(i);
			int firstColumn = ca.getFirstColumn();
			int lastColumn = ca.getLastColumn();
			int firstRow = ca.getFirstRow();
			int lastRow = ca.getLastRow();

			if(row >= firstRow && row <= lastRow){

				if(column >= firstColumn && column <= lastColumn){
					Row fRow = sheet.getRow(firstRow);
					Cell fCell = fRow.getCell(firstColumn);
					return getCellValue(fCell) ;
				}
			}
		}

		return null ;
	}

	/**
	 * 判断指定的单元格是否是合并单元格
	 * @param sheet
	 * @param row 行下标
	 * @param column 列下标
	 * @return
	 */
	private static boolean isMergedRegion(Sheet sheet,int row ,int column) {
		int sheetMergeCount = sheet.getNumMergedRegions();
		for (int i = 0; i < sheetMergeCount; i++) {
			CellRangeAddress range = sheet.getMergedRegion(i);
			int firstColumn = range.getFirstColumn();
			int lastColumn = range.getLastColumn();
			int firstRow = range.getFirstRow();
			int lastRow = range.getLastRow();
			if(row >= firstRow && row <= lastRow){
				if(column >= firstColumn && column <= lastColumn){
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 获取单元格的值
	 * @param cell
	 * @return
	 */
	private static String getCellValue(Cell cell){

		if(cell == null) return "";

		if(cell.getCellType() == Cell.CELL_TYPE_STRING){

			return cell.getStringCellValue();

		}else if(cell.getCellType() == Cell.CELL_TYPE_BOOLEAN){

			return String.valueOf(cell.getBooleanCellValue());

		}else if(cell.getCellType() == Cell.CELL_TYPE_FORMULA){

			return cell.getCellFormula() ;

		}else if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){

			return String.valueOf(cell.getNumericCellValue());

		}
		return "";
	}
}
