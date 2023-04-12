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

    public Employee addEmployee(Employee employee) {   //Добавление сотрудников
        employees.add(employee);
        return employee;
    }
        public Employee findMaxSalary(int department) throws NotFindException{
        return employees.stream()
                .filter(e -> e.getDepartment() == department)// лямбда выражение или анономная функция
                .max(Comparator.comparingInt(Employee::getSalary)) // нахождение максимального значения по зп в отделе
                .orElseThrow(() -> new NotFindException("Сотрудник с максимальной зарплатой не найден"));
    }
    public List<Employee> employeesFindAll(){
        return employees.stream()
                .collect(Collectors.toList());
    }


}
