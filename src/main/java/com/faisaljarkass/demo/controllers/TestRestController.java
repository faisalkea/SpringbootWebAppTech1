package com.faisaljarkass.demo.controllers;

import com.faisaljarkass.demo.domains.Customer;
import com.faisaljarkass.demo.domains.NewCaseArgs;
import com.faisaljarkass.demo.domains.PlanCase;
import com.faisaljarkass.demo.domains.TestRestModel;
import com.faisaljarkass.demo.services.TestRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class TestRestController {

    private static final String RESPONSE_OK = "ok";
    private static Logger logger = Logger.getLogger(TestRestController.class.getName());
    @Autowired
    TestRestService testRestService;

    @RequestMapping("/job")
    public String jobTrigger(
            @RequestParam(value = "jobInstanceId") String jobInstanceId,
            @RequestParam(value = "successUrl") String successUrl,
            @RequestParam(value = "failUrl") String failUrl) throws InterruptedException {

        logger.info("jobInstanceId: " + jobInstanceId + ". successUrl: " + successUrl + ". failUrl: " + failUrl);
        testRestService.asyncJobTrigger(jobInstanceId, successUrl, failUrl);

        logger.info("jobTrigger ran successfully...");
        return RESPONSE_OK;
    }

    @RequestMapping("/jobs")
    public @ResponseBody
    List<TestRestModel> testTrigger(
            @RequestParam(value = "jobInstanceId") String jobInstanceId,
            @RequestParam(value = "successUrl") String successUrl,
            @RequestParam(value = "failUrl") String failUrl) throws InterruptedException {

        List<TestRestModel> testRestModels = testRestService.asyncJobTrigger(jobInstanceId, successUrl, failUrl);
        return testRestModels;
    }

    @RequestMapping(value = "/case-create/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public NewCaseArgs postData(@PathVariable(value = "id") long id, @RequestBody NewCaseArgs newCaseArgs) {
        logger.info("NewCaseArgs: " + newCaseArgs);
        return newCaseArgs;
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String post(@RequestBody Customer cust) {
        System.out.println("/POST request, cust: " + cust.toString());
        return "/Post Successful!";
    }

    @PostMapping(value = "/testdata")
    public ResponseEntity<PlanCase> sendData(@RequestBody String newCaseArgs) {
        PlanCase planCase = new PlanCase();
        planCase.setExternalId("123");
        planCase.setCprNumber("345");
        return new ResponseEntity<>(planCase, HttpStatus.CREATED);
    }

}
