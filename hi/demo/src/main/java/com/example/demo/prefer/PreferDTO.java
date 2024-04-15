package com.example.demo.prefer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreferDTO {
    private List<Records> records; // 필드명을 records로 수정
    private int pageNo;
    private int resultCode;
    private int totalCount;
    private String type;
    private int numOfRows;
    private String resultMsg;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Records { // 정적 내부 클래스로 변경
        private String DEPTNAME;
        private String DEPTNAME_EN;
        private String BASE_YR;
        private String ISO_WD2_NAT_CD;
        private String HSCD;
        private String GBN_CD;
        private String CMDLT_NM;
        private String CMDLT_PARENT_NM;
        private String CMDLT_GR_PARENT_NM;
    }
}