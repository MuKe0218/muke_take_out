package com.mukework.muke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukework.muke.entity.Dish;
import com.mukework.muke.mapper.DishMapper;
import com.mukework.muke.service.DishService;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper,Dish> implements DishService {
}
