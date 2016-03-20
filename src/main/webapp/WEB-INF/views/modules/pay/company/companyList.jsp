<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>商户管理</title>
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
		<li class="active"><a href="${ctx}/pay/company/company/">商户列表</a></li>
		<shiro:hasPermission name="pay:company:company:edit"><li><a href="${ctx}/pay/company/company/form">商户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="company" action="${ctx}/pay/company/company/" method="post" class="breadcrumb form-search">
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
				<th>名称</th>
				<th>注册名</th>
				<th>状态</th>
				<th>电话</th>
				<th>创建时间</th>
				<shiro:hasPermission name="pay:company:company:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="company">
			<tr>
				<td><a href="${ctx}/pay/company/company/form?id=${company.id}">
					${company.name}
				</a></td>
				<td>
					${company.regName}
				</td>
				<td>
					${fns:getDictLabel(company.status, 'company_status', '')}
				</td>
				<td>
					${company.tel}
				</td>
				<td>
					<fmt:formatDate value="${company.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pay:company:company:edit"><td>
    				<a href="${ctx}/pay/company/company/form?id=${company.id}">修改</a>
					<a href="${ctx}/pay/company/company/delete?id=${company.id}" onclick="return confirmx('确认要删除该商户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>