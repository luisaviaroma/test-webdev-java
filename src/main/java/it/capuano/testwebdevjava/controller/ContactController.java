package it.capuano.testwebdevjava.controller;

import it.capuano.testwebdevjava.model.Contact;
import it.capuano.testwebdevjava.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactController {

    Logger logger = LoggerFactory.getLogger(ContactController.class);


    @Autowired
    private ContactService contactService;

    @GetMapping
    public ResponseEntity<List<Contact>> findAll() {
        return ResponseEntity.ok(contactService.findAll());
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<Contact> findById(@PathVariable Long contactId) {
        Optional<Contact> contactOptional = contactService.findById(contactId);
        if (!contactOptional.isPresent()) {
            logger.error("ContactId " + contactId + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(contactOptional.get());
    }

    @GetMapping("/{contactName}")
    public ResponseEntity<Contact> findByName(@PathVariable Long contactName) {
        Optional<Contact> contactOptional = contactService.findById(contactName);
        if (!contactOptional.isPresent()) {
            logger.error("contactName " + contactName + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(contactOptional.get());
    }

    @GetMapping("/{contactPhone}")
    public ResponseEntity<Contact> findByPhone(@PathVariable Long contactPhone) {
        Optional<Contact> contactOptional = contactService.findById(contactPhone);
        if (!contactOptional.isPresent()) {
            logger.error("ContactPhone " + contactPhone + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(contactOptional.get());
    }

    @GetMapping("/{contactEmail}")
    public ResponseEntity<Contact> findByEmail(@PathVariable Long contactEmail) {
        Optional<Contact> contactOptional = contactService.findById(contactEmail);
        if (!contactOptional.isPresent()) {
            logger.error("ContactEmail " + contactEmail + " is not existed");
            ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(contactOptional.get());
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Contact contact) {
        return ResponseEntity.status(HttpStatus.CREATED).body(contactService.save(contact));
    }

    @PatchMapping("/{contactId}")
    public ResponseEntity<Contact> update(@PathVariable Long contactId, @RequestBody Contact updatingContact) {
        Optional<Contact> contactOptional = contactService.findById(contactId);
        if (!contactOptional.isPresent()) {
            logger.error("ContactId " + contactId + " does not existed");
            ResponseEntity.badRequest().build();
        }

        Contact contact = contactOptional.get();
        if (!StringUtils.isEmpty(updatingContact.getName())) contact.setName(updatingContact.getName());
        if (!StringUtils.isEmpty(updatingContact.getPhone())) contact.setName(updatingContact.getPhone());
        if (!StringUtils.isEmpty(updatingContact.getEmail())) contact.setName(updatingContact.getEmail());

        return ResponseEntity.accepted().body(contactService.save(contact));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        contactService.deleteById(id);

        return ResponseEntity.accepted().build();
    }
}