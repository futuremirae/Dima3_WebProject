package com.example.demo.fraud;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class FraudDownload {
    public static String getFraud() throws IOException{
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B410001/cmmrcFraudCase/cmmrcFraudCase");

        //필수 = ServiceKey, numOfRows, pageNo
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=1Oo0omv4bVoztr5VGKOWz2QJYFpy2gmu8Kq8DbYg%2F%2B7gX2EfMQBU2jTu6MpQfsbJoqxzTTyyyDdSfpVZ0FIPlA%3D%3D");
        urlBuilder.append("&" + URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("search1", "UTF-8") + "=" + URLEncoder.encode("미국", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("search2", "UTF-8") + "=" + URLEncoder.encode("이메일", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("search3", "UTF-8") + "=" + URLEncoder.encode("20181107", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));

        URL url = new URL(urlBuilder.toString());
        log.info(url.toString());

        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Connect-type", "application/json");
        System.out.println("Response code : " + conn.getResponseCode());
        
        BufferedReader rd;

        if(conn.getResponseCode() >=200 && conn.getResponseCode() <=300) {
            rd = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader( new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        System.out.println(new String(sb.toString().getBytes("EUC-KR"), "UTF-8"));
        return sb.toString();
    }
}

/*
 * serviceKey   = 인증키
 * type         = 데이터 타입
 * numOfRows    = 한 페이지 결과 수
 * pageNo       = 페이지 번호
 * search1      = 국가명
 * search2      = 뉴스 제목
 * search3      = 뉴스 게시일자
 */
