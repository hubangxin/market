package com.wz.cashloan.core.service;

import com.github.pagehelper.Page;

import java.util.Map;

public interface UserService {

    Page<Map<String,Object>> pageList(Map<String, Object> params, int current, int pageSize);

//    int updateByPrimaryKeySelective(UserAmount record);

}
