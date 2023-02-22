package com.mukework.muke.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mukework.muke.common.R;
import com.mukework.muke.entity.Employee;
import com.mukework.muke.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    //登录
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request,@RequestBody Employee employee){
        String password = employee.getPassword();
        //密码加密 DigestUtils.md5DigestAsHex
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername,employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);
        if (emp == null){
            return R.error("登录失败,账号不存在");
        }
        if (!emp.getPassword().equals(password)){
            return  R.error("登录失败，密码错误");
        }
        if (emp.getStatus() == 0){
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("employee",emp.getId());
        return R.success(emp);
    }
    //退出
    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
    //新增员工
    @PostMapping
    public R<String> save(HttpServletRequest request,@RequestBody Employee employee){
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes(StandardCharsets.UTF_8)));
        //employee.setCreateTime(LocalDateTime.now());
       // employee.setUpdateTime(LocalDateTime.now());
        //Long empId = (Long) request.getSession().getAttribute("employee");
       // employee.setCreateUser(empId);
       // employee.setUpdateUser(empId);
        employeeService.save(employee);
        return R.success("新增员工成功");
    }
    //分页查询
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
        //构建分页构造器
        Page pageInfo = new Page(page,pageSize);
        //条件构造器
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name),Employee::getName,name).orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo,lambdaQueryWrapper);
        return R.success(pageInfo);
    }
    //启用禁用
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        //employee.setUpdateTime(LocalDateTime.now());
        //employee.setUpdateUser((Long) request.getSession().getAttribute("employee"));
        employeeService.updateById(employee);
        return R.success("修改成功");
    }
    //获取员工信息
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if (employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }
}
