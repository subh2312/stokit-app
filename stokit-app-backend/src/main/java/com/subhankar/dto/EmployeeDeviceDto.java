package com.subhankar.dto;

import com.subhankar.entity.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
//This class is used to retreive all devices for a particular employee
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDeviceDto {
    private long employeeId;
    private String employeeCode;
    private String name;
    private String email;
    private List<Device> devices;
    private int size;


}
