package com.subhankar.dao;

import com.subhankar.config.PageResponseConfig;
import com.subhankar.dto.EmployeeDeviceDto;
import com.subhankar.dto.EmployeeDto;
import com.subhankar.dto.LoginDto;
import com.subhankar.entity.Device;
import com.subhankar.entity.Employee;
import com.subhankar.exceptions.InvalidCredentials;
import com.subhankar.exceptions.ResourceNotFoundException;
import com.subhankar.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class EmployeeDaoImpl implements EmployeeDao {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public EmployeeDto signUp(EmployeeDto employeeDto) {
        Employee employee = modelMapper.map(employeeDto,Employee.class);
        Employee newEmployee = employeeRepository.save(employee);

        return modelMapper.map(newEmployee
                ,EmployeeDto.class);
    }

    @Override
    public EmployeeDto signIn(LoginDto loginDto) {
        String userName = loginDto.getEmail();
        String pass = loginDto.getPassword();
        Employee employee=null;

        try {
            employee = employeeRepository.findByEmailIdOrContactNumber(userName);

            if(employee.getPassword().equals(pass)){
                return modelMapper.map(employee,EmployeeDto.class);
            }
            else {
                throw new InvalidCredentials(HttpStatus.UNAUTHORIZED, "Invalid Password");
            }

        }catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Employee not found with email "+userName);
        }





    }

    @Override
    public PageResponseConfig getAllEmployees(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort;
        if (sortDir.equalsIgnoreCase("asc")){
            sort= Sort.by(Sort.Direction.ASC, sortBy);
        }
        else {
            sort= Sort.by(Sort.Direction.DESC, sortBy);
        }
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Employee> empPage = employeeRepository.findAll(pageable);
        List<Employee> empList = empPage.getContent();
        List<EmployeeDto> employeeDtos = empList.stream().map(employee -> modelMapper.map(employee,EmployeeDto.class)).collect(Collectors.toList());

        return new PageResponseConfig(employeeDtos,
                empPage.getNumber(),
                empPage.getSize(),
                empPage.getTotalElements(),
                empPage.getTotalPages(),
                empPage.isLast());
    }

    @Override
    public EmployeeDto getEmployeeByEmployeeCode(String empCode) {
        try{
            Employee employee = employeeRepository.findByEmployeeCode(empCode);
            return modelMapper.map(employee,EmployeeDto.class);
        }catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Employee not found with employee code "+empCode);
        }

    }

    @Override
    public EmployeeDto getEmployeeById(long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Employee Not Found with id "+id));
        return modelMapper.map(employee,EmployeeDto.class);
    }

    @Override
    public PageResponseConfig getEmployeeByName(String name, int pageNo, int pageSize, String sortBy, String sortDir) {
        List<EmployeeDto> employeeDtos=null;
        Page<Employee> empPage=null;
        try {
            Sort sort;
            if (sortDir.equalsIgnoreCase("asc")) {
                sort = Sort.by(Sort.Direction.ASC, sortBy);
            } else {
                sort = Sort.by(Sort.Direction.DESC, sortBy);
            }
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            empPage = employeeRepository.findByName(name, pageable);
            List<Employee> empList = empPage.getContent();
            employeeDtos = empList.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        }
        catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Employees were found with name"+name);
        }finally {
            if(employeeDtos.size()==0){
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Employees were found with name"+name);

            }else {
                return new PageResponseConfig(employeeDtos,
                        empPage.getNumber(),
                        empPage.getSize(),
                        empPage.getTotalElements(),
                        empPage.getTotalPages(),
                        empPage.isLast());
            }
        }
    }

    @Override
    public PageResponseConfig getEmployeeByEmail(String email, int pageNo, int pageSize, String sortBy, String sortDir) {
        List<EmployeeDto> employeeDtos=null;
        Page<Employee> empPage=null;
        try {
            Sort sort = null;
            if (sortDir.equalsIgnoreCase("asc")) {
                sort = Sort.by(Sort.Direction.ASC, sortBy);
            } else {
                sort = Sort.by(Sort.Direction.DESC, sortBy);
            }
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            empPage = employeeRepository.findByEmailId(email, pageable);
            List<Employee> empList = empPage.getContent();
            employeeDtos = empList.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        }
        catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Employees were found with email"+email);
        }finally {
            if(employeeDtos.size()==0){
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Employees were found with email"+email);

            }else {
                return new PageResponseConfig(employeeDtos,
                        empPage.getNumber(),
                        empPage.getSize(),
                        empPage.getTotalElements(),
                        empPage.getTotalPages(),
                        empPage.isLast());
            }
        }
    }


    @Override
    public PageResponseConfig getEmployeeByAnyInput(String keyword, int pageNo, int pageSize, String sortBy, String sortDir) {
        List<EmployeeDto> employeeDtos=null;
        Page<Employee> empPage=null;
        try {
            Sort sort = null;
            if (sortDir.equalsIgnoreCase("asc")) {
                sort = Sort.by(Sort.Direction.ASC, sortBy);
            } else {
                sort = Sort.by(Sort.Direction.DESC, sortBy);
            }
            Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
            empPage = employeeRepository.getDataByAnyInput(keyword, pageable);
            List<Employee> empList = empPage.getContent();
            employeeDtos = empList.stream().map(employee -> modelMapper.map(employee, EmployeeDto.class)).collect(Collectors.toList());
        }
        catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Employees were found matching with"+keyword);
        }finally {
            if(employeeDtos.size()==0){
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Employees were found matching with"+keyword);

            }else {
                return new PageResponseConfig(employeeDtos,
                        empPage.getNumber(),
                        empPage.getSize(),
                        empPage.getTotalElements(),
                        empPage.getTotalPages(),
                        empPage.isLast());
            }
        }
    }

    @Override
    public EmployeeDto updateEmployeeById(long id, EmployeeDto employeeDto) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Employee Not Found with id "+id));
        Employee newEmployee = modelMapper.map(employeeDto,Employee.class);

        employee.setEmployeeCode(newEmployee.getEmployeeCode());
        employee.setName(newEmployee.getName());
        employee.setEmailId(newEmployee.getEmailId());
        employee.setContactNumber(newEmployee.getContactNumber());
        employee.setPassword(newEmployee.getPassword());

        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }

    @Override
    public EmployeeDto updateEmployeeByCode(String empCode, EmployeeDto employeeDto) {
        Employee employee=null;
        try{
            employee = employeeRepository.findByEmployeeCode(empCode);
        }catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Employee not found with employee code "+empCode);
        }
        Employee newEmployee = modelMapper.map(employeeDto,Employee.class);

        employee.setEmployeeCode(newEmployee.getEmployeeCode());
        employee.setName(newEmployee.getName());
        employee.setEmailId(newEmployee.getEmailId());
        employee.setContactNumber(newEmployee.getContactNumber());
        employee.setPassword(newEmployee.getPassword());

        return modelMapper.map(employeeRepository.save(employee),EmployeeDto.class);
    }

    @Override
    public String deleteEmployeeById(long id) {
        Employee employee=employeeRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Employee Not Found with id "+id));
        employeeRepository.delete(employee);
        return "Employee Deleted Successfully";
    }

    @Override
    public String deleteEmployeeByCode(String empCode) {
        Employee employee=null;
        try{
            employee = employeeRepository.findByEmployeeCode(empCode);
            employeeRepository.delete(employee);
            return "Employee deleted Successfully";
        }catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Employee not found with employee code "+empCode);
        }

    }

    @Override
    public EmployeeDeviceDto getAllDevicesForAnEmployee(String employeeCode) {
        Employee employee=null;
        try{
            employee = employeeRepository.findByEmployeeCode(employeeCode);

        }catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Employee not found with employee code "+employeeCode);
        }

        EmployeeDeviceDto employeeDeviceDto =new EmployeeDeviceDto(employee.getId(),employee.getEmployeeCode(),employee.getName(), employee.getEmailId(), employee.getDeviceList(), employee.getDeviceList().size());

        return employeeDeviceDto;
    }
}
