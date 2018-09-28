package com.pos.api.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wz.cashloan.core.mapper.ProductLoanMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hbx on 2018/9/27.
 */
@Service
public class ProductService {
    @Resource
    private ProductLoanMapper productLoanMapper;

    public Page<Map> findAll(String labelId, int currentPage, int pageSize) {
        Map params = new HashMap();
        if (StringUtils.isNotBlank(labelId)) {
            params.put("labelIds", labelId);
        }
        PageHelper.startPage(currentPage, pageSize);
        List<Map> mapList = productLoanMapper.findAllProductLoan(params);
        return (Page<Map>) mapList;
    }

    public Map findById(Long id){
        return productLoanMapper.findById(id);
    }
}
