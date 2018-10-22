package com.pos.api.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.mapper.ProductHitMapper;
import com.wz.cashloan.core.mapper.ProductLoanMapper;
import com.wz.cashloan.core.model.ProductHit;
import com.wz.cashloan.core.model.ProductLoan;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
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
    @Resource
    private ProductHitMapper productHitMapper;

    public Page<Map> findAll(String labelId, int currentPage, int pageSize) {
        Map params = new HashMap();
        if (StringUtils.isNotBlank(labelId)) {
            params.put("labelIds", labelId);
        }
        PageHelper.startPage(currentPage, pageSize);
        List<Map> mapList = productLoanMapper.findAllProductLoan(params);
        String serverHost = Global.getValue("server_host");
        for (int i = 0; i < mapList.size(); i++) {
            Map map = mapList.get(i);
            try {
                map.put("picture", serverHost + "/readFile.htm?path=" + (StringUtils.isBlank(map.get("picture").toString()) ? "" : URLEncoder.encode(map.get("picture").toString(), "UTF-8")));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return (Page<Map>) mapList;
    }

    public Map findById(Long id) {
        Map map = productLoanMapper.findById(id);
        String serverHost = Global.getValue("server_host");
        try {
            map.put("applyProcessImg", serverHost + "/readFile.htm?path=" + (StringUtils.isBlank(map.get("applyProcessImg").toString()) ? "" : URLEncoder.encode(map.get("applyProcessImg").toString(), "UTF-8")));
            map.put("picture", serverHost + "/readFile.htm?path=" + (StringUtils.isBlank(map.get("picture").toString()) ? "" : URLEncoder.encode(map.get("picture").toString(), "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return map;
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

    /**
     * 详情
     *
     * @param id
     * @return
     */
    public Map detail(Long id) {
        Map<String, Object> res = new HashMap<>();
        Map<String, Object> data = new HashMap<>();
        ProductLoan productLoan1 = productLoanMapper.selectByPrimaryKey(id);
        if (productLoan1 == null) {
            res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
            res.put(Constant.RESPONSE_CODE_MSG, "无效id");
            return res;
        }

        String serverHost = Global.getValue("server_host");
        data.put("name", productLoan1.getName());
        data.put("proInstructions", productLoan1.getProInstructions());
//        String amount = "";
//        if (StringUtils.isNotBlank(productLoan1.getMinAmount()) && StringUtils.isNotBlank(productLoan1.getMaxAmount())) {
//            amount = productLoan1.getMinAmount() + "-" + productLoan1.getMaxAmount();
//        } else {
//            amount = productLoan1.getMinAmount() + "" + productLoan1.getMaxAmount();
//        }
        data.put("minAmount", productLoan1.getMinAmount());
        data.put("maxAmount", productLoan1.getMaxAmount());

        data.put("minLimit", productLoan1.getMinLimit());
        data.put("maxLimit", productLoan1.getMaxLimit());
        data.put("limitUnit", productLoan1.getLimitUnit());
        data.put("labelIds", productLoan1.getLabelIds());
        data.put("description", productLoan1.getDescription());
        try {
            data.put("applyProcessImg", serverHost + "/readFile.htm?path=" + (StringUtils.isBlank(productLoan1.getApplyProcessImg()) ? "" : URLEncoder.encode(productLoan1.getApplyProcessImg(), "UTF-8")));
            data.put("picture", serverHost + "/readFile.htm?path=" + (StringUtils.isBlank(productLoan1.getPicture()) ? "" : URLEncoder.encode(productLoan1.getPicture(), "UTF-8")));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        data.put("applyCondition", productLoan1.getApplyCondition());
        res.put(Constant.RESPONSE_DATA, data);
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        return res;
    }


    public Page<Map> list(Map<String, Object> queryMap, int currentPage, int pageSize) {

        PageHelper.startPage(currentPage, pageSize);
        List<Map> mapList = productLoanMapper.findAllProductLoan(queryMap);
        return (Page<Map>) mapList;
    }


    public Map apply(Long productId, String ip) {
        Map<String, Object> res = new HashMap();
        ProductHit productHit = new ProductHit();
        productHit.setIp(ip);
        productHit.setProductId(productId);
        productHitMapper.insert(productHit);

        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        return res;
    }
}
