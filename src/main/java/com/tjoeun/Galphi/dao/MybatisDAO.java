package com.tjoeun.Galphi.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.tjoeun.Galphi.vo.AccountVO;
import com.tjoeun.Galphi.vo.BookCommentVO;
import com.tjoeun.Galphi.vo.BookVO;
import com.tjoeun.Galphi.vo.Param;

//	mapper로 사용할 인터페이스
//	이 인터페이스의 풀 패키지 와 이름을 sql 명령이 저장된 xml 파일의 namespace에 정확히 적어야 한다.

public interface MybatisDAO {

	ArrayList<BookVO> selectBestList(HashMap<String, Integer> hmap);

	ArrayList<BookVO> selectDailyList(HashMap<String, Integer> hmap);

	int selectNewCount();
	ArrayList<BookVO> selectNewList(HashMap<String, Integer> hmap);

	int selectCategoryCount(String categoryList);
	ArrayList<BookVO> selectCategoryList(Param param);

	BookVO selectByISBN(int ISBN);

	BookCommentVO selectcommentByIdx(int idx);
	ArrayList<BookCommentVO> selectCommentList(int iSBN);


	int selectCount();
	ArrayList<BookVO> selectList(HashMap<String, Integer> hmap);

	int selectMultiCount(Param param2);
	ArrayList<BookVO> selectMultiList(Param param2);

	int Login(HashMap<String, String> hmap);
	int IdCheck(String input_id);
	String nickname(HashMap<String, String> hmap);

	int nickCheck(String nickname);
	int accountIdCheck(String id);
	void insert(HashMap<String, String> hmap);

	void insertComment(BookCommentVO co);
	void update(Param param);

	void deleteComment(int idx);

	void updateComment(Param param);


	
}
