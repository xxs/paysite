<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>门店授权</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/pay/store/payStore/">门店列表</a></li>
		<li class="active"><a href="${ctx}/pay/store/payStore/assign?id=${payStore.id}"><shiro:hasPermission name="pay:store:payStore:edit">门店授权</shiro:hasPermission><shiro:lacksPermission name="sys:role:edit">人员列表</shiro:lacksPermission></a></li>
	</ul>
	<div class="container-fluid breadcrumb">
		<div class="row-fluid span12">
			<span class="span4">门店名称: <b>${payStore.name}</b></span>
			<span class="span4">门店状态: ${fns:getDictLabel(payStore.status, 'pay_store_flag', '')}</span>
		</div>
		<div class="row-fluid span8">
			<span class="span4">门店电话: ${payStore.tel}</span>
			<span class="span4">门店地址: ${payStore.addr}</span>
			<span class="span4">门店描述: ${payStore.remarks}</span>
		</div>
	</div>
	<sys:message content="${message}"/>
	<div class="breadcrumb">
		<form id="assignPayStoreForm" action="${ctx}/pay/store/payStore/assignpayStore" method="post" class="hide">
			<input type="hidden" name="id" value="${payStore.id}"/>
			<input id="idsArr" type="hidden" name="idsArr" value=""/>
		</form>
		<input id="assignButton" class="btn btn-primary" type="submit" value="分配功能按钮"/>
		<script type="text/javascript">
			$("#assignButton").click(function(){
				top.$.jBox.open("iframe:${ctx}/pay/store/payStore/selectFunctionToPayStore?id=${payStore.id}", "分配门店",810,$(top.document).height()-240,{
					buttons:{"确定分配":"ok", "清除已选":"clear", "关闭":true},submit:function(v, h, f){
						var pre_ids = h.find("iframe")[0].contentWindow.pre_ids;
						var ids = h.find("iframe")[0].contentWindow.ids;
						//nodes = selectedTree.getSelectedNodes();
						if (v=="ok"){
							// 删除''的元素
							if(ids[0]==''){
								ids.shift();
								pre_ids.shift();
							}
							if(pre_ids.sort().toString() == ids.sort().toString()){
								top.$.jBox.tip("未给门店【${payStore.name}】分配新成员！", 'info');
								return false;
							};
					    	// 执行保存
					    	loading('正在提交，请稍等...');
					    	var idsArr = "";
					    	for (var i = 0; i<ids.length; i++) {
					    		idsArr = (idsArr + ids[i]) + (((i + 1)== ids.length) ? '':',');
					    	}
					    	$('#idsArr').val(idsArr);
					    	$('#assignPayStoreForm').submit();
					    	return true;
						} else if (v=="clear"){
							h.find("iframe")[0].contentWindow.clearAssign();
							return false;
		                }
					}, loaded:function(h){
						$(".jbox-content", top.document).css("overflow-y","hidden");
					}
				});
			});
		</script>
	</div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>编号</th><th>功能名称</th><shiro:hasPermission name="pay:store:payStore:edit"><th>操作</th></shiro:hasPermission></tr></thead>
		<tbody>
		<c:forEach items="${payFunctions}" var="payFunction">
			<tr>
				<td>${payFunction.number}</td>
				<td>${payFunction.name}</td>
				<shiro:hasPermission name="pay:store:payStore:edit"><td>
					<a href="${ctx}/pay/store/payStore/outpayStore?payStoreId=${payStore.id}&payFunctionId=${payFunction.id}" 
						onclick="return confirmx('确认要将功能按钮<b>[${payFunction.name}]</b>从<b>[${payStore.name}]</b>门店中移除吗？', this.href)">移除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
