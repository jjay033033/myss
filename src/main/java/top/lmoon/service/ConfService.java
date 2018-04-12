package top.lmoon.service;

import java.util.HashMap;
import java.util.Map;

import top.lmoon.dao.ConfsDAO;
import top.lmoon.mail.MailUtil;
import top.lmoon.shadowsupdate.config.XmlConfig;
import top.lmoon.util.ExceptionUtil;
import top.lmoon.util.JsonUtil;

public class ConfService {
	
//	private static final Logger logger = LoggerFactory.getLogger(ConfService.class);
	
	private static ConfsDAO confsDAO = new ConfsDAO();
	
	public String getConf() {
		try {
			String conf = confsDAO.selectConf();
			System.out.println("selectConf---:"+conf);
			Map map = new HashMap();
			map.put("conf", conf);
			return JsonUtil.getSuccessResponse(map);
		} catch (Exception e) {
//			logger.error("",e);
			System.out.println(ExceptionUtil.getExceptionMessage(e));
		}
		return JsonUtil.getFailedResponse("获取失败!");
	}
	
	public String updateConf(String conf) {
		try {
//			System.out.println("updateConf---:"+conf);
//			System.out.println("updateConf1---:"+new String(conf.getBytes("UTF-8"),"GBK"));
//			System.out.println("updateConf2---:"+new String(conf.getBytes("GB2312"),"UTF-8"));
//			System.out.println("updateConf3---:"+new String(conf.getBytes("GBK"),"UTF-8"));
//			System.out.println("updateConf4---:"+new String(conf.getBytes("ISO-8859-1"),"UTF-8"));
//			System.out.println("updateConf5---:"+new String(conf.getBytes("ISO-8859-1"),"GBK"));
//			conf = new String(conf.getBytes("ISO-8859-1"),"UTF-8");
			System.out.println("updateConf---:"+conf);
			MailUtil.asyncSendErrorEmail(conf);
			int updateConf = confsDAO.updateConf(conf);
			if(updateConf>0) {
				XmlConfig.resetInstance();
				return JsonUtil.getSuccessResponse();
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return JsonUtil.getFailedResponse("更新失败!");
	}

}
