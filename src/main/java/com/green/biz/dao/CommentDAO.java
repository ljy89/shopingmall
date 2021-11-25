package com.green.biz.dao;

import java.util.*;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.green.biz.dto.ProductCommentVO;
import com.green.biz.utils.Criteria;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate mybatis;
	
	//상품평 목록 조회
	public List<ProductCommentVO> getCommentList(int pseq) {
		return mybatis.selectList("CommentDAO.getCommentList" , pseq);
	}
	
	public int saveComment(ProductCommentVO vo) {
		return mybatis.insert("CommentDAO.saveComment", vo);
		
		
	}
	
	public int updateComment(ProductCommentVO vo) {
		return mybatis.update("CommentDAO.updateComment", vo);
	}
	
	public int deleteComment(int comment_seq) {
		return mybatis.delete("CommentDAO.deleteComment",comment_seq);
	}
	
	public int countCommentList(int pseq) {
		
		
		return mybatis.selectOne("CommentDAO.countCommentList",pseq);
	}
	
	public List<ProductCommentVO> getCommentListWithPageing(Criteria criteria, int pseq) {
		HashMap<String, Object> map = new HashMap<>();
		
		map.put("criteria", criteria);
		map.put("pseq", pseq);
		
		return mybatis.selectList("CommentDAO.getCommentListWithPageing", map);
	}
}
