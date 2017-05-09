/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
import com.thinkgem.jeesite.modules.mms.entity.*;
import com.thinkgem.jeesite.modules.mms.service.*;
import com.thinkgem.jeesite.modules.mms.utils.MmsUtils;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 市场产品Controller
 * @author jiang
 * @version 2017-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/marketProduct")
public class MarketProductController extends BaseController {

	@Autowired
	private MarketProductService marketProductService;

	@Autowired
	private ProductService productService;

	@Autowired
	private ComprehensiveProductService comprehensiveProductService;

	@Autowired
	private DeclareProductService declareProductService;

	@Autowired
	private ProductFlowNumberService productFlowNumberService;

	@Autowired
	private EnterpriseInformationService enterpriseInformationService;
	
	@ModelAttribute
	public MarketProduct get(@RequestParam(required=false) String id) {
		MarketProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = marketProductService.get(id);
		}
		if (entity == null){
			entity = new MarketProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:marketProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
		model.addAttribute("page", page);
		return "modules/mms/marketProductList";
	}

	@RequiresPermissions("mms:marketProduct:view")
	@RequestMapping(value = "form")
	public String form(MarketProduct marketProduct, Model model) {
		model.addAttribute("marketProduct", marketProduct);

		return "modules/mms/marketProductForm";
	}

