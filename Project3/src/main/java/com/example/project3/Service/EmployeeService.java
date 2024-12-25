package com.example.project3.Service;

import com.example.project3.DTOin.CustomerDTOin;
import com.example.project3.DTOin.EmplyeeDTOIn;
import com.example.project3.DTOout.EmployeeDTOout;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    
    private final EmployeeRepository employeeRepository;
    private final AuthRepository authRepository;
    
    
    

    public EmployeeDTOout getEmployeeDTOOutById(Integer id) {
        Employee employee = employeeRepository.findEmployeeById(id);
        if (employee == null) {
            throw new RuntimeException("Employee not found");
        }
        EmployeeDTOout employeeDTOout = new EmployeeDTOout();
        employeeDTOout.setPosition(employee.getPosition());
        employeeDTOout.setSalary(employee.getSalary());
        employeeDTOout.setUsername(employee.getUser().getUsername());
        employeeDTOout.setEmail(employee.getUser().getEmail());
        employeeDTOout.setName(employee.getUser().getName());
        return employeeDTOout;
    }
    
    
    
    public void deleteEmployeeById(Integer id){
        Employee employee = employeeRepository.findEmployeeById(id);
        User user = authRepository.findUserById(employee.getUser().getId());
        if (user == null || employee == null){
            throw new RuntimeException("Employee not found");
            
        }
        employeeRepository.delete(employee);
        authRepository.delete(user);
    }

    public void updateEmployee(Integer id ,EmplyeeDTOIn emplyeeDTOIn) {
        Employee employee = employeeRepository.findEmployeeById(id);
        employee.setPosition(employee.getPosition());
        employee.setSalary(employee.getSalary());

        User user = new User();
        user.setUsername(emplyeeDTOIn.getUsername());
        String hashPassword = new BCryptPasswordEncoder().encode(emplyeeDTOIn.getPassword());
        user.setPassword(hashPassword);
        user.setEmail(emplyeeDTOIn.getEmail());
        user.setName(emplyeeDTOIn.getName());

        employee.setUser(user);
        authRepository.save(user);
        employeeRepository.save(employee);
    }
   
}
