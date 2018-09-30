package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.core.model.ProductHit;
@RDBatisDao
public interface ProductHitMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductHit record);

    int insertSelective(ProductHit record);

    ProductHit selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductHit record);

    int updateByPrimaryKey(ProductHit record);
}