<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>市场产品管理</title>
	<meta name="decorator" content="default"/>
	<link href="${ctxStatic}/jquery-ui/jquery-ui.css" type="text/css"/>
	<script src="${ctxStatic}/jquery-ui/jquery-ui.js"></script>
	<link href="https://cdn.bootcss.com/jquery-autocomplete/1.0.7/jquery.auto-complete.min.css" rel="stylesheet">
	<style>
		.ui-helper-hidden-accessible{
			display: none;
		}
		.ui-autocomplete { position: absolute; cursor: default; border-radius: 5px; border: 1px solid green; background-color: black;color: white}
		.ui-menu {list-style: none;padding: 0;margin: 0;display: block;outline: none;}
		.ui-menu .ui-menu-item a {text-decoration:none;display:block;padding:.2em .4em;line-height:2.5;zoom:1;}

		.ui-state-hover, .ui-widget-content .ui-state-hover, .ui-widget-header .ui-state-hover, .ui-state-focus, .ui-widget-content .ui-state-focus, .ui-widget-header .ui-state-focus {background: #ff8a00;border: none;color:#000;border-radius:0;font-weight: normal;}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
//			$("#name").focus();
			$("#inputForm").validate({
                rules: {
                    productNumber: {remote: "${ctx}/mms/marketProduct/checkProductNumber?oldProductNumber=" + encodeURIComponent("${marketProduct.productNumber}")}
                },
                messages: {
                    productNumber: {remote: "产品编号已存在"}
                },
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mms/marketProduct/">市场产品列表</a></li>
		<li class="active"><a href="${ctx}/mms/marketProduct/form?id=${marketProduct.id}">市场产品<shiro:hasPermission name="mms:marketProduct:edit">${not empty marketProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mms:marketProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="marketProduct" action="${ctx}/mms/marketProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产品编号：</label>
			<div class="controls">
				<input id="oldProductNumber" name="oldProductNumber" type="hidden" value="${marketProduct.productNumber}">
				<form:input path="productNumber" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文名称：</label>
			<div class="controls">
				<form:input path="englishName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中文名称：</label>
			<div class="controls">
				<form:input path="chineseName" htmlEscape="false" maxlength="100" class="input-xlarge"/>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原产国：</label>
			<div class="controls">
				<form:input path="countryOfOrigin" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
				<form:select path="productType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('product_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作事项：</label>
			<div class="controls">
				<form:input path="workMatters" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品负责人：</label>
			<div class="controls">
				<form:input path="productLeader" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目负责人：</label>
			<div class="controls">
				<form:input path="projectLeader" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请企业：</label>
			<div class="controls">
				<form:input path="enterpriseApplication" htmlEscape="false" maxlength="64" class="input-xlarge enterprise" id="enterpriseApplication" data-type="1"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请企业地址：</label>
			<div class="controls">
				<form:input path="enterpriseApplicationAddress" htmlEscape="false" maxlength="100" class="input-xlarge " id="enterpriseApplicationAddress"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请企业电话：</label>
			<div class="controls">
				<form:input path="enterpriseApplicationPhone" htmlEscape="false" maxlength="32" class="input-xlarge " id="enterpriseApplicationPhone"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请企业联系人：</label>
			<div class="controls">
				<form:input path="enterpriseApplicationContacts" htmlEscape="false" maxlength="100" class="input-xlarge " id="enterpriseApplicationContacts"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际生产企业：</label>
			<div class="controls">
				<form:input path="actualProductionEnterprise" htmlEscape="false" maxlength="64" class="input-xlarge enterprise" data-type="2" id="actualProductionEnterprise"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际生产企业地址：</label>
			<div class="controls">
				<form:input path="actualProductionEnterpriseAddress" htmlEscape="false" maxlength="64" class="input-xlarge " id="actualProductionEnterpriseAddress"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在华责任单位：</label>
			<div class="controls">
				<form:input path="responsibleUnitInChina" htmlEscape="false" maxlength="64" class="input-xlarge enterprise" data-type="3" id="responsibleUnitInChina"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在华责任单位地址：</label>
			<div class="controls">
				<form:input path="responsibleUnitInChinaAddress" htmlEscape="false" maxlength="64" class="input-xlarge " id="responsibleUnitInChinaAddress"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在华责任单位电话：</label>
			<div class="controls">
				<form:input path="responsibleUnitInChinaPhone" htmlEscape="false" maxlength="64" class="input-xlarge " id="responsibleUnitInChinaPhone"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在华责任单位传真：</label>
			<div class="controls">
				<form:input path="responsibleUnitInChinaFax" htmlEscape="false" maxlength="64" class="input-xlarge " id="responsibleUnitInChinaFax"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在华责任单位邮编：</label>
			<div class="controls">
				<form:input path="responsibleUnitInChinaZipCode" htmlEscape="false" maxlength="64" class="input-xlarge " id="responsibleUnitInChinaZipCode"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">立项时间：</label>
			<div class="controls">
				<input name="projectTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${marketProduct.projectTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同编号：</label>
			<div class="controls">
				<form:input path="contractNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同签订时间：</label>
			<div class="controls">
				<input name="contractSigningTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${marketProduct.contractSigningTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来款单位：</label>
			<div class="controls">
				<form:input path="arrivalCompany" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mms:marketProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
	<script>
		//申请企业
        $('#enterpriseApplication').autocomplete({
            source: function (request, response) {
				$.ajax({
					url:'${ctx}/mms/marketProduct/getEnterpriseName?type=1',
                    async: false,
					success:function (data) {
                        var matcher = new RegExp($.ui.autocomplete.escapeRegex( request.term ), "i" );
                        response( $.grep( data, function( item ){
                            return matcher.test( item );
                        }) );
                    }
				})
            },
			select: function( event, ui ) {
                var value = ui.item.value;

                //查找对应的其他信息
                $.ajax({
                    url:'${ctx}/mms/marketProduct/getEnterpriseInfoByName?type=1&name=' + value,
                    async: false,
                    success:function (data) {
                        console.log('select',data);
                        //在对应的输入框内赋值
						$('#enterpriseApplicationAddress').val(data.enterpriseAddress);
						$('#enterpriseApplicationPhone').val(data.enterprisePhone);
						$('#enterpriseApplicationContacts').val(data.enterpriseContacts);
                    }
                });
            }
        });

        //实际生产企业
        $('#actualProductionEnterprise').autocomplete({
            source: function (request, response) {
                $.ajax({
                    url:'${ctx}/mms/marketProduct/getEnterpriseName?type=2',
                    async: false,
                    success:function (data) {
                        var matcher = new RegExp($.ui.autocomplete.escapeRegex( request.term ), "i" );
                        response( $.grep( data, function( item ){
                            return matcher.test( item );
                        }) );
                    }
                })
            },
            select: function( event, ui ) {
                var value = ui.item.value;
                //查找对应的其他信息
                $.ajax({
                    url:'${ctx}/mms/marketProduct/getEnterpriseInfoByName?type=2&name=' + value,
                    async: false,
                    success:function (data) {
                        //在对应的输入框内赋值
                        $('#actualProductionEnterpriseAddress').val(data.enterpriseAddress);
                    }
                });
            }
        });
        //在华责任单位
        $('#responsibleUnitInChina').autocomplete({
            source: function (request, response) {
                $.ajax({
                    url:'${ctx}/mms/marketProduct/getEnterpriseName?type=3',
                    async: false,
                    success:function (data) {
                        var matcher = new RegExp($.ui.autocomplete.escapeRegex( request.term ), "i" );
                        response( $.grep( data, function( item ){
                            return matcher.test( item );
                        }) );
                    }
                })
            },
            select: function( event, ui ) {
                var value = ui.item.value;

                //查找对应的其他信息
                $.ajax({
                    url:'${ctx}/mms/marketProduct/getEnterpriseInfoByName?type=3&name=' + value,
                    async: false,
                    success:function (data) {
                        //在对应的输入框内赋值
                        $('#responsibleUnitInChinaAddress').val(data.enterpriseAddress);
                        $('#responsibleUnitInChinaPhone').val(data.enterprisePhone);
                        $('#responsibleUnitInChinaFax').val(data.nterpriseFax);
                        $('#responsibleUnitInChinaZipCode').val(data.rnterpriseZipCode);
                    }
                });
            }
        })
	</script>
</body>
</html>