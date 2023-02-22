package com.mukework.muke.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mukework.muke.entity.Category;

public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
