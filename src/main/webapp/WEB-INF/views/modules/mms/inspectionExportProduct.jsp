<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出送检产品数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/mms/product/inspectionExportProductInfo/export");
                        $("#searchForm").submit();
                        //跳转链接改回查询
                        $("#searchForm").attr("action", "${ctx}/mms/product/inspectionExportProductInfo");
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
		<li class="active"><a href="${ctx}/mms/product/">送检导出清单列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/mms/product/inspectionExportProductInfo" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>时间范围：</label> <input id="startDate" name="startDate"
											type="text" readonly="readonly" maxlength="20"
											class="input-mini Wdate"
											value="${product.startDate}"
											onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true});" />
				&nbsp;&nbsp;--&nbsp;&nbsp;</label><input id="endDate" name="endDate"
														 type="text" readonly="readonly" maxlength="20"
														 class="input-mini Wdate"
														 value="${product.endDate}"
														 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:true})" />
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
				<th>产品编号</th>
				<th>中文名称</th>
				<th>英文名称</th>
				<th>产品负责人</th>
				<th>申请企业地址</th>
				<th>申请企业电话</th>
				<th>申请企业联系人</th>
				<th>在华责任单位</th>
				<th>在华责任单位地址</th>
				<th>在华责任单位电话</th>
				<th>在华责任单位传真</th>
				<th>在华责任单位邮编</th>
				<th>颜色性状</th>
				<th>样品标记</th>
				<th>保质期或限期使用日期</th>
				<th>规格</th>
				<th>行政许可送检时间</th>
				<th>行政许可检验机构</th>
				<th>行政许可检验项目</th>
				<th>行政许可送检数量</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td>
						${product.marketProduct.productNumber}
				</td>
				<td>
						${product.marketProduct.chineseName}
				</td>
				<td>
						${product.marketProduct.englishName}
				</td>
				<td>
						${product.marketProduct.productLeader}
				</td>
				<td>
						${product.marketProduct.enterpriseApplicationAddress}
				</td>
				<td>
						${product.marketProduct.enterpriseApplicationPhone}
				</td>
				<td>
						${product.marketProduct.enterpriseApplicationContacts}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChina}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChinaAddress}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChinaPhone}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChinaFax}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChinaZipCode}
				</td>
				<td>
						${product.declareProduct.colorCharacter}
				</td>
				<td>
						${product.declareProduct.sampleMarking}
				</td>
				<td>
						${product.declareProduct.dateOfExpiry}
				</td>
				<td>
						${product.declareProduct.specifications}
				</td>
				<td>
						<fmt:formatDate value="${product.declareProduct.sendBodyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${product.declareProduct.sendBodyOrganization}
				</td>
				<td>
						${product.declareProduct.sendBodyProject}
				</td>
				<td>
						${product.declareProduct.sendBodyNumber}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>