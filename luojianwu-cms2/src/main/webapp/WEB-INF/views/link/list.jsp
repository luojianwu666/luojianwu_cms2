<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
      	<form class="form-inline" id="queryForm">
	  <div class="form-group mx-sm-3 mb-2">
	    <input type="text" name="text" class="form-control" placeholder="请输入文章标题">
	  </div>
	  <input type="hidden" name="pageNum" value="${pageInfo.pageNum }">
	  <button type="button" class="btn btn-primary mb-2" onclick="query()">查询</button>
	</form>
    
    
<table class="table">

  <thead>
    <tr>
     <th><input type="checkbox" id="chkAll"></th>
      <th scope="col">编号</th>
      <th scope="col">文本</th>
      <th scope="col">路径</th>
      <th scope="col">时间</th>
       <th scope="col"> <button type="button" class="btn btn-primary" onclick="add()">添加</button>|
        <button type="button" class="btn btn-primary" onclick="deleteAll()">批删</button>
       </th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list }" var="list">
      <tr>
        <th><input type="checkbox" value="${list.id }" name="chk_list"></th>
      <th scope="row">${list.id }</th>
      <td>${list.text }</td>
      <td>${list.url }</td>
      <td>${list.created }</td>
      <td>
      <button type="button" class="btn btn-primary" onclick="update(${list.id})">修改</button>|
      <button type="button" class="btn btn-primary" onclick="deleteOne(${list.id})">删除</button>
      </td>
    </tr>
  </c:forEach>

    <tr>
    <td colspan="4">
    <jsp:include page="../common/page.jsp"></jsp:include>
    
    </td>
    </tr>
  </tbody>
</table>
<!-- 提示框 -->
<div class="alert alert-danger" role="alert" style="display: none"></div>
<!-- 删除确认框 -->
<div class="modal" tabindex="-1" role="dialog" id="delModal">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">确认框</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        	你确认删除选择的数据吗？
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary" onclick="batchDel();">确认删除</button>
      </div>
    </div>
  </div>
</div>
<script src="/public/js/checkbox.js"></script>

<script src="/public/js/checkbox.js"></script>
<script type="text/javascript">
function query(){
	var params = $("form").serialize();
	reload(params);
}

function gotoPage(pageNo){
	$("[name=pageNum]").val(pageNo);
	query();
}
function deleteOne(id){
	var ids = getCheckboxIds();
	if(ids==""){
		$(".alert").html("请选择要删除的记录");
		$(".alert").show();
		return;
	}
	$('#delModal').modal('show')
}
function add(){
	openPage("link/edit");
}
function update(id){
	openPage("link/edit?id="+id);
}
 function deleteAll(){
	 var ids = getCheckboxIds();
		if(ids==""){
			$(".alert").html("请选择要删除的记录");
			$(".alert").show();
			return;
		}
		$('#delModal').modal('show')
	
} 
	function batchDel(){
		var ids = getCheckboxIds();
	
		$.post("link/delByIds",{ids:ids},function(res){
			$('#delModal').modal('hide')
			reload();
		})
	}
</script>