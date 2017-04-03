<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出产品负责人统计数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/mms/product/selectByProductLeader/export");
                        $("#searchForm").submit();
                        //跳转链接改回查询
                        $("#searchForm").attr("action", "${ctx}/mms/product/selectByProductLeader");
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
            });
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
		<li class="active"><a href="${ctx}/mms/product/selectByProductLeader">产品列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="productStatusVo" action="${ctx}/mms/product/selectByProductLeader" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品负责人：</label>
				<form:input path="productLeader" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>时间范围：</label> <input id="startDate" name="startDate"
											type="text" readonly="readonly" maxlength="20"
											class="input-mini Wdate"
											value="${productStatusVo.startDate}"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});" />
				&nbsp;&nbsp;--&nbsp;&nbsp;</label><input id="endDate" name="endDate"
														 type="text" readonly="readonly" maxlength="20"
														 class="input-mini Wdate"
														 value="${productStatusVo.endDate}"
														 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false})" />
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品负责人</th>
				<th>初审产品数量</th>
				<th>送检产品数量</th>
				<th>申报产品数量</th>
				<th>完善资料产品数量</th>
				<th>取得批件产品数量</th>
				<th>不予批准产品数量</th>
				<th>终止申报产品数量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="productStatusVo">
			<tr>
				<td>
						${productStatusVo.productLeader}
				</td>
				<td>
						${productStatusVo.trialProductNumber}
				</td>
				<td>
						${productStatusVo.inspectionProductNumber}
				</td>
				<td>
						${productStatusVo.declareProductNumber}
				</td>
				<td>
						${productStatusVo.perfectInformationProductNumber}
				</td>
				<td>
						${productStatusVo.getApprovalProductNumber}
				</td>
				<td>
						${productStatusVo.notApprovedProductNumber}
				</td>
				<td>
						${productStatusVo.terminationDeclarationProductNumber}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>