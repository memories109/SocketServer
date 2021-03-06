package com.realtime.message;

import org.json.JSONObject;

import com.realtime.model.HS10Model;

public class KakaoDeductMessage extends KakaoMessage {

	public KakaoDeductMessage(HS10Model model) {
		
		templateCode = "realtime_deduct_h_v2";
		
		message = model.getMemberInfo().getShopName()+"\n" + 
				"[포인트 차감]\n" + 
				"\n" + 
				"▶ 차감 포인트 : "+model.getApplPntString()+"P\n" + 
				"▶ 차감 전 가용 포인트 : "+model.getBeforePntString()+"P\n" + 
				"▶ 차감 후 잔여 포인트 : "+model.getRemainPntString()+"P\n" + 
				"\n" + 
				"(카드)사용일시 : "+model.getUseDateTimeString("yyyy-MM-dd HH:mm")+" \n" + 
				"(카드)사용처 : "+model.getStoreName()+"\n" + 
				"(카드)사용금액 : "+model.getUseMoneyString()+"원 \n" + 
				"\n" + 
				"▣안내사항 \n" + 
				"- 본 차감건은 "+model.getMemberInfo().getPointName()+"를 통하여 차감되는 건으로 실제 \n" + 
				"카드결제 건에서는 제외 또는 환급될 수 있습니다.\n" + 
				"\n" + 
				"▣[고객센터] "+model.getMemberInfo().getCallCenterNumber()+"\n" + 
				"-상담시간 안내 \n" + 
				"평일 09:00~18:00 \n" + 
				"점심 12:00~13:00 \n" + 
				"토, 일, 공휴일 휴무";
		
		smsMessage = "[포인트차감] "+model.getMemberInfo().getShopName()+" "+model.getApplPntString()+"P / 잔여 : "+model.getRemainPntString()+"P";
		
		// 수신자번호
		receiverNumber = model.getMemberInfo().getHpNo(); 
		
		
		// 버튼추가
		JSONObject btn = new JSONObject();
		
		btn.put("name", "자세히 보기 / 차감취소");
		btn.put("type", "WL");
		btn.put("url_mobile", "https://cert.benecafe.co.kr/member/login?&cmpyNo="+model.getMemberInfo().getCmpyNo()+"&lc=https://cert.benecafe.co.kr/mywel/costApplicationListCo?dispCatNo=");
		
		// 버튼정보추가		
		buttons.put("button1", btn);
	}
}
