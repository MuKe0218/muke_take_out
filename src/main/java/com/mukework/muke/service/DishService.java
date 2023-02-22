package com.mukework.muke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mukework.muke.dto.DishDto;
import com.mukework.muke.entity.Dish;

public interface DishService extends IService<Dish> {
    //新增数据，同时插入菜品对应口味表，需要操作两张表
    public void saveWithFlavor(DishDto dishDto);
}
