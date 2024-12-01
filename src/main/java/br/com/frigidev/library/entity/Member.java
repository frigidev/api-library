package br.com.frigidev.library.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="member")
public class Member {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="member_id")
		private Integer memberId;
		
		@Column(name="name")
		private String name;
		
		@OneToMany(mappedBy="member")
		@JsonManagedReference
		private Set<Loan> memberLoans;
		
		public Integer getMemberId() {
			return memberId;
		}
		
		public String getName() {
			return name;
		}
		
		public Set<Loan> getMemberLoans() {
			return memberLoans;
		}
		
		public void setMemberId(Integer memberId) {
			this.memberId = memberId;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public void setMemberLoans(Set<Loan> memberLoans) {
			this.memberLoans = memberLoans;
		}
	
}
