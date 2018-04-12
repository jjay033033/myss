<%@page import="org.apache.commons.collections.MapUtils"%>
<%@page import="top.lmoon.util.JsonResponse"%>
<%@page import="top.lmoon.service.ConfService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String conf = "";
	ConfService confService = new ConfService();
	String result = confService.getConf();
	JsonResponse jr = JsonResponse.newInstance(result);
	if(jr.isSuccess()){
		conf = MapUtils.getString(jr.getDataMap(), "conf", "");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>conf</title>
</head>
<body>
	<form id="cform" action="#" method="post">
		<textarea cols="60" rows="40"
			style="margin: 0px; padding: 0px; width: 100%;"><%=conf %></textarea>
		<!-- <input type="text" th:field="*{conf}"/> -->
		<input type="submit" />
	</form>
</body>
</html>