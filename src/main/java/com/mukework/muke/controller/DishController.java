package com.mukework.muke.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mukework.muke.common.R;
import com.mukework.muke.entity.Dish;
import com.mukework.muke.entity.DishFlavor;
import com.mukework.muke.service.DishFlavorService;
import com.mukework.muke.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dish")
@Slf4j
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private DishFlavorService dishFlavorService;
    //分页查询菜品
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);
        dishService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }
    //列表
    @GetMapping("/list")
    public R<List<Dish>> list(Long categoryId){
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Dish::getCategoryId,categoryId);
        List<Dish> list = dishService.list(lambdaQueryWrapper);
        return R.success(list);
    }
    //新增菜品
    @PostMapping
    public R<String> add(@RequestBody Dish dish){
//        //菜品表
//        dishService.save(dish);
//        //菜品口味关系表
//        if (dish.get)
//        DishFlavor dishFlavor = new DishFlavor();
//        dishFlavor.setDishId(dish.getId());
        return R.success("新增菜品成功");
    }
}
