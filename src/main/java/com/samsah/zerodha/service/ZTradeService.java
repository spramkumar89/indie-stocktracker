package com.samsah.zerodha.service;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.samsah.zerodha.beans.ZTrade;
import com.samsah.zerodha.repository.ZTradeRepository;
import com.samsah.zerodha.utils.ProcessCSV;

@Service
public class ZTradeService {

	@Autowired
	private ProcessCSV processCSV;
	
	private final ZTradeRepository tradeRepository;
	
	@Autowired
	public ZTradeService(ZTradeRepository tradeRepository) {
		this.tradeRepository = tradeRepository;
	}
	
	public void uploadTradeRecords(MultipartFile file) {
		DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
		
		Iterable<CSVRecord> csvRecords = processCSV.process(file);
		for (CSVRecord csvRecord : csvRecords) {
			ZTrade trade = new ZTrade();
			trade.setOrder_id(Long.parseLong(csvRecord.get("order_id")));
			trade.setSymbol(csvRecord.get("symbol"));
			trade.setIsin(csvRecord.get("isin"));
			try {
				trade.setTrade_date(dateFormatter.parse(csvRecord.get("trade_date"),Locale.ENGLISH));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			trade.setExchange(csvRecord.get("exchange"));
			trade.setSegment(csvRecord.get("segment"));
			trade.setSeries(csvRecord.get("series"));
			trade.setTrade_type(csvRecord.get("trade_type"));
			trade.setAuction(csvRecord.get("auction"));
			trade.setQuantity(Float.parseFloat(csvRecord.get("quantity")));
			trade.setPrice(Float.parseFloat(csvRecord.get("price")));
			trade.setTrade_id(csvRecord.get("trade_id"));
			trade.setOrder_execution_time(csvRecord.get("order_execution_time"));
			addZTradeData(trade);
		}
	}
	
	public void addZTradeData(ZTrade trade) {
		tradeRepository.save(trade);
	}
}
