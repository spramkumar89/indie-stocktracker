package com.samsah.zerodha.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "zerodha_trade")
public class ZTrade {
	@Id
	public Long order_id;
	public String symbol;
	public String isin;
	@DateTimeFormat(pattern="dd-MM-yyyy")
	public Date trade_date;
	public String exchange;
	public String segment;
	public String series;
	public String trade_type;
	public String auction;
	public float quantity;
	public float price;
	public String trade_id;
	public String order_execution_time;
}
