package com.pos.api.controller;

import com.github.pagehelper.Page;
import com.pos.api.service.ProductService;
import com.wz.cashloan.core.common.context.Constant;
import com.wz.cashloan.core.common.context.Global;
import com.wz.cashloan.core.common.model.FileTypeUtil;
import com.wz.cashloan.core.common.model.UploadFileRes;
import com.wz.cashloan.core.common.util.RdPage;
import com.wz.cashloan.core.common.util.ServletUtils;
import com.wz.cashloan.core.common.web.controller.BaseController;
import com.wz.cashloan.core.model.ProductLoan;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hbx on 2018/9/27.
 */
@Scope("prototype")
@Controller
public class ProductController extends BaseController {
    public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    @Resource
    private ProductService productService;

    @RequestMapping(value = "/loanProduct.htm", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public void loanProduct(@RequestParam(value = "labelId") String labelId,
                            @RequestParam(value = "currentPage") int currentPage,
                            @RequestParam(value = "pageSize") int pageSize)

    {
        Map<String, Object> result = new HashMap<>();
        Page<Map> page = productService.findAll(labelId, currentPage, pageSize);
        result.put("list", page.getResult());
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        ServletUtils.writeToResponse(response, result);
    }


    @RequestMapping(value = "/toProductView.htm", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
    public void toProductView(@RequestParam(value = "productId") Long productId)

    {
        Map<String, Object> result = new HashMap<>();
        result = productService.findById(productId);
        ServletUtils.writeToResponse(response, result);
    }


    /**
     * @param name            名称
     * @param description     描述
     * @param minAmount       最小金额
     * @param maxAmount       最大
     * @param minLimit        最小期限
     * @param maxLimit        最大
     * @param limitUnit       D 天  M 月
     * @param rate            利率
     * @param rateType        利率类型  D M  Y
     * @param proCode         标识码
     * @param sort            排序
     * @param labelIds        标签ID
     * @param proUrl          注册地址
     * @param applyCondition  申请条件
     * @param applyProcessImg 申请流程图片
     * @param picture         logo
     * @param proInstructions 说明
     */
    @RequestMapping("/product/saveOrUpdate.htm")
    public void saveOrUpdate(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "name", required = true) String name,
                             @RequestParam(value = "description", required = true) String description,
                             @RequestParam(value = "minAmount", required = false) String minAmount,
                             @RequestParam(value = "maxAmount", required = true) String maxAmount,
                             @RequestParam(value = "minLimit", required = false) String minLimit,
                             @RequestParam(value = "maxLimit", required = true) String maxLimit,
                             @RequestParam(value = "limitUnit", required = true) String limitUnit,
                             @RequestParam(value = "rate", required = true) String rate,
                             @RequestParam(value = "rateType", required = true) String rateType,
                             @RequestParam(value = "proCode", required = true) String proCode,
                             @RequestParam(value = "sort", required = false) Integer sort,
                             @RequestParam(value = "labelIds", required = true) String labelIds,
                             @RequestParam(value = "proUrl", required = true) String proUrl,
                             @RequestParam(value = "applyCondition", required = false) String applyCondition,
                             @RequestParam(value = "applyProcessImg", required = false) MultipartFile applyProcessImg,
                             @RequestParam(value = "picture", required = false) MultipartFile picture,
                             @RequestParam(value = "proInstructions", required = false) String proInstructions)

    {
        Map<String, Object> result = new HashMap<>();
//        List<UploadFileRes> uploadFileResList = new LinkedList<>();
//        saveMultipartFile(uploadFileResList, picture);
//        saveMultipartFile(uploadFileResList, applyProcessImg);
        ProductLoan productLoan = new ProductLoan();
        productLoan.setName(name);
        productLoan.setDescription(description);
        if (StringUtils.isNotBlank(minAmount)) {
            productLoan.setMinAmount(minAmount);
        }
        productLoan.setMaxAmount(maxAmount);
        if (StringUtils.isNotBlank(minLimit)) {
            productLoan.setMinLimit(minLimit);
        }
        productLoan.setMaxLimit(maxLimit);
        productLoan.setLimitUnit(limitUnit);
        productLoan.setRate(rate);
        productLoan.setRateType(rateType);
        productLoan.setProCode(proCode);
        productLoan.setSort(sort);

        productLoan.setLabelIds(labelIds);
        productLoan.setProUrl(proUrl);
        productLoan.setApplyCondition(applyCondition);
        productLoan.setProInstructions(proInstructions);

        if (picture != null && !picture.isEmpty()) {
            UploadFileRes model = save(picture);
            productLoan.setPicture(model.getResPath());
        }

        if (applyProcessImg != null && !applyProcessImg.isEmpty()) {
            UploadFileRes model = save(applyProcessImg);
            productLoan.setApplyProcessImg(model.getResPath());
        }


        //更新
        if (id != null) {
            productLoan.setUpdateTime(new Date());
            productLoan.setId(id);
            result = productService.update(productLoan);

        } else {
            productLoan.setCreateTime(new Date());
            productLoan.setState("1");
            result = productService.save(productLoan);
        }
//        result = productService.findById(productId);
        ServletUtils.writeToResponse(response, result);
    }

//    /**
//     * 修改
//     *
//     * @param productId
//     */
//    @RequestMapping("/product/update.htm")
//    public void update(@RequestParam(value = "productId") Long productId)
//
//    {
//        Map<String, Object> result = new HashMap<>();
//        result = productService.findById(productId);
//        ServletUtils.writeToResponse(response, result);
//    }

    /**
     * 禁用
     *
     * @param productId
     */
    @RequestMapping("/product/forbidden.htm")
    public void forbidden(@RequestParam(value = "productId") Long productId)

    {
        Map<String, Object> result = new HashMap<>();
        ProductLoan productLoan = new ProductLoan();
        productLoan.setId(productId);
        productLoan.setState("2");
        productLoan.setUpdateTime(new Date());
        result = productService.update(productLoan);
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 启用
     *
     * @param productId
     */
    @RequestMapping("/product/open.htm")
    public void open(@RequestParam(value = "productId") Long productId)

    {
        Map<String, Object> result = new HashMap<>();
        ProductLoan productLoan = new ProductLoan();
        productLoan.setId(productId);
        productLoan.setState("1");
        productLoan.setUpdateTime(new Date());
        result = productService.update(productLoan);
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 删除
     *
     * @param productId
     */
    @RequestMapping("/product/delete.htm")
    public void delete(@RequestParam(value = "productId") Long productId)

    {
        Map<String, Object> result = new HashMap<>();
        result = productService.delete(productId);
        ServletUtils.writeToResponse(response, result);
    }

    /**
     * 查看
     *
     * @param productId
     */
    @RequestMapping("/product/detail.htm")
    public void detail(@RequestParam(value = "productId") Long productId)

    {
        Map<String, Object> result = new HashMap<>();
        result = productService.detail(productId);
        ServletUtils.writeToResponse(response, result);
    }


    @RequestMapping("/product/list.htm")
    public void list(@RequestParam(value = "name", required = false) String name,
                     @RequestParam(value = "currentPage") int currentPage,
                     @RequestParam(value = "pageSize") int pageSize,
                     @RequestParam(value = "state", required = false) String state)

    {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> query = new HashMap<>();
        if (StringUtils.isNotBlank(name)) {
            query.put("name", name);
        }
        if (StringUtils.isNotBlank(state)) {
            query.put("state", state);
        }
        Page<Map> page = productService.list(query, currentPage, pageSize);
        result.put(Constant.RESPONSE_DATA, page.getResult());
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        ServletUtils.writeToResponse(response, result);
    }


    @RequestMapping("/product/apply.htm")
    public void apply(@RequestParam(value = "ip") String ip,
                      @RequestParam(value = "productId") Long productId)

    {
        Map<String, Object> result = new HashMap<>();
        result = productService.apply(productId, ip);
        ServletUtils.writeToResponse(response, result);
    }

    @RequestMapping("/product/count.htm")
    public void count(@RequestParam(value = "currentPage") int currentPage,
                      @RequestParam(value = "pageSize") int pageSize)

    {
        Map<String, Object> result = new HashMap<>();
        Page<Map> page = productService.count(null, currentPage, pageSize);
        result.put("list", page.getResult());
        result.put(Constant.RESPONSE_DATA_PAGE, new RdPage(page));
        ServletUtils.writeToResponse(response, result);
        ServletUtils.writeToResponse(response, result);
    }

    private void saveMultipartFile(List<UploadFileRes> list, MultipartFile file) {
        if (!file.isEmpty()) {
            UploadFileRes model = save(file);
            list.add(model);
        }
    }

    private UploadFileRes save(MultipartFile file) {
        UploadFileRes model = new UploadFileRes();
        model.setCreateTime(new Date());

        // 文件名称
        String picName = file.getOriginalFilename();

        CommonsMultipartFile cf = (CommonsMultipartFile) file;
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File newFile = (File) fi.getStoreLocation();
        logger.info("图片----------" + newFile);
        // 文件格式
        String fileType = FileTypeUtil.getFileType(newFile);
        if (StringUtils.isBlank(fileType) || !FileTypeUtil.isImage(newFile, fileType)) {
            model.setErrorMsg("图片格式错误或内容不规范");
            return model;
        }
        // 校验图片大小
        Long picSize = file.getSize();
        if (picSize.compareTo(20971520L) > 0) {
            model.setErrorMsg("文件超出20M大小限制");
            return model;
        }
        // 保存文件
        /*String s=File.separator;
        String filePath = s+"data"+s+"image"+s + fileType + s + System.currentTimeMillis() + s + picName;
		if(s.equals("\\")){
			filePath="D:"+filePath;
		}*/
        String s = File.separator;
        String dataDir = Global.getValue("file_home");

        String filePath = "";
        if (s.equals("\\")) {  // windows
            filePath = "D:" + s + "data" + s + "image" + s + fileType + s + System.currentTimeMillis() + s + picName;
        } else {
            filePath = FilenameUtils.getFullPath(dataDir) + "data" + s + "image" + s + fileType + s + System.currentTimeMillis() + s + picName;
        }
        File files = new File(filePath);
        if (!files.exists()) {
            try {
                files.mkdirs();
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                model.setErrorMsg("文件目录不存在");
                return model;
            }
        }
        try {
            file.transferTo(files);
        } catch (IllegalStateException | IOException e) {
            logger.error(e.getMessage(), e);
        }
        // 转存文件
        model.setResPath(filePath);
        model.setFileName(picName);
        model.setFileFormat(fileType);
        model.setFileSize(new BigDecimal(picSize));
        return model;
    }
}
