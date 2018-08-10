package top.lmoon.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.lmoon.mail.MailUtil;
import top.lmoon.shadowsupdate.ShadowsUpdate;
import top.lmoon.shadowsupdate.config.XmlConfig;

public class InitServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9036870399826955518L;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("server start!");
		MailUtil.asyncSendErrorEmail("ss:server start!");
		XmlConfig.init();
//		ShadowsUpdate.start();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}
	
}
