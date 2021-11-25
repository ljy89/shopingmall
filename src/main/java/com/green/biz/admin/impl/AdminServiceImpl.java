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
	
	//�Է� �Ķ���� 
	//id : ����� �Է� id, pwd : ����ڰ� �Է� ��ȣ
	
	@Override
	public int workerCheck(String id, String pwd) {
		
		int result = -1;
		
		String pwd_id_db = adminDao.workerCheck(id); // ���̺� �����ϴ� ������ id�� pwd��ȯ
		
		if(pwd_id_db == null) { // ���̵� ����
			result = -1;
		}else {
			if(pwd.equals(pwd_id_db)) {// �Է� pwd�� db�� ����� pwd�� ��ġ
				result = 1;
			}else {
				result = 0; // pwd ��ġ���� ����
			}
		}
		
		return result;
	}

	@Override
	public WorkerVO getEmployee(String id) {
		
		return adminDao.getEmployee(id);
	}

}
