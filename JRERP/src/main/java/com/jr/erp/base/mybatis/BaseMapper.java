package com.jr.erp.base.mybatis;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseMapper<T extends BaseEntity> {

	int deleteByPrimaryKey(Integer pk);

	int insert(T record);

	T selectByPrimaryKey(Integer pk);

	int updateByPrimaryKey(T record);
}