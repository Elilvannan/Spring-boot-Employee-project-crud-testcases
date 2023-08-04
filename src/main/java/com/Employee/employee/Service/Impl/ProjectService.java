package com.Employee.employee.Service.Impl;

import com.Employee.employee.Model.ProjectModel;
import com.Employee.employee.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;
    public ProjectModel save(ProjectModel project) {
        return projectRepository.save(project);
    }


    public List<ProjectModel> findAll() {
        return projectRepository.findAll();
    }

    public Optional<ProjectModel> findById(long id) {
        return projectRepository.findById(id);
    }

    public void deleteById(long id) {
        projectRepository.deleteById(id);
    }
}
