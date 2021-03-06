package com.realtime.server;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.realtime.enums.AUTO_APPL_HDL_CD;
import com.realtime.message.KakaoDeductCancleMessage;
import com.realtime.message.KakaoDeductMessage;
import com.realtime.message.KakaoMessage;
import com.realtime.message.SMSSender;
import com.realtime.model.HS10Model;
import com.realtime.model.ResultModel;


public class SocketThread extends Thread {

	private static final Logger log = LoggerFactory.getLogger(SocketThread.class);
	
	private String _telegram = null;
	
	private static final String adminSMSNumber = "01099117307";
	
	public SocketThread() {}
	public SocketThread(String telegram) {
		_telegram = telegram;
	}
	
	
	public void run() {
		
		// 전문처리 실행
		//RealTimeServer.THREAD_COUNT++;
		RealTimeServer.addToThreadCount();
			
		// 전문으로 모델을 정의 한다.
		// 전문저장하고, 회원정보도 여기서 저장한다.
		HS10Model model = null;		
		ResultModel result = new ResultModel();
		
		try {			
			
			model = new HS10Model(_telegram);
			
			// 이제 저장된 전문으로 비용처리 시작
			result = model.process();
			
			if(result.isSuccess() == true) {
				// 비용처리/취소 성공
				// 카카오알림톡 전송
				
				if(result.getCode() == AUTO_APPL_HDL_CD.S03 || result.getCode() == AUTO_APPL_HDL_CD.S04) {					
					
					try {
						
						
						// 원 대상 번호						
						log.debug("원 수신자 : >> "+result.getHs10Model().getMemberInfo().getHpNo());
								
						String telNo = result.getHs10Model().getMemberInfo().getHpNo();
						
						result.getHs10Model().getMemberInfo().setHpNo(telNo);
						
						boolean isDev = System.getProperty("spring.profiles.active") == null||"local".equals(System.getProperty("spring.profiles.active"))||"dev".equals(System.getProperty("spring.profiles.active"));
						
						if(isDev) {
							// 개발모드 이면 관리자 전화번호로 셋팅
							result.getHs10Model().getMemberInfo().setHpNo(adminSMSNumber);
						}
						
						
						KakaoMessage kakao = null;
						
						if(result.getCode() == AUTO_APPL_HDL_CD.S03) {
							kakao = new KakaoDeductMessage(result.getHs10Model());
						}
						else if(result.getCode() == AUTO_APPL_HDL_CD.S04) {
							kakao = new KakaoDeductCancleMessage(result.getHs10Model());
						}	
						
						kakao.send();
					}
					catch(Exception ex) {
						// 성공인데 차감/취소 말고 다른게 있다면 알리기
						SMSSender sms = new SMSSender();
						
						// 이도한도 받기					
						sms.setTo(adminSMSNumber);
						sms.setMessage("Real Time KakaoError >> "+ex.getMessage());
						
						sms.send();
					}					
					
				}
				else {
					// 성공인데 차감/취소 말고 다른게 있다면 알리기
					SMSSender sms = new SMSSender();
					
					// 이도한도 받기					
					sms.setTo(adminSMSNumber);
					sms.setMessage("Real Time Unknown >> "+result.getCode());
					
					sms.send();
				}
			}
			else {
				// 실패
				// 실패사유가 오류라면 관리자에게 SMS 발송
				if(result.getCode() == AUTO_APPL_HDL_CD.P03) {
					// 관리자 SMS 발송
					SMSSender sms = new SMSSender();
					
					// 이도한도 받기					
					sms.setTo(adminSMSNumber);
					sms.setMessage("Real Time Error >> "+result.getMessage());
					
					sms.send();
				}
			}
			
		}
		catch (Exception e) {			
			
			result.setCode(AUTO_APPL_HDL_CD.XX);
			result.setMessage("Exception >> "+e.toString());
		}
		
		RealTimeServer.LASTEXEC_DATETIME = new Date(); // 실행된 마지막 시간을 기록
		
		RealTimeServer.subtractFromThreadCount();
		
		RealTimeServer.removeCi(model.getCi());			// ciList에서 작업 완료된 ci  삭제.

		//log.debug("("+RealTimeServer.THREAD_COUNT+") "+result.getMessage());
		log.debug("("+RealTimeServer.THREAD_COUNT+":"+RealTimeServer.getThreadPoolQueueSize()+") "+result.getMessage());
	}
	
}
