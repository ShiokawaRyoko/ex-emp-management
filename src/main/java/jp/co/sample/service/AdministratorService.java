package jp.co.sample.service;

/**
 * 管理者情報を操作するサービス
 * 
 *  @author shiokawaryoko
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jp.co.sample.domain.Administrator;
import jp.co.sample.repository.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {
	@Autowired
	private AdministratorRepository administratorRepository;

	/**
	 * 管理者情報を挿入するメソッド
	 */
	public void insert(Administrator administrator) {
		administratorRepository.insert(administrator);
	}
	/**
	 * ログイン処理をする
	 */
	public Administrator login(String mailAddress,String password) {
		return administratorRepository.findByMailAddressAndPassWord(mailAddress,password);
	}
}
