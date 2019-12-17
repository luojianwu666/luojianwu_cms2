<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<form>
	<input type="hidden" name="id" value="${settings.id }">
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">我的昵称</label>
		<div class="col-sm-10">
			<input type="text" name="siteDomain" value="${settings.siteDomain }" class="form-control" placeholder="蝈蝈">
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">我的生日</label>
		<div class="col-sm-3">
			<input type="date">
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">我的性别</label>
		<div class="col-sm-3">
			   <div class="form-check">
				  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
				  <label class="form-check-label" for="exampleRadios1">男</label>
			   </div>
			   <div class="form-check">
				  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
				  <label class="form-check-label" for="exampleRadios1">女</label>
			   </div>
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">博客网址</label>
		<div class="col-sm-10">
			<input type="text" name="siteName" value="${settings.siteName }" class="form-control" placeholder="https://www.csdn.net/nav/java">
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-10">
			<button type="button" class="btn btn-primary" onclick="save()">保存设置</button>
		</div>
	</div>
	<div class="alert alert-danger" role="alert" style="display: none"></div>
</form>
<script type="text/javascript">
	function save() {
		$.post("/admin/settings/save",$("form").serialize(),function(res){
			if(res.result){
				$(".alert").html("设置成功");
				$(".alert").show();
			}else{
				alert(res.message);
			}
		});
	}
</script>
