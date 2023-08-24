package com.example.AMS.dto.request;

import com.example.AMS.dto.types.AbsentType;

import java.io.Serializable;
import java.time.LocalDate;

public class AttendanceUpdateRequest implements Serializable {
    private long id;
    private int studentId;
    private int teacherId;

    private LocalDate Date;
    private boolean IsPresent;
    private String classId;
    private int delegationId;
    private int hours;
    private Long leaveId;
    private AbsentType absentType;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(int delegationId) {
        this.delegationId = delegationId;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public boolean isPresent() {
        return IsPresent;
    }

    public void setPresent(boolean present) {
        IsPresent = present;
    }


    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Long getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Long leaveId) {
        this.leaveId = leaveId;
    }

    public AbsentType getAbsentType() {
        return absentType;
    }

    public void setAbsentType(AbsentType absentType) {
        this.absentType = absentType;
    }
}
