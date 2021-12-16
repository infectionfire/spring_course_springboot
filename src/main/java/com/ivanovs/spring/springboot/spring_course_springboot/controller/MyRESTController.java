package com.ivanovs.spring.springboot.spring_course_springboot.controller;


import com.ivanovs.spring.springboot.spring_course_springboot.entity.Employee;
import com.ivanovs.spring.springboot.spring_course_springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//управляет рест запросами и ответами
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees(){

    List<Employee> allEmployees = employeeService.getAllEmployees();
    return allEmployees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id){//с помощью аннтоации передается число в метод и выводится результат
        Employee employee = employeeService.getEmployee(id);


        return employee;//json получается автоматически при помощи спринга и джексон датабайнд
    }

//    //локальная обработка исключений, заменена на глобальную
//    @ExceptionHandler//ловим исключение по неверному ид работника (отсутствующий ид в базе)
//    public ResponseEntity<EmployeeIncorrectData> handleException(NoSuchEmployeeException exception){
//        EmployeeIncorrectData data = new EmployeeIncorrectData();
//        data.setInfo(exception.getMessage());//берем сообщение из параметра конструктора исключения
//        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
//    }
//
//
//    @ExceptionHandler//ловим исключение по вводу символов в ид работника
//    public ResponseEntity<EmployeeIncorrectData> handleException(Exception exception){
//        EmployeeIncorrectData data = new EmployeeIncorrectData();
//        data.setInfo(exception.getMessage());//берем сообщение из параметра конструктора исключения
//        return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
//    }

    @PostMapping("/employees")//пост запрос для добавления
    public Employee addNewEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);

        return employee;//спринг и джексон автоматически конветрируют
    }

    @PutMapping("/employees")//put запрос изменения работника
    public Employee updateNewEmployee(@RequestBody Employee employee){

        employeeService.saveEmployee(employee);
        return employee;
    }
    @DeleteMapping("/employees/{id}")//удаление
    public String deleteEmployee(@PathVariable int id){
        Employee employee = employeeService.getEmployee(id);
               employeeService.deleteEmployee(id);
        return "Employee with id =" + id +" was deleted";

    }

}
