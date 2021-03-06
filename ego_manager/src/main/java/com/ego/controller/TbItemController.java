package com.ego.controller;

import com.ego.commons.execption.DaoExecption;
import com.ego.commons.pojo.EasyUIDatagrid;
import com.ego.commons.pojo.EgoResult;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItem;
import com.ego.service.TbItemService;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TbItemController {

    @Autowired
    private TbItemService tbItemService;

    /**
     * 显示商品
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/item/list")
    @ResponseBody
    public EasyUIDatagrid showItem(int page,int rows){
        System.out.println("page:" +  page + "rows:" +  rows);
        return tbItemService.showItem(page,rows);
    }

    /**
     * 刪除
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/delete")
    @ResponseBody
    public EgoResult delete(long[] ids){
        return tbItemService.updataStatus(ids,3);
    }

    /**
     * 上架
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/reshelf")
    @ResponseBody
    public EgoResult reshelf(long[] ids){
        return tbItemService.updataStatus(ids,1);
    }

    /**
     * 下架
     * @param ids
     * @return
     */
    @RequestMapping("/rest/item/instock")
    @ResponseBody
    public EgoResult instock(long[] ids){
        return tbItemService.updataStatus(ids,2);
    }

    /**
     * 商品新增功能
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping("/item/save")
    @ResponseBody
    public EgoResult insert(TbItem item, String desc){
        return tbItemService.insert(item,desc);
    }

    /**
     * 修改
     * @param item
     * @param desc
     * @return
     */
    @RequestMapping("/rest/item/update")
    @ResponseBody
    public EgoResult update(TbItem item,String desc) throws DaoExecption {
        return tbItemService.update(item,desc);
    }


}
