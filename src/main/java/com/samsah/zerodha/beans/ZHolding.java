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
@Table(name = "zerodha_holdings")
public class ZHolding {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long holding_id;
	public String symbol;
	public String isin;
	public String sector;
	public String instrument_type;
	public float quantity_available;
	public float quantity_discrepant;
	public float quantity_long_term;
	public float quantity_pledged_margin;
	public float quantity_pledged_loan;
	public float average_price;
	public float previous_closing_price;
	public float unrealized_PL;
	public float unrealize_PL_Percentage;
	
}
