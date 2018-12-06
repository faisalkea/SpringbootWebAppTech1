package com.faisaljarkass.demo.services;

import com.faisaljarkass.demo.domains.TestRestModel;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TestRestService {

    List<TestRestModel> asyncJobTrigger(String jobInstanceId, String successUrl, String failUrl) throws InterruptedException;

}
