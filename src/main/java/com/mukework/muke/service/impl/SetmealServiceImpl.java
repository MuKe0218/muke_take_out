package com.mukework.muke.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukework.muke.common.BaseContext;
import com.mukework.muke.common.CustomException;
import com.mukework.muke.dto.SetmealDto;
import com.mukework.muke.entity.Setmeal;
import com.mukework.muke.entity.SetmealDish;
import com.mukework.muke.mapper.SetmealMapper;
import com.mukework.muke.service.SetmealDishService;
import com.mukework.muke.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    /**
     * 新增套餐
     * @param setmealDto
     */
    @Override
    @Transactional
    public void saveSetmealDish(SetmealDto setmealDto) {
        this.save(setmealDto);
        //保存关联关系
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item)->{
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐
     * @param list
     */
    @Override
    @Transactional
    public void removeWithDish(List<Long> list) {
        //先判断是否可以删除
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(Setmeal::getId,list).eq(Setmeal::getStatus,1);
        int count = this.count(lambdaQueryWrapper);
        if (count > 0){
            throw new CustomException("套餐正在售卖中，不能删除");
        }
        this.removeByIds(list);
        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper1 = new LambdaQueryWrapper<>();
        lambdaQueryWrapper1.in(SetmealDish::getId,list);
        setmealDishService.remove(lambdaQueryWrapper1);
    }
}
