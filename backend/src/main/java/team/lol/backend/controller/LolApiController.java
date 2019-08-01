package team.lol.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import team.lol.backend.util.LolApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:3000",maxAge = 3600)
@RestController
@RequestMapping("/lol")
public class LolApiController {

    @Autowired
    LolApiService service;
    
    @GetMapping("/test")
    public String test(){
        return "test";
    }

    //소환사 검색
    @GetMapping("/search/{sName}")
    public Object searchSummoner(@PathVariable String sName){
        System.out.println("=============SUMMONER SEARCH===============");
        return service.getSummoner(sName);
    }

    //랭킹
    @GetMapping("/rank/{param}")
    public String getMethodName(@RequestParam String param) {
        return null;
    }
    
    
    
}