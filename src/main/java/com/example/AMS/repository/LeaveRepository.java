package com.example.AMS.repository;

import com.example.AMS.entities.LeaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaveRepository extends JpaRepository<LeaveEntity, Long>
{
    List<LeaveEntity> findByStudentId(int studentId);
    List<LeaveEntity> findByUpdatedBy(int updatedBy);


    // You can add custom query methods if needed
}
