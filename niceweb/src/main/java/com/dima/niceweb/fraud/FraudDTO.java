package com.example.demo.fraud;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FraudDTO {
    private Response response;
}


@Data
@NoArgsConstructor
@AllArgsConstructor
class Response {
    private Header header;
    private Body body;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Header {
    private String resultCode;
    private String resultMsg;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Body {
    private String totalCnt;
    private String pageNo;
    private ItemList itemList;
    private String numOfRows;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ItemList {
    private Item[] item;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Item {
    private String bdtCntnt;
    private String dataType;
    private String ovrofInfo;
    private String dmgeAmt;
    private String fraudType;
    private String othbcDt;
    private String bbstxSn;
    private String titl;
    private String natn;
    private String regn;
}