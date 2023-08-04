package com.Employee.employee.Controller;

import com.Employee.employee.Model.EmployeeModel;
import com.Employee.employee.Model.ProjectModel;
import com.Employee.employee.Service.Impl.ProjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectControllerTest {

    @Mock
    ProjectService projectService;
    @InjectMocks
    ProjectController projectController;
    @Test
    void add() {
        EmployeeModel employeeModel1= new EmployeeModel(1,"elil",25,"batticaloa","elil@gmail.com","ICT");
        ProjectModel projectModel1 = new ProjectModel(1,"html",employeeModel1);
        Mockito.when(projectService.save(projectModel1)).thenReturn(projectModel1);

        assertEquals(projectModel1,projectController.add(projectModel1));

    }

    @Test
    void getall() {
        EmployeeModel employeeModel1= new EmployeeModel(1,"elil",25,"batticaloa","elil@gmail.com","ICT");
        ProjectModel projectModel = new ProjectModel(1,"html",employeeModel1);
        List<ProjectModel> projectModels= new ArrayList<>();
        projectModels.add(projectModel);
        lenient().when(projectService.findAll()).thenReturn(projectModels);
        assertEquals(projectModels,projectController.getall().getBody());
    }

    @Test
    void getallException(){
         List<ProjectModel> projectModels= new ArrayList<>();
        lenient().when(projectService.findAll()).thenReturn(projectModels);
        assertEquals(HttpStatus.NOT_FOUND,projectController.getall().getStatusCode());
    }

    @Test
    void getById() {
        EmployeeModel employeeModel1= new EmployeeModel(1,"elil",25,"batticaloa","elil@gmail.com","ICT");
        ProjectModel projectModel = new ProjectModel(1,"html",employeeModel1);
        long id=1;
        lenient().when(projectService.findById(id)).thenReturn(Optional.of(projectModel));
        assertEquals(projectModel,projectController.getById(id).getBody());
    }

    @Test
    void getByIdException(){
        EmployeeModel employeeModel1= new EmployeeModel(1,"elil",25,"batticaloa","elil@gmail.com","ICT");
        ProjectModel projectModel = new ProjectModel(1,"html",employeeModel1);
        long id=1;
        lenient().when(projectService.findById(id)).thenReturn(Optional.of(projectModel));
        assertEquals(HttpStatus.NOT_FOUND,projectController.getById(2).getStatusCode());

    }

    @Test
    void delete() {
        EmployeeModel employeeModel1= new EmployeeModel(1,"elil",25,"batticaloa","elil@gmail.com","ICT");
        ProjectModel projectModel = new ProjectModel(1,"html",employeeModel1);
        long id=1;
        when(projectService.findById(id)).thenReturn(Optional.of(projectModel));
        assertEquals(HttpStatus.ACCEPTED,projectController.delete(id).getStatusCode());
        assertEquals(projectModel,projectController.delete(id).getBody());
    }

    @Test
    void deleteException(){
        long id=1;
        when(projectService.findById(id)).thenReturn(Optional.empty());
        assertEquals(HttpStatus.NOT_FOUND,projectController.delete(id).getStatusCode());
        assertEquals(null,projectController.delete(id).getBody());

    }
}