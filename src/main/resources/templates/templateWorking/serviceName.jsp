<!DOCTYPE html>
<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="GG" uri="/WEB-INF/tlds/business-component.tld"%>
<%@taglib prefix="EF" uri="/WEB-INF/framework/tlds/EF-2.0.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="ei" scope="request"
	class="com.baosight.iplat4j.core.ei.EiInfo" />
<%
	String actionUrl = request.getContextPath() + "/DispatchAction.do";
%>
<c:set var="list" value="{ei.blocks.result_set.rows}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>项目信息维护</title>
<script type="text/javascript" src="./EF/iplat-ui-2.0.js"></script>
<script type="text/javascript" src="./PF/PJ/${serviceName}.js"></script>
<script type="text/javascript" src="./config/config.js"></script>

<style type="text/css">
.bgA {
	background-color: #ffbbbd !important;
}

.bgB {
	background-color: #ed8be8 !important;
}

.bgC {
	background-color: #fbfb99 !important;
}

table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	border-style: solid;
	border-color: #666666;
	background-color: #33ccff;
}

table.gridtable td.gridtd {
	border-width: 1px;
	border-style: solid;
	border-color: #666666;
	background-color: white;
}
</style>
</head>

<body class="bodyBackground">

	<form id="forms" method="POST" action="<%=actionUrl%>">
		<jsp:include flush="false" page="../../EF/Form/iplat.ef.head.jsp"></jsp:include>
		<div id="ef_region_inqu" title="查询条件" efRegionShowClear="true">
			<div style="overflow: hidden; width: 100%">
				<table width="100%">
					<tr>
						<td nowrap align="right" width="10%">项目名称</td>
						<td nowrap align="left" width="10%">
							<EF:EFInput blockId="inqu_status" row="0" ename="projectName" />
						</td>
						<td nowrap align="right" width="10%">项目开始日期</td>
						<td nowrap align="left" width="10%">
							<EF:EFInput blockId="inqu_status" ename="projectStartTime" row="0" popup="date" etc="maxLength='8' size='7'  style='width:150%'"   />
						</td>
						<td nowrap align="right" width="10%">项目完成日期</td>
						<td nowrap align="left" width="10%">
							<EF:EFInput blockId="inqu_status" ename="projectEndTime" row="0" popup="date" etc="maxLength='8' size='7'  style='width:150%'"  />
						</td> 
						<td nowrap align="right" width="10%">申通方项目经理</td>
						<td nowrap align="left" width="10%">
							<EF:EFInput blockId="inqu_status" row="0" ename="firstManagerName" />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div id="ef_region_result" title="记录集" style="overflow: scroll">
			<div id="ef_grid_result" title="记录集"
				style="overflow: hidden; height: 400px;"></div>
		</div>
		<EF:EFGrid blockId="result" autoDraw="no" style="toolBar:true" ajax="true">   
			
			<EF:EFComboColumn ename="deptName" cname="部门名称"  width="100" enable="true" align="center"   >
				<EF:EFOption label="" value=""></EF:EFOption>
				<EF:EFOption label="信息化事业部" value="信息化事业部"></EF:EFOption>
				<EF:EFOption label="智能化事业部" value="智能化事业部"></EF:EFOption>
			</EF:EFComboColumn>			
			<EF:EFColumn ename="projectName" cname="项目名称" width="100" align="center"  />
		</EF:EFGrid>

		<EF:EFRegion />
		<jsp:include flush="false" page="../../EF/Form/iplat.ef.tail.jsp"></jsp:include>
	</form>
	<iframe id = "downloadObject" width=0 height=0></iframe>
</body>
<script type="text/javascript">
    efregion.show("ef_region_result");
    efregion.show("ef_region_inqu"); 
    
</script>
</html>