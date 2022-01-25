package com.subhankar.dto;

import com.subhankar.entity.Device;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class EmployeeDto {
    private long id;
    @NotEmpty
    private String employeeCode;
    @NotEmpty
    @Pattern(regexp = "^[a-z A-z]*$", message = "Must not contain any special character or 0-9")
    private String name;
    @NotEmpty
    @Email(message = "please enter a valid email")
    private String emailId;
    @NotEmpty
    @Size(min = 10, max = 10, message = "should be 10 digits")
    @Pattern(regexp = "^[0-9]*$", message = "should contain only numbers")
    private String contactNumber;
}
