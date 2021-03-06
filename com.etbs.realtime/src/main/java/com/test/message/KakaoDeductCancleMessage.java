package com.realtime.message;

import java.util.Date;

import org.json.JSONObject;

import com.realtime.model.HS10Model;
import com.realtime.util.DateUtil;

public class KakaoDeductCancleMessage extends KakaoMessage {
	
	public KakaoDeductCancleMessage(HS10Model model) {
		
		templateCode = "realtime_deduct_cancel_h";
		
		message = model.getMemberInfo().getShopName()+"\n" + 
				"[포인트 차감 취소] \n" + 
				"\n" + 
				"▶ 차감취소 포인트 : "+model.getApplPntString()+"P\n" + 
				"▶ 차감취소 후 잔여 포인트 : "+model.getRemainPntString()+"P\n" + 
				"\n" + 
				"차감취소일시 : "+DateUtil.getDateString(new Date(), "yyyy-MM-dd HH:mm")+"\n" + 
				"\n" + 
				"(카드)사용처 : "+model.getStoreName()+" \n" + 
				"\n" + 
				"▣유의사항\n" + 
				" - 포인트 차감 취소내역은 "+model.getMemberInfo().getShopName()+" (PC&모바일) > 마이페이지 > 차감신청관리에서 확인 가능합니다.\n" + 
				" \n" + 
				"▣[고객센터] "+model.getMemberInfo().getCallCenterNumber()+"\n" + 
				"-상담시간 안내 \n" + 
				" 평일 09:00~18:00 \n" + 
				" 점심 12:00~13:00 \n" + 
				" 토, 일, 공휴일 휴무";
		
		smsMessage = "[포인트차감취소] "+model.getStoreName()+" "+model.getApplPntString()+"P / 잔여 : "+model.getRemainPntString()+"P";
		
		// 수신자번호
		receiverNumber = model.getMemberInfo().getHpNo();
		
		// 버튼추가
		JSONObject btn = new JSONObject();
		
		btn.put("name", "차감내역 조회하기");
		btn.put("type", "WL");
		btn.put("url_mobile", "https://cert.benecafe.co.kr/member/login?&cmpyNo="+model.getMemberInfo().getCmpyNo()+"&lc=https://cert.benecafe.co.kr/mywel/costApplicationListCo?dispCatNo=");
		
		// 버튼정보추가		
		buttons.put("button1", btn);
	}
}
