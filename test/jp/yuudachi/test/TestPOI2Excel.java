package jp.yuudachi.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

/**
 * 改为xssf则为读取xlsx文件
 * 
 * @author Administrator
 * 
 */
public class TestPOI2Excel {

	@Test
	public void testWrite03Excel() throws Exception {
		// 1.创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 2.创建工作表
		HSSFSheet sheet = workbook.createSheet("Helloworld");
		// 3.创建行 创建第3+1行
		HSSFRow row = sheet.createRow(3);
		// 4.创建单元格 创建第4+1个单元格
		HSSFCell cell = row.createCell(4);
		cell.setCellValue("Hello World");
		// 输出到硬盘
		FileOutputStream out = new FileOutputStream("E:\\test\\测试.xls");
		// 把excel输出到具体的地址
		workbook.write(out);
		workbook.close();
		out.close();
	}

	@Test
	public void testRead03Excel() throws Exception {

		FileInputStream in = new FileInputStream("E:\\test\\测试.xls");

		// 1.读取工作簿
		HSSFWorkbook workbook = new HSSFWorkbook(in);
		// 2.读取第一个工作表
		HSSFSheet sheet = workbook.getSheetAt(0);
		// 3.创建行 创建第3+1行
		HSSFRow row = sheet.getRow(3);
		// 4.创建单元格 创建第4+1个单元格
		HSSFCell cell = row.getCell(4);
		System.out.println("第三行第三列的单元格内容为" + cell.getStringCellValue());
		workbook.close();
		in.close();
	}

	@Test
	public void testRead03And07Excel() throws Exception {
		String fileName = "E:\\test\\测试.xls";
		FileInputStream in = new FileInputStream("E:\\test\\测试.xls");
		// 判断是否为excel文件
		if (fileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			boolean is03Excel = fileName.matches("^.+\\.(?i)((xls))$");
			// 1.读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(in)
					: new XSSFWorkbook(in);
			// 2.读取第一个工作表
			Sheet sheet = workbook.getSheetAt(0);
			// 3.创建行 创建第3+1行
			Row row = sheet.getRow(3);
			// 4.创建单元格 创建第4+1个单元格
			Cell cell = row.getCell(4);
			System.out.println("第三行第三列的单元格内容为" + cell.getStringCellValue());
			System.out.println("这是一个xls文件吗？" + is03Excel);
			workbook.close();
			in.close();
		}
	}
	@Test
	public void testExcelStyle() throws Exception {
		// 1.创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//1.1创建合并单元格对象：合并第3行的第3列到第5列
		CellRangeAddress cellRangeAddress = new CellRangeAddress(2,2,2,4);//起始行号 结束行号 起始列号 结束列号
		//1.2创建单元格样式
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//1.3创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
		//这个方法会将字号除以20
//		font.setFontHeight((short)16);//设置字体大小
		font.setFontHeightInPoints((short)16);
		//1.4 单元格背景
		//设置填充模式
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		//设置填充背景色
		style.setFillBackgroundColor(HSSFColor.YELLOW.index);
		//设置填充前景色
		style.setFillForegroundColor(HSSFColor.RED.index);
		//加载字体
		style.setFont(font);
		// 2.创建工作表
		HSSFSheet sheet = workbook.createSheet("Helloworld");
		//2.1加载合并单元格对象
		sheet.addMergedRegion(cellRangeAddress);
		// 3.创建行 创建第2+1行
		HSSFRow row = sheet.createRow(2);
		// 4.创建单元格 创建第2+1个单元格
		HSSFCell cell = row.createCell(2);
		//加载样式
		cell.setCellStyle(style);
		cell.setCellValue("Hello World");
		// 输出到硬盘
		FileOutputStream out = new FileOutputStream("E:\\test\\测试.xls");
		// 把excel输出到具体的地址
		workbook.write(out);
		workbook.close();
		out.close();
	}
}
