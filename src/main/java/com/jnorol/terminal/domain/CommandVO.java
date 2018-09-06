package com.jnorol.terminal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "TB_COMMAND")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommandVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COM_SEQ", nullable = false, updatable = false)
	private int seq;
	
	@Column(name = "COM_COMMAND", nullable = false, updatable = false)
	private String command;

	@Column(name = "COM_TEXT", nullable = false, updatable = false, length = 100000)
	private String text;

	@JoinColumn(name = "USER_SEQ")
	@ManyToOne(fetch = FetchType.LAZY)
	private UserVO userVO;

	public void update(CommandVO vo) {
		if(vo.seq != 0) seq = vo.seq;
		if(vo.command != null) command = vo.command;
		if(vo.text != null) text = vo.text;
		if(vo.userVO != null) userVO = vo.userVO;
	}
}
