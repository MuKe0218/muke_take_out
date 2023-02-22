package com.mukework.muke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukework.muke.entity.DishFlavor;
import com.mukework.muke.mapper.DishFlavorMapper;
import com.mukework.muke.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
