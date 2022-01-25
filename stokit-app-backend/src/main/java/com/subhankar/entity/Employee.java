package com.subhankar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "employee_code")
    private String employeeCode;
    @Column(name = "name")
    private String name;
    @Column(name = "email_id")
    private String emailId;
    @Column(name = "contact_number")
    private String contactNumber;
    @Column(name = "password")
    private String password;
    @JsonBackReference
    @OneToMany(mappedBy = "employee",cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    List<Device> deviceList;

}
