<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="editForm">
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">文章标题</label>
		<div class="col-sm-6">
			<input type="text" id="title" name="title" value="${link.title }" class="form-control" >
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">评论内容</label>
		<div class="col-sm-6">
			<input type="text" id="content" name="content" value="${link.content }" class="form-control" >
		</div>
	</div>
	<input type="hidden" id="id" name="id" value="${link.id }">
	<div class="form-group row">
		<div class="col-sm-10">
			<button type="button" class="btn btn-primary" onclick="save();">保存</button>
		</div>
	</div>
</form>
<div class="alert alert-danger" role="alert" style="display: none"></div>
<script type="text/javascript">
function save(){
	$.post(
	"comment/user/save",
	$("#editForm").serialize(),
	function(msg){
		 reload(); 
	}
	)
}

</script>