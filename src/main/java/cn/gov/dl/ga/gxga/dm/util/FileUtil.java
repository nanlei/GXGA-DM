package cn.gov.dl.ga.gxga.dm.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileUtil {
	private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	public static InputStream readAsInputStream(String filePath) {
		File file = new File(filePath);
		InputStream is = null;
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			logger.debug("{}", ExceptionUtils.getStackTrace(e));
		}
		return is;
	}

	public static void writeToFile(String content, String filePath) {
		File file = new File(filePath);
		try {
			file.createNewFile();
			BufferedWriter bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.flush();
			bw.close();
		} catch (Exception e) {
			logger.debug("{}", ExceptionUtils.getStackTrace(e));
		}
	}

	public static String getFileExtensionName(String filePath) {
		int split = filePath.lastIndexOf(".");

		if (split == -1) {
			return "txt";
		}

		return filePath.substring(split + 1, filePath.length());
	}
}
