<%@page import="org.apache.commons.collections.MapUtils"%>
<%@page import="top.lmoon.util.JsonResponse"%>
<%@page import="top.lmoon.util.JsonUtil"%>
<%@page import="top.lmoon.service.ShadowsService"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	List<Map<String, Object>> list = null;
	ShadowsService shadowsService = new ShadowsService();
	String result = shadowsService.getSs();
	JsonResponse jr = JsonResponse.newInstance(result);
	if (jr.isSuccess()) {
		Map dataMap = jr.getDataMap();
		list = (List<Map<String, Object>>) dataMap.get("list");
	}
%>
<!DOCTYPE html>
<html>
<head>
<title>Hello LMoon!</title>
<meta name="viewport"
	content="width=device-width,inital-scale=1.0,minimum-scale=0.5,maximum-scale=2.0">
<script src="./resources/js/jquery-1.7.2.min.js"></script>
<script src="./resources/js/clipboard.min.js"></script>
<script src="./resources/js/portscanner.js"></script>
<style type="text/css">
div .list {
	margin-top: 5px;
	margin-bottom: 5px;
}
</style>
</head>
<body>
	<%
		if (list == null || list.isEmpty()) {
	%>
	<div>Hello LMoon!Failed to get ss account...</div>
	<%
		}
		for(Map<String, Object> entry:list){
			String id = MapUtils.getString(entry, "id", "");
			String server = MapUtils.getString(entry, "server", "");
			int serverPort = MapUtils.getIntValue(entry, "serverPort", 0);
			String method = MapUtils.getString(entry, "method", "");
			String password = MapUtils.getString(entry, "password", "");
			String remarks = MapUtils.getString(entry, "remarks", "");
			String url = MapUtils.getString(entry, "url", "");
	%>
		<div class="list">
			<a id="<%=id %>" href="<%=url %>" ><%=server %></a>&nbsp;&nbsp;<input type="button" value="测试连接" class="testbutton" id="test<%=id %>" onclick="javascript:testConnect('<%=id %>','<%=server %>','<%=serverPort %>');" >&nbsp;&nbsp;<input type="button" value="二维码" onclick="javascript:showQrcode('<%=url %>');" >&nbsp;&nbsp;<input type="button" onclick="javascript:showDesc(this);" value="﹀" style="font-family: serif;">
			<div style="display: none;font-family: serif;">
				server:<b><%=server %></b><br>
				serverPort:<b><%=serverPort %></b><br>
				method:<b><%=method %></b><br>
				password:<b><%=password %></b><br>
				remarks:<b><%=remarks %></b><br>
			</div>
		</div>
	<%
		}
	%>
		

	<script language="javascript" type="text/javascript">
		$(document).ready(function() {
			$(".testbutton").each(function(index, el) {
				setTimeout(function() {
					el.click();
				}, 1000 * index);
			});
		});

		function showDesc(e){
			var ne=e.nextElementSibling;
			if(e.value=="﹀"){
				e.value = "︿";
				ne.style.display="block";
			}else if(e.value=="︿"){
				e.value = "﹀";
				ne.style.display="none";
			}
			
		}

		function showResult(dom, imgsrc) {
	        dom.innerHTML = '<div id="ss_plugin_mask" style="margin:0;font-family:\'Source Sans Pro\', \'Microsoft Yahei\',sans-serif,Arial; font-size:14px;color:black;position:fixed;top:0;right:0;bottom:0;left:0;background:#f1f1f1;background:rgba(0,0,0,0.39);z-index:99999"></div>' + 
	                        '<div style="margin:0;font-family:\'Source Sans Pro\', \'Microsoft Yahei\',sans-serif,Arial; font-size:14px;color:black;position:fixed;top:80px;left:50%;margin-left:-141px;padding:10px 10px;width:260px;border:1px solid #d9d9d9;background:#fff;text-align:center;z-index:100000;">' + 
	                            '<a id="ss_plugin_close" href="javascript:;" style="text-decoration:none;color:#666;position:absolute;top:5px;right:10px;display:block;width:20px;height:20px;font-size:20px;cursor:pointer">×</a>' + 
	                            '<p style="margin:0;padding: 0;line-height:10px">生成二维码为：</p>' + 
	                            '<img style="box-shadow:2px 2px 9px 3px #888888;margin: 10px;" width="220" height="220" src="' + imgsrc + '" /></div>';
		    dom.style.display = "block"; // show

		    var close_btn = document.getElementById("ss_plugin_close");
		    close_btn.onclick = function() {
		        dom.innerHTML = " "; // hide
		    }
		    var mask = document.getElementById("ss_plugin_mask");
		    mask.onclick = function() {
		        dom.innerHTML = " "; // hide
		    }
		    
		    return false;
		}

		function getMaskContainer() {
		    var qrcode_div = document.getElementById("ss_qrcode_plugin");
		    if (!qrcode_div) {
		        qrcode_div = document.createElement("div");
		        qrcode_div.id = "ss_qrcode_plugin";
		        qrcode_div.style.display = "none";
		        document.body.appendChild(qrcode_div);
		    }
		    return qrcode_div;
		}

		function showQrcode(url) {
		    var qrcode_dom = getMaskContainer();
		    showResult(qrcode_dom, "/s/qrcode?url="+url);	    
		}

		function testConnect(id,host,port) {
			$("#test"+id).val("testing...");
			$("#test"+id).attr("disabled","true");
			var ps = new PortScanner();
			ps.scanPort(function(id,result){
				$("#test"+id).val(result==1?"√":"×");
				$("#test"+id).removeAttr("disabled");
				},host,port,3000,id);
		}
	</script>
</body>
</html>
