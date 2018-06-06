package com.intuit.cg.backendtechassessment.controller.requestmappings;

public class RequestMappings {
    public static final String PROJECTS = "/projects";
    public static final String EMPLOYERS = "/employers";
    public static final String BIDDERS = "/bidders";
    public static final String BIDS = "/bids";
    public static final String CREATE_BID = "/create-bid";
    public static final String CREATE_PROJECT = "/create-project";
    public static final String GET_PROJECT = "/get-project/{projectId}";
    public static final String CREATE_EMPLOYER = "/create-employer";
    public static final String CREATE_BIDDER = "/create-bidder";
    
    private RequestMappings() {
    }
}
