package com.employee.emp.system.repository;

import com.employee.emp.system.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> getAll() {
        return jdbcTemplate.query("SELECT * FROM employee", (rs, rowNum) -> {
            Employee e = new Employee();
            e.setId(rs.getInt("id"));
            e.setName(rs.getString("name"));
            e.setDepartment(rs.getString("department"));
            e.setSalary(rs.getDouble("salary"));
            return e;
        });
    }

    public int insert(Employee e) {
        return jdbcTemplate.update(
                "INSERT INTO employee(name, department, salary) VALUES (?, ?, ?)",
                e.getName(), e.getDepartment(), e.getSalary()
        );
    }

    public int update(Employee e) {
        return jdbcTemplate.update(
                "UPDATE employee SET name=?, department=?, salary=? WHERE id=?",
                e.getName(), e.getDepartment(), e.getSalary(), e.getId()
        );
    }

    public int delete(int id) {
        return jdbcTemplate.update("DELETE FROM employee WHERE id=?", id);
    }
}
