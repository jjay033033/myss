package top.lmoon.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import top.lmoon.dao.ConfsDAO;
import top.lmoon.util.JsonUtil;

public class ConfService {
	
	private static final Logger logger = LoggerFactory.getLogger(ConfService.class);
	
	private static ConfsDAO confsDAO = new ConfsDAO();
	
	public String getConf() {
		try {
			String conf = confsDAO.selectConf();
			Map map = new HashMap();
			map.put("conf", conf);
			return JsonUtil.getSuccessResponse(map);
		} catch (Exception e) {
			logger.error("",e);
		}
		return JsonUtil.getFailedResponse("获取失败!");
	}

}
