<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>账号管理</title>
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
		<li class="active"><a href="${ctx}/pay/adminuser/adminUser/">账号列表</a></li>
		<shiro:hasPermission name="pay:adminuser:adminUser:edit"><li><a href="${ctx}/pay/adminuser/adminUser/form">账号添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="adminUser" action="${ctx}/pay/adminuser/adminUser/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>帐号：</label>
				<form:input path="id" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>类型：</label>
				<form:select path="flag" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_adminuser_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>状态：</label>
				<form:radiobuttons path="status" items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>帐号</th>
				<th>类型</th>
				<th>状态</th>
				<th>更新时间</th>
				<shiro:hasPermission name="pay:adminuser:adminUser:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="adminUser">
			<tr>
				<td><a href="${ctx}/pay/adminuser/adminUser/form?id=${adminUser.id}">
					${adminUser.id}
				</a></td>
				<td>
					${fns:getDictLabel(adminUser.flag, 'pay_adminuser_flag', '')}
				</td>
				<td>
					${fns:getDictLabel(adminUser.status, 'yes_no', '')}
				</td>
				<td>
					<fmt:formatDate value="${adminUser.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pay:adminuser:adminUser:edit"><td>
    				<a href="${ctx}/pay/adminuser/adminUser/form?id=${adminUser.id}">修改</a>
					<a href="${ctx}/pay/adminuser/adminUser/delete?id=${adminUser.id}" onclick="return confirmx('确认要删除该账号吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>