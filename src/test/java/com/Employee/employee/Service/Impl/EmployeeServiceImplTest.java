package com.Employee.employee.Service.Impl;

import com.Employee.employee.Model.EmployeeModel;
import com.Employee.employee.Repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    @Mock
    EmployeeRepository employeeRepository;

    @InjectMocks
    EmployeeServiceImpl employeeService;
    @Test
    void save() {
        EmployeeModel employeeModel=new EmployeeModel(1,"elil",25,"batticallo","elilvanna","ICT");

        lenient().when(employeeRepository.save(employeeModel)).thenReturn(employeeModel);
        assertEquals(employeeModel,employeeService.save(employeeModel));
    }

    @Test
    void getEmployee() {
        EmployeeModel employeeModel=new EmployeeModel(1,"elil",25,"batticallo","elilvanna","ICT");
        List<EmployeeModel> employeeModels = new ArrayList<>();
        employeeModels.add(employeeModel);

        Mockito.when(employeeRepository.findAll()).thenReturn(employeeModels);
        assertEquals(employeeModels,employeeService.getEmployee());
    }

    @Test
    void getById() {
        EmployeeModel employeeModel=new EmployeeModel(1,"elil",25,"batticallo","elilvanna","ICT");
        long id=1;
        when(employeeRepository.findById(id)).thenReturn(Optional.of(employeeModel));
        assertEquals(employeeModel,employeeService.getById(id).get());
    }
}