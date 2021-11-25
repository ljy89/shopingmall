package com.green.biz.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.QnaVO;

@Repository
public class QnaDAO {

	@Autowired //생성되어져 있는 객체 제공
	private SqlSessionTemplate mybatis;
	
	public List<QnaVO> listQna(String id){
		
		
		return mybatis.selectList("QnaDAO.listQna", id);
	}
	
	public QnaVO getQna(int qseq) {
		return mybatis.selectOne("QnaDAO.getQna",qseq);
	}
	
	public void insertQna(QnaVO vo) {
		
		mybatis.insert("QnaDAO.insertQna", vo);
	}
	
	
}
