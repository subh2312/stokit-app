package com.subhankar.dto;

import com.subhankar.entity.Employee;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class DeviceDto {
    private long id;
    @NotEmpty
    private String serialNumber;
    @NotEmpty
    @Size(min = 4, message = "Name too short. Must be min 4 characters")
    @Pattern(regexp = "^[a-z A-z]*$", message = "Must not contain any special character or 0-9")
    private String name;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-z]*$",message = "Must be only letters without spaces")
    private String status;
    private String issuedDate;
    private String returnDate;
    private String description;
    private Employee employee;

}
