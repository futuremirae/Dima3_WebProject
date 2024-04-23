package com.dima.niceweb.globe;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StaticInfoDTO {
    
    private String statId;

    private String statNtn;

    private String statPop;

    private String statCpt;

    private String statLan;

    // export
    private Long exp19;

    private Long exp20;

    private Long exp21;

    private Long exp22;

    private Long exp23;

    // import
    private Long imp19;

    private Long imp20;

    private Long imp21;

    private Long imp22;

    private Long imp23;

    //Balance
    private Long bal19;

    private Long bal20;

    private Long bal21;

    private Long bal22;

    private Long bal23;

    // ECONOMY GROWTH
    private Long gwt19;

    private Long gwt20;

    private Long gwt21;

    private Long gwt22;

    private Long gwt23;

    // GDP
    private Long gdp19;

    private Long gdp20;

    private Long gdp21;

    private Long gdp22;

    private Long gdp23;

    public static StaticInfoDTO toDTO(StaticInfoEntity staticInfoEntity) {
        return StaticInfoDTO.builder()
                .statId(staticInfoEntity.getStatId())
                .statNtn(staticInfoEntity.getStatNtn())
                .statPop(staticInfoEntity.getStatPop())
                .statCpt(staticInfoEntity.getStatCpt())
                .statLan(staticInfoEntity.getStatLan())
                .exp19(staticStatEntity.getExp19())
                .exp20(staticStatEntity.getExp20())
                .exp21(staticStatEntity.getExp21())
                .exp22(staticStatEntity.getExp22())
                .exp23(staticStatEntity.getExp23())
                .imp19(staticStatEntity.getImp19())
                .imp20(staticStatEntity.getImp20())
                .imp21(staticStatEntity.getImp21())
                .imp22(staticStatEntity.getImp22())
                .imp23(staticStatEntity.getImp23())
                .bal19(staticStatEntity.getBal19())
                .bal20(staticStatEntity.getBal20())
                .bal21(staticStatEntity.getBal21())
                .bal22(staticStatEntity.getBal22())
                .bal23(staticStatEntity.getBal23())
                .gwt19(staticStatEntity.getGwt19())
                .gwt20(staticStatEntity.getGwt20())
                .gwt21(staticStatEntity.getGwt21())
                .gwt22(staticStatEntity.getGwt22())
                .gwt23(staticStatEntity.getGwt23())
                .gdp19(staticStatEntity.getGdp19())
                .gdp20(staticStatEntity.getGdp20())
                .gdp21(staticStatEntity.getGdp21())
                .gdp22(staticStatEntity.getGdp22())
                .gdp23(staticStatEntity.getGdp23())
                .build();
    }
}
