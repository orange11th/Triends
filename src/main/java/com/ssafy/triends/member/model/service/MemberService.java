package com.ssafy.triends.member.model.service;

import com.ssafy.triends.member.model.Member;

public interface MemberService {

	Member login(String userId, String userPass);

	void regist(Member member);

}
