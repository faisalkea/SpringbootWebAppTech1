package com.faisaljarkass.demo.services;

import com.faisaljarkass.demo.domains.Blog;
import com.faisaljarkass.demo.domains.TestRestModel;
import com.faisaljarkass.demo.repositories.TestRestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.sql.DataSource;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TestRestServiceImpl implements TestRestService {

    private static Logger logger = Logger.getLogger(TestRestServiceImpl.class.getName());

    private static final String WEB_SERVICE_URL = "localhost:8080/ajaxData";

    @Autowired
    @Qualifier("restEntityManager")
    private EntityManager entityManager;

    @Autowired
    @Qualifier("restDataSource")
    private DataSource dataSource;

    @Autowired
    TestRestRepo testRestRepo;

    private final RestTemplate restTemplate;

    public TestRestServiceImpl(RestTemplateBuilder restTemplateBuilder,
                               @Value("${rest.test.username}") String username,
                               @Value("${rest.test.passwors}") String password) {

        this.restTemplate = restTemplateBuilder.basicAuthorization(username, password).build();
    }

    @Override
    @Async
    public List<TestRestModel> asyncJobTrigger(String jobInstanceId, String successUrl, String failUrl) throws InterruptedException {
        //TODO: Find a service to call where i Verify My Self!
        //String result = restTemplate.getForObject(WEB_SERVICE_URL, String.class);
        //logger.info("Result: " + result);

        //A Call to the DB
        StringBuilder select = new StringBuilder();
        select.append("SELECT * FROM testTable");

        Query query = entityManager.createNativeQuery(select.toString(), TestRestModel.class);
        //query.setParameter("planid_1", jobInstanceId);
        List<TestRestModel> list = query.getResultList();
        Thread.sleep(1000);
        list.stream().forEach(element -> logger.info("" + element));
        //list.stream().forEach(System.out::println);
        return list;
    }

}
