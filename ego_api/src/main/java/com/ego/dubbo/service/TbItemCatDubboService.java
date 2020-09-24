package com.ego.dubbo.service;

import com.ego.pojo.TbItemCat;

import java.util.List;

public interface TbItemCatDubboService {

    /**
     * 根据父id查询所有子类目
     * @param pid 父id
     * @return 所有子类目
     */
    List<TbItemCat> selectByPid(Long pid);
}