package com.wz.cashloan.core.mapper;

import com.wz.cashloan.core.common.mapper.RDBatisDao;
import com.wz.cashloan.core.model.ProductLoan;

import java.util.List;
import java.util.Map;

@RDBatisDao
public interface ProductLoanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductLoan record);

    int insertSelective(ProductLoan record);

    ProductLoan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductLoan record);

    int updateByPrimaryKey(ProductLoan record);

    List<Map> findAllProductLoan(Map<String, Object> map);

    Map findById(Long id);

    ProductLoan selectByProCode(String proCode);
}