package com.ego.service.impl;

import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.service.TbItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TbItemServiceImpl implements TbItemService {

    @Reference
    private TbItemDubboService tbItemDubboService;

    @Override
    public EasyUIDatagrid showItem(int page, int rows) {
        List<TbItem> list = tbItemDubboService.selectByPage(rows, page);
        long total = tbItemDubboService.selectCount();
//        System.out.println("EasyUIDatagrid++++++++++++"+total);
        return new EasyUIDatagrid(total,list);
    }

    @Override
    public EgoResult updataStatus(long[] ids, int status) {
        try {
            int index = tbItemDubboService.updataStatusByIds(ids, status);
            if (index == 1) {
                return EgoResult.ok();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return EgoResult.error("操作失敗！");
    }
}
