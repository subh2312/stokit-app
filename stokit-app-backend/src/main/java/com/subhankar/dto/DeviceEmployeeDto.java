package com.subhankar.dto;

import lombok.Data;
//This class is used to tag or detag an employee to a device
@Data
public class DeviceEmployeeDto {
    private String serialNo;
    private String employeeCode;
    private String status;
    private String assignedDate;
    private String returnDate;
}
