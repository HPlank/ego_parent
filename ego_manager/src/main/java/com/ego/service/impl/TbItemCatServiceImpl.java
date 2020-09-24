package com.ego.service.impl;

import com.ego.commons.pojo.EasyUITree;
import com.ego.dubbo.service.TbItemCatDubboService;
import com.ego.dubbo.service.TbItemDubboService;
import com.ego.pojo.TbItemCat;
import com.ego.service.TbItemCatService;
import com.ego.service.TbItemService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class TbItemCatServiceImpl implements TbItemCatService {

    @Reference
    private TbItemCatDubboService tbItemCatDubboService;

    @Override
    public List<EasyUITree> showTree(Long pid) {
        List<EasyUITree> listTree  = new ArrayList<>();
        List<TbItemCat> list = tbItemCatDubboService.selectByPid(pid);
        for (TbItemCat cat:list
             ) {
            EasyUITree easyUITree = new EasyUITree();
            easyUITree.setId(cat.getId());
            easyUITree.setState(cat.getIsParent()?"closed":"open");
            easyUITree.setText(cat.getName());

            listTree.add(easyUITree);
        }
            
        return listTree;
    }
}
