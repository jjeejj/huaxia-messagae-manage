<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>申报产品管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/mms/declareProduct/">申报产品列表</a></li>
    <%--<c:if test="${fns:isAdmin()}">--%>
    <%--<shiro:hasPermission name="mms:declareProduct:edit"><li><a href="${ctx}/mms/declareProduct/form">申报产品添加</a></li></shiro:hasPermission>--%>
    <%--</c:if>--%>
</ul>
<form:form id="searchForm" modelAttribute="product" action="${ctx}/mms/declareProduct/" method="post"
           class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <ul class="ul-form">
        <li><label>送检总数：</label>
            <form:input path="declareProduct.totalSubmission" htmlEscape="false" maxlength="64" class="input-medium"/>
        </li>
        <li><label>受理编号：</label>
            <form:input path="declareProduct.acceptanceNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
        </li>
        <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
        <li class="clearfix"></li>
    </ul>
</form:form>
<sys:message content="${message}"/>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>产品编号</th>
        <th>英文名称</th>
        <th>中文名称</th>
        <th>原产国</th>
        <th>类别</th>
        <th>工作事项</th>
        <th>申请企业</th>
        <th>申请企业地址</th>
        <th>实际生产企业</th>
        <th>实际生产企业地址</th>
        <th>在华责任单位</th>
        <th>在华责任单位地址</th>
        <th>来样时间</th>
        <th>样品数量</th>
        <th>送检总数</th>
        <th>行政许可送检时间</th>
        <th>行政许可检验机构</th>
        <th>行政许可检验项目</th>
        <th>行政许可送检数量</th>
        <th>人体检验送检时间</th>
        <th>人体检验送检机构</th>
        <th>人体检验项目</th>
        <th>人体检验数量</th>
        <th>风险检验时间</th>
        <th>风险检验机构</th>
        <th>风险检验项目</th>
        <th>风险检验数量</th>
        <th>上报时间</th>
        <th>受理时间</th>
        <th>受理编号</th>
        <th>取得批件时间</th>
        <th>批件编号</th>
        <th>产品状态备注</th>
        <th>下意见时间</th>
        <th>意见内容</th>
        <th>回复意见时间</th>
        <th>颜色性状</th>
        <th>样品标记</th>
        <th>保质期或限期使用日期</th>
        <th>保质期</th>
        <th>气味</th>
        <th>规格</th>
        <th>其他说明</th>
        <shiro:hasPermission name="mms:declareProduct:edit">
            <th>操作</th>
        </shiro:hasPermission>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="product">
        <tr>
            <td>
                <a href="${ctx}/mms/declareProduct/form?id=${product.declareProductId}">
                    ${product.marketProduct.productNumber}
                </a>
            </td>
            <td>
                    ${product.marketProduct.englishName}
            </td>
            <td>
                    ${product.marketProduct.chineseName}
            </td>
            <td>
                    ${product.marketProduct.countryOfOrigin}
            </td>
            <td>
                    ${fns:getDictLabel(product.marketProduct.productType, 'product_type', '')}
            </td>
            <td>
                    ${product.marketProduct.workMatters}
            </td>
            <td>
                    ${product.marketProduct.enterpriseApplication}
            </td>
            <td>
                    ${product.marketProduct.enterpriseApplicationAddress}
            </td>
            <td>
                ${product.marketProduct.actualProductionEnterprise}
            </td>
            <td>
                    ${product.marketProduct.actualProductionEnterpriseAddress}
            </td>
            <td>
                    ${product.marketProduct.responsibleUnitInChina}
            </td>
            <td>
                    ${product.marketProduct.responsibleUnitInChinaAddress}
            </td>
            <td>
                <fmt:formatDate value="${product.comprehensiveProduct.sampleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${product.comprehensiveProduct.sampleQuantity}
            </td>
            <td>
                    ${product.declareProduct.totalSubmission}
            </td>
            <td>
                <fmt:formatDate value="${product.declareProduct.administrativeLicenseInspectionTime}"
                                pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${product.declareProduct.administrativeLicenseInspectionOrganization}
            </td>
            <td>
                    ${product.declareProduct.administrativeLicenseInspectionProject}
            </td>
            <td>
                    ${product.declareProduct.administrativeLicenseInspectionNumber}
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
            <td>
                <fmt:formatDate value="${product.declareProduct.sendRiskTestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${product.declareProduct.sendRiskTestOrganization}
            </td>
            <td>
                    ${product.declareProduct.sendRiskTestProject}
            </td>
            <td>
                    ${product.declareProduct.sendRiskTestNumber}
            </td>
            <td>
                <fmt:formatDate value="${product.declareProduct.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>

            </td>
            <td>
                <fmt:formatDate value="${product.declareProduct.acceptanceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${product.declareProduct.acceptanceNumber}
            </td>

            <td>
                <fmt:formatDate value="${product.declareProduct.documentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${product.declareProduct.documentNumber}
            </td>
            <td>
                    ${fns:getDictLabel(product.declareProduct.productStatusRemark, 'product_status_remark', '')}
            </td>
            <td>
                <fmt:formatDate value="${product.declareProduct.nextOpinionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${product.declareProduct.opinionContent}
            </td>
            <td>
                <fmt:formatDate value="${product.declareProduct.replyOpinion}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
                    ${product.declareProduct.technologyDateOfExpiry}
            </td>
            <td>
                    ${product.declareProduct.smell}
            </td>
            <td>
                    ${product.declareProduct.specifications}
            </td>
            <td>
                    ${product.declareProduct.otherDescription}
            </td>
            <shiro:hasPermission name="mms:declareProduct:edit">
                <td>
                    <a href="${ctx}/mms/declareProduct/form?id=${product.declareProductId}">修改</a>
                </td>
            </shiro:hasPermission>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>