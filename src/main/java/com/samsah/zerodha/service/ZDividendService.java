package com.samsah.zerodha.service;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.samsah.zerodha.beans.ZDividend;
import com.samsah.zerodha.repository.ZDividendRepository;
import com.samsah.zerodha.utils.ProcessCSV;

@Service
public class ZDividendService {

	@Autowired
	private ProcessCSV processCSV;
	
	private final ZDividendRepository dividendRepository;
	
	@Autowired
	public ZDividendService(ZDividendRepository dividendRepository) {
		this.dividendRepository = dividendRepository;
	}
	
	public void uploadTradeRecords(MultipartFile file) {
		DateFormatter dateFormatter = new DateFormatter("yyyy-MM-dd");
		
		Iterable<CSVRecord> csvRecords = processCSV.process(file);
		for (CSVRecord csvRecord : csvRecords) {
			ZDividend dividend = new ZDividend();
			dividend.setSymbol(csvRecord.get("Symbol"));
			dividend.setISIN(csvRecord.get("ISIN"));
			try {
				dividend.setDate(dateFormatter.parse(csvRecord.get("Date"),Locale.ENGLISH));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			dividend.setQuantity(Float.parseFloat(csvRecord.get("Quantity")));
			dividend.setDividend_per_share(Float.parseFloat(csvRecord.get("Dividend Per Share")));
			dividend.setNet_dividend_amount(Float.parseFloat(csvRecord.get("Net Dividend Amount")));
			addZTradeData(dividend);
		}
	}
	
	public void addZTradeData(ZDividend dividend) {
		dividendRepository.save(dividend);
	}
}
