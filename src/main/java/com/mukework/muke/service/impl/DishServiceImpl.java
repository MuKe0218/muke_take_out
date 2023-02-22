package com.mukework.muke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukework.muke.dto.DishDto;
import com.mukework.muke.entity.Dish;
import com.mukework.muke.entity.DishFlavor;
import com.mukework.muke.mapper.DishMapper;
import com.mukework.muke.service.DishFlavorService;
import com.mukework.muke.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper,Dish> implements DishService {
    @Autowired
    private DishFlavorService dishFlavorService;
    @Override
    @Transactional//事务注解
    public void saveWithFlavor(DishDto dishDto) {
        this.save(dishDto);
        Long dishId = dishDto.getId();
        List<DishFlavor> list = dishDto.getFlavors();
        list.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());
        dishFlavorService.saveBatch(list);
    }
}
