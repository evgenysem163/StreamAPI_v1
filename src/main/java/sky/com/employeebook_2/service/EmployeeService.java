package sky.com.employeebook_2.service;

import org.springframework.stereotype.Service;
import sky.com.employeebook_2.exception.NotFindException;
import sky.com.employeebook_2.models.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public Employee addEmployee(Employee employee) {
        employees.add(employee);
        return employee;
    }

    public Employee findMaxSalary(int department) throws NotFindException {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new NotFindException("Сотрудник с максимальной зарплатой не найден"));
    }

    public Employee findMinSalary(int department) throws NotFindException {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .min(Comparator.comparingInt(Employee::getSalary))
                .orElseThrow(() -> new NotFindException(" Сотрудник с минимальной зарплатой"));
    }

    public List<Employee> findEmployeeFromDepartment(int department) {
        return employees.stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer,List<Employee>> findEmployeeAllFromDepartment() {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }



}

