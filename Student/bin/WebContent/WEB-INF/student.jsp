<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>STUDENT管理系统</title>
<!-- jQuery -->
<script type="text/javascript" charset="utf8"
	src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<!-- DataTables -->
<script type="text/javascript" charset="utf8"
	src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript" src=jquery-3.3.1.js></script>
<script src="https://ajaxzip3.github.io/ajaxzip3.js"></script>
<script type="text/javascript">
	$(function() {
		$("#idquerysubmit").click(function() {
			$("#queryform").submit();
		})
	})
	$(function() {
		$("#namequerysubmit").click(function() {
			$("#queryform").submit();
		})
	})

	$(function() {
		$("#addbutton").click(function() {
			$("#addForm").submit();
		})
	})
	$(function() {
		$("#IDdel").click(function() {
			$("#delForm").submit();
		})
	})
	$(function() {
		$("#updatebutton").click(function() {
			$("#editForm").submit();
		})
	})
</script>


</head>
<body>
	<div>
		<h1 align="center">学生管理系统</h1>
		<table border="6" align="center" >
			<tr>
				<td>id</td>
				<td>name</td>
				<td>birthday</td>
				<td>age</td>
				<td>score</td>
				<td>classid</td>
				<td>address</td>
			</tr>

			<c:forEach items="${student}" var="student">
				<tr>
					<td>${student.id}</td>
					<td>${student.name}</td>
					<td>${student.birthday}</td>
					<td>${student.age}</td>
					<td>${student.score}</td>
					<td>${student.classid}</td>
					<td>${student.address}</td>
				</tr>
			</c:forEach>
		</table>
	</div>



	<div align="center">
		<h2>学生ID捜索</h2>
		<form id="queryform" action="queryById" method="post">
			<input id="stuId" type="text" placeholder="要查询的id" name="id" />
			<button id="idquerysubmit" type="button">
				<p>查询</p>
			</button>
		</form>
	</div>

	<div align="center">
		<h2>学生名字捜索</h2>
		<form id="queryform" action="queryByName" method="post">
			<input id="stuName" type="text" placeholder="要查询的名字" name="name" />
			<button id="namequerysubmit" type="button">


				<p>查询</p>
			</button>
		</form>
	</div>



	<div id="add_comm" class="all" align="center">
		<h2 id="edit_title">学生追加</h2>
		<form id="addForm" action="add" method="post" class="checkform">

			<input id="addname" type="text" placeholder="氏名" name="name" /> <input
				id="addbirthday" type="text" placeholder="生年月日" name="birthday" />
			<input id="addage" type="text" placeholder="年齢" name="age"
				class="number" /> <span class="alertarea"></span> <br> <input
				id="addscore" type="text" placeholder="成績" name="score" /> <input
				id="addclassid" type="text" placeholder="班级编号" name="classid" />
			<button id="addbutton" type="button">
				<p>添加</p>
			</button>
		</form>
	</div>
	<div id="edit_comm" class="all" align="center">
		<h2>刪除學員</h2>
		<form id="delForm" action="deleteById" method="post">
			<input id="delid" type="text" placeholder="刪除學員的id" name="id" />
			<button id="IDdel" type="button">
				<p>刪除</p>
			</button>
		</form>
	</div>

	<div id="edit_comm" class="all" align="center">
		<h2 id="edit_title">学生更新</h2>
		<form id="editForm" action="update" method="post">
			<input id="editid" type="text" placeholder="要修改的id" id="edit_id"
				name="id" /><br> <br> <input id="editname" type="text"
				placeholder="氏名" name="name" /> <input id="editbirthday"
				type="text" placeholder="生年月日" name="birthday" /> <input
				id="editage" type="text" placeholder="年齢" name="age" /> <br> <input
				id="editscore" type="text" placeholder="成績" name="score" /> <input
				id="editscore" type="text" placeholder="班级编号" name="classid" />
			<button id="updatebutton" type="button">
				<p>修正确认</p>
			</button>
		</form>
	</div>
	<br>
	<div id="edit_comm" class="contro" align="center">
		<h2 id="edit_title">郵便と住所</h2>
		<form id="addAddress" action="address" method="post">
			<label>郵便番号</label> <input id="a" type="text" name="zip01" size="15"
				maxlength="8" placeholder="xxx-xxxx"
				onKeyUp="AjaxZip3.zip2addr(this,'','pref01','addr01');"> <br>
			<label>都道府県</label> <input id="b" type="text" name="pref01" size="15">
			<br> <label>以降の住所</label> <input id="c" type="text"
				name="addr01" size="80"> <br> <label>学生番号</label> <input
				id="d" type="text" name="id" placeholder="学生ID"> <br>
		    <input type="submit" />

		</form>
	</div>






</body>
</html>