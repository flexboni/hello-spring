package hello.hellospring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
    private final MemberService service;

    @Autowired
    public MemberController(MemberService service) {
        this.service = service;
    }

}