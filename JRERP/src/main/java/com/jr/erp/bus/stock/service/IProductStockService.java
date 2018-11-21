package com.jr.erp.bus.stock.service;

import java.util.List;

import com.jr.erp.base.mybatis.BaseEntity;
import com.jr.erp.base.mybatis.IBaseService;
import com.jr.erp.bill.purchase.entity.BillPurchase;
import com.jr.erp.bill.transfer.entity.BillTransfer;
import com.jr.erp.bus.stock.entity.ProductStock;

/**     
 * 类名称：IProductStockService    
 * 类描述：库存管理    
 * 创建人：admin    
 * 创建时间：2018年11月15日 下午5:49:30    
 * 修改人：admin    
 * 修改时间：2018年11月15日 下午5:49:30    
 * 修改备注：    
 * @version  1.0    
 */
public interface IProductStockService extends IBaseService<BaseEntity>{

    /**    
     * addPurchaseStock(这里用一句话描述这个方法的作用)    
     * 添加库存       
     * @param @param billPurchase
     * @param @param counterCode
     * @param @param status      状态
     * @return void
     * @Exception 异常对象
    */
    void addPurchaseStock(BillPurchase billPurchase,String counterCode);

    /**    
     * queryStockByQueryBuilder(这里用一句话描述这个方法的作用)    
     * 根据查询条件获取库存明细       
     * @param @param queryBuilder     
     * @return void
     * @Exception 异常对象
    */
    List<ProductStock> queryStockByQueryBuilder(String querySql);

    void addPurchaseStock(BillTransfer billTransfer, String counterCode);
}
