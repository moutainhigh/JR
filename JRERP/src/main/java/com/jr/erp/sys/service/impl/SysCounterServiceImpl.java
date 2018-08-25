package com.jr.erp.sys.service.impl;

import org.springframework.stereotype.Service;

import com.jr.erp.base.mybatis.AbstractBaseService;
import com.jr.erp.sys.entity.SysCounter;
import com.jr.erp.sys.service.ISysCounterService;

/**
 * @author Administrator
 * 系统门店的service
 */
@Service(value="sysCounterService")
public class SysCounterServiceImpl extends AbstractBaseService<SysCounter> implements ISysCounterService{
}
