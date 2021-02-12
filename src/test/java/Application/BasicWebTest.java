package Application;

import Application.persistence.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BasicWebTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldReturnABuddy() throws Exception {
        HttpEntity<BuddyInfo> request = new HttpEntity<>(new BuddyInfo("Tom", "613"));
        BuddyInfo bud = restTemplate.postForObject("http://localhost:" + port + "/buddies", request, BuddyInfo.class);
        assertEquals("Tom", bud.getName());
        assertEquals("613", bud.getPhone());
    }
}