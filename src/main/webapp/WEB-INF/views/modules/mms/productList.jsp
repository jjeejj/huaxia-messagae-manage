<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出产品数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/mms/product/export");
                        $("#searchForm").submit();
                        //跳转链接改回查询
                        $("#searchForm").attr("action", "${ctx}/mms/product/");
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
		<li class="active"><a href="${ctx}/mms/product/">产品概况</a></li>
		<%--<shiro:hasPermission name="mms:product:edit"><li><a href="${ctx}/mms/product/form">产品添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/mms/product/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品编号：</label>
				<form:input path="marketProduct.productNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>中文名称：</label>
				<form:input path="marketProduct.englishName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<%--<li><label>英文名称：</label>--%>
				<%--<form:input path="marketProduct.chineseName" htmlEscape="false" maxlength="100" class="input-medium"/>--%>
			<%--</li>--%>
			<li><label>产品状态：</label>
				<form:select path="productStatus" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('product_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<%--<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>--%>
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
				<th>类别</th>
				<th>工作事项</th>
				<th>产品负责人</th>
				<th>项目进度</th>
				<th>产品状态</th>
				<th>办理时限</th>
				<%--<shiro:hasPermission name="mms:product:edit"><th>操作</th></shiro:hasPermission>--%>
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
						${fns:getDictLabel(product.marketProduct.productType, 'product_type', '')}
				</td>
				<td>
						${product.marketProduct.workMatters}
				</td>
				<td>
						${product.marketProduct.productLeader}
				</td>
				<td>
					<progress value="${product.productProcess}" max="100"></progress>
					<c:if test="${product.productStatus eq '7'}">
						<font color="#dcdcdc">&nbsp;&nbsp;${product.productProcess}%</font>
					</c:if>
					<c:if test="${product.productStatus eq '6'}">
						<font color="red">&nbsp;&nbsp;${product.productProcess}%</font>
					</c:if>
					<c:if test="${product.productStatus eq '5'}">
						<font color="green">&nbsp;&nbsp;${product.productProcess}%</font>
					</c:if>
					<c:if test="${product.productStatus eq '4'}">
						<font color="#ff8c00">&nbsp;&nbsp;${product.productProcess}%</font>
					</c:if>
					<c:if test="${product.productStatus eq '3' || product.productStatus eq '2' ||product.productStatus eq '1' }">
						<font>&nbsp;&nbsp;${product.productProcess}%</font>
					</c:if>
				</td>
				<td>
						${fns:getDictLabels(product.productStatus, 'product_status', '')}
				</td>

				<td>
						${product.dateLimitContent}
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>