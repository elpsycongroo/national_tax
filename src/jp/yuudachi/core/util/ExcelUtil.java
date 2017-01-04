package jp.yuudachi.core.util;

import java.util.List;

import javax.servlet.ServletOutputStream;

import jp.yuudachi.nsfw.user.entity.User;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtil {
	/**
	 * 导出用户的所有列表到excel
	 * 
	 * @param userList
	 *            用户列表
	 * @param outputStream
	 *            输出流
	 * 
	 */
	public static void exportUserExcel(List<User> userList,
			ServletOutputStream outputStream) {
		try {
			// 1.创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 1.1创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 4);// 起始行
			// 1.2头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short) 16);
			// 1.3列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short) 13);
			// 2.创建工作表
			HSSFSheet sheet = workbook.createSheet("用户列表");
			// 2.1加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			// 设置默认列宽
			sheet.setDefaultColumnWidth(20);
			// 3.创建行
			// 3.1创建头标题行 并且设置头标题
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			// 加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("用户列表");
			// 3.2创建列标题行 并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			String[] titles = { "用户名", "账号", "所属部门", "性别", "电子邮箱" };
			for (int i = 0; i < titles.length; i++) {
				HSSFCell cell2 = row2.createCell(i);
				// 加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			// 4.操作单元格 将用户列表写入excel
			if (userList != null) {
				for (int j = 0; j < userList.size(); j++) {
					HSSFRow row = sheet.createRow(j + 2);// 前两行为特殊行
					HSSFCell cell10 = row.createCell(0);
					cell10.setCellValue(userList.get(j).getName());
					HSSFCell cell11 = row.createCell(1);
					cell11.setCellValue(userList.get(j).getAccount());
					HSSFCell cell12 = row.createCell(2);
					cell12.setCellValue(userList.get(j).getDept());
					HSSFCell cell13 = row.createCell(3);
					cell13.setCellValue(userList.get(j).isGender() ? "男" : "女");
					HSSFCell cell14 = row.createCell(4);
					cell14.setCellValue(userList.get(j).getEmail());
				}
			}
			// 5.输出
			workbook.write(outputStream);
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建单元格样式
	 * 
	 * @param workbook
	 *            工作簿
	 * @param fontsize
	 *            字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook,
			short fontsize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
		// 1.2.1创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 加粗
		font.setFontHeightInPoints(fontsize);
		// 加载字体
		style.setFont(font);
		return style;
	}
}
