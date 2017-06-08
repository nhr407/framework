package priv.zhouhuayi.framework.util.pio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;

public class ExportUtil {
	public static void main(String[] args) {
		String tableName = "用户信息";
		Map<String, String> columnMap = new HashMap<String, String>();
		columnMap.put("id", "用户编号");
		columnMap.put("realName", "用户姓名");
		columnMap.put("phone", "手机号");
		columnMap.put("address", "地址");
		
		List<Map<String, Object>> listData = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 100; i++) {
			Map<String, Object> valueMap = new HashMap<String, Object>();
			valueMap.put("id", ""+i);
			valueMap.put("realName", "齐天大圣"+i);
			valueMap.put("phone", 1300322167+i);
			valueMap.put("address", "上海市"+i);
			listData.add(valueMap);
		}
		
		List<UserPojo> listBean = new ArrayList<UserPojo>();
		for (int i = 0; i < 2; i++) {
			UserPojo user = new UserPojo();
			user.setId(i);
			user.setRealName("实体：齐天大圣" + i);
			user.setPhone("实体：" + 1300322167 + i);
			user.setAddress("实体：上海市" + i);
			listBean.add(user);
		}
		
		try {
			OutputStream os = new FileOutputStream("D:/students.xls");
			System.out.println(exportExcel(tableName, columnMap, listData, os));
			os.close();
			
			OutputStream osBean = new FileOutputStream("D:/studentsBean.xls");
			System.out.println(exportExcelByBean(tableName, columnMap, listBean, osBean));
			osBean.close();
			
			InputStream is = new FileInputStream("e://zhy资料文件夹/资产负债表.xlsx");
			System.out.println(readXls(is));
			System.out.println(new Date().getTime());
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 导出excel表
	 * 
	 * @author zhy
	 * @param tableName 表格名
	 * @param columnMap 列头
	 * @param listData 列表值
	 * @param os 输出流
	 * @return
	 */
	public static boolean exportExcel(String tableName, Map<String, String> columnMap, 
			List<Map<String, Object>> listData, OutputStream os) {
		String[] rowArray = new String[columnMap.size()];
		
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(tableName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		// 设置这些样式
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中

		 // 背景色
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillBackgroundColor(HSSFColor.BLUE.index); 

		HSSFCell cell = row.createCell((short) 0);
		short rowIndex = 0;
		
		Iterator<String> it = columnMap.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = columnMap.get(key);
			rowArray[rowIndex] = key;
			cell.setCellValue(value);
			cell.setCellStyle(style);
			rowIndex ++;
			cell = row.createCell(rowIndex);
		}
		
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		int listLength = listData.size();
		
		for (int i = 0; i < listLength; i++) {
			row = sheet.createRow((int) i + 1);
			Map<String, Object> mapData = listData.get(i);
			
			// 第四步，创建单元格，并设置值
			for (short j = 0; j < rowIndex; j++) {
				Object value = mapData.get(rowArray[j]);
				row.createCell(j).setCellValue(value == null ? "" : value.toString());
			}
		}
		// 第六步，将文件存到指定位置
		try {
			wb.write(os);
			os.close();
			wb.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 导出excel表
	 * 
	 * @author zhy
	 * @param tableName 表格名
	 * @param columnMap 列头
	 * @param listData 列表值
	 * @param os 输出流
	 * @return
	 */
	public static boolean exportExcelByBean(String tableName, Map<String, String> columnMap, 
			List<?> listData, OutputStream os) {
		String[] rowArray = new String[columnMap.size()];
		
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(tableName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		// 设置这些样式
//		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中 
//		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中

		 // 背景色
		style.setFillForegroundColor(HSSFColor.BLUE.index);
		style.setFillBackgroundColor(HSSFColor.BLUE.index); 

		HSSFCell cell = row.createCell((short) 0);
		short rowIndex = 0;
		
		Iterator<String> it = columnMap.keySet().iterator();
		
		while(it.hasNext()) {
			String key = it.next();
			String value = columnMap.get(key);
			rowArray[rowIndex] = key;
			cell.setCellValue(value);
			cell.setCellStyle(style);
			rowIndex ++;
			cell = row.createCell(rowIndex);
		}
		
		// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
		int listLength = listData.size();
		
		for (int i = 0; i < listLength; i++) {
			row = sheet.createRow((int) i + 1);
			Object bean = listData.get(i);
			Class<?> cls = bean.getClass();
			
			// 第四步，创建单元格，并设置值
			try {
				for (short j = 0; j < rowIndex; j++) {
					Field field = cls.getDeclaredField(rowArray[j]);
					field.setAccessible(true);
					Object value = field.get(bean);
					row.createCell(j).setCellValue(value == null ? "" : value.toString());
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
		// 第六步，将文件存到指定位置
		try {
			wb.write(os);
			os.close();
			wb.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
 
    /**
     * 读取xls文件内容
     * 
     * @param is输入流
     * @return List<XlsDto>对象
     * @throws IOException
     * 输入/输出(i/o)异常
     */
    public static List<Map<String, Object>> readXls(InputStream is) throws IOException {
    	List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    	Map<String, Object> xlsMap = null;
    	HSSFWorkbook hssfWorkbook = null;
    	try {
            hssfWorkbook = new HSSFWorkbook(is);
            // 循环工作表Sheet
            for (int numSheet = 0; numSheet < hssfWorkbook.getNumberOfSheets(); numSheet++) {
                HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(numSheet);
                if (hssfSheet == null) {
                    continue;
                }
                // 循环行Row
                for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
                    HSSFRow hssfRow = hssfSheet.getRow(rowNum);
                    xlsMap = new HashMap<String, Object>();
                    
                    if (hssfRow == null) {
                        continue;
                    }
                    // 循环列Cell
                    for (Cell cell : hssfRow) {
                    	xlsMap.put(""+cell.getColumnIndex(), cell.getStringCellValue());
					}
                    list.add(xlsMap);
                }
            }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			hssfWorkbook.close();
		}
        return list;
    }
}
