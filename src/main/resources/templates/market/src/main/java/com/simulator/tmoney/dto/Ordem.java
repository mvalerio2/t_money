package com.simulator.tmoney.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;

import lombok.Data;

@Data
public class Ordem {

	private Integer carteiraId;
	
	private Double quantidade;
    
	private Double total;
}
