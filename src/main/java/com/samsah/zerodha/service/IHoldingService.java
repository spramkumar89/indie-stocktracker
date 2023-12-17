package com.samsah.zerodha.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.samsah.zerodha.beans.IHolding;
import com.samsah.zerodha.repository.IHoldingsRepository;
import com.samsah.zerodha.utils.ProcessCSV;

@Service
public class IHoldingService {

	@Autowired
	private ProcessCSV processCSV;
	
	private final IHoldingsRepository holdingsRepository;
	
	@Autowired
	public IHoldingService(IHoldingsRepository holdingsRepository) {
		this.holdingsRepository = holdingsRepository;
	}
	
	public void uploadHoldingRecords(MultipartFile file) {
		Iterable<CSVRecord> csvRecords = processCSV.process(file);
		for (CSVRecord csvRecord : csvRecords) {
			IHolding holding = new IHolding();
			holding.setStock_name(csvRecord.get("Stock Name"));
			holding.setStock(csvRecord.get("Stock"));
			holding.setISIN(csvRecord.get("ISIN"));
			holding.setAllocated_quantity(Float.parseFloat(csvRecord.get("Allocated Quantity")));
			holding.setBlocked_for_trade(Float.parseFloat(csvRecord.get("Blocked for Trade")));
			holding.setBlock_for_margin(Float.parseFloat(csvRecord.get("Block For Margin")));
			holding.setCurrent_market_price(Float.parseFloat(csvRecord.get("Current Market Price")));
			holding.setPercentage_change(Float.parseFloat(csvRecord.get("Percentage Change")));
			holding.setMarket_value(Float.parseFloat(csvRecord.get("Market Value")));
			addIHoldingData(holding);
		}
	}
	
	public void addIHoldingData(IHolding holding) {
		holdingsRepository.save(holding);
	}
}
