package com.subhankar.repository;

import com.subhankar.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface DeviceRepository extends JpaRepository<Device,Long> {

    @Query("SELECT d from Device d WHERE d.serialNumber LIKE %?1%")
    Device findBySerialNumber(String serialNum);

    @Query("SELECT d from Device d WHERE DATE_FORMAT(d.issuedDate, '%d-%m-%Y') LIKE %?1%")
    Page<Device> findByIssuedDate(String issuedOn, Pageable pageable);

    @Query("SELECT d from Device d WHERE DATE_FORMAT(d.returnDate, '%d-%m-%Y') LIKE %?1%")
    Page<Device> findByReturnDate(String returnedOn, Pageable pageable);

    @Query("SELECT d from Device d WHERE d.name LIKE %?1%")
    Page<Device> findByName(String name, Pageable pageable);

    @Query("SELECT d from Device d WHERE d.status LIKE %?1%")
    Page<Device> findByStatus(String status, Pageable pageable);

    @Query("SELECT d from Device d WHERE d.description LIKE %?1%")
    Page<Device> findByDescription(String description, Pageable pageable);

    @Query("SELECT d from Device d WHERE " +
            "d.serialNumber LIKE %?1%" +
            "OR d.id LIKE %?1%" +
            "OR d.name LIKE %?1%" +
            "OR d.status LIKE %?1%" +
            "OR d.description LIKE %?1%" +
            "OR DATE_FORMAT(d.returnDate, '%d-%m-%Y') LIKE %?1%" +
            "OR DATE_FORMAT(d.issuedDate, '%d-%m-%Y') like %?1%")
    Page<Device> getByAnyKeyword(String keyword, Pageable pageable);
}
