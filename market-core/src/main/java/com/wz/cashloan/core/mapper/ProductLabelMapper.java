package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.core.model.ProductLabel;
@RDBatisDao
public interface ProductLabelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductLabel record);

    int insertSelective(ProductLabel record);

    ProductLabel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductLabel record);

    int updateByPrimaryKey(ProductLabel record);
}