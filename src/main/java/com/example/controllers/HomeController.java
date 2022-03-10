package com.example.controllers;

import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;
import io.swagger.v3.oas.annotations.Hidden;

import java.util.HashMap;
import java.util.Map;

@Controller
@Hidden
public class HomeController {
    @Secured(SecurityRule.IS_ANONYMOUS)
    @View("home")
    @Get
    public Map<String, Object> index() {
        return new HashMap<>();
    }
}
