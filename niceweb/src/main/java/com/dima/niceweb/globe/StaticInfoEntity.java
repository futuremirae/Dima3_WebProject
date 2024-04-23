package com.dima.niceweb.globe;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaticInfoEntity {

    @Id
    @Column(name = "STAT_ID")
    private String statId;

    @Column(name = "STAT_NTN")
    private String statNtn;

    @Column(name = "STAT_POP")
    private String statPop;

    @Column(name = "STAT_CPT")
    private String statCpt;

    @Column(name = "STAT_LAN")
    private String statLan;

    // export
    @Column(name = "EXP_2019")
    private Long exp19;

    @Column(name = "EXP_2020")
    private Long exp20;

    @Column(name = "EXP_2021")
    private Long exp21;

    @Column(name = "EXP_2022")
    private Long exp22;

    @Column(name = "EXP_2023")
    private Long exp23;

    // import
    @Column(name = "IMP_2019")
    private Long imp19;

    @Column(name = "IMP_2020")
    private Long imp20;

    @Column(name = "IMP_2021")
    private Long imp21;

    @Column(name = "IMP_2022")
    private Long imp22;

    @Column(name = "IMP_2023")
    private Long imp23;

    //Balance
    @Column(name = "BAL_2019")
    private Long bal19;

    @Column(name = "BAL_2020")
    private Long bal20;

    @Column(name = "BAL_2021")
    private Long bal21;

    @Column(name = "BAL_2022")
    private Long bal22;

    @Column(name = "BAL_2023")
    private Long bal23;

    // ECONOMY GROWTH
    @Column(name = "GWT_2019")
    private Long gwt19;

    @Column(name = "GWT_2020")
    private Long gwt20;

    @Column(name = "GWT_2021")
    private Long gwt21;

    @Column(name = "GWT_2022")
    private Long gwt22;

    @Column(name = "GWT_2023")
    private Long gwt23;

    // GDP
    @Column(name = "GDP_2019")
    private Long gdp19;

    @Column(name = "GDP_2020")
    private Long gdp20;

    @Column(name = "GDP_2021")
    private Long gdp21;

    @Column(name = "GDP_2022")
    private Long gdp22;

    @Column(name = "GDP_2023")
    private Long gdp23;

    public static StaticInfoEntity toEntity(StaticInfoDTO staticInfoDTO) {
        return StaticInfoEntity.builder()
                .statId(staticInfoDTO.getStatId())
                .statNtn(staticInfoDTO.getStatNtn())
                .statPop(staticInfoDTO.getStatPop())
                .statCpt(staticInfoDTO.getStatCpt())
                .statLan(staticInfoDTO.getStatLan())
                .exp19(staticStatDTO.getExp19())
                .exp20(staticStatDTO.getExp20())
                .exp21(staticStatDTO.getExp21())
                .exp22(staticStatDTO.getExp22())
                .exp23(staticStatDTO.getExp23())
                .imp19(staticStatDTO.getImp19())
                .imp20(staticStatDTO.getImp20())
                .imp21(staticStatDTO.getImp21())
                .imp22(staticStatDTO.getImp22())
                .imp23(staticStatDTO.getImp23())
                .bal19(staticStatDTO.getBal19())
                .bal20(staticStatDTO.getBal20())
                .bal21(staticStatDTO.getBal21())
                .bal22(staticStatDTO.getBal22())
                .bal23(staticStatDTO.getBal23())
                .gwt19(staticStatDTO.getGwt19())
                .gwt20(staticStatDTO.getGwt20())
                .gwt21(staticStatDTO.getGwt21())
                .gwt22(staticStatDTO.getGwt22())
                .gwt23(staticStatDTO.getGwt23())
                .gdp19(staticStatDTO.getGdp19())
                .gdp20(staticStatDTO.getGdp20())
                .gdp21(staticStatDTO.getGdp21())
                .gdp22(staticStatDTO.getGdp22())
                .gdp23(staticStatDTO.getGdp23())
                .build();
    }
}

// CREATE TABLE STAT_INFO
// ( 
//       STAT_ID       CHAR(2)         PRIMARY KEY     -- 국가 넘버
//     , STAT_NTN      VARCHAR2(500)   UNIQUE NOT NULL -- 국가 명
//     , STAT_POP      VARCHAR2(500)   NOT NULL        -- 국가 인구
//     , STAT_CPT      VARCHAR2(500)   UNIQUE NOT NULL -- 국가 수도
//     , STAT_LAN      VARCHAR2(500)   NOT NULL        -- 국가 언어
// );