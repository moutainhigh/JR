package com.jr.erp.sheet.purchase.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jr.erp.base.mybatis.BaseEntity;
import com.jr.erp.base.shiro.ShiroUtils;
import com.jr.erp.sys.entity.SysPurchaseColumnExample;
import com.jr.erp.sys.entity.SysPurchaseSecheme;
import com.jr.erp.sys.entity.SysUser;
import com.jr.erp.sys.service.ISysPurchaseColumnService;
import com.jr.erp.sys.service.ISysPurchaseSechemeService;

@Controller
@RequestMapping("/purchaseSecheme")
public class PurchaseSechemeController {    
    
    @Autowired
    private ISysPurchaseColumnService sysPurchaseColumnService;
    
    @Autowired
    private ISysPurchaseSechemeService sysPurchaseSechemeService;
    /**    
     * editSecheme(这里用一句话描述这个方法的作用)    
     * 保持方案       
     * @param @param request
     * @param @param model
     * @param @return     
     * @return String
     * @Exception 异常对象
    */
    @RequestMapping(value = "/editSecheme.do")
    public String editSecheme(HttpServletRequest request,Integer sechemeId, Model model)
    {
        SysUser user = ShiroUtils.getSysUser();
        SysPurchaseColumnExample example = new SysPurchaseColumnExample();
        example.createCriteria().andCompanyNoEqualTo(user.getCompanyNo());
        List<BaseEntity> allColumn = sysPurchaseColumnService.selectByExample(example);
        model.addAttribute("allColumn", allColumn);
        
        if(sechemeId!=null)
        {
            SysPurchaseSecheme secheme = (SysPurchaseSecheme) sysPurchaseSechemeService.getById(sechemeId);
            model.addAttribute("secheme", secheme);
        }
        
        return "bill/secheme/editSecheme";
    }
    
    @RequestMapping(value = "/saveSecheme.do")
    public String saveSecheme(@RequestBody SysPurchaseSecheme secheme,HttpServletRequest request,Integer sechemeId, Model model)
    {
        System.out.println(secheme);
        SysUser user = ShiroUtils.getSysUser();
        return "bill/secheme/editSecheme";
    }
}    