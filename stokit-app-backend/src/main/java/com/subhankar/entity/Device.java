package com.subhankar.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "devices")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "serial_number", unique = true)
    private String serialNumber;
    @Column(name = "name")
    private String name;
    @Column(name = "status")
    private String status;
    @Column(name = "issued_date")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    @Temporal(TemporalType.DATE)
    private Date issuedDate;
    @Column(name = "return_date")
    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "Asia/Kolkata")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Column(name = "description")
    private String description;
    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="device_employee", joinColumns = @JoinColumn(name = "device_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id", referencedColumnName = "id")
    )
    private Employee employee;

}
