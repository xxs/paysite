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
		<li class="active"><a href="${ctx}/pay/company/payCompany/">商户列表</a></li>
		<shiro:hasPermission name="pay:company:payCompany:edit"><li><a href="${ctx}/pay/company/payCompany/form">商户添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="payCompany" action="${ctx}/pay/company/payCompany/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>商户名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>营业执照：</label>
				<form:input path="regName" htmlEscape="false" maxlength="128" class="input-medium"/>
			</li>
			<li><label>电话：</label>
				<form:input path="tel" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>地址：</label>
				<form:input path="addr" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>商户名称</th>
				<th>营业执照</th>
				<th>商户状态</th>
				<th>电话</th>
				<th>地址</th>
				<th>描述</th>
				<th>logo</th>
				<th>创建时间</th>
				<shiro:hasPermission name="pay:company:payCompany:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payCompany">
			<tr>
				<td><a href="${ctx}/pay/company/payCompany/form?id=${payCompany.id}">
					${payCompany.name}
				</a></td>
				<td>
					${payCompany.regName}
				</td>
				<td>
					${fns:getDictLabel(payCompany.status, 'pay_company_status', '')}
				</td>
				<td>
					${payCompany.tel}
				</td>
				<td>
					${payCompany.addr}
				</td>
				<td>
					${payCompany.memo}
				</td>
				<td>
					${payCompany.image}
				</td>
				<td>
					<fmt:formatDate value="${payCompany.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pay:company:payCompany:edit"><td>
					<a href="${ctx}/pay/company/payCompany/assign?id=${payCompany.id}">授权用户</a>
    				<a href="${ctx}/pay/company/payCompany/form?id=${payCompany.id}">修改</a>
					<a href="${ctx}/pay/company/payCompany/delete?id=${payCompany.id}" onclick="return confirmx('确认要删除该商户吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>