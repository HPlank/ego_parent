package com.ego.dubbo.service;

import com.ego.commons.execption.DaoExecption;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;

import java.util.List;

/**
 * 对商品表操作
 */
public interface TbItemDubboService {

    /**
     * 分页查询
     * @param pageSize 每页大小
     * @param pageNumber 页码
     * @return  当前页显示数据
     */
    List<TbItem> selectByPage(int pageSize,int pageNumber);

    /**
     * 查询总条数
     * @return
     */
    long selectCount();

    /**事务一定要写在privoder
     * 批量修改
     * @param ids 所有要修改的id
     * @param status 修改的状态值
     * @return 成功1 失败0
     * @throws DaoExecption
     */
    int updataStatusByIds(long[] ids,int status)throws DaoExecption;

    /**
     * 新增
     * @param tbItem
     * @param tbItemDesc
     * @return
     * @throws DaoExecption
     */
    int insert(TbItem tbItem, TbItemDesc tbItemDesc) throws DaoExecption;

    int update(TbItem item, TbItemDesc tbItemDesc)throws DaoExecption;

}
