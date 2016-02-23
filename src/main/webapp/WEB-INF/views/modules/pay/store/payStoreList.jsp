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
		<li class="active"><a href="${ctx}/pay/store/payStore/">门店列表</a></li>
		<shiro:hasPermission name="pay:store:payStore:edit"><li><a href="${ctx}/pay/store/payStore/form">门店添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="payStore" action="${ctx}/pay/store/payStore/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>删除标记：</label>
				<form:radiobuttons path="delFlag" items="${fns:getDictList('del_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li><label>状态：</label>
				<form:select path="status" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('pay_store_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>门店电话：</label>
				<form:input path="tel" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>门店地址：</label>
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
				<th>名称</th>
				<th>状态</th>
				<th>门店电话</th>
				<th>门店地址</th>
				<th>创建时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="pay:store:payStore:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payStore">
			<tr>
				<td><a href="${ctx}/pay/store/payStore/form?id=${payStore.id}">
					${payStore.name}
				</a></td>
				<td>
					${fns:getDictLabel(payStore.status, 'pay_store_flag', '')}
				</td>
				<td>
					${payStore.tel}
				</td>
				<td>
					${payStore.addr}
				</td>
				<td>
					<fmt:formatDate value="${payStore.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${payStore.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${payStore.remarks}
				</td>
				<shiro:hasPermission name="pay:store:payStore:edit"><td>
    				<a href="${ctx}/pay/store/payStore/form?id=${payStore.id}">修改</a>
					<a href="${ctx}/pay/store/payStore/delete?id=${payStore.id}" onclick="return confirmx('确认要删除该门店吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>