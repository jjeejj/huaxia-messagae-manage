/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.mms.entity.ComprehensiveProduct;
import com.thinkgem.jeesite.modules.mms.entity.DeclareProduct;
import com.thinkgem.jeesite.modules.mms.entity.MarketProduct;
import com.thinkgem.jeesite.modules.mms.service.ComprehensiveProductService;
import com.thinkgem.jeesite.modules.mms.service.DeclareProductService;
import com.thinkgem.jeesite.modules.mms.service.MarketProductService;
import com.thinkgem.jeesite.modules.mms.vo.InspectionExportProductVo;
import com.thinkgem.jeesite.modules.mms.vo.ProductStatusVo;
import com.thinkgem.jeesite.modules.mms.vo.ProductVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mms.entity.Product;
import com.thinkgem.jeesite.modules.mms.service.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 产品Controller
 * @author jiang
 * @version 2017-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/product")
public class ProductController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private MarketProductService marketProductService;

	@Autowired
	private ComprehensiveProductService comprehensiveProductService;

	@Autowired
	private DeclareProductService declareProductService;
	
	@ModelAttribute
	public Product get(@RequestParam(required=false) String id) {
		Product entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = productService.get(id);
		}
		if (entity == null){
			entity = new Product();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:product:view")
	@RequestMapping(value = {"list", ""})
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product);

		page.setList(handleDateLimitContent(page.getList()));

		model.addAttribute("page", page);
		return "modules/mms/productList";
	}

	/**
	 * 处理办理时限的内容显示
	 * 办理时限,显示内容	判定项
	 	送检，剩7天	     来样时间选择后显示，倒计时			超过7天后变为红色叹号标记,行政许可送检时间有值时停止计时
	 	申报，剩7天	     行政许可报告到达时间选择后显示，倒计时超过7天后变为红色叹号标记，如填写后续送人体检验时间则停止计算，填写人体报告到达时间重新计算7天,后续送风险检验时间停止计算
	 	回复意见时间，剩15天	 下意见时间选择后显示，倒计时			超过15天后变为红色叹号标记
	 * @param productList
	 * @return
	 */
	private List<Product> handleDateLimitContent(List<Product> productList){

		for (Product product : productList){
			Date replyOpinion = product.getDeclareProduct().getReplyOpinion();//回复意见时间
			Date nextOpinionTime = product.getDeclareProduct().getNextOpinionTime();//下意见时间

			Date humanTestAcceptanceReportTime = product.getComprehensiveProduct().getHumanTestAcceptanceReportTime();//人体检验取报告时间
			Date sendBodyTime = product.getDeclareProduct().getSendBodyTime();//人体检验送检时间
			Date sendRiskTestTime = product.getDeclareProduct().getSendRiskTestTime();//风险检验时间
			Date administrativeLicenseInspectionReportTime = product.getComprehensiveProduct().getAdministrativeLicenseInspectionReportTime();//行政许可检验取报告时间

			Date administrativeLicenseInspectionTime = product.getDeclareProduct().getAdministrativeLicenseInspectionTime();//行政许可送检时间
			Date sampleTime = product.getComprehensiveProduct().getSampleTime();//来样时间


			double dateBetween = 0L; //时间间隔

			//第一种情况
			if(nextOpinionTime !=null && replyOpinion ==null){ //下意见时间 有，回复意见没有
				 dateBetween = DateUtils.getDistanceOfTwoDate(nextOpinionTime,new Date());
				if(dateBetween > 15){
					product.setDateLimitContent("回复意见时间，剩15天 <font color='red'>!</font>");
				}else{
					product.setDateLimitContent("回复意见时间，剩"+(15 - dateBetween) +"天");
				}

				continue;
			}
			if(sendRiskTestTime == null){ //第二种情况
				if(humanTestAcceptanceReportTime !=null){ //人体报告到达时间
					 dateBetween = DateUtils.getDistanceOfTwoDate(humanTestAcceptanceReportTime,new Date());
					if(dateBetween > 7){
						product.setDateLimitContent("申报，剩7天 <font color='red'>!</font>");
					}else{
						product.setDateLimitContent("申报，剩"+(7 - dateBetween) +"天");
					}

					continue;
				}else{
					if(sendBodyTime ==null && administrativeLicenseInspectionReportTime !=null){

						dateBetween = DateUtils.getDistanceOfTwoDate(administrativeLicenseInspectionReportTime,new Date());

						if(dateBetween > 7){
							product.setDateLimitContent("申报，剩7天 <font color='red'>!</font>");
						}else{
							product.setDateLimitContent("申报，剩"+(7 - dateBetween) +"天");
						}
						continue;
					}
				}
			}

			if(administrativeLicenseInspectionTime == null && sampleTime !=null){ //第3种情况
				dateBetween = DateUtils.getDistanceOfTwoDate(sampleTime,new Date());
				if(dateBetween > 7){
					product.setDateLimitContent("送检，剩7天 <font color='red'>!</font>");
				}else{
					product.setDateLimitContent("送检，剩"+(7 - dateBetween) +"天");
				}
				continue;
			}
		}
		return productList;
	}

	/**
	 * 产品详细信息
	 * @param product
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "productInformation")
	public String productInformation(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {

		String isFirst = request.getParameter("isFirst"); //判断是不是首次进来
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product);

		if(StringUtils.isNoneEmpty(isFirst) && isFirst.equals("1")){ //首次进入，需要赋默认显示项
			product.setFirst(false);
			product.setIsShowProductNumber("1");
			product.setIsShowChineseName("1");
			product.setIsShowEnglishName("1");
			product.setIsShowProductLeader("1");
			product.setIsShowEnterpriseApplication("1");
			product.setIsShowActualProductionEnterprise("1");
			product.setIsShowProjectTime("1");
			product.setIsShowContractNumber("1");
			product.setIsShowArrivalCompany("1");
			product.setIsShowArrivalTime("1");
			product.setIsShowReportTime("1");
		}
		model.addAttribute("page", page);
		return "modules/mms/productInformationList";
	}

	@RequiresPermissions("mms:product:view")
	@RequestMapping(value = "form")
	public String form(Product product, Model model) {

		ProductVo productVo = this.getProductVo(product);

		model.addAttribute("productVo", productVo);

		return "modules/mms/productForm";
	}

	@RequiresPermissions("mms:product:edit")
	@RequestMapping(value = "save")
	public String save(Product product, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, product)){
			return form(product, model);
		}
		productService.save(product);
		addMessage(redirectAttributes, "保存产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/product/?repage";
	}
	
	@RequiresPermissions("mms:product:edit")
	@RequestMapping(value = "delete")
	public String delete(Product product, RedirectAttributes redirectAttributes) {
		productService.delete(product);
		addMessage(redirectAttributes, "删除产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/product/?repage";
	}

	/**
	 * 导出产品数据
	 * @param product
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(Product product, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "产品数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

			List<Product>  productList = productService.findList(product);

			List<ProductVo> productVoList = new ArrayList<ProductVo>();

			if(productList !=null && productList.size() >0){
				for (Product productTemp:productList){
					ProductVo productVo = new ProductVo();
					productVo = this.getProductVo(productTemp);
					productVoList.add(productVo);
				}
			}
			new ExportExcel("产品数据", ProductVo.class).setDataList(productVoList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出产品！失败信息：" + e.getMessage());
			logger.info("导出产品！失败信息：" + e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mms/product/list/?repage";
	}

	/**
	 * 根据产品汇总的id 信息拼接得到产品的具体信息
	 * @param product
	 * @return
	 */
	private  ProductVo getProductVo(Product product){
		//根据每个部门的产品id ，查询出具体的信息

		String marketProductId = product.getMarketProductId();//市场产品id
		String comprehensiveProductId = product.getComprehensiveProductId();//综合产品id
		String declareProductId = product.getDeclareProductId();//申报产品id

		MarketProduct marketProduct = marketProductService.get(marketProductId);
		ComprehensiveProduct comprehensiveProduct = comprehensiveProductService.get(comprehensiveProductId);
		DeclareProduct declareProduct = declareProductService.get(declareProductId);

		//合并赋值到产品VO
		ProductVo productVo = new ProductVo();

		productVo.setId(product.getId());

		//市场产品信息
		if(marketProduct!=null){
			productVo.setProductNumber(marketProduct.getProductNumber()); //产品编号
			productVo.setEnglishName(marketProduct.getEnglishName());
			productVo.setChineseName(marketProduct.getChineseName());
			productVo.setCountryOfOrigin(marketProduct.getCountryOfOrigin());
			productVo.setProductType(marketProduct.getProductType());
			productVo.setWorkMatters(marketProduct.getWorkMatters());
			productVo.setProductLeader(marketProduct.getProductLeader());
			productVo.setProjectLeader(marketProduct.getProjectLeader());
			productVo.setEnterpriseApplication(marketProduct.getEnterpriseApplication());
			productVo.setEnterpriseApplicationAddress(marketProduct.getEnterpriseApplicationAddress());
			productVo.setEnterpriseApplicationPhone(marketProduct.getEnterpriseApplicationPhone());
			productVo.setEnterpriseApplicationContacts(marketProduct.getEnterpriseApplicationContacts());
			productVo.setActualProductionEnterprise(marketProduct.getActualProductionEnterprise());
			productVo.setActualProductionEnterpriseAddress(marketProduct.getActualProductionEnterpriseAddress());
			productVo.setResponsibleUnitInChina(marketProduct.getResponsibleUnitInChina());
			productVo.setResponsibleUnitInChinaAddress(marketProduct.getResponsibleUnitInChinaAddress());
			productVo.setResponsibleUnitInChinaPhone(marketProduct.getResponsibleUnitInChinaPhone());
			productVo.setResponsibleUnitInChinaFax(marketProduct.getResponsibleUnitInChinaFax());
			productVo.setResponsibleUnitInChinaZipCode(marketProduct.getResponsibleUnitInChinaZipCode());

			productVo.setProjectTime(marketProduct.getProjectTime());
			productVo.setContractNumber(marketProduct.getContractNumber());
			productVo.setContractSigningTime(marketProduct.getContractSigningTime());
			productVo.setArrivalCompany(marketProduct.getArrivalCompany());
		}
		//综合产品信息
		if(comprehensiveProduct!=null){
			productVo.setArrivalTime(comprehensiveProduct.getArrivalTime());
			productVo.setSampleTime(comprehensiveProduct.getSampleTime());
			productVo.setSampleQuantity(comprehensiveProduct.getSampleQuantity());
			productVo.setAdministrativeLicenseInspectionNo(comprehensiveProduct.getAdministrativeLicenseInspectionNo());
			productVo.setAdministrativeLicenseInspectionReportTime(comprehensiveProduct.getAdministrativeLicenseInspectionReportTime());

			productVo.setHumanTestAcceptanceNo(comprehensiveProduct.getHumanTestAcceptanceNo());
			productVo.setHumanTestAcceptanceReportTime(comprehensiveProduct.getHumanTestAcceptanceReportTime());

			productVo.setRiskTestAcceptanceNo(comprehensiveProduct.getRiskTestAcceptanceNo());
			productVo.setRiskTestAcceptanceReportTime(comprehensiveProduct.getRiskTestAcceptanceReportTime());

		}
		//申报产品
		if(declareProduct !=null){

			productVo.setTotalNumberOfSamples(declareProduct.getAcceptanceNumber());
			productVo.setColorCharacter(declareProduct.getColorCharacter());
			productVo.setSampleMarking(declareProduct.getSampleMarking());
			productVo.setDateOfExpiry(declareProduct.getDateOfExpiry());
			productVo.setTechnologyDateOfExpiry(declareProduct.getTechnologyDateOfExpiry());
			productVo.setSmell(declareProduct.getSmell());
			productVo.setSpecifications(declareProduct.getSpecifications());

			productVo.setAdministrativeLicenseInspectionTime(declareProduct.getAdministrativeLicenseInspectionTime());
			productVo.setAdministrativeLicenseInspectionOrganization(declareProduct.getAdministrativeLicenseInspectionOrganization());
			productVo.setAdministrativeLicenseInspectionProject(declareProduct.getAdministrativeLicenseInspectionProject());
			productVo.setAdministrativeLicenseInspectionNumber(declareProduct.getAdministrativeLicenseInspectionNumber());

			productVo.setSendBodyTime(declareProduct.getSendBodyTime());
			productVo.setSendBodyNumber(declareProduct.getSendBodyNumber());
			productVo.setSendBodyProject(declareProduct.getSendBodyProject());
			productVo.setSendBodyOrganization(declareProduct.getSendBodyOrganization());


			productVo.setSendRiskTestNumber(declareProduct.getSendRiskTestNumber());
			productVo.setSendRiskTestOrganization(declareProduct.getSendRiskTestOrganization());
			productVo.setSendRiskTestTime(declareProduct.getSendRiskTestTime());
			productVo.setSendRiskTestProject(declareProduct.getSendRiskTestProject());

			productVo.setReportTime(declareProduct.getReportTime());
			productVo.setAcceptanceTime(declareProduct.getAcceptanceTime());
			productVo.setAcceptanceNumber(declareProduct.getAcceptanceNumber());
			productVo.setDocumentTime(declareProduct.getDocumentTime());
			productVo.setDocumentNumber(declareProduct.getDocumentNumber());
			productVo.setProductStatusRemark(declareProduct.getProductStatusRemark());
			productVo.setNextOpinionTime(declareProduct.getNextOpinionTime());
			productVo.setOpinionContent(declareProduct.getOpinionContent());
			productVo.setReplyOpinion(declareProduct.getReplyOpinion());
			productVo.setOtherDescription(declareProduct.getOtherDescription());
		}
		return productVo;
	}

	/**
	 * 根据 产品负责人进行分组,统计每个状态的数量
	 * @param productStatusVo
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "selectByProductLeader")
	public String selectByProductLeader(ProductStatusVo productStatusVo, HttpServletRequest request, HttpServletResponse response,
										Model model, RedirectAttributes redirectAttributes) {

		Page<ProductStatusVo> page = productService.findPageSelectByProductLeader(new Page<ProductStatusVo>(request, response), productStatusVo);
//		List<ProductStatusVo> productStatusVoList = productService.selectByProductLeader(productStatusVo);
//		model.addAttribute("productStatusVoList", productStatusVoList);
		model.addAttribute("page", page);
		return "modules/mms/producStatustList";
	}

	/**
	 * 导出 产品负责人进行分组,统计每个状态的数量 的数据
	 * @param productStatusVo
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "selectByProductLeader/export")
	public String selectByProductLeaderExport(ProductStatusVo productStatusVo, HttpServletRequest request, HttpServletResponse response,
										Model model, RedirectAttributes redirectAttributes) {

		try {
			String fileName = "产品负责人统计数据" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

			List<ProductStatusVo>  productStatusVoList = productService.selectByProductLeader(productStatusVo);

			new ExportExcel("产品负责人统计数据", ProductStatusVo.class).setDataList(productStatusVoList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出产品负责人统计数据！失败信息：" + e.getMessage());
			logger.info("导出产品负责人统计数据！失败信息：" + e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mms/product/selectByProductLeader/?repage";

	}

	/**
	 * 送检导出产品信息
	 * @param product
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "inspectionExportProductInfo")
	public String inspectionExportProductInfo(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
		model.addAttribute("page", page);
		return "modules/mms/inspectionExportProduct";
	}

	/**
	 * 送检导出产品信息的excel 处理
	 * @param product
	 * @param request
	 * @param response
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "inspectionExportProductInfo/export")
	public String inspectionExportProductInfoExport(Product product, HttpServletRequest request, HttpServletResponse response,
											  Model model, RedirectAttributes redirectAttributes) {

		try {
			String fileName = "送检导出产品信息" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";

			List<Product>  productList = productService.findList(product);

			List<InspectionExportProductVo> inspectionExportProductVoList = new ArrayList<InspectionExportProductVo>();
			//把产品信息，转换到导出的vo中
			if(productList !=null && productList.size() > 0){
				for (Product productTemp : productList){
					InspectionExportProductVo inspectionExportProductVo = new InspectionExportProductVo();
					MarketProduct marketProduct = productTemp.getMarketProduct();//市场产品
					DeclareProduct declareProduct = productTemp.getDeclareProduct();//申报产品Entity

					inspectionExportProductVo.setProductNumber(marketProduct.getProductNumber());// 产品编号
					inspectionExportProductVo.setEnglishName(marketProduct.getEnglishName());
					inspectionExportProductVo.setChineseName(marketProduct.getChineseName());
					inspectionExportProductVo.setProductLeader(marketProduct.getProductLeader());
					inspectionExportProductVo.setEnterpriseApplicationAddress(marketProduct.getEnterpriseApplicationAddress());
					inspectionExportProductVo.setEnterpriseApplicationContacts(marketProduct.getEnterpriseApplicationContacts());
					inspectionExportProductVo.setEnterpriseApplicationPhone(marketProduct.getEnterpriseApplicationPhone());
					inspectionExportProductVo.setResponsibleUnitInChina(marketProduct.getResponsibleUnitInChina());
					inspectionExportProductVo.setResponsibleUnitInChinaAddress(marketProduct.getResponsibleUnitInChinaAddress());
					inspectionExportProductVo.setResponsibleUnitInChinaPhone(marketProduct.getResponsibleUnitInChinaPhone());
					inspectionExportProductVo.setResponsibleUnitInChinaFax(marketProduct.getResponsibleUnitInChinaFax());
					inspectionExportProductVo.setResponsibleUnitInChinaZipCode(marketProduct.getResponsibleUnitInChinaZipCode());
					inspectionExportProductVo.setColorCharacter(declareProduct.getColorCharacter());
					inspectionExportProductVo.setSampleMarking(declareProduct.getSampleMarking());
					inspectionExportProductVo.setDateOfExpiry(declareProduct.getDateOfExpiry());
//					inspectionExportProductVo.setTechnologyDateOfExpiry(declareProduct.getTechnologyDateOfExpiry());
					inspectionExportProductVo.setSpecifications(declareProduct.getSpecifications());
					inspectionExportProductVo.setAdministrativeLicenseInspectionTime(declareProduct.getAdministrativeLicenseInspectionTime());
					inspectionExportProductVo.setAdministrativeLicenseInspectionOrganization(declareProduct.getAdministrativeLicenseInspectionOrganization());
					inspectionExportProductVo.setAdministrativeLicenseInspectionProject(declareProduct.getAdministrativeLicenseInspectionProject());
					inspectionExportProductVo.setAdministrativeLicenseInspectionNumber(declareProduct.getAdministrativeLicenseInspectionNumber());

					inspectionExportProductVoList.add(inspectionExportProductVo);
				}
			}
			new ExportExcel("送检导出产品信息", InspectionExportProductVo.class).setDataList(inspectionExportProductVoList).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "送检导出产品信息！失败信息：" + e.getMessage());
			logger.info("送检导出产品信息！失败信息：" + e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/mms/product/inspectionExportProductInfo/?repage";

	}

}