package com.samsah.zerodha.service;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.samsah.zerodha.beans.ITrade;
import com.samsah.zerodha.repository.ITradeRepository;
import com.samsah.zerodha.utils.ProcessCSV;

@Service
public class ITradeService {

	@Autowired
	private ProcessCSV processCSV;
	
	private final ITradeRepository tradeRepository;
	
	@Autowired
	public ITradeService(ITradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}
	
	public void uploadTradeRecords(MultipartFile file) {
		DateFormatter dateFormatter = new DateFormatter("dd-MMM-yy");
		
		Iterable<CSVRecord> csvRecords = processCSV.process(file);
		for (CSVRecord csvRecord : csvRecords) {
			ITrade trade = new ITrade();
			trade.setOrder_ref(csvRecord.get("Order Ref."));
			try {
				trade.setDate(dateFormatter.parse(csvRecord.get("Date"),Locale.ENGLISH));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			trade.setStock(csvRecord.get("Stock"));
			trade.setAction(csvRecord.get("Action"));
			trade.setQuantity(Float.parseFloat(csvRecord.get("Qty")));
			trade.setPrice(Float.parseFloat(csvRecord.get("Price")));
			trade.setTrade_value(Float.parseFloat(csvRecord.get("Trade Value")));
			trade.setSettlement(csvRecord.get("Settlement"));
			trade.setSegment(csvRecord.get("Segment"));
			trade.setDpid_clientdpid(csvRecord.get("DP Id - Client DP Id"));
			trade.setExchange(csvRecord.get("Exchange"));
			trade.setSTT(Float.parseFloat(csvRecord.get("STT")));
			trade.setTransaction_and_SEBI_turnover_charges(Float.parseFloat(csvRecord.get("Transaction and SEBI Turnover charges")));
			trade.setStamp_duty(Float.parseFloat(csvRecord.get("Stamp Duty")));
			trade.setBrokerage_service_tax(Float.parseFloat(csvRecord.get("Brokerage + Service Tax")));
			trade.setBrokerage_incl_taxes(Float.parseFloat(csvRecord.get("Brokerage incl. taxes")));
			addITradeData(trade);
		}
	}
	
	public void addITradeData(ITrade trade) {
		tradeRepository.save(trade);
	}
}
