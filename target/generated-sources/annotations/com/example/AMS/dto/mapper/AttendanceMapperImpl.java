package com.example.AMS.dto.mapper;

import com.example.AMS.dto.request.AttendanceRequest;
import com.example.AMS.dto.request.AttendanceUpdateRequest;
import com.example.AMS.dto.response.AttendanceResponse;
import com.example.AMS.entities.AttendanceEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-24T22:36:50+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
public class AttendanceMapperImpl implements AttendanceMapper {

    @Override
    public AttendanceEntity fromRequestToEntity(AttendanceRequest employeeRequest) {
        if ( employeeRequest == null ) {
            return null;
        }

        AttendanceEntity attendanceEntity = new AttendanceEntity();

        attendanceEntity.setStudentId( employeeRequest.getStudentId() );
        attendanceEntity.setTeacherId( employeeRequest.getTeacherId() );
        attendanceEntity.setDate( employeeRequest.getDate() );
        attendanceEntity.setPresent( employeeRequest.isPresent() );
        attendanceEntity.setClassId( employeeRequest.getClassId() );
        attendanceEntity.setHours( employeeRequest.getHours() );
        attendanceEntity.setDelegationId( employeeRequest.getDelegationId() );
        attendanceEntity.setLeaveId( employeeRequest.getLeaveId() );
        attendanceEntity.setAbsentType( employeeRequest.getAbsentType() );

        return attendanceEntity;
    }

    @Override
    public AttendanceResponse fromEntityToResponse(AttendanceEntity employeeEntity) {
        if ( employeeEntity == null ) {
            return null;
        }

        AttendanceResponse attendanceResponse = new AttendanceResponse();

        attendanceResponse.setId( (int) employeeEntity.getId() );
        attendanceResponse.setStudentId( employeeEntity.getStudentId() );
        attendanceResponse.setTeacherId( employeeEntity.getTeacherId() );
        attendanceResponse.setClassId( employeeEntity.getClassId() );
        attendanceResponse.setDelegationId( employeeEntity.getDelegationId() );
        attendanceResponse.setDate( employeeEntity.getDate() );
        attendanceResponse.setPresent( employeeEntity.isPresent() );
        attendanceResponse.setLeaveId( employeeEntity.getLeaveId() );
        attendanceResponse.setAbsentType( employeeEntity.getAbsentType() );
        attendanceResponse.setHours( employeeEntity.getHours() );

        return attendanceResponse;
    }

    @Override
    public List<AttendanceResponse> fromEntityListToResponse(List<AttendanceEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<AttendanceResponse> list = new ArrayList<AttendanceResponse>( entityList.size() );
        for ( AttendanceEntity attendanceEntity : entityList ) {
            list.add( fromEntityToResponse( attendanceEntity ) );
        }

        return list;
    }

    @Override
    public List<AttendanceEntity> fromRequestListToEntity(List<AttendanceRequest> attendanceRequestList) {
        if ( attendanceRequestList == null ) {
            return null;
        }

        List<AttendanceEntity> list = new ArrayList<AttendanceEntity>( attendanceRequestList.size() );
        for ( AttendanceRequest attendanceRequest : attendanceRequestList ) {
            list.add( fromRequestToEntity( attendanceRequest ) );
        }

        return list;
    }

    @Override
    public AttendanceEntity fromUpdateRequestToEntity(AttendanceUpdateRequest attendanceUpdateRequest) {
        if ( attendanceUpdateRequest == null ) {
            return null;
        }

        AttendanceEntity attendanceEntity = new AttendanceEntity();

        attendanceEntity.setId( attendanceUpdateRequest.getId() );
        attendanceEntity.setStudentId( attendanceUpdateRequest.getStudentId() );
        attendanceEntity.setTeacherId( attendanceUpdateRequest.getTeacherId() );
        attendanceEntity.setDate( attendanceUpdateRequest.getDate() );
        attendanceEntity.setPresent( attendanceUpdateRequest.isPresent() );
        attendanceEntity.setClassId( attendanceUpdateRequest.getClassId() );
        attendanceEntity.setHours( attendanceUpdateRequest.getHours() );
        attendanceEntity.setDelegationId( attendanceUpdateRequest.getDelegationId() );
        attendanceEntity.setLeaveId( attendanceUpdateRequest.getLeaveId() );
        attendanceEntity.setAbsentType( attendanceUpdateRequest.getAbsentType() );

        return attendanceEntity;
    }
}
