package com.mukework.muke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mukework.muke.entity.Employee;
import com.mukework.muke.mapper.EmployeeMapper;
import com.mukework.muke.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}
