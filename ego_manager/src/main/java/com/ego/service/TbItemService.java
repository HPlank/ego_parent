package com.ego.service;

import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;

/**
 * 完成视图逻辑
 */
public interface TbItemService {
/**
 * 方法的返回值为页面要的东西
 * 方法的参数为页面传递过来的东西
 */

    /**
     * 分页商品信息
     * @param page  页码
     * @param rows  每页大小
     * @return  easyui要的模板数据
     */
    EasyUIDatagrid showItem(int page,int rows);


    /**
     * 操作狀態值
     * @param ids
     * @param status
     * @return
     */
    EgoResult updataStatus(long[] ids,int status);

}
