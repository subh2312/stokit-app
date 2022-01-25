package com.subhankar.service;

import com.subhankar.config.PageResponseConfig;
import com.subhankar.dao.DeviceDao;
import com.subhankar.dto.DeviceDto;
import com.subhankar.dto.DeviceEmployeeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    private DeviceDao deviceDao;
    @Override
    public DeviceDto addNewDevice(DeviceDto deviceDto) {
        return deviceDao.addNewDevice(deviceDto);
    }

    @Override
    public PageResponseConfig getAllDevices(int pageNo,int pageSize,String sortBy,String sortDir) {
        return deviceDao.getAllDevices(pageNo, pageSize, sortBy, sortDir);
    }

    @Override
    public DeviceDto getDeviceBySerialNumber(String serialNum) {
        return deviceDao.getDeviceBySerialNumber(serialNum);
    }

    @Override
    public DeviceDto getDeviceById(long id) {
        return deviceDao.getDeviceById(id);
    }

    @Override
    public PageResponseConfig getDeviceByIssuedDate(String issuedOn, int pageNo, int pageSize, String sortBy, String sortDir) {
        return deviceDao.getDeviceByIssuedDate(issuedOn, pageNo, pageSize, sortBy, sortDir);
    }

    @Override
    public PageResponseConfig getDeviceByReturnedDate(String returnedOn,int pageNo,int pageSize,String sortBy,String sortDir) {
        return deviceDao.getDeviceByReturnedDate(returnedOn,pageNo, pageSize, sortBy, sortDir);
    }

    @Override
    public PageResponseConfig getDeviceByStatus(String status,int pageNo,int pageSize,String sortBy,String sortDir) {
        return deviceDao.getDeviceByStatus(status,pageNo, pageSize, sortBy, sortDir);
    }

    @Override
    public PageResponseConfig getDeviceByDescription(String description,int pageNo,int pageSize,String sortBy,String sortDir) {
        return deviceDao.getDeviceByDescription(description,pageNo, pageSize, sortBy, sortDir);
    }

    @Override
    public PageResponseConfig getDeviceByAnyInput(String keyword,int pageNo,int pageSize,String sortBy,String sortDir) {
        return deviceDao.getDeviceByAnyInput(keyword,pageNo, pageSize, sortBy, sortDir);
    }

    @Override
    public DeviceDto tagEmployeeToDevice(DeviceEmployeeDto deviceEmployeeDto) {
        return deviceDao.tagEmployeeToDevice(deviceEmployeeDto);
    }

    @Override
    public DeviceDto updateDeviceById(long id, DeviceDto deviceDto) {
        return deviceDao.updateDeviceById(id,deviceDto);
    }

    @Override
    public DeviceDto updateDeviceBySerialNumber(String serialNum, DeviceDto deviceDto) {
        return deviceDao.updateDeviceBySerialNumber(serialNum,deviceDto);
    }

    @Override
    public String deleteDeviceById(long id) {
        return deviceDao.deleteDeviceById(id);
    }

    @Override
    public String deleteDeviceBySerialNum(String serialNum) {
        return deviceDao.deleteDeviceBySerialNum(serialNum);
    }

    @Override
    public PageResponseConfig getDeviceByName(String name,int pageNo,int pageSize,String sortBy,String sortDir) {
        return deviceDao.getDeviceByName(name,pageNo, pageSize, sortBy, sortDir);
    }

}
