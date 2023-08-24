package com.example.AMS.controller;

import com.example.AMS.entities.LeaveEntity;
import com.example.AMS.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/leave")
public class LeaveController {

    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @GetMapping
    public List<LeaveEntity> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @GetMapping("/{id}")
    public LeaveEntity getLeaveById(@PathVariable Long id) {
        return leaveService.getLeaveById(id);
    }

    @GetMapping("/student")
    public List<LeaveEntity> getLeavesByStudentId(@RequestParam int studentId)
    {
        return leaveService.getLeavesByStudentId(studentId);
    }
    @GetMapping("/teacher")
    public List<LeaveEntity> getLeavesByUpdatedBy(@RequestParam int updatedBy)
    {
        return leaveService.getLeavesByUpdatedBy(updatedBy);
    }
    @PostMapping
    public LeaveEntity createLeave(@RequestBody LeaveEntity leave) {
        return leaveService.createLeave(leave);
    }

    @PutMapping("/{id}")
    public LeaveEntity updateLeave(@PathVariable Long id, @RequestBody LeaveEntity leave) {

        return leaveService.updateLeave(id, leave);
    }


    @DeleteMapping("/{id}")
    public void deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
    }
}
