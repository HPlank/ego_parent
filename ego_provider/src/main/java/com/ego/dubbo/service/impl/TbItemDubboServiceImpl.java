package com.ego.dubbo.service.impl;

import com.ego.commons.execption.DaoExecption;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.mapper.TbItemMapper;
import com.ego.pojo.TbItem;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class TbItemDubboServiceImpl implements TbItemDubboService {

    @Autowired
    private TbItemMapper tbItemMapper;

    @Override
    public List<TbItem> selectByPage(int pageSize, int pageNumber) {
        //分页插件在查询上，有效
        PageHelper.startPage(pageNumber,pageSize);

        //Example相当于sql中的where,没有where时参数为null
        //select * from tb_item limit ?,?
        List<TbItem> list = tbItemMapper.selectByExample(null);
//        for (TbItem i:list){
//            System.out.println("+++++++"+i);
//        }
        //千万不要忘记构造方法参数
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        return pageInfo.getList();

    }

    @Override
    public long selectCount() {
        return tbItemMapper.countByExample(null);
    }

    @Override
    //监听到异常执行事务回滚
    @Transactional
    public int updataStatusByIds(long[] ids, int status) throws DaoExecption {

        int index = 0;
        Date date = new Date();
        System.out.println(ids);
        for(long id:ids){
            TbItem t = new TbItem();
            t.setId(id);
            t.setStatus((byte)status);
            t.setUpdated(date);
            index+=tbItemMapper.updateByPrimaryKeySelective(t);
//            System.out.println("++++++++++++++++++++++++++++++     "+index+"    +++++++++++++++++");
        }
//        System.out.println("++++++++++++++++++++++++++++++   LastIndex  "+index+"    +++++++++++++++++");
        if(index==ids.length){
            return 1;
        }
        //事務回滾
        throw new DaoExecption("批量修改失败");
    }
}
