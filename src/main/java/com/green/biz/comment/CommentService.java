package com.green.biz.comment;

import java.util.List;

import com.green.biz.dto.ProductCommentVO;
import com.green.biz.utils.Criteria;

public interface CommentService {
	
	
	public List<ProductCommentVO> getCommentList(int pseq);
	
	public int saveComment(ProductCommentVO vo);
	
	public int updateComment(ProductCommentVO vo);
	
	public int deleteComment(int comment_seq);
	
	public int countCommentList(int pseq);
	
	public List<ProductCommentVO> getCommentListWithPageing(Criteria criteria, int pseq);
}
