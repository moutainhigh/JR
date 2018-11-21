package com.jr.erp.bus.stock.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jr.erp.base.mybatis.AbstractBaseService;
import com.jr.erp.bill.purchase.entity.BillPurchase;
import com.jr.erp.bill.purchase.entity.BillPurchaseItem;
import com.jr.erp.bill.transfer.entity.BillTransfer;
import com.jr.erp.bill.transfer.entity.BillTransferItem;
import com.jr.erp.bus.stock.dao.ProductStockMapper;
import com.jr.erp.bus.stock.entity.ProductStock;
import com.jr.erp.bus.stock.service.IProductStockService;

/**     
 * 类名称：ProductStockServiceImpl    
 * 类描述：库存管理service    
 * 创建人：admin    
 * 创建时间：2018年11月15日 下午5:51:11    
 * 修改人：admin    
 * 修改时间：2018年11月15日 下午5:51:11    
 * 修改备注：    
 * @version  1.0    
 */
@Service(value="productStockService")
public class ProductStockServiceImpl extends AbstractBaseService<ProductStock> implements IProductStockService
{

    @Autowired
    ProductStockMapper thisMappper;
    
    @Override
    public void addPurchaseStock(BillPurchase billPurchase, String counterCode)
    {
        for (BillPurchaseItem item : billPurchase.getItemList())
        {
            ProductStock stock = new ProductStock() ;
            BeanUtils.copyProperties(item,stock);
            this.insert(stock);
            
        }
    }

    @Override
    public List<ProductStock> queryStockByQueryBuilder(String querySql)
    {
        return thisMappper.queryStockByQueryBuilder(querySql);
    }

    @Override
    public void addPurchaseStock(BillTransfer billTransfer, String counterCode)
    {
        for (BillTransferItem item : billTransfer.getItemList())
        {
            ProductStock stock = new ProductStock() ;
            BeanUtils.copyProperties(item,stock);
            stock.setAreaCode(billTransfer.getInAreaCode());
            stock.setAreaName(billTransfer.getInAreaName());
            stock.setCounterAreaCode(counterCode);
            stock.setCounterAreaName(billTransfer.getCounterAreaName());
            stock.setNum(item.getOutNum());
            stock.setGoldWeight(item.getOutGoldWeight());
            stock.setmJewelWeight(item.getOutMJewelWeight());
            stock.setLabelPriceSum(item.getOutLabelPriceSum());
            this.insert(stock);
        }
    }
}
