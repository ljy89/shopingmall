package com.green.biz.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.admin.AdminService;
import com.green.biz.dao.AdminDAO;
import com.green.biz.dto.WorkerVO;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
	@Autowired
	private AdminDAO adminDao;
	
	//입력 파라미터 
	//id : 사용자 입력 id, pwd : 사용자가 입력 암호
	
	@Override
	public int workerCheck(String id, String pwd) {
		
		int result = -1;
		
		String pwd_id_db = adminDao.workerCheck(id); // 테이블에 존재하는 관리자 id의 pwd반환
		
		if(pwd_id_db == null) { // 아이디 없음
			result = -1;
		}else {
			if(pwd.equals(pwd_id_db)) {// 입력 pwd와 db의 저장된 pwd가 일치
				result = 1;
			}else {
				result = 0; // pwd 일치하지 않음
			}
		}
		
		return result;
	}

	@Override
	public WorkerVO getEmployee(String id) {
		
		return adminDao.getEmployee(id);
	}

}
