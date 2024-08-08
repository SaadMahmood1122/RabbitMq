package com.futurenostics.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.futurenostics.dto.User;
import com.futurenostics.service.QueueService;

@RestController
@RequestMapping("/api/v1")
public class MessageController {
	@Autowired
	private QueueService queueService;
	
	
	
	@GetMapping(value ="/publish")
	public ResponseEntity<String> publishMsg(@RequestParam String message){
		queueService.sendMessageToQueue(message);
		return ResponseEntity.ok("Message send successfully....");
	}
	
	@PostMapping(value ="/publishJson")
	public ResponseEntity<String> publishJsonMsg(@RequestBody User user){
		queueService.sendMessageToQueue(user);
		return ResponseEntity.ok("Message send successfully....");
	}
	

}
