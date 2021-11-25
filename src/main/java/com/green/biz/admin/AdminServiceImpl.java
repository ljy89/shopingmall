package com.green.biz.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.dao.AdminDAO;
import com.green.biz.dto.WorkerVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDao;
	
	/*
	 * 입력 파라메터:
	 * 	id - 사용자 입력 id
	 *  pwd - 사용자 입력 암호
	 */
	@Override
	public int workerCheck(String id, String pwd) {
		int result = -1;
		
		String pwd_in_db = adminDao.workerCheck(id);	// 테이블에 존재하는 관리자id의 pwd반환
		
		if (pwd_in_db == null) {
			result = -1;	// id가 존재하지 않음
		} else {
			if (pwd.equals(pwd_in_db)) {  // 입력 pwd와 DB저장 pwd가 일치
				result = 1;
			} else {
				result = 0;
			}
		}
		
		return result;
	}

	@Override
	public WorkerVO getEmployee(String id) {
		
		return adminDao.getEmployee(id);
	}

}
