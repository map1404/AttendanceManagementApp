package com.example.AMS.service;



import com.example.AMS.entities.AttendanceEntity;
import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.response.AttendanceResponse;
import com.example.AMS.repository.AttendanceRepository;
import com.example.AMS.service.impl.AttendanceServiceImpl;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// Other necessary imports...

public class AttendanceServiceTest {

    @InjectMocks
    private AttendanceServiceImpl attendanceService;

    @Mock
    private AttendanceRepository attendanceRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testGetBystudentidindaterange() {
        int studentId = 333;
        LocalDate startdate = LocalDate.parse("2023-01-01");
        LocalDate enddate = LocalDate.parse("2023-01-31");

        List<AttendanceEntity> expectedEntityList = new ArrayList<>();
        AttendanceEntity entity1 = new AttendanceEntity(100, studentId, 5002, startdate, true, "X A", 5002, 8,null,null,null);
        AttendanceEntity entity2 = new AttendanceEntity(101, studentId, 5002, enddate, true, "X A", 5002, 8,null,null,null);
        expectedEntityList.add(entity1);
        expectedEntityList.add(entity2);

        Mockito.when(attendanceRepository.existsById((long) studentId)).thenReturn(true);
        Mockito.when(attendanceRepository.getBystudentidindaterangeusingJpql(studentId, startdate, enddate))
                .thenReturn(expectedEntityList);


        List<AttendanceEntity> result = attendanceService.getBystudentidindaterange(studentId, startdate, enddate);
        assertNotNull(result); // Check that the result is not null
        assertEquals(expectedEntityList, result);
    }


    @Test
    public void testSaveAllAttendance(){
        LocalDate now = LocalDate.now();
        List<AttendanceRequest> requestList = new ArrayList<>();
        requestList.add(new AttendanceRequest(1,5001,now,false,"XA",0,8,null,null));
        requestList.add(new AttendanceRequest(2,5001,now,false,"XA",0,8,null,null));


        List<AttendanceEntity> mockEntityList = new ArrayList<>();
        mockEntityList.add(new AttendanceEntity(1,1,5001,now,false,"XA",0,8,null,null,null));
        mockEntityList.add(new AttendanceEntity(2,2,5001,now,false,"XA",0,8,null,null,null));

        when(attendanceRepository.saveAll(anyList())).thenReturn(mockEntityList);
        List<AttendanceResponse> responseList = attendanceService.saveAllAttendance(requestList);


        assertNotNull(responseList);
        assertEquals(2, responseList.size());




    }
}








//    @Test
//    void getAllAttendance() {
//
//        List<AttendanceEntity> attendanceEntities = new ArrayList<>();
//        LocalDate now = LocalDate.now();
//
//        AttendanceEntity attendanceEntity = new AttendanceEntity(1,1,5001,now,false,"X A",8,0);
//
//
//        attendanceEntities.add(attendanceEntity);
//
//        when(attendanceRepository.findAll()).thenReturn(attendanceEntities);
//
//
//        List<AttendanceResponse> result = attendanceService.findAllAttendance();
//
//
//        verify(attendanceRepository).findAll();
//
//        assertNotNull(result);
//    }
//}