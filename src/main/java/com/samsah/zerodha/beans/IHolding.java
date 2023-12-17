package com.samsah.zerodha.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "icici_holdings")
public class IHolding {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long holding_id;
	public String stock_name;
	public String stock;
	public String ISIN;
	public float allocated_quantity;
	public float blocked_for_trade;
	public float block_for_margin;
	public float current_market_price;
	public float percentage_change;
	public float market_value;
	
}
