package com.Employee.employee.Service.Impl;

import com.Employee.employee.Model.EmployeeModel;
import com.Employee.employee.Model.ProjectModel;
import com.Employee.employee.Repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {
    @InjectMocks
    ProjectService projectService;

    @Mock
    ProjectRepository projectRepository;

    @Test
    void save() {
        EmployeeModel employeeModel1= new EmployeeModel(1,"elil",25,"batticaloa","elil@gmail.com","ICT");
        ProjectModel projectModel = new ProjectModel(1,"html",employeeModel1);
        when(projectRepository.save(projectModel)).thenReturn(projectModel);
        assertEquals(projectModel,projectService.save(projectModel));
    }

    @Test
    void findAll() {
        EmployeeModel employeeModel1= new EmployeeModel(1,"elil",25,"batticaloa","elil@gmail.com","ICT");
        ProjectModel projectModel = new ProjectModel(1,"html",employeeModel1);

        List<ProjectModel> projectModels = new ArrayList<>();
        projectModels.add(projectModel);

        when(projectRepository.findAll()).thenReturn(projectModels);
        assertEquals(projectModels,projectService.findAll());
    }

    @Test
    void findById() {
        EmployeeModel employeeModel1= new EmployeeModel(1,"elil",25,"batticaloa","elil@gmail.com","ICT");
        ProjectModel projectModel = new ProjectModel(1,"html",employeeModel1);
        long id =1;
        when(projectRepository.findById(id)).thenReturn(Optional.of(projectModel));
        assertEquals(projectModel,projectService.findById(id).get());

    }

    @Test
    void deleteById() {
        long idToDelete = 1L;

        // Act
        projectService.deleteById(idToDelete);

        // Assert
        verify(projectRepository, times(1)).deleteById(idToDelete);

    }
}