package com.example.AMS.service.impl;

import com.example.AMS.entities.AttendanceEntity;
import com.example.AMS.entities.LeaveEntity;
import com.example.AMS.dto.types.AbsentType;
import com.example.AMS.repository.LeaveRepository;
import com.example.AMS.repository.AttendanceRepository;
import com.example.AMS.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class LeaveServiceImpl implements LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;


    @Override
    public List<LeaveEntity> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Override
    public LeaveEntity getLeaveById(Long id) {
        Optional<LeaveEntity> leave = leaveRepository.findById(id);
        return leave.orElse(null);
    }

    @Override
    public LeaveEntity createLeave(LeaveEntity leave) {
        return leaveRepository.save(leave);
    }

    @Override
    public LeaveEntity updateLeave(long id, LeaveEntity leave) {
        if (leaveRepository.existsById(id)) {
            leave.setLeaveId(id);

            // Check if the status is being changed to "Approved"
            if ("Approved".equalsIgnoreCase(leave.getStatus())) {
                //Create a new AttendanceEntity with appropriate values
                AttendanceEntity attendanceEntity = new AttendanceEntity();
                attendanceEntity.setStudentId(leave.getStudentId());
                attendanceEntity.setClassId(leave.getClassId());
                attendanceEntity.setDate(leave.getStartDate()); // Set the date as today
                attendanceEntity.setAbsentType(AbsentType.LEAVE_APPLIED);

                // Fetch the updated LeaveEntity after saving
                LeaveEntity updatedLeave = leaveRepository.save(leave);

                attendanceEntity.setDelegationId(0);

                // Set the leave_id in the AttendanceEntity
                attendanceEntity.setLeaveId(updatedLeave.getLeaveId());

                // Set the teacher_id as the updated_by value
                attendanceEntity.setTeacherId(updatedLeave.getUpdatedBy());
                // Save the AttendanceEntity

                AttendanceEntity attendanceResponse = attendanceRepository.save(attendanceEntity);
            }

            return leaveRepository.save(leave);
        }
        return leaveRepository.save(leave);
    }

    @Override
    public List<LeaveEntity> getLeavesByStudentId(int studentId){
        return leaveRepository.findByStudentId(studentId);
    }

    @Override
    public List<LeaveEntity> getLeavesByUpdatedBy(int updatedBy) {
        return leaveRepository.findByUpdatedBy(updatedBy);
    }

    @Override
    public void deleteLeave(long id) {
        leaveRepository.deleteById(id);
    }
}
