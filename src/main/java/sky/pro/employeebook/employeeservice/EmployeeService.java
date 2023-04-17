package sky.pro.employeebook.employeeservice;

import org.springframework.stereotype.Service;
import sky.pro.employeebook.exceptions.NotFindException;
import sky.pro.employeebook.models.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    public List<Employee> findEmployeeAllFromDepartment() {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getDepartment))
                .collect(Collectors.toList());
    }


}
