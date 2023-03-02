package com.mukework.muke.dto;

import com.mukework.muke.entity.Setmeal;
import com.mukework.muke.entity.SetmealDish;
import lombok.Data;

import java.util.List;

@Data
public class SetmealDto extends Setmeal {
    private List<SetmealDish> setmealDishes;
    private String categoryName;
}
