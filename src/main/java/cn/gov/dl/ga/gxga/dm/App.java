package cn.gov.dl.ga.gxga.dm;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import cn.gov.dl.ga.gxga.dm.process.MonitorXLSProcess;
import cn.gov.dl.ga.gxga.dm.util.ExcelUtil;

public class App {

	private static final String ROOT_PATH = System.getProperty("user.dir");
	private static final String FILE_PATH = ROOT_PATH + "\\source\\monitor.xls";

	public static void main(String[] args) throws Exception {
		Workbook wb = ExcelUtil.getExcelWorkbook(FILE_PATH);

		Sheet[] sheets = ExcelUtil.getExcelSheets(wb);

		MonitorXLSProcess monitorXLSProcess = new MonitorXLSProcess();

		monitorXLSProcess.process(sheets);
	}
}
