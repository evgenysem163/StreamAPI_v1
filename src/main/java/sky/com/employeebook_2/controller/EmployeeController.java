package sky.com.employeebook_2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.com.employeebook_2.exception.NotFindException;
import sky.com.employeebook_2.models.Employee;
import sky.com.employeebook_2.service.EmployeeService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName,
                                @RequestParam("salary") int salary, @RequestParam("department")
                                 int department) {
        return employeeService.addEmployee(
                new Employee(firstName, lastName, salary, department));
    }

    @GetMapping("/max-salary")
    public Employee findMaxSalary(@RequestParam("departmentId")
                                  int department) throws NotFindException {
        return employeeService.findMaxSalary(department);
    }

    @GetMapping("/min-salary")
    public Employee findMinSalary(@RequestParam("departmentId")
                                  int department) throws NotFindException {
        return employeeService.findMinSalary(department);
    }
    @GetMapping(value = "/all", params = "departmentId")
    public List<Employee> findEmployeesFromDepartment(@RequestParam("departmentId") int department){
        return employeeService.findEmployeeFromDepartment(department);
    }

    @GetMapping("/all")
    public Map<Integer,List<Employee>> findEmployeeFromDepartment(){
            return employeeService.findEmployeeAllFromDepartment();
        }
    }



