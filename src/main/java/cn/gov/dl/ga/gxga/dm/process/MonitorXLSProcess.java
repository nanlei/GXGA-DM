package cn.gov.dl.ga.gxga.dm.process;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.gov.dl.ga.gxga.dm.model.Monitor;
import cn.gov.dl.ga.gxga.dm.unit.Branch;
import cn.gov.dl.ga.gxga.dm.unit.HeKou;
import cn.gov.dl.ga.gxga.dm.unit.LingShui;
import cn.gov.dl.ga.gxga.dm.unit.LongWangTang;
import cn.gov.dl.ga.gxga.dm.unit.QiXianLing;
import cn.gov.dl.ga.gxga.dm.util.FileUtil;
import cn.gov.dl.ga.gxga.dm.util.JSONUtil;

public class MonitorXLSProcess {
	private static Logger logger = LoggerFactory
			.getLogger(MonitorXLSProcess.class);

	public void process(Sheet[] sheets) {
		Branch branch = new Branch();
		LingShui lingshui = new LingShui();
		QiXianLing qixianling = new QiXianLing();
		HeKou hekou = new HeKou();
		LongWangTang longwangtang = new LongWangTang();

		for (int i = 0; i < sheets.length; i++) {
			HSSFSheet xlsSheet = (HSSFSheet) sheets[i];

			ArrayList<Monitor> monitors = null;

			if (i == 0) {// Sheet 0: 分局
				monitors = processSheet(xlsSheet, branch.getId_prefix());
				branch.setMonitors(monitors);
			} else if (i == 1) {// Sheet 1: 凌水
				monitors = processSheet(xlsSheet, lingshui.getId_prefix());
				lingshui.setMonitors(monitors);
			} else if (i == 2) {// Sheet 2: 七贤岭
				monitors = processSheet(xlsSheet, qixianling.getId_prefix());
				qixianling.setMonitors(monitors);
			} else if (i == 3) {// Sheet 3: 河口
				monitors = processSheet(xlsSheet, hekou.getId_prefix());
				hekou.setMonitors(monitors);
			} else if (i == 4) {// Sheet 4: 龙王塘
				monitors = processSheet(xlsSheet, longwangtang.getId_prefix());
				longwangtang.setMonitors(monitors);
			}
		}

		String branchJson = JSONUtil.getJson(branch.getMonitors());
		String lingshuiJson = JSONUtil.getJson(lingshui.getMonitors());
		String qixianlingJson = JSONUtil.getJson(qixianling.getMonitors());
		String hekouJson = JSONUtil.getJson(hekou.getMonitors());
		String longwangtangJson = JSONUtil.getJson(longwangtang.getMonitors());

		String rootPath = System.getProperty("user.dir");

		FileUtil.writeToFile("var branch_monitor =" + branchJson, rootPath
				+ "\\data\\branch.js");
		FileUtil.writeToFile("var lingshui_monitor =" + lingshuiJson, rootPath
				+ "\\data\\lingshui.js");
		FileUtil.writeToFile("var qixianling_monitor =" + qixianlingJson,
				rootPath + "\\data\\qixianling.js");
		FileUtil.writeToFile("var hekou_monitor =" + hekouJson, rootPath
				+ "\\data\\hekou.js");
		FileUtil.writeToFile("var longwangtang_monitor =" + longwangtangJson,
				rootPath + "\\data\\longwangtang.js");
	}

	private ArrayList<Monitor> processSheet(HSSFSheet xlsSheet, String prefix) {
		ArrayList<Monitor> monitors = new ArrayList<Monitor>();
		for (int i = 1; i <= xlsSheet.getLastRowNum(); i++) {
			Monitor monitor = new Monitor();
			HSSFRow row = xlsSheet.getRow(i);
			if (row == null) {
				continue;
			}

			for (int j = 0; j < row.getLastCellNum(); j++) {
				HSSFCell cell = row.getCell(j);

				String cellStr = null;
				if (cell == null) {// 单元格为空设置cellStr为空串
					cellStr = "";
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {// 对布尔值的处理
					cellStr = String.valueOf(cell.getBooleanCellValue());
				} else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {// 对数字值的处理
					cellStr = cell.getNumericCellValue() + "";
				} else {// 其余按照字符串处理
					cellStr = cell.getStringCellValue();
				}

				monitor.setId(prefix
						+ StringUtils.leftPad(String.valueOf(i), 3, '0'));
				if (j == 0) {
					monitor.setArea(cellStr);
				} else if (j == 1) {
					monitor.setName(cellStr);
				} else if (j == 2) {
					monitor.setLng(cellStr);
				} else if (j == 3) {
					monitor.setLat(cellStr);
				} else {
					monitor.setStatus("Y".equalsIgnoreCase(cellStr) ? "√" : "×");
				}

				monitor.setRemark("");
			}
			monitors.add(monitor);
			logger.debug("{}", monitor);
		}

		return monitors;
	}
}
