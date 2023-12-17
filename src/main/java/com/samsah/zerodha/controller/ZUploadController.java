package com.samsah.zerodha.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.samsah.zerodha.beans.ResponseMessage;
import com.samsah.zerodha.service.ZDividendService;
import com.samsah.zerodha.service.ZHoldingService;
import com.samsah.zerodha.service.ZTradeService;

@RestController
@RequestMapping("/zerodha")
public class ZUploadController {
	
	@Autowired
	private ZTradeService tradeService;
	
	@Autowired
	private ZDividendService dividendService;
	
	@Autowired
	private ZHoldingService holdingService;
	
	@PostMapping("/trade/upload")
	public ResponseEntity<ResponseMessage> uploadTradeFile(@RequestParam("file") MultipartFile file) {
		tradeService.uploadTradeRecords(file);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(file.getName(), "Success", new Date()));
	}
	
	@PostMapping("/dividend/upload")
	public ResponseEntity<ResponseMessage> uploadDividendFile(@RequestParam("file") MultipartFile file) {
		dividendService.uploadTradeRecords(file);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(file.getName(), "Success", new Date()));
	}

	@PostMapping("/holdings/upload")
	public ResponseEntity<ResponseMessage> uploadHoldingsFile(@RequestParam("file") MultipartFile file) {
		holdingService.uploadHoldingRecords(file);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(file.getName(), "Success", new Date()));
	}
}
