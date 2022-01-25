package com.subhankar.controller;

import com.subhankar.config.PageResponseConfig;
import com.subhankar.dto.DeviceDto;
import com.subhankar.dto.DeviceEmployeeDto;
import com.subhankar.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/zkteco")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/device")
    public ResponseEntity<DeviceDto> addNewDevice(@Valid @RequestBody DeviceDto deviceDto){
        return new ResponseEntity<>(deviceService.addNewDevice(deviceDto), HttpStatus.CREATED);
    }
    @GetMapping("/devices")
    public PageResponseConfig getAllDevices(@RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                         @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                         @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                         @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return deviceService.getAllDevices(pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/device/serialnumber/{serial}")
    public ResponseEntity<DeviceDto> getDeviceBySerialNumber(@PathVariable(name = "serial")String serialNum){
        return ResponseEntity.ok(deviceService.getDeviceBySerialNumber(serialNum));
    }
    @GetMapping("/device/id/{id}")
    public ResponseEntity<DeviceDto> getDeviceById(@PathVariable(name = "id")long id){
        return ResponseEntity.ok(deviceService.getDeviceById(id));
    }
    @GetMapping("/device/name/{name}")
    public PageResponseConfig getDeviceByName(@PathVariable(name = "name")String name,
                                              @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                              @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                              @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                              @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return deviceService.getDeviceByName(name, pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/device/issueddate/{issuedOn}")
    public PageResponseConfig getDeviceByIssuedDate(@PathVariable(name = "issuedOn") String issuedOn,
                                                 @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                                 @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                                 @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return deviceService.getDeviceByIssuedDate(issuedOn, pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/device/returneddate/{returnedOn}")
    public PageResponseConfig getDeviceByReturnedDate(@PathVariable(name = "returnedOn")String returnedOn,
                                                   @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                                   @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                                   @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                                   @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return deviceService.getDeviceByReturnedDate(returnedOn, pageNo, pageSize, sortBy, sortDir);
    }
    @GetMapping("/device/status/{status}")
    public PageResponseConfig getDeviceByStatus(@PathVariable(name = "status")String status,
                                             @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                             @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                             @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                             @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return deviceService.getDeviceByStatus(status, pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/device/desc/{desc}")
    public PageResponseConfig getDeviceByDescription(@PathVariable(name = "desc")String description,
                                                  @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                                  @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                                  @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                                  @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return deviceService.getDeviceByDescription(description, pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/device/{keyword}")
    public PageResponseConfig getDeviceByAnyInput(@PathVariable(name = "keyword")String keyword,
                                               @RequestParam(value = "pageNo", defaultValue = "0", required = false)int pageNo,
                                               @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize,
                                               @RequestParam(value = "sortBy", defaultValue = "name", required = false)String sortBy,
                                               @RequestParam(value = "sortDir", defaultValue = "ASC", required = false)String sortDir){
        return deviceService.getDeviceByAnyInput(keyword, pageNo, pageSize, sortBy, sortDir);
    }

    @PutMapping("/device/id/{id}")
    public ResponseEntity<DeviceDto> updateDeviceById(@PathVariable(name = "id")long id,@Valid @RequestBody DeviceDto deviceDto){
        return new ResponseEntity<>(deviceService.updateDeviceById(id,deviceDto), HttpStatus.OK);
    }
    @PutMapping("/device/serialnumber/{serial}")
    public ResponseEntity<DeviceDto> updateDeviceById(@PathVariable(name = "serial")String serialNum,@Valid @RequestBody DeviceDto deviceDto){
        return new ResponseEntity<>(deviceService.updateDeviceBySerialNumber(serialNum,deviceDto), HttpStatus.OK);
    }
    @DeleteMapping("/device/id/{id}")
    public ResponseEntity<String> deleteDeviceById(@PathVariable(name = "id")long id){
        return new ResponseEntity<>(deviceService.deleteDeviceById(id), HttpStatus.OK);
    }
    @DeleteMapping("/device/serialnumber/{serial}")
    public ResponseEntity<String> deleteDeviceBySerialNum(@PathVariable(name = "serial")String serialNum){
        return new ResponseEntity<>(deviceService.deleteDeviceBySerialNum(serialNum), HttpStatus.OK);
    }

    @PostMapping("/device/employee/")
    public ResponseEntity<DeviceDto> tagEmployeeToDevice(@RequestBody DeviceEmployeeDto deviceEmployeeDto){
        return new ResponseEntity<>(deviceService.tagEmployeeToDevice(deviceEmployeeDto),HttpStatus.CREATED);
    }

}
