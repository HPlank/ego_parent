package com.ego.service.impl;

import com.ego.commons.execption.DaoExecption;
import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.commons.utils.IDUtils;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.pojo.TbItemDesc;
import com.ego.service.TbItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Override
    public EgoResult insert(TbItem item, String desc) {
        Date date = new Date();
        long id = IDUtils.genItemId();
        // 商品表数据
        item.setCreated(date);
        item.setUpdated(date);
        // 项目表主键通过算法生成。使用自定义主键值。
        item.setId(id);
        item.setStatus((byte)1);

        // 商品描述表
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(id);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setUpdated(date);
        tbItemDesc.setCreated(date);

        try {
            int index = tbItemDubboService.insert(item, tbItemDesc);
            if(index==1){
                return EgoResult.ok();
            }
        } catch (DaoExecption e) {
            e.printStackTrace();
        }
        EgoResult egoResult = EgoResult.error("新增失败");
        return egoResult;
    }


    @Override
    public EgoResult update(TbItem item, String desc) throws DaoExecption {
        Date date = new Date();
        item.setUpdated(date);

        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(item.getId());

        try {
            int index = tbItemDubboService.update(item, tbItemDesc);
            if(index==1){
                return EgoResult.ok();
            }
        } catch (DaoExecption e) {
            e.printStackTrace();
        }
        return EgoResult.error("修改失败");
    }

}
