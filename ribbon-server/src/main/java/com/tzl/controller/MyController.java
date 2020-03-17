package com.tzl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MyController {

    @GetMapping("/person/{personId}")
    public Map<String,Object> getPerson(@PathVariable(value = "personId") String personId, HttpServletRequest request){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("personName","张三");
        map.put("personId",personId);
        map.put("Url",request.getRequestURL().toString());
        return map;
    }
}
