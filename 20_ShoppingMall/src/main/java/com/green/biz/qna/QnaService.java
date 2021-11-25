package com.green.biz.qna;

import java.util.*;
import com.green.biz.dto.QnaVO;

public interface QnaService {

	List<QnaVO> listQna(String id);
	QnaVO getQna(int qseq);
	
	void insertQna(QnaVO vo);
}
