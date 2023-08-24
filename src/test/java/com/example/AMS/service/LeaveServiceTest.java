
package com.example.AMS.service;

import com.example.AMS.entities.LeaveEntity;
import com.example.AMS.dto.types.LeaveType;
import com.example.AMS.repository.LeaveRepository;
import com.example.AMS.service.impl.LeaveServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class LeaveServiceTest {

    @Mock
    private LeaveRepository leaveRepository;

    @InjectMocks
    private LeaveServiceImpl leaveService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetLeavesByStudentId() {
        int studentId = 123;


        LeaveEntity leave1 = new LeaveEntity();
        leave1.setApplicationDate(LocalDate.of(2023, 8, 1));
        leave1.setAttachment("attachment1.pdf");
        leave1.setEndDate(LocalDate.of(2023, 8, 10));
        leave1.setLastUpdated(LocalDate.now());
        leave1.setStartDate(LocalDate.of(2023, 8, 5));
        leave1.setStatus("Pending");
        leave1.setStudentId(studentId);
        leave1.setClassId("A101");
        leave1.setType(LeaveType.SICK_LEAVE);
        leave1.setUpdatedBy(456);
        leave1.setDescription("Sick leave due to fever");

        LeaveEntity leave2 = new LeaveEntity();
        leave2.setApplicationDate(LocalDate.of(2023, 8, 5));
        leave2.setAttachment("attachment2.pdf");
        leave2.setEndDate(LocalDate.of(2023, 8, 15));
        leave2.setLastUpdated(LocalDate.now());
        leave2.setStartDate(LocalDate.of(2023, 8, 7));
        leave2.setStatus("Approved");
        leave2.setType(LeaveType.VACATION_LEAVE);
        leave2.setStudentId(studentId);
        leave2.setClassId("A102");
        leave2.setUpdatedBy(456);
        leave2.setDescription("Vacation leave request");

        List<LeaveEntity> expectedLeaves = new ArrayList<>();
        expectedLeaves.add(leave1);
        expectedLeaves.add(leave2);


        when(leaveRepository.findByStudentId(studentId)).thenReturn(expectedLeaves);


        List<LeaveEntity> actualLeaves = leaveService.getLeavesByStudentId(studentId);


        verify(leaveRepository, times(1)).findByStudentId(studentId);


        assertEquals(expectedLeaves.size(), actualLeaves.size());
        assertEquals(expectedLeaves.get(0), actualLeaves.get(0));
        assertEquals(expectedLeaves.get(1), actualLeaves.get(1));
    }
    @Test
    void testCreateLeave() {
        int studentId = 123;


        LeaveEntity leave1 = new LeaveEntity();
        leave1.setLeaveId(1);
        leave1.setApplicationDate(LocalDate.of(2023, 8, 1));
        leave1.setAttachment("attachment1.pdf");
        leave1.setEndDate(LocalDate.of(2023, 8, 10));
        leave1.setLastUpdated(LocalDate.now());
        leave1.setStartDate(LocalDate.of(2023, 8, 5));
        leave1.setStatus("Pending");
        leave1.setStudentId(studentId);
        leave1.setClassId("A101");
        leave1.setType(LeaveType.SICK_LEAVE);
        leave1.setUpdatedBy(456);
        leave1.setDescription("Sick leave due to fever");
        Mockito.when(leaveRepository.save(Mockito.any(LeaveEntity.class)))
                .thenReturn(leave1);
        LeaveEntity savedleave1 = leaveService.createLeave(leave1);
        assertEquals(savedleave1.getLeaveId(),leave1.getLeaveId());
    }

    @Test
    void testGetLeavesByStudentId_NoLeavesFound() {
        int studentId = 456;


        when(leaveRepository.findByStudentId(studentId)).thenReturn(new ArrayList<>());


        List<LeaveEntity> actualLeaves = leaveService.getLeavesByStudentId(studentId);

        verify(leaveRepository, times(1)).findByStudentId(studentId);

        assertEquals(0, actualLeaves.size());
    }
}

