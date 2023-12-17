package com.samsah.zerodha.beans;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class ResponseMessage {
	public String fileName;
	public String status;
	public Date date;
}
