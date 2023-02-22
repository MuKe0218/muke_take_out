package com.mukework.muke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukework.muke.common.CustomException;
import com.mukework.muke.entity.Category;
import com.mukework.muke.entity.Dish;
import com.mukework.muke.entity.Setmeal;
import com.mukework.muke.mapper.CategoryMapper;
import com.mukework.muke.mapper.DishMapper;
import com.mukework.muke.service.CategoryService;
import com.mukework.muke.service.DishService;
import com.mukework.muke.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;
    @Override
    public void remove(Long id) {
        //是否关联菜品
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int dishCount = dishService.count(dishLambdaQueryWrapper);
        if (dishCount > 0){
            //已经关联了菜品
            throw new CustomException("当前分类下关联了菜品，不能删除");
        }
        //是否关联套餐
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper =new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int setmealCount = setmealService.count(setmealLambdaQueryWrapper);
        if (setmealCount > 0){
            //已经关联套餐
            throw new CustomException("当前分类下关联了套餐，不能删除");
        }
        super.removeById(id);
    }
}
