package com.mukework.muke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mukework.muke.dto.SetmealDto;
import com.mukework.muke.entity.Setmeal;
import com.mukework.muke.entity.SetmealDish;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {
    void saveSetmealDish(SetmealDto setmealDto);
    void removeWithDish(List<Long> list);
}
