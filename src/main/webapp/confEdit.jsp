<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>conf</title>
<script type="text/javascript" src="./js/jquery-1.7.2.min.js"></script>
</head>
<body>
	<form id="cform" action="/conf" method="post">
		<input type="hidden" name="action" value="update" />
		<textarea cols="60" rows="40"
			style="margin: 0px; padding: 0px; width: 100%;" name="conf" id="conf"></textarea>
		<!-- <input type="text" th:field="*{conf}"/> -->
		<input type="submit" />
	</form>
<script type="text/javascript">
$(document).ready(function (){
	$.ajax({ 
		url: "/conf?action=select", 
		data:{},
		success: function(result){
			var jr = eval('(' + result + ')');
			if(jr.success==1){
				$("#conf").text(jr.data.conf);
			}else{
				alert(jr.message);
			}
        }
    });
});
</script>
</body>
</html>