package com.elay.user.authority.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.elay.user.authority.service.IMenuService;
import com.elay.user.authority.entity.Menu;
import com.elay.user.authority.mapper.MenuMapper;
import com.elay.user.authority.request.menu.AddMenuReq;
import com.elay.user.authority.request.menu.UpdMenuReq;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author eLay
 * @since 2024-04-13
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Transactional
    @Override
    public boolean add(AddMenuReq params) {
        if(this.exitByNameAndPath(params.getMenuName(),params.getMenuPath())){
            return false;
        }
        Menu menu = new Menu();
        BeanUtil.copyProperties(params,menu);
        return this.save(menu);
    }

    @Transactional
    @Override
    public boolean upd(UpdMenuReq params) {
        Menu menu = this.getById(params.getMenuId());
        if(menu != null){
            BeanUtil.copyProperties(params,menu);
            if(!this.exitByNameAndPath(menu.getMenuName(),menu.getMenuPath())){
                return false;
            }
            return this.updateById(menu);
        }
        return false;
    }

    @Override
    public Page<Menu> pageByParentId(Integer parentId, Integer page, Integer size) {
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_menu_id",parentId);
        Page<Menu> menuPage = new Page<>(page, size);
        Page<Menu> selectPage = baseMapper.selectPage(menuPage, wrapper);
        return selectPage;
    }

    @Transactional
    @Override
    public boolean delById(Integer menuId) {
        return removeById(menuId);
    }


    private  boolean exitByNameAndPath(String name,String path){
        QueryWrapper<Menu> wrapper = new QueryWrapper<>();
        wrapper.eq("menu_name", name)
                .eq("menu_path", path);
        return baseMapper.selectCount(wrapper) > 0;
    }
}
