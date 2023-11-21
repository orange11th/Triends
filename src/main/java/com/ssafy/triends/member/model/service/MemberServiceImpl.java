package com.ssafy.triends.member.model.service;

import org.springframework.stereotype.Service;

import com.ssafy.triends.member.model.Member;
import com.ssafy.triends.member.model.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService {
	private final MemberMapper memberMapper;
	public MemberServiceImpl(MemberMapper memberMapper) {
		this.memberMapper = memberMapper;
	}
	@Override
	public Member login(String userId, String userPass) {
		return memberMapper.select(userId, userPass);
	}
	@Override
	public void regist(Member member) {
		memberMapper.insert(member);
	}
	@Override
	public Member userInfo(String userId) {
		return memberMapper.selectById(userId);
	}
	@Override
	public void modify(Member member) {
		memberMapper.update(member);
	}
}
