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
@Table(name = "icici_trade")
public class ITrade {
	@Id
	public String order_ref;
	@DateTimeFormat(pattern="dd-MMM-yy")
	public Date date;
	public String stock;
	public String action;
	public float quantity;
	public float price;
	public float trade_value;
	public String settlement;
	public String segment;
	public String dpid_clientdpid;
	public String exchange;
	public float STT;
	public float transaction_and_SEBI_turnover_charges;
	public float stamp_duty;
	public float brokerage_service_tax;
	public float brokerage_incl_taxes;
	
}
