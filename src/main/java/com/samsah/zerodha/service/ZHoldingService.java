package com.samsah.zerodha.service;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.samsah.zerodha.beans.ZHolding;
import com.samsah.zerodha.repository.ZHoldingsRepository;
import com.samsah.zerodha.utils.ProcessCSV;

@Service
public class ZHoldingService {

	@Autowired
	private ProcessCSV processCSV;
	
	private final ZHoldingsRepository holdingsRepository;
	
	@Autowired
	public ZHoldingService(ZHoldingsRepository holdingsRepository) {
		this.holdingsRepository = holdingsRepository;
	}
	
	public void uploadHoldingRecords(MultipartFile file) {
		Iterable<CSVRecord> csvRecords = processCSV.process(file);
		for (CSVRecord csvRecord : csvRecords) {
			ZHolding holding = new ZHolding();
			holding.setSymbol(csvRecord.get("Symbol"));
			holding.setIsin(csvRecord.get("ISIN"));
			holding.setSector(csvRecord.get("Sector"));
			holding.setInstrument_type(csvRecord.get("Instrument Type"));
			holding.setQuantity_available(Float.parseFloat(csvRecord.get("Quantity Available")));
			holding.setQuantity_discrepant(Float.parseFloat(csvRecord.get("Quantity Discrepant")));
			if(csvRecord.get("Quantity Long Term").toString().equals("-")) {
				holding.setQuantity_long_term(0);
			} else {
				holding.setQuantity_long_term(Float.parseFloat(csvRecord.get("Quantity Long Term")));
			}
			holding.setQuantity_pledged_margin(Float.parseFloat(csvRecord.get("Quantity Pledged (Margin)")));
			holding.setQuantity_pledged_loan(Float.parseFloat(csvRecord.get("Quantity Pledged (Loan)")));
			holding.setAverage_price(Float.parseFloat(csvRecord.get("Average Price")));
			holding.setPrevious_closing_price(Float.parseFloat(csvRecord.get("Previous Closing Price")));
			holding.setUnrealized_PL(Float.parseFloat(csvRecord.get("Unrealized P&L")));
			holding.setUnrealize_PL_Percentage(Float.parseFloat(csvRecord.get("Unrealize P&L Pct.")));
			addZHoldingData(holding);
		}
	}
	
	public void addZHoldingData(ZHolding holding) {
		holdingsRepository.save(holding);
	}
}
