package com.Employee.employee.Controller;

import com.Employee.employee.Model.EmployeeModel;
import com.Employee.employee.Service.Impl.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.empty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
    @Mock
    EmployeeServiceImpl employeeService;
    @InjectMocks
    EmployeeController employeeController;


    @Test
    void getAllEmployee() {
        EmployeeModel employeeModel=new EmployeeModel(1,"elil",25,"batticallo","elilvanna","ICT");
        List <EmployeeModel> employeeModels=new ArrayList<>() ;

        employeeModels.add(employeeModel);
        when(employeeService.getEmployee()).thenReturn( employeeModels);
        List<EmployeeModel> employeeModel1 = employeeController.getAllEmployee();
        assertEquals(employeeModels,employeeModel1);
    }

    @Test
    void saveEmployee() {
        EmployeeModel employeeModel=new EmployeeModel(1,"elil",25,"batticallo","elilvanna","ICT");
        when(employeeService.save(employeeModel)).thenReturn(employeeModel);
        EmployeeModel employeeModel1=employeeController.saveEmployee(employeeModel);
        assertEquals(employeeModel,employeeModel1);
    }

    @Test
    void getById() {
        EmployeeModel employeeModel=new EmployeeModel(1,"elil",25,"batticallo","elilvanna","ICT");
        long id=1;
        when(employeeService.getById(id)).thenReturn(Optional.of(employeeModel));
        EmployeeModel employeeModel1 =employeeController.getById(id).getBody();
        assertEquals(employeeModel,employeeModel1);
    }
    @Test
    void getByIdException(){
       long id=1;
        when(employeeService.getById(anyLong())).thenReturn(empty());
        ResponseEntity<EmployeeModel> employeeModel1 =employeeController.getById(anyLong());
        assertEquals(HttpStatus.NOT_FOUND,employeeModel1.getStatusCode());
    }
    @Test
    void getById2() {
        EmployeeModel employeeModel=new EmployeeModel(1,"elil",25,"batticallo","elilvanna","ICT");
        long id=1;
        when(employeeService.getById(id)).thenReturn(Optional.of(employeeModel));
        ResponseEntity<EmployeeModel> responseEntity = employeeController.getById2(id);
        assertEquals(employeeModel,responseEntity.getBody());
    }
    @Test
    void getBuId2Exception(){
        long id=1L;
        when(employeeService.getById(anyLong())).thenReturn(empty());
        ResponseEntity<EmployeeModel> responseEntity =employeeController.getById2(id);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());
    }

    @Test
    void update() {
        EmployeeModel employeeModel=new EmployeeModel(1,"elil",25,"batticallo","elilvanna","ICT");
        EmployeeModel employeeModel2=new EmployeeModel(1,"aj",25,"batticallo","elilvanna","ICT");
        long id=1;
        employeeModel.setEmail(employeeModel2.getName());
        when(employeeService.getById(id)).thenReturn(Optional.of(employeeModel2));
        ResponseEntity<EmployeeModel> employeeModel1 = employeeController.update(employeeModel.getId(),employeeModel);
        assertNotEquals(employeeModel2,employeeModel1.getBody());
    }

    @Test
    void updateException(){
        EmployeeModel employeeModel=new EmployeeModel(2,"elil",25,"batticallo","elilvanna","ICT");

        long id=1;

        when(employeeService.getById(id)).thenReturn(empty());
        ResponseEntity<EmployeeModel> responseEntity = employeeController.update(id,employeeModel);
        assertEquals(HttpStatus.NOT_FOUND,responseEntity.getStatusCode());

    }
}