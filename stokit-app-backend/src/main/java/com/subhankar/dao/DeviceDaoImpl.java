package com.subhankar.dao;

import com.subhankar.config.PageResponseConfig;
import com.subhankar.dto.DeviceDto;
import com.subhankar.dto.DeviceEmployeeDto;
import com.subhankar.entity.Device;
import com.subhankar.entity.Employee;
import com.subhankar.exceptions.ResourceNotFoundException;
import com.subhankar.repository.DeviceRepository;
import com.subhankar.repository.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DeviceDaoImpl implements DeviceDao {
    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DeviceDto addNewDevice(DeviceDto deviceDto) {
        Device device = modelMapper.map(deviceDto,Device.class);
        return modelMapper.map(deviceRepository.save(device),DeviceDto.class);
    }

    @Override
    public PageResponseConfig getAllDevices(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("ASC")){
            sort=Sort.by(Sort.Direction.ASC,sortBy);
        }
        else
        {
            sort=Sort.by(Sort.Direction.DESC,sortBy);
        }
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Device> devices = deviceRepository.findAll(pageable);
        List<Device> deviceList = devices.getContent();
        List<DeviceDto> deviceDtoList= deviceList.stream().map(device -> modelMapper.map(device,DeviceDto.class)).collect(Collectors.toList());
        return new PageResponseConfig(deviceDtoList,devices.getNumber(),
                devices.getSize(),
                devices.getTotalElements(),
                devices.getTotalPages(),
                devices.isLast());
    }

    @Override
    public DeviceDto getDeviceBySerialNumber(String serialNum) {
        try {
            Device device = deviceRepository.findBySerialNumber(serialNum);
            return modelMapper.map(device, DeviceDto.class);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Device not found with serial number "+serialNum);
        }
    }

    @Override
    public DeviceDto getDeviceById(long id) {
        Device device = deviceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Device not found with id "+id));
        return modelMapper.map(device,DeviceDto.class);
    }

    @Override
    public PageResponseConfig getDeviceByIssuedDate(String issuedOn,int pageNo,int pageSize,String sortBy,String sortDir) {
        List<DeviceDto> deviceDtoList=null;
        Page<Device> devices = null;
        try {
            Sort sort = null;
            if(sortDir.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC,sortBy);
            }
            else {
                sort = Sort.by(Sort.Direction.DESC,sortBy);
            }
            Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
            devices = deviceRepository.findByIssuedDate(issuedOn,pageable);
            List<Device> deviceList = devices.getContent();
            deviceDtoList= deviceList.stream().map(device -> modelMapper.map(device,DeviceDto.class)).collect(Collectors.toList());

        }catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Devices were issued on"+issuedOn);
        }finally {
            if (deviceDtoList.size()==0){
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Devices were issued on"+issuedOn);
            }
            else {
                return new PageResponseConfig(deviceDtoList,devices.getNumber(),
                        devices.getSize(),
                        devices.getTotalElements(),
                        devices.getTotalPages(),
                        devices.isLast());
            }
        }

    }

    @Override
    public PageResponseConfig getDeviceByReturnedDate(String returnedOn,int pageNo,int pageSize,String sortBy,String sortDir) {
        List<DeviceDto> deviceDtoList=null;
        Page<Device> devices = null;
        try {
            Sort sort = null;
            if(sortDir.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC,sortBy);
            }
            else {
                sort = Sort.by(Sort.Direction.DESC,sortBy);
            }
            Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
            devices = deviceRepository.findByReturnDate(returnedOn,pageable);
            List<Device> deviceList = devices.getContent();
            deviceDtoList= deviceList.stream().map(device -> modelMapper.map(device,DeviceDto.class)).collect(Collectors.toList());

        }catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Devices were issued on"+returnedOn);
        }finally {
            if (deviceDtoList.size() == 0) {
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "No Devices were issued on" + returnedOn);
            } else {
                return new PageResponseConfig(deviceDtoList, devices.getNumber(),
                        devices.getSize(),
                        devices.getTotalElements(),
                        devices.getTotalPages(),
                        devices.isLast());
            }
        }
    }


    @Override
    public PageResponseConfig getDeviceByStatus(String status,int pageNo,int pageSize,String sortBy,String sortDir) {
            List<DeviceDto> deviceDtoList=null;
            Page<Device> devices = null;
            try {
                Sort sort = null;
                if(sortDir.equals("asc")){
                    sort=Sort.by(Sort.Direction.ASC,sortBy);
                }
                else {
                    sort = Sort.by(Sort.Direction.DESC,sortBy);
                }
                Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
                devices = deviceRepository.findByStatus(status,pageable);
                List<Device> deviceList = devices.getContent();
                deviceDtoList= deviceList.stream().map(device -> modelMapper.map(device,DeviceDto.class)).collect(Collectors.toList());

            }catch (Exception e){
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Devices were issued on"+status);
            }finally {
                if (deviceDtoList.size() == 0) {
                    throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "No Devices were issued on" + status);
                } else {
                    return new PageResponseConfig(deviceDtoList, devices.getNumber(),
                            devices.getSize(),
                            devices.getTotalElements(),
                            devices.getTotalPages(),
                            devices.isLast());
                }
            }
    }

    @Override
    public PageResponseConfig getDeviceByDescription(String description,int pageNo,int pageSize,String sortBy,String sortDir) {
                List<DeviceDto> deviceDtoList=null;
                Page<Device> devices = null;
                try {
                    Sort sort = null;
                    if(sortDir.equals("asc")){
                        sort=Sort.by(Sort.Direction.ASC,sortBy);
                    }
                    else {
                        sort = Sort.by(Sort.Direction.DESC,sortBy);
                    }
                    Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
                    devices = deviceRepository.findByDescription(description,pageable);
                    List<Device> deviceList = devices.getContent();
                    deviceDtoList= deviceList.stream().map(device -> modelMapper.map(device,DeviceDto.class)).collect(Collectors.toList());

                }catch (Exception e){
                    throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Devices were issued on"+description);
                }finally {
                    if (deviceDtoList.size() == 0) {
                        throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "No Devices were issued on" + description);
                    } else {
                        return new PageResponseConfig(deviceDtoList, devices.getNumber(),
                                devices.getSize(),
                                devices.getTotalElements(),
                                devices.getTotalPages(),
                                devices.isLast());
                    }
                }

    }

    @Override
    public PageResponseConfig getDeviceByAnyInput(String keyword,int pageNo,int pageSize,String sortBy,String sortDir) {
        List<DeviceDto> deviceDtoList=null;
        Page<Device> devices = null;
        try {
            Sort sort = null;
            if(sortDir.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC,sortBy);
            }
            else {
                sort = Sort.by(Sort.Direction.DESC,sortBy);
            }
            Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
            devices = deviceRepository.getByAnyKeyword(keyword,pageable);
            List<Device> deviceList = devices.getContent();
            deviceDtoList= deviceList.stream().map(device -> modelMapper.map(device,DeviceDto.class)).collect(Collectors.toList());

        }catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Devices were issued on"+keyword);
        }finally {
            if (deviceDtoList.size() == 0) {
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "No Devices were issued on" + keyword);
            } else {
                return new PageResponseConfig(deviceDtoList, devices.getNumber(),
                        devices.getSize(),
                        devices.getTotalElements(),
                        devices.getTotalPages(),
                        devices.isLast());

            }
        }

    }

    @Override
    public DeviceDto tagEmployeeToDevice(DeviceEmployeeDto deviceEmployeeDto) {
        String deviceSN = deviceEmployeeDto.getSerialNo();
        String employeeCode=deviceEmployeeDto.getEmployeeCode();
        String  issuedDate = deviceEmployeeDto.getAssignedDate();
        String returnDate = deviceEmployeeDto.getReturnDate();

        try {
            Device device = deviceRepository.findBySerialNumber(deviceSN);
            try{
                Employee employee = employeeRepository.findByEmployeeCode(employeeCode);
                device.setEmployee(employee);
            }catch (Exception e){
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Employee not found with employee code "+employeeCode);
            }

            try {
                device.setIssuedDate(new SimpleDateFormat("yyyy-mm-dd").parse(issuedDate));
                if(returnDate==null||returnDate.equals("")){
                    device.setReturnDate(null);
                }
                else {

                    device.setReturnDate(new SimpleDateFormat("yyyy-mm-dd").parse(returnDate));
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
            Device newDevice = deviceRepository.save(device);
            return modelMapper.map(device, DeviceDto.class);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Device not found with serial number "+deviceSN);
        }



    }

    @Override
    public DeviceDto updateDeviceById(long id, DeviceDto deviceDto) {
        Device device = deviceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Device not found with id "+id));
        Device newDevice = modelMapper.map(deviceDto,Device.class);
        device.setSerialNumber(newDevice.getSerialNumber());
        device.setName(newDevice.getName());
        device.setStatus(newDevice.getStatus());
        device.setIssuedDate(newDevice.getIssuedDate());
        device.setReturnDate(newDevice.getReturnDate());
        device.setDescription(newDevice.getDescription());

        return modelMapper.map(deviceRepository.save(device),DeviceDto.class);
    }

    @Override
    public DeviceDto updateDeviceBySerialNumber(String serialNum, DeviceDto deviceDto) {
        try {
            Device device = deviceRepository.findBySerialNumber(serialNum);
            Device newDevice = modelMapper.map(deviceDto,Device.class);
            device.setSerialNumber(newDevice.getSerialNumber());
            device.setName(newDevice.getName());
            device.setStatus(newDevice.getStatus());
            device.setIssuedDate(newDevice.getIssuedDate());
            device.setReturnDate(newDevice.getReturnDate());
            device.setDescription(newDevice.getDescription());

            return modelMapper.map(deviceRepository.save(device),DeviceDto.class);
        }catch (Exception e)
        {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Device not found with serial number "+serialNum);
        }

    }

    @Override
    public String deleteDeviceById(long id) {
        Device device = deviceRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Device not found with id "+id));
        deviceRepository.delete(device);
        return "Device Deleted Successfully";
    }

    @Override
    public String deleteDeviceBySerialNum(String serialNum) {
        try {
            Device device = deviceRepository.findBySerialNumber(serialNum);
            deviceRepository.delete(device);
            return "Device Deleted Successfully";
        }catch (Exception e)
        {
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"Device not found with serial number "+serialNum);
        }


    }

    @Override
    public PageResponseConfig getDeviceByName(String name,int pageNo,int pageSize,String sortBy,String sortDir) {
        List<DeviceDto> deviceDtoList=null;
        Page<Device> devices = null;
        try {
            Sort sort = null;
            if(sortDir.equals("asc")){
                sort=Sort.by(Sort.Direction.ASC,sortBy);
            }
            else {
                sort = Sort.by(Sort.Direction.DESC,sortBy);
            }
            Pageable pageable = PageRequest.of(pageNo, pageSize,sort);
            devices = deviceRepository.findByName(name,pageable);
            List<Device> deviceList = devices.getContent();
            deviceDtoList= deviceList.stream().map(device -> modelMapper.map(device,DeviceDto.class)).collect(Collectors.toList());

        }catch (Exception e){
            throw new ResourceNotFoundException(HttpStatus.NOT_FOUND,"No Devices were issued on"+name);
        }finally {
            if (deviceDtoList.size() == 0) {
                throw new ResourceNotFoundException(HttpStatus.NOT_FOUND, "No Devices were issued on" + name);
            } else {
                return new PageResponseConfig(deviceDtoList, devices.getNumber(),
                        devices.getSize(),
                        devices.getTotalElements(),
                        devices.getTotalPages(),
                        devices.isLast());

            }
        }

    }


}
