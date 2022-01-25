package com.subhankar.service;

import com.subhankar.config.PageResponseConfig;
import com.subhankar.dao.EmployeeDao;
import com.subhankar.dto.EmployeeDeviceDto;
import com.subhankar.dto.EmployeeDto;
import com.subhankar.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{
    @Autowired
    private EmployeeDao employeeDao;


    @Override
    public EmployeeDto signUp(EmployeeDto employeeDto) {
        return employeeDao.signUp(employeeDto);
    }

    @Override
    public EmployeeDto signIn(LoginDto loginDto) {
        return employeeDao.signIn(loginDto);
    }

    @Override
    public PageResponseConfig getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {
        return employeeDao.getAllEmployees(pageNo,pageSize,sortBy,sortDir);
    }

    @Override
    public EmployeeDto getEmployeeByEmployeeCode(String empCode) {
        return employeeDao.getEmployeeByEmployeeCode(empCode);
    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        return employeeDao.getEmployeeById(id);
    }

    @Override
    public PageResponseConfig getEmployeeByName(String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        return employeeDao.getEmployeeByName(name, pageNo,pageSize,sortBy,sortDir);
    }

    @Override
    public PageResponseConfig getEmployeeByEmail(String email, int pageNo, int pageSize, String sortBy, String sortDir) {
        return employeeDao.getEmployeeByEmail(email,pageNo,pageSize,sortBy,sortDir);
    }

    @Override
    public PageResponseConfig getEmployeeByAnyInput(String keyword, int pageNo, int pageSize, String sortBy, String sortDir) {
        return employeeDao.getEmployeeByAnyInput(keyword, pageNo, pageSize, sortBy, sortDir);
    }

    @Override
    public EmployeeDto updateEmployeeById(long id, EmployeeDto employeeDto) {
        return employeeDao.updateEmployeeById(id, employeeDto);
    }

    @Override
    public EmployeeDto updateEmployeeByCode(String empCode, EmployeeDto employeeDto) {
        return employeeDao.updateEmployeeByCode(empCode,employeeDto);
    }

    @Override
    public String deleteEmployeeById(long id) {
        return employeeDao.deleteEmployeeById(id);
    }

    @Override
    public String deleteEmployeeByCode(String empCode) {
        return employeeDao.deleteEmployeeByCode(empCode);
    }

    @Override
    public EmployeeDeviceDto getAllDevicesForAnEmployee(String employeeCode) {
        return employeeDao.getAllDevicesForAnEmployee(employeeCode);
    }
}
