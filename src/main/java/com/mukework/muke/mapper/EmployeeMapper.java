package com.mukework.muke.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mukework.muke.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
