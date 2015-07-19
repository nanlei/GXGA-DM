package cn.gov.dl.ga.gxga.dm.util;

import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	private static final String XLS_SUFFIX = "xls";
	private static final String XLSX_SUFFIX = "xlsx";

	public static Workbook getExcelWorkbook(String filePath) throws Exception {

		String fileExtensionName = FileUtil.getFileExtensionName(filePath);

		InputStream is = FileUtil.readAsInputStream(filePath);

		Workbook wb = null;

		if (XLS_SUFFIX.equalsIgnoreCase(fileExtensionName)) {
			wb = new HSSFWorkbook(is);
		} else if (XLSX_SUFFIX.equalsIgnoreCase(fileExtensionName)) {
			wb = new XSSFWorkbook(is);
		} else {
		}
		is.close();
		return wb;
	}

	public static Sheet[] getExcelSheets(Workbook wb) {
		Sheet[] sheets = new Sheet[wb.getNumberOfSheets()];
		if (wb instanceof HSSFWorkbook) {
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				HSSFSheet sheet = (HSSFSheet) wb.getSheetAt(i);
				sheets[i] = sheet;
			}
		} else if (wb instanceof XSSFWorkbook) {
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				XSSFSheet sheet = (XSSFSheet) wb.getSheetAt(i);
				sheets[i] = sheet;
			}
		}
		return sheets;
	}
}
