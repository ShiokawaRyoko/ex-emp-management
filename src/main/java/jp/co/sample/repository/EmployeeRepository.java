package jp.co.sample.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリ(Dao)
 * 
 * @author shiokawaryoko
 *
 */

@Repository
public class EmployeeRepository {
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setID(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hireDate"));
		employee.setMailAddress(rs.getString("mailAddress"));
		employee.setZipCode(rs.getString("zipCode"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependentsCount"));
		return employee;
	};

/**
 * 従業員一覧情報を入社日順(降順)で取得する
 * (従業員が存在しない場合はサイズ0件の従業員一覧を返す)
 */
	public Employee findAll() {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees ORDER BY dependents_count";
		List<Employee> employeeList = template.query(sql, EMPLOYEE_ROW_MAPPER);
		if (employeeList.size() == 0) {
			return null;
		}
		return employeeList.get(0);
	}
/**
 * 主キーから従業員情報を取得する
 */
	public Employee load(Integer id) {
		String sql = "SELECT id,name,image,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count FROM employees WHERE id=:id";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Employee employee = template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
		return employee;
	}
/**
 * 従業員情報を変更する
 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);
		if (employee.getID() == null) {
			String insertSql = "INSERT INTO employees(name,age,gender,hire_date,mail_address,zip_code,address,telephone,salary,characteristics,dependents_count)"
					+ "VALUES(:name,:age,:gender,:hireDate,:mailAddress,:zipCode,address,telephone,salary,characteristics,dependentsCount)";
			template.update(insertSql, param);
		} else {
			String updateSql = "UPDATE employees SET name=:name,age=:age,gender=:gender,hire_date=:hireDate,mail_address=:mailAddress,zip_code=:zipCode,address=:address,telephone=:telephone,salary=:salary,characteristics=:characteristics,dependents_count=:dependentsCount"
					+ "WHERE id=:id";
			template.update(updateSql, param);
		}
	}
}
