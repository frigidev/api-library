package br.com.frigidev.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.frigidev.library.entity.Member;
import br.com.frigidev.library.service.MemberService;

@RestController
@RequestMapping("/api/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@PostMapping
	public ResponseEntity<Member> createMember(@RequestBody Member member) {
		Member newMember = memberService.saveMember(member);
		if(newMember != null)
			return new ResponseEntity<>(newMember, HttpStatus.CREATED);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping
	public ResponseEntity<List<Member>> getAllMembers() {
		List<Member> members = memberService.getAllMembers();
		if(members != null) 
			return new ResponseEntity<>(members, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Member> getMemberById(@PathVariable Integer id) {
		Member member = memberService.getMemberById(id);
		if(member != null) 
			return new ResponseEntity<>(member, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Boolean> deleteMemberById(@PathVariable Integer id) {
		Boolean deleted = memberService.deleteMember(id);
		if(deleted) 
			return new ResponseEntity<>(true, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping
	public ResponseEntity<Boolean> deleteAllMembers() {
		Boolean deleted = memberService.deleteAllMembers();
		if(deleted)
			return new ResponseEntity<>(true, HttpStatus.OK);
		return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Member> updateMember(@RequestBody Member member, @PathVariable Integer id) {
		Member memberUpdated = memberService.updateMember(member, id);
		if(memberUpdated != null) 
			return new ResponseEntity<>(memberUpdated, HttpStatus.OK);
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
}
