package top.lmoon.service;

import java.util.List;
import java.util.Map;

import top.lmoon.shadowsupdate.ShadowsUpdate;
import top.lmoon.util.JsonUtil;

public class ShadowsService {
	
	public String getSs() {
		try {
			List<Map<String, Object>> getss = ShadowsUpdate.getss();
			return JsonUtil.getSuccessResponse(getss);
		} catch (Exception e) {
//			logger.error("",e);
			e.printStackTrace();;
		}
		return JsonUtil.getFailedResponse("获取ss账号 失败!");
	}

}
