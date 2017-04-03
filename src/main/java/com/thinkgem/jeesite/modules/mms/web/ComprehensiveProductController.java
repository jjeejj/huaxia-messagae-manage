/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
import com.thinkgem.jeesite.modules.mms.entity.ComprehensiveProduct;
import com.thinkgem.jeesite.modules.mms.entity.DeclareProduct;
import com.thinkgem.jeesite.modules.mms.entity.Product;
import com.thinkgem.jeesite.modules.mms.service.ComprehensiveProductService;
import com.thinkgem.jeesite.modules.mms.service.DeclareProductService;
import com.thinkgem.jeesite.modules.mms.service.ProductService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;

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
	public String list(Product product, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<ComprehensiveProduct> page = comprehensiveProductService.findPage(new Page<ComprehensiveProduct>(request, response), comprehensiveProduct);
		Page<Product> page = productService.findPage(new Page<Product>(request, response), product);
		model.addAttribute("page", page);
		return "modules/mms/comprehensiveProductList";
	}

	@RequiresPermissions("mms:comprehensiveProduct:view")
	@RequestMapping(value = "form")
	public String form(ComprehensiveProduct comprehensiveProduct, Model model) {


		model.addAttribute("comprehensiveProduct", comprehensiveProduct);
		return "modules/mms/comprehensiveProductForm";
	}

	@RequiresPermissions("mms:comprehensiveProduct:edit")
	@RequestMapping(value = "save")
	public String save(ComprehensiveProduct comprehensiveProduct, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, comprehensiveProduct)){
			return form(comprehensiveProduct, model);
		}
		//获取当前登录用户.处理人ID
		User user  = UserUtils.getUser();
		comprehensiveProduct.setProductHandlePersonId(user.getId());
		//根据来样时间判断产品状态，因为这个状态是中间的状态，所有要进行和现在的产品状态进行对比

		/**
		 * 1:先查处产品的信息
		 * 2:取得产品现在的状态在进行对比
		 * 3：更新产品的状态
		 */
		Product product = productService.getByComprehensiveProductId(comprehensiveProduct.getId());

		if(product !=null){
			String nowProductProcess = product.getProductProcess();
			if(Integer.valueOf(nowProductProcess) < Integer.valueOf(MmsConstant.PRODUCT_PROCESS_2)){ //小于 40
				product.setProductProcess(MmsConstant.PRODUCT_PROCESS_2);
				product.setProductStatus(MmsConstant.PRODUCT_STATUS_2);
				productService.save(product);
			}

			addMessage(redirectAttributes, "保存综合产品成功");
		}else{
			addMessage(redirectAttributes, "产品信息有错误");
		}

		comprehensiveProductService.save(comprehensiveProduct);
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