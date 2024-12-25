package com.example.project3.Controller;

import com.example.project3.DTOin.EmplyeeDTOIn;
import com.example.project3.Model.User;
import com.example.project3.Service.AuthService;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class EmployeeController {


    private final AuthService authService;
    private final EmployeeService employeeService;

    @GetMapping("/myEmployee")
    public ResponseEntity getEmployeeDTOOutById(@AuthenticationPrincipal User user){
        return ResponseEntity.ok().body(employeeService.getEmployeeDTOOutById(user.getId()));
    }

    @DeleteMapping("/deleteEmployee")
    public ResponseEntity deleteEmployeeById(@AuthenticationPrincipal User user){
        employeeService.deleteEmployeeById(user.getId());
        return ResponseEntity.ok().body("Employee deleted successfully");
    }
    @PutMapping("updateEmployee")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user ,@RequestBody@Valid EmplyeeDTOIn emplyeeDTOIn){
        employeeService.updateEmployee(user.getId(),emplyeeDTOIn);
        return ResponseEntity.ok().body("Employee updated successfully");
    }


}
