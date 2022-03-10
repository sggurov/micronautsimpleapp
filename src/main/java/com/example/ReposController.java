package com.example;

import io.micronaut.http.HttpHeaderValues;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.Authentication;
import io.micronaut.security.rules.SecurityRule;
import io.micronaut.views.View;
import io.swagger.v3.oas.annotations.Hidden;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.micronaut.security.oauth2.endpoint.token.response.OauthAuthenticationMapper.ACCESS_TOKEN_KEY;

@Controller("/repos")
@Hidden
public class ReposController {

    public static final String CREATED = "created";
    public static final String DESC = "desc";
    public static final String REPOS = "repos";

    private final GithubApiClient githubApiClient;

    public ReposController(GithubApiClient githubApiClient) {
        this.githubApiClient = githubApiClient;
    }

    @Secured(SecurityRule.IS_AUTHENTICATED)
    @View("repos")
    @Get
    Map<String, Object> index(Authentication authentication) {
        List<GithubRepo> repos = githubApiClient.repos(CREATED, DESC, authorizationValue(authentication));
        Map<String, Object> model = new HashMap<>();
        model.put(REPOS, repos);
        return model;
    }

    private String authorizationValue(Authentication authentication) {
        Object claim = authentication.getAttributes().get(ACCESS_TOKEN_KEY);
        if (claim instanceof String) {
            return HttpHeaderValues.AUTHORIZATION_PREFIX_BEARER + ' ' + claim;
        }
        return null;
    }
}
