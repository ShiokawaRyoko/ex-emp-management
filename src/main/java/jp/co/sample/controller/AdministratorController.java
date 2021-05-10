package jp.co.sample.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.domain.Administrator;
import jp.co.sample.form.InsertAdministratorForm;
import jp.co.sample.form.LoginForm;
import jp.co.sample.service.AdministratorService;

/**
 * 
 * 管理者登録画面を表示する処理を記述する
 * 
 * @author shiokawaryoko
 */

@Controller
@RequestMapping("/")
public class AdministratorController {
	
	@Autowired
	private AdministratorService administratorService;
		
	@ModelAttribute
	private InsertAdministratorForm	setUpInsertAdministratorForm() {
		return new InsertAdministratorForm();
	}
	
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "administrator/insert";
	}
	
/**
 * 管理者情報を登録するメソッド
 */
	@RequestMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		System.out.println(form);
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "";
	}
	
	@ModelAttribute
	private LoginForm setUpLoginForm() {
		return new LoginForm();
	}
	
	@RequestMapping("/")
	private String toLogin() {
		return "administrator/login";
	}
}
