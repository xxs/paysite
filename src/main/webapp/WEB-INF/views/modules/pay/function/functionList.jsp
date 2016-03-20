<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>功能管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/pay/function/function/">功能列表</a></li>
		<shiro:hasPermission name="pay:function:function:edit"><li><a href="${ctx}/pay/function/function/form">功能添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="function" action="${ctx}/pay/function/function/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标志</th>
				<th>名称</th>
				<th>状态</th>
				<th>类型</th>
				<th>创建时间</th>
				<shiro:hasPermission name="pay:function:function:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="function">
			<tr>
				<td><a href="${ctx}/pay/function/function/form?id=${function.id}">
					${function.id}
				</a></td>
				<td>
					${function.name}
				</td>
				<td>
					${fns:getDictLabel(function.status, 'function_status', '')}
				</td>
				<td>
					${fns:getDictLabel(function.funcType, 'function_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${function.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pay:function:function:edit"><td>
    				<a href="${ctx}/pay/function/function/form?id=${function.id}">修改</a>
					<a href="${ctx}/pay/function/function/delete?id=${function.id}" onclick="return confirmx('确认要删除该功能吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>