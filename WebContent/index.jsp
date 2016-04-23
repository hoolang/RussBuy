<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="post" action="csv/importCSV.action"
		enctype="multipart/form-data">
		<input name="file" type="file" /> <input type="submit" name="导入CSV"
			value="导入CSV"><br />
	</form>
	<hr>

	<form method="post" action="csv/spiderWish.action">
		<textarea rows="10" cols="80" name="urls"></textarea>
		<input type="submit" name="爬虫" value="Wish采集"><br />
	</form>
	<hr>

	<form method="post" action="csv/spider.action">
		<textarea rows="10" cols="80" name="urls"></textarea>
		<input type="submit" name="爬虫" value="PPKOO采集"><br />
	</form>
	<hr>

	<form method="post" action="csv/list.action">
		<input type="submit" name="爬虫" value="采集结果列表"><br />
	</form>
	<hr>

	<form method="post" action="addUser.action"
		enctype="multipart/form-data">
		用户名：<input type="text" name="user.name"><br /> 用户图标<input
			name="file" type="file" /><br /> 性别：男<input type="radio"
			name="user.sex" value="1"> 女<input type="radio"
			name="user.sex" value="0"><br /> 省份：<input type="text"
			name="user.province"><br /> 城市：<input type="text"
			name="user.city"><br /> 描述：
		<textarea rows="5" cols="30" name="user.description"></textarea>
		<br /> <input type="submit"><br />

	</form>

	<hr>

	<!-- 
	private long pid;
	private User user;
	private String content;
	private String icon;
	private Date date; -->
	<form method="post" action="addPost.action"
		enctype="multipart/form-data">
		发布show<input name="file" type="file" /><br /> 内容：
		<textarea rows="5" cols="30" name="post.content"></textarea>
		用户ID：<input type="text" value="" name="user.uid"><br /> <br />

		<input type="submit"><br />

	</form>

	<h1>评论</h1>
	<hr>
	<form method="post" action="nsjson/addComment.json">
		评论内容：
		<textarea rows="5" cols="30" name="comments.comment"></textarea>
		<br /> <input type="submit"><br />
	</form>

	<h1>点赞</h1>
	<form method="post" action="nsjson/addLike.json">
		该用户点赞：<input type="text" value="1" name="user.uid"><br />
		点赞的秀秀：<input type="text" value="42" name="post.pid"><br /> <br />
		<input type="submit"><br />
	</form>

	<h1>获取access_token</h1>
	<hr>
	<form method="post" action="oauth/oauthWish.action">
	client_id:<input type="text" value="1" name="client_id" value="571b48bd2b296c17406ff8e7"><br />
client_secret:<input type="text" value="1" name="client_secret" value="ef6ef7a6823f41beab714111b5764b2b"><br />
code:<input type="text" value="1" name="code" value="b22b533e3fb04b5d8bde1b33ae541df9"><br />
grant_type:<input type="text" value="1" name="grant_type" value="authorization_code"><br />
redirect_uri:<input type="text" value="1" name="redirect_uri" value="https://127.0.0.1"><br />
		<input type="submit"><br />
	</form>
</body>
</html>