package com.green.biz.admin;

import com.green.biz.dto.WorkerVO;

public interface AdminService {

	//������ id�� ���� ���� Ȯ��
	int workerCheck(String id, String pwd);
	WorkerVO getEmployee(String id);
}
