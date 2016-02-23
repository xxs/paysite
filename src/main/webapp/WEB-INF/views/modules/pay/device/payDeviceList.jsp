<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>设备管理</title>
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
		<li class="active"><a href="${ctx}/pay/device/payDevice/">设备列表</a></li>
		<shiro:hasPermission name="pay:device:payDevice:edit"><li><a href="${ctx}/pay/device/payDevice/form">设备添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="payDevice" action="${ctx}/pay/device/payDevice/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>所属门店：</label>
				<form:select path="payStore.id" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>编号：</label>
				<form:input path="number" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>名称：</label>
				<form:input path="name" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>状态：</label>
				<form:radiobuttons path="status" items="${fns:getDictList('pay_device_flag')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>所属门店</th>
				<th>名称</th>
				<th>备注信息</th>
				<th>创建者</th>
				<shiro:hasPermission name="pay:device:payDevice:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="payDevice">
			<tr>
				<td><a href="${ctx}/pay/device/payDevice/form?id=${payDevice.id}">
					${fns:getPayStoreById(payDevice.payStore.id).name}
				</a></td>
				<td>
					${payDevice.name}
				</td>
				<td>
					${payDevice.remarks}
				</td>
				<td>
					${fns:getUserById(payDevice.createBy.id).name}
				</td>
				<shiro:hasPermission name="pay:device:payDevice:edit"><td>
    				<a href="${ctx}/pay/device/payDevice/form?id=${payDevice.id}">修改</a>
					<a href="${ctx}/pay/device/payDevice/delete?id=${payDevice.id}" onclick="return confirmx('确认要删除该设备吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>