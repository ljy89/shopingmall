package com.green.biz.comment.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.green.biz.comment.CommentService;
import com.green.biz.dao.CommentDAO;
import com.green.biz.dto.ProductCommentVO;
import com.green.biz.utils.Criteria;
@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDAO cDao;
	
	@Override
	public List<ProductCommentVO> getCommentList(int pseq) {
		
		return cDao.getCommentList(pseq);
	}

	@Override
	public int saveComment(ProductCommentVO vo) {
		return cDao.saveComment(vo);

	}

	@Override
	public int updateComment(ProductCommentVO vo) {
		return cDao.updateComment(vo);

	}

	@Override
	public int deleteComment(int comment_seq) {
		return cDao.deleteComment(comment_seq);

	}

	@Override
	public int countCommentList(int pseq) {
		
		return cDao.countCommentList(pseq);
	}

	@Override
	public List<ProductCommentVO> getCommentListWithPageing(Criteria criteria, int pseq) {
		
		return cDao.getCommentListWithPageing(criteria, pseq);
	}

}
