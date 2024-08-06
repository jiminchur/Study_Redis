package com.sprata.redis.session;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    @GetMapping("/set")
    public String set(
            @RequestParam("q") // /set?q={password}
            String q,
            HttpSession session
    ){
        session.setAttribute("q", q);
        return "Saved : " + q;
    }

    @GetMapping("/get")
    public String get(
            HttpSession session
    ){
        return String.valueOf(session.getAttribute("q"));
    }
}
