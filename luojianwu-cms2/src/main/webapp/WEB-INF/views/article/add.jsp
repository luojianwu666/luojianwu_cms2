<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>
	var editor = null;
	$(document).ready( function(){
		KindEditor.ready(function(K) {
			editor = K.create('textarea[name="content1"]', {
				cssPath : '/public/kindeditor/plugins/code/prettify.css',
				uploadJson : '/file/upload',
				fileManagerJson : '/file/manager',
				allowFileManager : true,
				afterCreate : function() {
					var self = this;
					K.ctrl(document, 13, function() {
						self.sync();
						document.forms['articleForm'].submit();
					});
					K.ctrl(self.edit.doc, 13, function() {
						self.sync();
						document.forms['articleForm'].submit();
					});
				}
			});
			editor.readonly(false);
			//prettyPrint();
		});
	});

	
</script>
<form id="articleForm" name="articleForm">
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">文章标题</label>
		<div class="col-sm-6">
			<input type="text" name="title" class="form-control" placeholder="请输入标题">
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">文章图片</label>
		<div class="col-sm-6">
			<div class="input-prepend">
				<jsp:include page="../common/uploadImg.jsp" >
					<jsp:param name="feildName" value="picture" />
					<jsp:param name="feildValue" value="${article.picture }"/>
				</jsp:include>
			</div>
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">所属频道</label>
		<div class="col-sm-6">
			<select id="channelId" name="channelId" onchange="changeCate();" class="form-control">
		        <option selected>请选择频道...</option>
		        <c:forEach items="${channelList }" var="item">
		        	<option value="${item.id }">${item.name }</option>
		        </c:forEach>
		      </select>
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">所属分类</label>
		<div class="col-sm-6">
			<select id="categoryId" name="categoryId" class="form-control">
		        <option selected>请选择分类...</option>
		    </select>
		</div>
	</div>
	<div class="form-group row">
		<label for="inputEmail3" class="col-sm-2 col-form-label">文章内容</label>
		<div class="col-sm-10">
			<textarea name="content1" cols="100" rows="8" style="width:760px;height:200px;visibility:hidden;">${article.content}</textarea>
		</div>
	</div>
	<div class="form-group row">
		<div class="col-sm-10">
			<button type="button" class="btn btn-primary" onclick="save();">保存</button>
			<button type="button" class="btn btn-primary" onclick="saveAndCheck();">保存并提交审核</button>
		</div>
	</div>
</form>
<script type="text/javascript">
	function changeCate() {
		var channelId = $("#channelId").val();
		$("#categoryId").html('<option selected>请选择分类...</option>');
		$.get("/article/getCateList",{channelId:channelId},function(res){
			for (var i = 0; i < res.data.length; i++) {
				$("#categoryId").append('<option value="'+res.data[i].id+'" selected>'+res.data[i].name+'</option>');
			}
		})
	}

	function save(){
		var formData = new FormData($("#articleForm")[0]);
		formData.set("content",editor.html());
		console.log(formData.get("content"));
		console.log(editor.html());
		/* $.post("/article/add",formData,function(res){
			console.log(res);
		}); */
		$.ajax({
			type:"post",
			data:formData,
			processData:false,
			contentType:false,
			url:"/admin/user/locked",
			sucess:function(res){
				console.log(res);
			}
		});
	}
</script>
