package com.miniproject.service.member;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.miniproject.domain.Login;
import com.miniproject.domain.Member;
import com.miniproject.domain.PointLog;
import com.miniproject.domain.Session;
import com.miniproject.persistence.MemberDAO;
import com.miniproject.persistence.PointLogDAO;

@Service
public class MemberServiceImpl implements MemberService {

   @Inject
   MemberDAO mDao;
   
   @Inject
   PointLogDAO pDao;
   
   @Override
   @Transactional(rollbackFor = Exception.class)
   public Member login(Login tmpMember) throws Exception {
      System.out.println("MemberServiceImpl : 로그인 처리하자 "+tmpMember.toString());
      
      // 1) 로그인 한다. (member 테이블에서 userId, userPwd 일치 여부 확인)
      Member loginMember = mDao.login(tmpMember);
      
      // 2) 로그인 성공하면 포인트 부여
      if(loginMember != null) {
         System.out.println(loginMember.toString());
         if(mDao.updateUserPoint("로그인", loginMember.getUserId())==1) {
            // 3) pointlog테이블에 저장
            pDao.insertPointLog(new PointLog(-1, null, "로그인", 5, loginMember.getUserId()));
         }
      }
      
      return loginMember;
   }

@Override
public boolean duplChkUserId() {
	// TODO Auto-generated method stub
	return false;
}

@Override
public boolean remember(Session session) throws Exception {
	boolean result = false;
	System.out.println("mDao.updateSession(session) 결과 값 : " + mDao.updateSession(session));
	if(mDao.updateSession(session) == 1) {
		System.out.println("쿠키 저장됨. -1");
		result = true;
	}
	
	return result;
}

}