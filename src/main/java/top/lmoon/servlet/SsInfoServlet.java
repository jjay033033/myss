package top.lmoon.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import top.lmoon.shadowsupdate.ShadowsUpdate;

public class SsInfoServlet extends HttpServlet {

	private static final long serialVersionUID = -3950059313457885599L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");
		String method = req.getParameter("method");
		PrintWriter writer = resp.getWriter();
		if (method != null && "getss".equals(method)) {
			List<Map<String, Object>> getss = ShadowsUpdate.getss();
			JSONArray ja = JSONArray.fromObject(getss);
//			JSONObject json = JSONObject.fromObject(getss);
			String result = ja.toString();			
			writer.write(result);
			return;
		}
		writer.write("hello world!");
		// BufferedOutputStream bos = new
		// BufferedOutputStream(resp.getOutputStream());
		// qrcoder.encode(content, resp.getOutputStream());
		// resp.setContentType("image/jpeg;charset=GB2312");
	}

}
