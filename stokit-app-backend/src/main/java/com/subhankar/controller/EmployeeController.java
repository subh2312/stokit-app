package com.subhankar.controller;

import com.subhankar.config.PageResponseConfig;
import com.subhankar.dto.EmployeeDeviceDto;
import com.subhankar.dto.EmployeeDto;
import com.subhankar.dto.LoginDto;
import com.subhankar.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/zkteco")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/employee/signup")
    public ResponseEntity<EmployeeDto> signUp(@Valid @RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.signUp(employeeDto), HttpStatus.CREATED);
    }

    @PostMapping("/employee/signin")
    public ResponseEntity<EmployeeDto> signIn(@Valid @RequestBody LoginDto loginDto){
        return new ResponseEntity<>(employeeService.signIn(loginDto), HttpStatus.OK);
    }


    @GetMapping("/employees")
    public PageResponseConfig getAllEmployees(@RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return employeeService.getAllEmployees(pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/employee/employeeCode/{empCode}")
    public ResponseEntity<EmployeeDto> getEmployeeByEmployeeCode(@PathVariable(name = "empCode")String empCode){
        return ResponseEntity.ok(employeeService.getEmployeeByEmployeeCode(empCode));
    }
    @GetMapping("/employee/id/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }
    @GetMapping("/employee/name/{name}")
    public PageResponseConfig getEmployeeByName(@PathVariable(name = "name")String name,
                                              @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                              @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                              @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return employeeService.getEmployeeByName(name, pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/employee/email/{email}")
    public PageResponseConfig getEmployeeByEmail(@PathVariable(name = "email") String email,
                                                 @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                                 @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                                 @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return employeeService.getEmployeeByEmail(email, pageNo, pageSize, sortBy, sortDir);
    }


    @GetMapping("/employee/{keyword}")
    public PageResponseConfig getEmployeeByAnyInput(@PathVariable(name = "keyword")String keyword,
                                               @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                               @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                               @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return employeeService.getEmployeeByAnyInput(keyword, pageNo, pageSize, sortBy, sortDir);
    }

    @PutMapping("/employee/id/{id}")
    public ResponseEntity<EmployeeDto> updateEmployeeById(@PathVariable(name = "id")long id,@Valid @RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.updateEmployeeById(id,employeeDto), HttpStatus.OK);
    }
    @PutMapping("/employee/ecode/{empCode}")
    public ResponseEntity<EmployeeDto> updateEmployeeByCode(@PathVariable(name = "empCode")String empCode,@Valid @RequestBody EmployeeDto employeeDto){
        return new ResponseEntity<>(employeeService.updateEmployeeByCode(empCode,employeeDto), HttpStatus.OK);
    }
    @DeleteMapping("/employee/id/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable(name = "id")long id){
        return new ResponseEntity<>(employeeService.deleteEmployeeById(id), HttpStatus.OK);
    }
    @DeleteMapping("/employee/ecode/{empCode}")
    public ResponseEntity<String> deleteEmployeeBySerialNum(@PathVariable(name = "empCode")String empCode){
        return new ResponseEntity<>(employeeService.deleteEmployeeByCode(empCode), HttpStatus.OK);
    }

    @GetMapping("/employee/{id}/devices")
    public EmployeeDeviceDto getAllDevicesForAnEmployee(@PathVariable(name = "id")String employeeCode){
        return employeeService.getAllDevicesForAnEmployee(employeeCode);

    }

}
