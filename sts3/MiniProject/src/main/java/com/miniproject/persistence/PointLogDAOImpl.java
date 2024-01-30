package com.miniproject.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.miniproject.domain.PointLog;

@Repository // DAO단임을 알림
public class PointLogDAOImpl implements PointLogDAO {
	private static String ns = "com.miniproject.mappers.pointLogMapper";
	
	@Inject
	private SqlSession ses;
	
	@Override
	public int insertPointLog(PointLog pointLog) throws Exception {
		return ses.insert(ns + ".insertPointLog", pointLog);
	}

}
