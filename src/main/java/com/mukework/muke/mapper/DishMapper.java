package com.mukework.muke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mukework.muke.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
