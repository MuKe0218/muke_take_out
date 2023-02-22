package com.mukework.muke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukework.muke.entity.Setmeal;
import com.mukework.muke.mapper.SetmealMapper;
import com.mukework.muke.service.SetmealService;
import org.springframework.stereotype.Service;

@Service
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
}
