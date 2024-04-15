package com.example.demo.prefer;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.io.BufferedReader;
import java.io.IOException;

public class PreferDownload {

    public static String getPrefer() throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B410001/DS00000126/getDS00000126"); /*URL*/
        
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=1Oo0omv4bVoztr5VGKOWz2QJYFpy2gmu8Kq8DbYg%2F%2B7gX2EfMQBU2jTu6MpQfsbJoqxzTTyyyDdSfpVZ0FIPlA%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        //urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        
        URL url = new URL(urlBuilder.toString());

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");

        System.out.println("Response code : " + conn.getResponseCode());

        BufferedReader rd;

        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader( new InputStreamReader(conn.getInputStream(),"UTF-8"));
        } else {
            rd = new BufferedReader( new InputStreamReader(conn.getErrorStream(),"UTF-8"));
        }

        StringBuilder sb = new StringBuilder();
        String line;

        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();

        System.out.println(new String(sb.toString().getBytes("EUC-KR"), "UTF-8"));

        return sb.toString();
    }
}


