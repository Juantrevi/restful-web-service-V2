package com.webservices.restfulwebservice.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    @GetMapping(path = "/v1/person")
    public PersonV1 getFirstVersionOfPerson() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/v2/person")
    public PersonV2 getSecondVersionOfPerson() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
    //Different ways of versioning REST APIs (URI, Request Parameter, Header Parameter, Produces Parameter)
    //pros and cons of each of them, and which one to use in which scenario
    //URI pollution, Misuse of HTTP headers, Caching, Can we execute the request on the browser, API Documentation
    //URI pollution - if we have a lot of resources and a lot of versions, then the URI will be polluted
    //Misuse of HTTP headers - HTTP headers are not meant for versioning, they are meant for headers
    //Caching - Caching is difficult to implement in case of header and request parameter versioning
    //Can we execute the request on the browser - We can execute the request on the browser in case of URI versioning
    //API Documentation - API documentation is easy in case of URI versioning
    //URI versioning is the best way to version REST APIs
    //Another way of versioning is by using URI (Twitter)

    //Another way of versioning is by using request parameter (Amazon)
    @GetMapping(path = "/person", params = "version=1")
    public PersonV1 getFirstVersionOfPersonRequestParameter() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonRequestParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //Another way of versioning is by using header parameter (Microsoft)
    @GetMapping(path = "/person/header", headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonHeaderParameter() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/header", headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonHeaderParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }

    //Another way of versioning is by using produces parameter (GitHub)
    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonProducesParameter() {
        return new PersonV1("Bob Charlie");
    }

    @GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonProducesParameter() {
        return new PersonV2(new Name("Bob", "Charlie"));
    }
}
