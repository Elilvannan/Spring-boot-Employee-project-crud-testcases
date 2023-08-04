package com.Employee.employee.Controller;
import com.Employee.employee.Model.ProjectModel;
import com.Employee.employee.Service.Impl.EmployeeServiceImpl;
import com.Employee.employee.Service.Impl.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/project")
public class ProjectController {
    @Autowired
    ProjectService projectService;
    @PostMapping("/save")
    public ProjectModel add(@RequestBody ProjectModel project){
        return projectService.save(project);
    }

    @GetMapping("/get")
    public ResponseEntity<List<ProjectModel>> getall(){
        List<ProjectModel> projectModels = projectService.findAll();
        if(projectModels.isEmpty()){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity<>(projectModels, HttpStatus.FOUND);
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<ProjectModel> getById(@PathVariable long id){
        Optional<ProjectModel> projectModel = projectService.findById(id);
        if(projectModel.isPresent()){
            return new ResponseEntity<>(projectModel.get(),HttpStatus.FOUND);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ProjectModel> delete(@PathVariable long id){
         Optional<ProjectModel> projectModel =projectService.findById(id);
         if(projectModel.isPresent()){
             projectService.deleteById(id);
             return new ResponseEntity<>(projectModel.get(),HttpStatus.ACCEPTED);
         }else{
             return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
         }
    }




}
