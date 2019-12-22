<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="editForm">
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">名称</label>
		<div class="col-sm-6">
			<input type="text" id="text" name="text" value="${link.text }" class="form-control" placeholder="请输入名称">
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">URL</label>
		<div class="col-sm-6">
			<input type="text" id="url" name="url" value="${link.url }" class="form-control" placeholder="请输入Url">
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
	"link/save",
	$("#editForm").serialize(),
	function(msg){
		 reload(); 
	}
	)
}

</script>