	@RequiresPermissions("mms:marketProduct:edit")
	@RequestMapping(value = "save")
	public String save(MarketProduct marketProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, marketProduct)){
			return form(marketProduct, model);
		}

		//获取当前登录用户.处理人ID
		User user  =UserUtils.getUser();
		marketProduct.setProductHandlePersonId(user.getId());

		marketProductService.save(marketProduct);

		//新建的产品，关联到产品汇总信息 中
		Product product = productService.getByMarketProductId(marketProduct.getId()); //产品信息
		Product	productSave = new Product();
		if(product == null){ //新建产品，第一次保存

			//自动创建综合产品记录和申报产品记录
			ComprehensiveProduct comprehensiveProduct = new ComprehensiveProduct();
			DeclareProduct declareProduct = new DeclareProduct();
			comprehensiveProductService.save(comprehensiveProduct);
			declareProductService.save(declareProduct);

			//产品状态判断
			if(marketProduct.getProjectTime() != null){ //立项时间有 就是初审状态，如果是新建，该时间是必填的 ---现改为不必填了，所以多加了一个判断
				productSave.setProductStatus(MmsConstant.PRODUCT_STATUS_1);
				productSave.setProductProcess(MmsConstant.PRODUCT_PROCESS_1); //产品进度
			}
			productSave.setMarketProductId(marketProduct.getId());
			productSave.setComprehensiveProductId(comprehensiveProduct.getId());
			productSave.setDeclareProductId(declareProduct.getId());
			productService.save(productSave);

			//如果是新建产品，生成处理产品编号，规则：在输入的产品编号的首位添加 四位年份+四位流水，20170001
			String productNumber = StringUtils.EMPTY;//产品编号
			String flowNumber = StringUtils.EMPTY;//流水号
			//流水编号记录
			ProductFlowNumber productFlowNumber = new ProductFlowNumber();
			//根据当前年 查找对应的最大的流水编号
			String nowYear = DateUtils.getYear();
			String bigNumber = productFlowNumberService.selectBigNumberByYear(nowYear);
			if(StringUtils.isEmpty(bigNumber)){ //该年没有记录，重0001 开始
				flowNumber = "0001";
				productNumber = nowYear + flowNumber;
			}else{
				flowNumber = MmsUtils.handleSerialNumber(bigNumber);
				productNumber = nowYear + flowNumber;
			}

			//保存该流水记录
			productFlowNumber.setFlowNumber(flowNumber);
			productFlowNumber.setFlowYear(nowYear);
			productFlowNumber.setProductId(productSave.getId());
			productFlowNumberService.save(productFlowNumber);

			//更新产品编号
			//产品编号----2017-05-04 修改为就是手动输入的数据
//			marketProduct.setProductNumber(productNumber+marketProduct.getProductNumber());
			marketProductService.save(marketProduct);

		}else{
			//产品状态判断,之前有数据
			//立项时间有 就是初审状态，如果是新建，该时间是必填的 ---现改为不必填了，所以多加了一个判断。如果产品有状态就不更新状态
			if(marketProduct.getProjectTime() != null && StringUtils.isEmpty(product.getProductStatus())){
				product.setProductStatus(MmsConstant.PRODUCT_STATUS_1);
				product.setProductProcess(MmsConstant.PRODUCT_PROCESS_1); //产品进度
				productService.save(product);
			}

			if(StringUtils.isEmpty(product.getProductStatus()) && marketProduct.getProjectTime() == null){ //第一次没有，没有状态和立项时间
				product.setProductProcess(MmsConstant.PRODUCT_PROCESS_0); //产品进度 --- 0
				productService.save(product);
			}
		}

		//更新产品负责人---保存代汇总产品表中
		if(StringUtils.isNoneEmpty(marketProduct.getProductLeader())){
			if(product == null){
				productSave.setProductLeader(marketProduct.getProductLeader());
				productService.save(productSave);
			}else{
				product.setProductLeader(marketProduct.getProductLeader());
				productService.save(product);
			}

		}

		//调用统计企业信息的方法
		statisticsEnterpriseInfo(marketProduct);
		addMessage(redirectAttributes, "保存市场产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/marketProduct/?repage";
	}
	
	@RequiresPermissions("mms:marketProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(MarketProduct marketProduct, RedirectAttributes redirectAttributes) {
		//市场部 删除产品 则每个部门都要删除的，有关该产品的所有记录

		//找到该产品总记录
		Product product = productService.getByMarketProductId(marketProduct.getId());

		String comprehensiveProductId = product.getComprehensiveProductId();//综合产品id
		if(StringUtils.isNoneEmpty(comprehensiveProductId)){ //有综合产品记录
			comprehensiveProductService.delete(comprehensiveProductService.get(comprehensiveProductId));
		}
		String declareProductId = product.getDeclareProductId();//综合产品id
		if(StringUtils.isNoneEmpty(declareProductId)){ //综合产品记录
			declareProductService.delete(declareProductService.get(declareProductId));
		}
		productService.delete(product);
		marketProductService.delete(marketProduct);
		addMessage(redirectAttributes, "删除市场产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/marketProduct/?repage";
	}


	/**
	 * 统计产品中的企业信息
	 * 根据新建或者更新的市场部产品信息，更新企业信息
	 * @param marketProduc 市场部产品信息
	 */
	private void statisticsEnterpriseInfo(MarketProduct marketProduc){

		String enterpriseApplication = marketProduc.getEnterpriseApplication();//申请企业
		String actualProductionEnterprise = marketProduc.getActualProductionEnterprise();//实际生产企业
		String responsibleUnitInChina = marketProduc.getResponsibleUnitInChina();//在华责任单位

		String enterpriseApplicationAddress = marketProduc.getEnterpriseApplicationAddress();
		String enterpriseApplicationPhone = marketProduc.getEnterpriseApplicationPhone();
		String enterpriseApplicationContacts = marketProduc.getEnterpriseApplicationContacts();

		String actualProductionEnterpriseAddress = marketProduc.getActualProductionEnterpriseAddress();

		String responsibleUnitInChinaAddress = marketProduc.getResponsibleUnitInChinaAddress();
		String responsibleUnitInChinaPhone = marketProduc.getResponsibleUnitInChinaPhone();
		String responsibleUnitInChinaFax = marketProduc.getResponsibleUnitInChinaFax();
		String responsibleUnitInChinaZipCode = marketProduc.getResponsibleUnitInChinaZipCode();


		//根据企业名称进行查询。没有进行插入，有着进行更新

		if(StringUtils.isNoneEmpty(enterpriseApplication)){ //申请企业
			List<EnterpriseInformation> applyEnterpriseInformationList = enterpriseInformationService.findListByName(enterpriseApplication);

			if(applyEnterpriseInformationList !=null && applyEnterpriseInformationList.size() > 0){
				EnterpriseInformation enterpriseInformationTemp = applyEnterpriseInformationList.get(0);
				enterpriseInformationTemp.setEnterpriseContacts(
						StringUtils.isNoneEmpty(enterpriseApplicationContacts)?enterpriseApplicationContacts : enterpriseInformationTemp.getEnterpriseContacts());
				enterpriseInformationTemp.setEnterprisePhone(
						StringUtils.isNoneEmpty(enterpriseApplicationPhone) ? enterpriseApplicationPhone :enterpriseInformationTemp.getEnterprisePhone());

				enterpriseInformationTemp.setEnterpriseAddress(
						StringUtils.isNoneEmpty(enterpriseApplicationAddress)?enterpriseApplicationAddress :enterpriseInformationTemp.getEnterpriseAddress() );

				enterpriseInformationService.save(enterpriseInformationTemp);

			}else{
				EnterpriseInformation enterpriseInformation = new EnterpriseInformation();
				enterpriseInformation.setEnterpriseName(enterpriseApplication);
				enterpriseInformation.setEnterpriseType(MmsConstant.APPLY_ENTERPRISE_TYPE);
				enterpriseInformation.setEnterpriseAddress(enterpriseApplicationAddress);
				enterpriseInformation.setEnterprisePhone(enterpriseApplicationPhone);
				enterpriseInformation.setEnterpriseContacts(enterpriseApplicationContacts);
				enterpriseInformationService.save(enterpriseInformation);
			}

		}

		if(StringUtils.isNoneEmpty(actualProductionEnterprise)){//实际生产企业
			List<EnterpriseInformation> actualEnterpriseInformationList = enterpriseInformationService.findListByName(actualProductionEnterprise);

			if(actualEnterpriseInformationList !=null && actualEnterpriseInformationList.size() >0){
				EnterpriseInformation enterpriseInformationTemp = actualEnterpriseInformationList.get(0);
				enterpriseInformationTemp.setEnterpriseAddress(
						StringUtils.isNoneEmpty(actualProductionEnterpriseAddress) ? actualProductionEnterpriseAddress : enterpriseInformationTemp.getEnterpriseAddress());
				enterpriseInformationService.save(enterpriseInformationTemp);
			}else{
				EnterpriseInformation enterpriseInformation = new EnterpriseInformation();

				enterpriseInformation.setEnterpriseName(actualProductionEnterprise);
				enterpriseInformation.setEnterpriseType(MmsConstant.ACTUAL_ENTERPRISE_TYPE);
				enterpriseInformation.setEnterpriseAddress(actualProductionEnterpriseAddress);
				enterpriseInformationService.save(enterpriseInformation);
			}


		}

		if(StringUtils.isNoneEmpty(responsibleUnitInChina)){ //在华责任单位

			List<EnterpriseInformation> chinaEnterpriseInformationList = enterpriseInformationService.findListByName(responsibleUnitInChina);


			if(chinaEnterpriseInformationList !=null && chinaEnterpriseInformationList.size() > 0){
				EnterpriseInformation enterpriseInformationTemp = chinaEnterpriseInformationList.get(0);
				enterpriseInformationTemp.setEnterprisePhone(
						StringUtils.isNoneEmpty(responsibleUnitInChinaPhone) ? responsibleUnitInChinaPhone :enterpriseInformationTemp.getEnterprisePhone());

				enterpriseInformationTemp.setEnterpriseAddress(
						StringUtils.isNoneEmpty(responsibleUnitInChinaAddress)?responsibleUnitInChinaAddress :enterpriseInformationTemp.getEnterpriseAddress() );

				enterpriseInformationTemp.setNterpriseFax(
						StringUtils.isNoneEmpty(responsibleUnitInChinaFax) ? responsibleUnitInChinaFax :enterpriseInformationTemp.getNterpriseFax());

				enterpriseInformationTemp.setRnterpriseZipCode(
						StringUtils.isNoneEmpty(responsibleUnitInChinaZipCode)?responsibleUnitInChinaZipCode :enterpriseInformationTemp.getRnterpriseZipCode() );

				enterpriseInformationService.save(enterpriseInformationTemp);
			}else{
				EnterpriseInformation enterpriseInformation = new EnterpriseInformation();
				enterpriseInformation.setEnterpriseName(responsibleUnitInChina);
				enterpriseInformation.setEnterpriseType(MmsConstant.CHINA_ENTERPRISE_TYPE);
				enterpriseInformation.setRnterpriseZipCode(responsibleUnitInChinaZipCode);
				enterpriseInformation.setNterpriseFax(responsibleUnitInChinaFax);
				enterpriseInformation.setEnterpriseAddress(responsibleUnitInChinaAddress);
				enterpriseInformation.setEnterprisePhone(responsibleUnitInChinaPhone);
				enterpriseInformationService.save(enterpriseInformation);
			}
		}

	}

	/**
	 * 根据企业类型获取企业信息
	 * type 企业类型 1：申请企业、2：实际生产企、3：在华责任单位）
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getEnterpriseName")
	public List<String> getEnterpriseName(HttpServletRequest request, HttpServletResponse response){
		String type = request.getParameter("type");
		EnterpriseInformation enterpriseInformation = new EnterpriseInformation();
		enterpriseInformation.setEnterpriseType(type);

		List<String> nameList = new ArrayList<String>();

		List<EnterpriseInformation> enterpriseInformationList = enterpriseInformationService.findList(enterpriseInformation);

		for (EnterpriseInformation enterpriseInformation1 : enterpriseInformationList){
			nameList.add(enterpriseInformation1.getEnterpriseName());
		}

		return nameList;
	}

	/**
	 * 根据企业类型和企业名称获取企业信息
	 * type 企业类型 1：申请企业、2：实际生产企、3：在华责任单位）
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getEnterpriseInfoByName")
	public EnterpriseInformation getEnterpriseInfoByName(HttpServletRequest request, HttpServletResponse response){
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		EnterpriseInformation enterpriseInformation = new EnterpriseInformation();
		enterpriseInformation.setEnterpriseType(type);
		enterpriseInformation.setEnterpriseName(name);



		List<EnterpriseInformation> enterpriseInformationList = enterpriseInformationService.findList(enterpriseInformation);

		if (enterpriseInformationList !=null && enterpriseInformationList.size() > 0) {
			enterpriseInformation = enterpriseInformationList.get(0);
		}
		return enterpriseInformation;
	}

	/**
	 * 新建产品的时候
	 * 校验产品编号是否存在
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "checkProductNumber")
	public String checkName(String oldProductNumber, String productNumber) {
		if (productNumber!=null && productNumber.equals(oldProductNumber)) {
			return "true";
		} else if (productNumber!=null) {
			MarketProduct marketProduct = new MarketProduct();
			marketProduct.setProductNumber(productNumber);
			List<MarketProduct> marketProductList = marketProductService.findList(marketProduct);
			if(marketProductList !=null && marketProductList.size() > 0){
				return "false";
			}else{
				return "true";
			}
		}
		return "false";
	}

}