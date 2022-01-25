package com.subhankar.service;

import com.subhankar.config.PageResponseConfig;
import com.subhankar.dto.EmployeeDeviceDto;
import com.subhankar.dto.EmployeeDto;
import com.subhankar.dto.LoginDto;



public interface EmployeeService {

    EmployeeDto signUp(EmployeeDto employeeDto);

    EmployeeDto signIn(LoginDto loginDto);

    PageResponseConfig getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir);

    EmployeeDto getEmployeeByEmployeeCode(String empCode);

    EmployeeDto getEmployeeById(long id);

    PageResponseConfig getEmployeeByName(String name, int pageNo, int pageSize, String sortBy, String sortDir);

    PageResponseConfig getEmployeeByEmail(String email, int pageNo, int pageSize, String sortBy, String sortDir);

    PageResponseConfig getEmployeeByAnyInput(String keyword, int pageNo, int pageSize, String sortBy, String sortDir);

    EmployeeDto updateEmployeeById(long id, EmployeeDto employeeDto);

    EmployeeDto updateEmployeeByCode(String empCode, EmployeeDto employeeDto);

    String deleteEmployeeById(long id);

    String deleteEmployeeByCode(String empCode);

    EmployeeDeviceDto getAllDevicesForAnEmployee(String employeeCode);
}
