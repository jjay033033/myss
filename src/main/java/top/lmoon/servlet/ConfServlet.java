package top.lmoon.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.lmoon.jdbc.CloseUtil;
import top.lmoon.service.ConfService;
import top.lmoon.util.ParamUtil;
import top.lmoon.util.StringUtil;

public class ConfServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5572433450665502998L;
	
//	private static final Logger logger = LoggerFactory.getLogger(ConfServlet.class);
	
	private static ConfService confService = new ConfService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = null;
		try {
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");
//			resp.setContentType("text/html;charset=UTF-8");
			String action = ParamUtil.getParameter(req, "action", "");
			String conf = ParamUtil.getParameter(req, "conf", "");
			out = resp.getWriter();
			if ("select".equals(action)) {
				out.print(confService.getConf());
				return;
			} else if ("update".equals(action)) {
				if(StringUtil.isNullOrBlank(conf)) {
					out.print("conf is empty!");
					return;
				}
				out.print(confService.updateConf(conf));
				return;
			}
			out.print("hello world!");
			return;
		} catch (Exception e) {
//			logger.error("处理请求出错", e);
			out.print("处理请求出错！");
		} finally {
			CloseUtil.closeSilently(out);
		}

	}

}
