package com.jnorol.terminal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @작성자 : 박형진
 * @작성일 : 2018. 6. 10.
 * @수정일 : 2018. 6. 10.
 * @개요 : 비밀번호 찾기 이메일 인증 기능에서 인증키 Parameter 를 담고있는 VO TB_AUTHENTICATION과 1대1 매핑되는 Value Obejct
 */
@Table(name = "TB_AUTHENTICATION")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AuthenticationVO {
	@Id
	@Column(name = "AUTH_KEY", nullable = false, updatable = false)
	private String key;
	
	@Column(name = "AUTH_EMAIL", nullable = false, updatable = false)
	private String email;
}