package com.realtime.service;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.realtime.dao.SMSDao;

@Service
public class SMSService {
	
	private static final Logger log = LoggerFactory.getLogger(SMSService.class);
	
	@Value("#{realtimeProp['sms.send.test']}")
	private Boolean _isTest;
	
	@Value("#{realtimeProp['profile.name']}")
	private String _profile;
	
	public SMSService() {
		
	}
	
	@Autowired
	private SMSDao dao;
	
	public void send(String from, String to, String message) {		
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("from", from);
		map.put("to", to);
		map.put("message", message);
		
		System.out.println(_isTest);
		
		log.debug(String.format("%s >> from : %s, to : %s, message : %s", _profile, map.get("from"), map.get("to"), map.get("message")));
		
		if(_isTest == false) {
			dao.send(map);
		}
		else {
			dao.sendTest(map);
		}
	}
}
