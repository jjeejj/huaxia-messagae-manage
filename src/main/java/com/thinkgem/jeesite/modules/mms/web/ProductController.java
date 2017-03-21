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
		model.addAttribute("page", page);
		return "modules/mms/productList";
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
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
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
				ProductVo productVo = new ProductVo();
				for (Product productTemp:productList){
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
			productVo.setProductType(marketProduct.getProductType());
			productVo.setWorkMatters(marketProduct.getWorkMatters());
			productVo.setProductLeader(marketProduct.getProductLeader());
			productVo.setProjectLeader(marketProduct.getProjectLeader());
			productVo.setEnterpriseApplication(marketProduct.getEnterpriseApplication());
			productVo.setActualProductionEnterprise(marketProduct.getActualProductionEnterprise());
			productVo.setResponsibleUnitInChina(marketProduct.getResponsibleUnitInChina());
			productVo.setProjectTime(marketProduct.getProjectTime());
			productVo.setContractSigningTime(marketProduct.getContractSigningTime());
		}
		//综合产品信息
		if(comprehensiveProduct!=null){
			productVo.setSampleTime(comprehensiveProduct.getSampleTime());
			productVo.setSampleQuantity(comprehensiveProduct.getSampleQuantity());
//			productVo.setTotalNumberOfSamples(comprehensiveProduct.getTotalNumberOfSamples());
//			productVo.setSubmissionTime(comprehensiveProduct.getSubmissionTime());
		}
		//申报产品
		if(declareProduct !=null){
//			productVo.setInspectionReportTime(declareProduct.getInspectionReportTime());
			productVo.setSendBodyTime(declareProduct.getSendBodyTime());
//			productVo.setBodyReportTime(declareProduct.getBodyReportTime());
			productVo.setReportTime(declareProduct.getReportTime());
			productVo.setAcceptanceTime(declareProduct.getAcceptanceTime());
			productVo.setDocumentTime(declareProduct.getDocumentTime());
			productVo.setNextOpinionTime(declareProduct.getNextOpinionTime());
			productVo.setReplyOpinion(declareProduct.getReplyOpinion());
			productVo.setRemarks(declareProduct.getRemarks());
		}

		return productVo;
	}

}