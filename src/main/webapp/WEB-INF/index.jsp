<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
    	<meta charset="utf-8" />
	    <meta http-equiv="X-UA-Compatible" content="chrome=1" />
	    <title>HTML5 Web Terminal</title>
	    <link href="https://fonts.googleapis.com/css?family=Inconsolata" rel="stylesheet" type="text/css" />
	    <link rel="stylesheet" type="text/css" href="http://localhost:8080/views/css.css">
 	</head>
  	<body>
	    <div id="container">
	      <output></output>
	      <div id="input-line" class="input-line">
	        <div class="prompt"></div><div><input class="cmdline" autofocus /></div>
	      </div>
	    </div>
	</body>
    <script src="https://code.jquery.com/jquery-2.1.1.min.js"></script>
    <script src="http://localhost:8080/views/js.js"></script>
    <script>
		$( document ).ready(function() {
			$.ajax({   
				type: "GET",
				url: "http://localhost:8080/api/setting/"+getCookie("token"),
				contentType:'application/json',
				success:function(data){
					if(data != null) {
						$("#inputTitle").val(data.title);
						$("#inputInfo").val(data.infoURL);
						$("#inputPhoto").val(data.imageURL);
						$("#inputPrompt").val(data.prompt);
						$("#inputTitleColor").val(data.titleColor);
						$("#inputTextColor").val(data.textColor);
						$("#inputPromptColor").val(data.promptColor);
						$("#inputBackgroundColor").val(data.backgroundColor);
						settingSeq = data.seq;
					} else {
						alert("ì´ê¸° ë°ì´í°ë¥¼ ì¸ííì¸ì!");
					}
				},
				error:function(data){
					alert("ì´ê¸° ë°ì´í°ë¥¼ ì¸ííì¸ì");
				}
			});
		});
    </script>
</html>