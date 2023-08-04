package com.Employee.employee.Controller;

import com.Employee.employee.Model.EmployeeModel;
import com.Employee.employee.Service.Impl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    @Autowired
    EmployeeServiceImpl employeeService;
    @GetMapping("/getall")
    public List<EmployeeModel> getAllEmployee(){
        return employeeService.getEmployee();
    }

    @PostMapping("/add")
    public EmployeeModel saveEmployee(@RequestBody EmployeeModel employeeModel){
        return employeeService.save(employeeModel);
    }

    @GetMapping("/getbyid/{id}")
    public ResponseEntity<EmployeeModel> getById(@PathVariable long id){
        Optional<EmployeeModel> employeeModel= employeeService.getById(id);
        if(employeeModel.isPresent()){
            return new ResponseEntity<>(employeeModel.get(),HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getbyid2/{id}")
    public ResponseEntity<EmployeeModel> getById2(@PathVariable long id){
        try {
            Optional<EmployeeModel> employeeModel= employeeService.getById(id);
            return new ResponseEntity<>(employeeModel.get(),HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("upadate/{id}")
    public ResponseEntity<EmployeeModel> update(@PathVariable long id, @RequestBody EmployeeModel employeeModel){


        Optional<EmployeeModel> oldEmployeeModel=employeeService.getById(id);
        if(oldEmployeeModel.isPresent()) {
            oldEmployeeModel.get().setName(employeeModel.getName());
            oldEmployeeModel.get().setDeparment(employeeModel.getDeparment());
            oldEmployeeModel.get().setAge(employeeModel.getAge());
            oldEmployeeModel.get().setLocation(employeeModel.getLocation());
            oldEmployeeModel.get().setEmail(employeeModel.getEmail());
        return new ResponseEntity<>(employeeService.save(oldEmployeeModel.get()),HttpStatus.CREATED);
        }else{
             return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
        
        }
}
