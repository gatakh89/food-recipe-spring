//package com.example.mypageservice.user.news;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//
//import com.example.opendata.dustseoul.DustSeoulRepository;
//import com.google.gson.Gson;
//
//@Service
//public class DustSeoulService {
//
//	private DustSeoulRepository repo;
//
//	@Autowired
//	private DustSeoulService(DustSeoulRepository repo) {
//		this.repo = repo;
//	}
//
//	public void searchPlace(String keyword) {
//		try {
//			keyword = URLEncoder.encode(keyword, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			throw new RuntimeException("encoding fail!", e);
//		}
//
//		String apiURL = "https://openapi.naver.com/v1/search/local.json?query=" + keyword
//				+ "&display=20&start=1&sort=random"; // json 결과
//		// String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text;
//		// // xml 결과
//
//		Map<String, String> requestHeaders = new HashMap<>();
//		requestHeaders.put("X-Naver-Client-Id", "자신의 키값입력");
//		requestHeaders.put("X-Naver-Client-Secret", "Client Secret 값 입력");
//		String responseBody = get(apiURL, requestHeaders);
//
//		System.out.println("네이버에서 받은 결과 = " + responseBody);
//		System.out.println("-----------------------------------------");
//
//		return convertData(responseBody);
//	}
//    private String get(String apiUrl, Map<String, String> requestHeaders){
//        HttpURLConnection con = connect(apiUrl);
//        try {
//            con.setRequestMethod("GET");
//            for(Map.Entry<String, String> header :requestHeaders.entrySet()) {
//                con.setRequestProperty(header.getKey(), header.getValue());
//            }
// 
//            int responseCode = con.getResponseCode();
//            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
//                return readBody(con.getInputStream());
//            } else { // 에러 발생
//                return readBody(con.getErrorStream());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException("API 요청과 응답 실패", e);
//        } finally {
//            con.disconnect();
//        }
//    }
//
//
//
//}