package com.jr.erp.sys.service;

import java.util.List;
import java.util.Map;

import com.jr.erp.base.mybatis.BaseEntity;
import com.jr.erp.base.mybatis.IBaseService;
import com.jr.erp.sys.entity.SysClassify;

/**
 * 系统管理service
 */
public interface ISysClassifyService extends IBaseService<BaseEntity>{

    /**    
     * saveClassify(这里用一句话描述这个方法的作用)    
     * 保存       
     * @param @param companyNo
     * @param @param sysClassify
     * @param @return     
     * @return List<String>
     * @Exception 异常对象
    */
    void saveClassify(SysClassify sysClassify);

    /**    
     * getInUseClassify(这里用一句话描述这个方法的作用)    
     * 获取在用的商品小类       
     * @param @param companyNo
     * @param @return     
     * @return Map<String,List<SysClassify>>
     * @Exception 异常对象
    */
    Map<String, List<SysClassify>> getInUseClassify(String companyNo);
}
