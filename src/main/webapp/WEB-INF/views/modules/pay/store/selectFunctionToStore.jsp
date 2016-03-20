<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>分配商户</title>
	<meta name="decorator" content="blank"/>
	<%@include file="/WEB-INF/views/include/treeview.jsp" %>
	<script type="text/javascript">
	
		var officeTree;
		var selectedTree;//zTree已选择对象
		
		// 初始化
		$(document).ready(function(){
			allFunctionsTree = $.fn.zTree.init($("#allFunctionsTree"), setting, allFunctionsTreeNodes);
			selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
		});

		var setting = {view: {selectedMulti:false,nameIsHTML:true,showTitle:false,dblClickExpand:false},
				data: {simpleData: {enable: true}},
				callback: {onClick: treeOnClick}};
		
		var allFunctionsTreeNodes=[
	            <c:forEach items="${allFunctions}" var="function">
	            {id:"${function.id}",
	             pId:"0", 
	             name:"${function.name}"},
	            </c:forEach>];
		var selectedNodes =[
		        <c:forEach items="${functions}" var="function">
		        {id:"${function.id}",
		         pId:"0",
		         name:"<font color='red' style='font-weight:bold;'>${function.name}</font>"},
		        </c:forEach>];
		
		var pre_ids = "${selectIds}".split(",");
		var ids = "${selectIds}".split(",");
		
		//点击选择项回调
		function treeOnClick(event, treeId, treeNode, clickFlag){
			$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
			if("allFunctionsTree"==treeId){
				if($.inArray(String(treeNode.id), ids)<0){
					selectedTree.addNodes(null, treeNode);
					ids.push(String(treeNode.id));
				}
			}
			if("selectedTree"==treeId){
				if($.inArray(String(treeNode.id), pre_ids)<0){
					selectedTree.removeNode(treeNode);
					ids.splice($.inArray(String(treeNode.id), ids), 1);
				}else{
					top.$.jBox.tip("门店原有功能按钮不能清除！", 'info');
				}
			}
		};
		function clearAssign(){
			var submit = function (v, h, f) {
			    if (v == 'ok'){
					var tips="";
					if(pre_ids.sort().toString() == ids.sort().toString()){
						tips = "未给门店【${payStore.name}】分配新功能按钮！";
					}else{
						tips = "已选功能按钮清除成功！";
					}
					ids=pre_ids.slice(0);
					selectedNodes=pre_selectedNodes;
					$.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
			    	top.$.jBox.tip(tips, 'info');
			    } else if (v == 'cancel'){
			    	// 取消
			    	top.$.jBox.tip("取消清除操作！", 'info');
			    }
			    return true;
			};
			tips="确定清除门店【${store.name}】下的已选功能按钮？";
			top.$.jBox.confirm(tips, "清除确认", submit);
		};
	</script>
</head>
<body>
	<div id="assignRole" class="row-fluid span12">
		<div class="span5" style="border-right: 1px solid #A8A8A8;">
			<p>待选功能按钮：</p>
			<div id="allFunctionsTree" class="ztree"></div>
		</div>
		<div class="span5" style="padding-left:16px;border-left: 0px solid #A8A8A8;">
			<p>已选功能按钮：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
	</div>
</body>
</html>
