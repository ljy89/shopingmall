package com.green.biz.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.biz.dto.WorkerVO;

@Repository
public class AdminDAO {
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public String workerCheck(String id) {
		return mybatis.selectOne("AdminDAO.workerCheck",id);
	}
	
	public WorkerVO getEmployee(String id) {
		
		return mybatis.selectOne("AdminDAO.getEmployee",id);
	}
}
