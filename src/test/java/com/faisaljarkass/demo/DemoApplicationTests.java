package com.faisaljarkass.demo;

import com.faisaljarkass.demo.domains.Customer;
import com.faisaljarkass.demo.domains.MyUser;
import com.faisaljarkass.demo.domains.NewCaseArgs;
import com.faisaljarkass.demo.domains.PlanCase;
import com.faisaljarkass.demo.domains.TestRestModel;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    //String restTestUrl ="http://localhost:5000/api/jobs?jobInstanceId=jobInstanceIdTEST&successUrl=successUrlTEST&failUrl=failUrlTEST";
    String restTestUrlNoParam = "http://localhost:5000/api/jobs?jobInstanceId={jobInstanceId}&successUrl={successUrl}&failUrl={failUrl}";
    //String restTestUrlNoParam ="http://localhost:5000/api/jobs{?jobInstanceId,successUrl,failUrl}";

    @Test
    public void contextLoads() {
    }

    @Test
    public void testRestAuth() {
        RestTemplate restTemplate = new RestTemplateBuilder()
                .basicAuthorization("test", "test").build();

        Map<String, String> map = new HashMap<>();
        map.put("jobInstanceId", "jobInstanceIdTEST");
        map.put("successUrl", "successUrlTEST");
        map.put("failUrl", "failUrlTEST");

        //TestRestModel[] list = restTemplate.getForObject(restTestUrl, TestRestModel[].class);
        TestRestModel[] list = restTemplate.getForObject(restTestUrlNoParam, TestRestModel[].class, map);

        Arrays.stream(list).forEach(System.out::println);

        assertEquals(2, list.length);
    }

    @Test
    public void testPost() {
        String plainCreds = "test:test";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        RestTemplate restTemplate = new RestTemplateBuilder().basicAuthorization("test", "test").build();
        MyUser user = new MyUser(1L, "face", "test", null);

        NewCaseArgs args = new NewCaseArgs();
        args.setTitle("Test Title");
        args.setJournalPlanCode("Test JournalPlanCode");
        args.setKeywordCode("Test KeywordCode");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity entity = new HttpEntity(args, headers);

        String locationForplan = "http://localhost:5000/api/case-create/1";
        ResponseEntity<String> responseEntity = restTemplate.exchange(locationForplan, HttpMethod.POST, entity, String.class);
        //ResponseEntity<String> responseEntity = restTemplate.postForEntity(locationForplan, args, String.class);
        System.out.println(responseEntity);
    }

    @Test
    public void testData() {
        String plainCreds = "test:test";
        byte[] plainCredsBytes = plainCreds.getBytes();
        byte[] base64CredsBytes = Base64.encodeBase64(plainCredsBytes);
        String base64Creds = new String(base64CredsBytes);

        RestTemplate restTemplate = new RestTemplateBuilder().basicAuthorization("test", "test").build();

        NewCaseArgs args = new NewCaseArgs();
        args.setTitle("Test Title");
        args.setJournalPlanCode("Test JournalPlanCode");
        args.setKeywordCode("Test KeywordCode");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        //headers.add("Authorization", "Basic " + base64Creds);

        HttpEntity entity = new HttpEntity(args, headers);

        String locationForplan = "http://localhost:5000/api/testdata";
        ResponseEntity<PlanCase> responseEntity = restTemplate.exchange(locationForplan, HttpMethod.POST, entity, PlanCase.class);
        //ResponseEntity<String> responseEntity = restTemplate.postForEntity(locationForplan, args, String.class);
        System.out.println(responseEntity);
    }

    @Test
    public void testPost2() {
        RestTemplate restTemplate = new RestTemplateBuilder().basicAuthorization("test", "test").build();
        Customer customer = new Customer(1, "Face", 32);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);
        HttpEntity entity = new HttpEntity(customer, headers);

        String locationForplan = "http://localhost:5000/api/post";
        ResponseEntity<String> responseEntity = restTemplate.exchange(locationForplan, HttpMethod.POST, entity, String.class);
        //ResponseEntity<String> responseEntity = restTemplate.postForEntity(locationForplan, customer, String.class);
        System.out.println(responseEntity);
    }

    @Test
    public void testJaxrs() {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:5000/api/post");
        Customer customer = new Customer(1, "Face", 32);

        Response response = client.target("http://localhost:5000/api/post").
                request(MediaType.APPLICATION_JSON).post(Entity.entity(customer, MediaType.APPLICATION_JSON));
        //JsonArray response = target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
//        System.out.println(response.toString());
        String str = response.readEntity(String.class);
        System.out.println("str" + str);

    }

    @Test
    public void testRestJavaOld() {
        try {

            URL url = new URL("http://localhost:5000/api/post");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"id\":10, \"name\":\"face\", \"age\":32 }";

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBCryptHash() {
        String password = "test";
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        System.out.println(hashedPassword);
    }

}
