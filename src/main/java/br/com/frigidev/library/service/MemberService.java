package br.com.frigidev.library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.frigidev.library.entity.Member;
import br.com.frigidev.library.repository.IMemberRepository;

@Service
public class MemberService {
	@Autowired
	IMemberRepository memberRepository;
	
	//Create Member
	public Member saveMember(Member member) {
		return memberRepository.save(member);
	}
	
	//Get All Members
	public List<Member> getAllMembers() {
		List<Member> members = memberRepository.findAll();
		if(members != null)
			return members;
	
		return null;
	}
	
	//Get Member By Id
	public Member getMemberById(Integer id) {
		Member member = memberRepository.findById(id).orElse(null);
		if(member != null)
			return member;
		
		return null;
	}
	
	//Delete Member By Id
	public boolean deleteMember(Integer id) {
		boolean exists = memberRepository.findById(id).isPresent();
		if(exists) {
			memberRepository.deleteById(id);
			return true;
		}
		return false;
	}
	
	//Delete All Members
	public boolean deleteAllMembers() {
		List<Member> members = memberRepository.findAll();
		if(members != null) {
			memberRepository.deleteAll(members);
			return true;
		}
				
		return false;
	}
	
	//Update Member By Id
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