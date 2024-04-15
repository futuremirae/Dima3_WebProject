package com.example.demo.ovsnews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class APIDTO {
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
    private String cmdltNmKorn;
    private String newsTitl;
    private String kotraNewsUrl;
    private String jobSeNm;
    private String hsCdNm;
    private String dataType;
    private String kwrd;
    private String cmdltNmEng;
    private String regn;
    private String cntntSumar;
    private String indstCl;
    private String indstCdList;
    private String newsBdt;
    private String ovrofInfo;
    private String othbcDt;
    private String bbstxSn;
    private String newsWrterNm;
    private String bbsSn;
    private String infoCl;
    private String natn;
}