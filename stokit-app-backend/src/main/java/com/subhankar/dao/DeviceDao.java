package com.subhankar.dao;

import com.subhankar.config.PageResponseConfig;
import com.subhankar.dto.DeviceDto;
import com.subhankar.dto.DeviceEmployeeDto;

import java.text.ParseException;
import java.util.List;

public interface DeviceDao {
    DeviceDto addNewDevice(DeviceDto deviceDto);

    PageResponseConfig getAllDevices(int pageNo,int pageSize,String sortBy,String sortDir);

    DeviceDto getDeviceBySerialNumber(String serialNum);

    DeviceDto getDeviceById(long id);

    PageResponseConfig getDeviceByIssuedDate(String issuedOn,int pageNo,int pageSize,String sortBy,String sortDir);

    PageResponseConfig getDeviceByReturnedDate(String returnedOn,int pageNo,int pageSize,String sortBy,String sortDir);

    DeviceDto updateDeviceById(long id, DeviceDto deviceDto);

    String deleteDeviceById(long id);

    String deleteDeviceBySerialNum(String serialNum);

    PageResponseConfig getDeviceByName(String name,int pageNo,int pageSize,String sortBy,String sortDir);

    DeviceDto updateDeviceBySerialNumber(String serialNum, DeviceDto deviceDto);

    PageResponseConfig getDeviceByStatus(String status,int pageNo,int pageSize,String sortBy,String sortDir);

    PageResponseConfig getDeviceByDescription(String description,int pageNo,int pageSize,String sortBy,String sortDir);

    PageResponseConfig getDeviceByAnyInput(String keyword,int pageNo,int pageSize,String sortBy,String sortDir);

    DeviceDto tagEmployeeToDevice(DeviceEmployeeDto deviceEmployeeDto);

}
