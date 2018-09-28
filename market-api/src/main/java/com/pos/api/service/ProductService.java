package com.pos.api.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.mapper.ProductLoanMapper;
import com.wz.cashloan.core.model.ProductLoan;
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

    public Map findById(Long id) {
        return productLoanMapper.findById(id);
    }

    public ProductLoan selectByPrimaryKey(Long id) {
        return productLoanMapper.selectByPrimaryKey(id);
    }

    /**
     * @param productLoan
     * @return
     */
    public Map save(ProductLoan productLoan) {
        Map<String, Object> res = new HashMap<>();
        ProductLoan productLoan1 = productLoanMapper.selectByProCode(productLoan.getProCode());
        if (productLoan1 != null) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "标识码重复");
            return res;
        }
        productLoanMapper.insert(productLoan);

        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        return res;
    }

    public Map update(ProductLoan productLoan) {
        Map<String, Object> res = new HashMap<>();
//        ProductLoan productLoan1 = productLoanMapper.selectByProCode(productLoan.getProCode());
//        if (productLoan1 != null) {
//            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
//            res.put(Constant.RESPONSE_CODE_MSG, "标识码重复");
//            return res;
//        }
        int a = productLoanMapper.updateByPrimaryKeySelective(productLoan);
        if (a == 0) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
            return res;
        }

        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        return res;
    }

}
