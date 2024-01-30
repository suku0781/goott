package com.miniproject.persistence;

import com.miniproject.domain.PointLog;

public interface PointLogDAO {
	// pointLog테이블에 insert
	int insertPointLog(PointLog pointLog) throws Exception;
}
