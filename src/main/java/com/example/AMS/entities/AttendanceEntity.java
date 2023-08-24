package com.example.AMS.entities;

import com.example.AMS.dto.types.AbsentType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "Attendance")

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude
public class AttendanceEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "student_ID")
    private int studentId;
    @Column(name = "teacher_ID")
    private int teacherId;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "isPresent")
    private boolean IsPresent;
    @Column(name = "class_ID")
    private String classId;
    @Column(name = "hours")
    private int hours;
    @Column(name = "delid")
    private int delegationId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Column(name = "leave_id",nullable = true)
    private Long leaveId;

    @Column(name = "absent_type")
    @Enumerated(EnumType.STRING)
    private AbsentType absentType;

    @OneToOne
    @JoinColumn(name = "leave_id",insertable = false, updatable = false, nullable = true)
    private LeaveEntity leave;

    public AttendanceEntity(){

    }
    public AttendanceEntity(long id, int studentId,int teacherId,LocalDate date,boolean IsPresent, String classId,int hours, int delid, Long leaveId, AbsentType absentType, LeaveEntity leave){
        this.id = id;
        this.studentId =studentId;
        this.teacherId =teacherId;
        this.date = date;
        this.IsPresent = IsPresent;
        this.classId = classId;
        this.delegationId = delid;
        this.hours =8;
        this.leaveId = leaveId;
        this.absentType = absentType;
        this.leave = leave;
    }

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isPresent() {
        return IsPresent;
    }

    public void setPresent(boolean present) {
        IsPresent = present;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getDelegationId() {
        return delegationId;
    }

    public void setDelegationId(int delegationId) {
        this.delegationId = delegationId;
    }
    @JsonInclude(JsonInclude.Include.ALWAYS)
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

    public LeaveEntity getLeave() {
        return leave;
    }

    public void setLeave(LeaveEntity leave) {
        this.leave = leave;
    }
}
