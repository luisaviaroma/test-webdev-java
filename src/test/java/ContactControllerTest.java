import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import it.capuano.testwebdevjava.Application;
import it.capuano.testwebdevjava.model.Contact;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ContactControllerTest {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    private String getRootUrl() {
        return "http://localhost:" + port;
    }

    @PostConstruct
    public void initialize() {
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL, MediaType.ALL));
        restTemplateBuilder
                .rootUri(getRootUrl())
                .additionalMessageConverters(mappingJackson2HttpMessageConverter)
                .build();
        restTemplate = new TestRestTemplate(restTemplateBuilder);
    }

    @Test
    public void contextLoads() {

    }

    @Test
    public void testGetAllContacts() {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(getRootUrl() + "/contacts",
                HttpMethod.GET, entity, String.class);
        assertNotNull(response.getBody());
    }

    @Test
    public void testGetContactById() {
        Contact contact = restTemplate.getForObject(getRootUrl() + "/contacts/1", Contact.class);
        System.out.println(contact.getName());
        assertNotNull(contact);
    }

    @Test
    public void testCreateContact() {
        Contact contact = new Contact("Vincenzo", "3331234567", "vincenzo@prova.com");
        ResponseEntity<Contact> postResponse = restTemplate.postForEntity(getRootUrl() + "/contacts", contact, Contact.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
    }

    @Test
    public void testUpdateContact() {
        int id = 1;
        Contact contact = restTemplate.getForObject(getRootUrl() + "/contacts/" + id, Contact.class);
        contact.setName("Vincenzo");
        restTemplate.put(getRootUrl() + "/contacts/" + id, contact);
        Contact updatedContact = restTemplate.getForObject(getRootUrl() + "/contacts/" + id, Contact.class);
        assertNotNull(updatedContact);
    }

    @Test
    public void testDeleteContact() {
        int id = 2;
        Contact contact = restTemplate.getForObject(getRootUrl() + "/contacts/" + id, Contact.class);
        assertNotNull(contact);
        restTemplate.delete(getRootUrl() + "/contacts/" + id);
        try {
            contact = restTemplate.getForObject(getRootUrl() + "/contacts/" + id, Contact.class);
        } catch (final HttpClientErrorException e) {
            assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }
}
