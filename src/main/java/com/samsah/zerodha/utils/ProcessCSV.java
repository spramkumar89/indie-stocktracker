package com.samsah.zerodha.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ProcessCSV {
	public Iterable<CSVRecord> process(MultipartFile file) {
		BufferedReader fileReader = null;
		CSVParser csvParser = null;
		Iterable<CSVRecord> csvRecords = null;
		try {
			fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
			csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader());
			
			csvRecords = csvParser.getRecords();
			
			for (CSVRecord csvRecord : csvRecords) {
				System.out.println(csvRecord.toString());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				csvParser.close();
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return csvRecords;
	}
}
