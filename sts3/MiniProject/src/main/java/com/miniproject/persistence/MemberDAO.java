package com.miniproject.persistence;

public interface MemberDAO {

	String getDate();

	// member테이블에 userPoijnt를 update
	int updateUserPoint(String why, String writter) throws Exception;

}
