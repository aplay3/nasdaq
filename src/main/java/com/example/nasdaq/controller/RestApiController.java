package com.example.nasdaq.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nasdaq.model.DTO.DailyUpdateDto;
import com.example.nasdaq.model.DTO.IndustryDto;
import com.example.nasdaq.service.DailyUpdateService;
import com.example.nasdaq.service.IndustryService;

@RestController
@RequestMapping("/api/v1/nasdaq")
public class RestApiController {
    

    @Autowired
    private DailyUpdateService dailyUpdateService;

    @Autowired
    private IndustryService industryService;


    // java script에서 사용할 rest handler
    @GetMapping("/search")
    public Map<String, Object> searchTickers(@RequestParam String ticker){
        List<String> tickers = dailyUpdateService.getTickersContaining(ticker);
        String recent_date = dailyUpdateService.getMostRecentDate();

        Map<String, Object> response = new HashMap<>();
        response.put("tickers", tickers);
        response.put("recent_date", recent_date);

        return response;
    }

    @GetMapping("/industries")
    public List<IndustryDto> industries() {
        List<IndustryDto> dtos = industryService.getAllIndustry();
        return dtos;
    }

    @GetMapping("/industry")
    public List<IndustryDto> industry(@RequestParam String industry) {
        List<IndustryDto> dtos = industryService.getWeeklyInfo(industry);
        return dtos;
    }

    @GetMapping("/ratios")
    public List<DailyUpdateDto> dailyUpdates(@RequestParam String ticker) {
        // String recentDate = dailyUpdateService.getMostRecentDate();
        List<DailyUpdateDto> dtos = dailyUpdateService.getWeeklyInfo(ticker);
        return dtos;
    }

    // @GetMapping("/test")
    // public List<TopTickersDto> test(@RequestParam String industry){
    //     List<TopTickersDto> tickers_names = dailyUpdateService.getTop5TickersByIndustry(industry);

    //     return tickers_names;
    // }
}
