package com.jr.erp.sys.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jr.erp.base.exception.ServiceAccessException;
import com.jr.erp.base.shiro.ShiroUtils;
import com.jr.erp.base.utils.BasePageForm;
import com.jr.erp.base.utils.Ret;
import com.jr.erp.base.utils.RetJqGridPage;
import com.jr.erp.base.utils.RetPage;
import com.jr.erp.sys.entity.SysCategorySet;
import com.jr.erp.sys.entity.SysCategorySetExample;
import com.jr.erp.sys.entity.SysClassify;
import com.jr.erp.sys.entity.SysClassifyExample;
import com.jr.erp.sys.entity.SysUser;
import com.jr.erp.sys.service.ISysCategorySetService;
import com.jr.erp.sys.service.ISysClassifyService;

/**     
 * 类名称：SysCategorySetController    
 * 类描述：通用类型    
 * 创建人：Administrator    
 * 创建时间：2018年9月17日 上午10:19:59    
 * 修改人：Administrator    
 * 修改时间：2018年9月17日 上午10:19:59    
 * 修改备注：    
 * @version  1.0    
 */
@Controller
@RequestMapping("/sysCategorySet")
public class SysCategorySetController
{

    @Autowired
    private ISysCategorySetService sysCategorySetService;

    @Autowired
    private ISysClassifyService sysClassifyService;
    /**    
     * viewStore(这里用一句话描述这个方法的作用)    
     * 跳转到门店页面       
     * @param @param id
     * @param @param request
     * @param @param model
     * @param @return     
     * @return String
     * @Exception 异常对象
    */
    @RequestMapping(value = "/viewCategorySet.do")
    public String viewCommonType(HttpServletRequest request, Model model)
    {
        return "sys/category/viewCategorySet";
    }
    
    /**    
     * getStoreListData(这里用一句话描述这个方法的作用)    
     * 获取门店列表       
     * @param @param request
     * @param @return     
     * @return RetPage
     * @Exception 异常对象
    */
    @RequestMapping(value = "/getCategoryData.do")
    @ResponseBody
    public RetJqGridPage getCategoryData(BasePageForm pageForm,String categoryType,HttpServletRequest request)
    {
        SysUser user = ShiroUtils.getSysUser();
        SysCategorySetExample exampale = new SysCategorySetExample();
//        exampale.setPage(pageForm.getPage());
//        exampale.setLimit(pageForm.getLimit());
        exampale.setOrderByClause("id desc");
        exampale.createCriteria().andCompanyNoEqualTo(user.getCompanyNo()).andCategoryTypeEqualTo(categoryType);
        RetPage page = sysCategorySetService.selectPage(exampale);
        return RetJqGridPage.ok(page.getCount(), page.getData());
    }
    
    /**    
     * saveStore(这里用一句话描述这个方法的作用)    
     * 保存门店       
     * @param @param store
     * @param @param request
     * @param @return     
     * @return Ret
     * @Exception 异常对象
    */
    @RequestMapping(value = "/saveCategory.do")
    @ResponseBody
    public Ret saveCategory(@RequestBody ArrayList<SysCategorySet> categoryList,HttpServletRequest request)
    {
        SysUser user = ShiroUtils.getSysUser();
        List<String> existList = sysCategorySetService.saveCategory(user.getCompanyNo(), categoryList);
        if (CollectionUtils.isNotEmpty(existList))
        {
            return Ret.warn("保存成功。", existList);
        } else
        {
            return Ret.ok("保存成功");
        }
    }
    
    /**    
     * updateCounter(这里用一句话描述这个方法的作用)    
     * 保存门店       
     * @param @param store
     * @param @param request
     * @param @return     
     * @return Ret
     * @Exception 异常对象
    */
    @RequestMapping(value = "/updateCategory.do")
    @ResponseBody
    public Ret updateCategory(SysCategorySet category,HttpServletRequest request)
    {
        SysUser user = ShiroUtils.getSysUser();
        try
        {
            category.setCompanyNo(user.getCompanyNo());
            sysCategorySetService.updateCategory(category);
        } catch (Exception e)
        {
            if (e instanceof ServiceAccessException)
            {
                return Ret.error(e.getMessage());
            }
            e.printStackTrace();
        }
        return Ret.ok("保存成功");
    }
    
