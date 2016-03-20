<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>交易流水管理</title>
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
		<li class="active"><a href="${ctx}/pay/txdevice/txDevice/">交易流水列表</a></li>
		<shiro:hasPermission name="pay:txdevice:txDevice:edit"><li><a href="${ctx}/pay/txdevice/txDevice/form">交易流水添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="txDevice" action="${ctx}/pay/txdevice/txDevice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>号码</th>
				<th>时间</th>
				<th>用户</th>
				<th>编号</th>
				<th>创建时间</th>
				<shiro:hasPermission name="pay:txdevice:txDevice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="txDevice">
			<tr>
				<td><a href="${ctx}/pay/txdevice/txDevice/form?id=${txDevice.id}">
					${txDevice.txCode}
				</a></td>
				<td>
					${txDevice.txTime}
				</td>
				<td>
					${txDevice.user.name}
				</td>
				<td>
					${txDevice.orderNo}
				</td>
				<td>
					<fmt:formatDate value="${txDevice.createDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="pay:txdevice:txDevice:edit"><td>
    				<a href="${ctx}/pay/txdevice/txDevice/form?id=${txDevice.id}">修改</a>
					<a href="${ctx}/pay/txdevice/txDevice/delete?id=${txDevice.id}" onclick="return confirmx('确认要删除该交易流水吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>