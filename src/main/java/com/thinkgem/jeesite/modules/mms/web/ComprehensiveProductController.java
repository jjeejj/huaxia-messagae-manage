/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
import com.thinkgem.jeesite.modules.mms.entity.DeclareProduct;
import com.thinkgem.jeesite.modules.mms.entity.Product;
import com.thinkgem.jeesite.modules.mms.service.DeclareProductService;
import com.thinkgem.jeesite.modules.mms.service.ProductService;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mms.entity.ComprehensiveProduct;
import com.thinkgem.jeesite.modules.mms.service.ComprehensiveProductService;

import java.util.List;

/**
 * 综合产品Controller
 * @author jiang
 * @version 2017-03-12
 */
@Controller
@RequestMapping(value = "${adminPath}/mms/comprehensiveProduct")
public class ComprehensiveProductController extends BaseController {

	@Autowired
	private ComprehensiveProductService comprehensiveProductService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private ProductService productService;

	@Autowired
	private DeclareProductService declareProductService;
	
	@ModelAttribute
	public ComprehensiveProduct get(@RequestParam(required=false) String id) {
		ComprehensiveProduct entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = comprehensiveProductService.get(id);
		}
		if (entity == null){
			entity = new ComprehensiveProduct();
		}
		return entity;
	}
	
	@RequiresPermissions("mms:comprehensiveProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(ComprehensiveProduct comprehensiveProduct, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ComprehensiveProduct> page = comprehensiveProductService.findPage(new Page<ComprehensiveProduct>(request, response), comprehensiveProduct); 
		model.addAttribute("page", page);
		return "modules/mms/comprehensiveProductList";
	}

	@RequiresPermissions("mms:comprehensiveProduct:view")
	@RequestMapping(value = "form")
	public String form(ComprehensiveProduct comprehensiveProduct, Model model) {

		//查询下发申报产品的所有人员
		List<User> userList =systemService.findUserByOfficeId("5");

		model.addAttribute("userList", userList);

		model.addAttribute("comprehensiveProduct", comprehensiveProduct);
		return "modules/mms/comprehensiveProductForm";
	}

	@RequiresPermissions("mms:comprehensiveProduct:edit")
	@RequestMapping(value = "save")
	public String save(ComprehensiveProduct comprehensiveProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comprehensiveProduct)){
			return form(comprehensiveProduct, model);
		}

		//查找对应的产品汇总及记录
		Product product = productService.getByComprehensiveProductId(comprehensiveProduct.getId());

		if(product !=null){
			if(StringUtils.isEmpty(product.getDeclareProductId())){ //之前没有往下分配过
				if(StringUtils.isNoneEmpty(comprehensiveProduct.getProductNextHandlePersonId())){ //往下分配了

					comprehensiveProduct.setProductNextHandlePersonId(comprehensiveProduct.getProductNextHandlePersonId());

					DeclareProduct declareProduct = new DeclareProduct();
					declareProduct.setProductHandlePersonId(comprehensiveProduct.getProductNextHandlePersonId());

					declareProductService.save(declareProduct);

					product.setDeclareProductId(declareProduct.getId());
					productService.save(product);
				}
			}

			//产品状态的变更--取样时间有数据
			if(comprehensiveProduct.getSampleTime() !=null){
				product.setProductStatus(MmsConstant.PRODUCT_STATUS_2); //送检
				productService.save(product);
			}

			comprehensiveProductService.save(comprehensiveProduct);
			addMessage(redirectAttributes, "保存综合产品成功");

		}else{
			addMessage(redirectAttributes, "产品信息有哦错误");
		}
		return "redirect:"+Global.getAdminPath()+"/mms/comprehensiveProduct/?repage";
	}
	
	@RequiresPermissions("mms:comprehensiveProduct:edit")
	@RequestMapping(value = "delete")
	public String delete(ComprehensiveProduct comprehensiveProduct, RedirectAttributes redirectAttributes) {
		comprehensiveProductService.delete(comprehensiveProduct);
		addMessage(redirectAttributes, "删除综合产品成功");
		return "redirect:"+Global.getAdminPath()+"/mms/comprehensiveProduct/?repage";
	}

}