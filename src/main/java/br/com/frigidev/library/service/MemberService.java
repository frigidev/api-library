package br.com.frigidev.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frigidev.library.entity.Member;
import br.com.frigidev.library.repository.IMemberRepository;

@Service
public class MemberService {
	@Autowired
	private IMemberRepository memberRepository;
	
	public Member saveMember(Member member) {
		return memberRepository.save(member);
	}
	
	public List<Member> getAllMembers() {
		List<Member> members = memberRepository.findAll();
		if(members != null)
			return members;
	
		return null;
	}
	
	public Member getMemberById(Integer id) {
		Member member = memberRepository.findById(id).orElse(null);
		if(member != null)
			return member;
		
		return null;
	}
	
	public boolean deleteMember(Integer id) {
		boolean exists = memberRepository.findById(id).isPresent();
		if(exists) {
			memberRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	public boolean deleteAllMembers() {
		List<Member> members = memberRepository.findAll();
		if(members != null) {
			memberRepository.deleteAll(members);
			return true;
		}
				
		return false;
	}
	
	public Member updateMember(Member member, Integer id) {
		Member memberUpdated = memberRepository.findById(id).orElse(null);
		if(member == memberUpdated) {
			return member;
		}else if (memberUpdated == null){
			return null;
		}else {
			memberUpdated.setName(member.getName());
			memberUpdated.setMemberLoans(member.getMemberLoans());
			memberRepository.save(memberUpdated);
			return memberUpdated;
		}
	}
}