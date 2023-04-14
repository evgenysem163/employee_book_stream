package sky.pro.employeebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sky.pro.employeebook.employeeservice.EmployeeService;
import sky.pro.employeebook.exceptions.NotFindException;
import sky.pro.employeebook.models.Employee;

import java.util.List;

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

    @GetMapping("/all")
    public List<Employee> findEmployeeFromDepartment(@RequestParam(value = "/departmentId",
            required = false) Integer department) {
        if (department != null) {
            return employeeService.findEmployeeFromDepartment(department);
        } else {
            return employeeService.findEmployeeAllFromDepartment();
        }
    }
}
