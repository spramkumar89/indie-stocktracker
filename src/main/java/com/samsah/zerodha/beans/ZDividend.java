package com.samsah.zerodha.beans;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

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
@Table(name = "zerodha_dividend")
public class ZDividend {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	public Long id;
	public String symbol;
	public String ISIN;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	public Date date;
	public float quantity;
	public float dividend_per_share;
	public float net_dividend_amount;
}
