package com.example.demo.prefer;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

import com.google.gson.Gson;

@Controller
public class PreferController {

    @GetMapping("/prefer")
    @ResponseBody
    public List<PreferDTO.Records> prefer() {
        try {
            String result = PreferDownload.getPrefer();

            Gson gson = new Gson();

            PreferDTO preferDTO = gson.fromJson(result, PreferDTO.class);
            return preferDTO.getRecords();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

// html에 꽂아보기
    // @GetMapping({"/",""})
    // public String jsonIndex() {

    //     return "jsonIndex";
    // }

}


