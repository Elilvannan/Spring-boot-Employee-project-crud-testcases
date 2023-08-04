package com.Employee.employee.Service.Impl;

import com.Employee.employee.Model.EmployeeModel;
import com.Employee.employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl {

    @Autowired
    EmployeeRepository employeeRepository;


    public EmployeeModel save(EmployeeModel employeeModel) {
        return employeeRepository.save(employeeModel);
    }



    public List<EmployeeModel> getEmployee() {
      return employeeRepository.findAll();
    }


    public Optional<EmployeeModel> getById(long id) {
        return employeeRepository.findById(id);
    }


}
