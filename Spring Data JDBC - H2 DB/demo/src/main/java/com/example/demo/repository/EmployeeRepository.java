package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;

@Repository
public class EmployeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final class EmployeeRowMapper implements RowMapper<Employee> {
        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee employee = new Employee();
            employee.setId(rs.getLong("id"));
            employee.setName(rs.getString("name"));
            employee.setRole(rs.getString("role"));
            return employee;
        }
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employees", new EmployeeRowMapper());
    }

    public Employee findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employees WHERE id = ?", new EmployeeRowMapper(), id);
    }

    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employees (name, role) VALUES (?, ?)",
                employee.getName(), employee.getRole());
    }

    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE employees SET name = ?, role = ? WHERE id = ?",
                employee.getName(), employee.getRole(), employee.getId());
    }

    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM employees WHERE id = ?", id);
    }
}
