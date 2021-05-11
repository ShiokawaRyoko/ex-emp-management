package jp.co.sample.service;

/**
 * 従業員情報一覧を全件検索する業務処理を行うメソッドを作成する
 * 
 * @author shiokawaryoko
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Employee;
import jp.co.sample.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全件取得するメソッド
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	/**
	 * 授業員情報詳細を全件検索する業務処理を行うメソッド
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}
}