    /**    
     * deleteCategory(这里用一句话描述这个方法的作用)    
     * 删除分类
     * @param @param ids
     * @param @return     
     * @return Ret
     * @Exception 异常对象
    */
    @RequestMapping(value = "/deleteCategorySet.do")
    @ResponseBody
    public Ret deleteCategorySet(Integer[] ids,HttpServletRequest request)
    {
        SysUser user = ShiroUtils.getSysUser();
        try
        {
            SysCategorySetExample exampale = new SysCategorySetExample();
            exampale.createCriteria().andCompanyNoEqualTo(user.getCompanyNo()).andIdIn(Arrays.asList(ids));
            sysCategorySetService.deleteByExample(exampale);
        } catch (Exception e)
        {
            if (e instanceof ServiceAccessException)
            {
                return Ret.error(e.getMessage());
            }
            e.printStackTrace();
        }
        return Ret.ok("删除成功");
    }
    
    /**    
     * viewClassify(这里用一句话描述这个方法的作用)    
     * 查看商品分类       
     * @param @param request
     * @param @param model
     * @param @return     
     * @return String
     * @Exception 异常对象
    */
    @RequestMapping(value = "/viewClassify.do")
    public String viewClassify(HttpServletRequest request, Model model)
    {
        return "sys/classify/viewClassify";
    }
    
    /**    
     * addOrUpdateClassify(这里用一句话描述这个方法的作用)    
     * 新增修改       
     * @param @param firstType
     * @param @param id
     * @param @param request
     * @param @param model
     * @param @return     
     * @return String
     * @Exception 异常对象
    */
    @RequestMapping(value = "/editClassify.do")
    public String editClassify(String firstType,Integer id,HttpServletRequest request, Model model)
    {
        SysUser user = ShiroUtils.getSysUser();
        SysClassify classify = new SysClassify();
        if(id!=null)
        {
            classify = (SysClassify) sysClassifyService.selectByPrimaryKey(id);
        }else
        {
            classify.setStatus(1);
            classify.setFirstType(firstType);
            switch (firstType)
            {
            case "gold":
                classify.setFirstTypeName("素金");
                break;
            case "notGold":
                classify.setFirstTypeName("非素");
                break;
            case "material":
                classify.setFirstTypeName("旧料");
                break;
            case "serviceFee":
                classify.setFirstTypeName("服务费");
                break;
            default:
                break;
            }
        }
        model.addAttribute("classify",classify);
        return "sys/classify/editClassify";
    }
    
    /**    
     * getClassifyData(这里用一句话描述这个方法的作用)    
     * 获取分页数据       
     * @param @param pageForm
     * @param @param firstType
     * @param @param request
     * @param @return     
     * @return RetJqGridPage
     * @Exception 异常对象
    */
    @RequestMapping(value = "/getClassifyData.do")
    @ResponseBody
    public RetJqGridPage getClassifyData(BasePageForm pageForm,String firstType,HttpServletRequest request)
    {
        SysUser user = ShiroUtils.getSysUser();
        SysClassifyExample exampale = new SysClassifyExample();
        exampale.setOrderByClause("id");
        exampale.createCriteria().andCompanyNoEqualTo(user.getCompanyNo()).andFirstTypeEqualTo(firstType);
        RetPage page = sysClassifyService.selectPage(exampale);
        return RetJqGridPage.ok(page.getCount(), page.getData());
    }
    
    /**    
     * saveClassify(这里用一句话描述这个方法的作用)    
     * 保存       
     * @param @param sysClassify
     * @param @param request
     * @param @return     
     * @return Ret
     * @Exception 异常对象
    */
    @RequestMapping(value = "/saveClassify.do")
    @ResponseBody
    public Ret saveClassify(SysClassify sysClassify,HttpServletRequest request)
    {
        SysUser user = ShiroUtils.getSysUser();
        sysClassify.setCompanyNo(user.getCompanyNo());
        sysClassifyService.saveClassify(sysClassify);
        return Ret.ok("保存成功");
    }
    
    /**    
     * deleteClassify(这里用一句话描述这个方法的作用)    
     * 删除分类       
     * @param @param ids
     * @param @param request
     * @param @return     
     * @return Ret
     * @Exception 异常对象
    */
    @RequestMapping(value = "/deleteClassify.do")
    @ResponseBody
    public Ret deleteClassify(Integer[] ids,HttpServletRequest request)
    {
        SysUser user = ShiroUtils.getSysUser();
        try
        {
            SysClassifyExample exampale = new SysClassifyExample();
            exampale.createCriteria().andCompanyNoEqualTo(user.getCompanyNo()).andIsSysDefNotEqualTo(1).andIdIn(Arrays.asList(ids));
            sysClassifyService.deleteByExample(exampale);
        } catch (Exception e)
        {
            if (e instanceof ServiceAccessException)
            {
                return Ret.error(e.getMessage());
            }
            e.printStackTrace();
        }
        return Ret.ok("删除成功");
    }
}
