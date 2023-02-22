package com.mukework.muke.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mukework.muke.common.R;
import com.mukework.muke.entity.Setmeal;
import com.mukework.muke.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/setmeal")
@Slf4j
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        Page pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        setmealService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }
}
