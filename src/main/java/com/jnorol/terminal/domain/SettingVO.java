package com.jnorol.terminal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TB_SETTING")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class SettingVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SET_SEQ", nullable = false, updatable = false)
	private int seq;
	
	@Column(name = "SET_TITLE", nullable = false)
	private String title;
	
	@Column(name = "SET_INFO", nullable = true)
	private String infoURL;
	
	@Column(name = "SET_IMAGE", nullable = true, length = 1000)
	private String imageURL;
	
	@Column(name = "SET_PROMPT", nullable = true)
	private String prompt;
	
	@Column(name = "SET_TITLE_COLOR", nullable = true)
	private String titleColor;

	@Column(name = "SET_TEXT_COLOR", nullable = true)
	private String textColor;

	@Column(name = "SET_PROMPT_COLOR", nullable = true)
	private String promptColor;
	
	@JoinColumn(name = "USER_SEQ", nullable = false)
	@OneToOne(fetch = FetchType.EAGER)
	private UserVO user;
}
