package cn.gov.dl.ga.gxga.dm.util;

import java.io.StringWriter;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JSONUtil {
	private static Logger logger = LoggerFactory.getLogger(JSONUtil.class);

	public static String getJson(Object object) {
		ObjectMapper objectMapper = new ObjectMapper();

		StringWriter sw = new StringWriter();

		JsonGenerator generator;

		try {
			generator = objectMapper.getJsonFactory().createJsonGenerator(sw);
			objectMapper.writeValue(generator, object);
		} catch (Exception e) {
			logger.error("{}", ExceptionUtils.getStackTrace(e));
		}

		return sw.toString();

	}
}
