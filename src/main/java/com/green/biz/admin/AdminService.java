package com.green.biz.admin;

import com.green.biz.dto.WorkerVO;

public interface AdminService {

	/*
	 * 관리자 id의 존재여부 확인
	 */
	int workerCheck(String id, String pwd);

	WorkerVO getEmployee(String id);

}