<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门店管理</title>
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
		<li class="active"><a href="${ctx}/pay/store/store/">门店列表</a></li>
		<shiro:hasPermission name="pay:store:store:edit"><li><a href="${ctx}/pay/store/store/form">门店添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="store" action="${ctx}/pay/store/store/" method="post" class="breadcrumb form-search">
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
				<th>门店标志</th>
				<th>状态</th>
				<th>电话</th>
				<th>地址</th>
				<th>创建时间</th>
				<shiro:hasPermission name="pay:store:store:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="store">
			<tr>
				<td><a href="${ctx}/pay/store/store/form?id=${store.id}">
					${store.name}
				</a></td>
				<td>
					${store.storeId}
				</td>
				<td>
					${fns:getDictLabel(store.status, 'store_status', '')}
				</td>
				<td>
					${store.tel}
				</td>
				<td>
					${store.addr}
				</td>
				<td>
					<fmt:formatDate value="${store.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pay:store:store:edit"><td>
					<a href="${ctx}/pay/store/store/assign?pk=${store.pk}">授权功能按钮</a>
    				<a href="${ctx}/pay/store/store/form?pk=${store.pk}">修改</a>
					<a href="${ctx}/pay/store/store/delete?pk=${store.pk}" onclick="return confirmx('确认要删除该门店吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>