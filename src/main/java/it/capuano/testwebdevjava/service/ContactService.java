package it.capuano.testwebdevjava.service;

import it.capuano.testwebdevjava.model.Contact;
import it.capuano.testwebdevjava.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    public Optional<Contact> findByName(String name) {
        return contactRepository.findByName(name);
    }
    public Optional<Contact> findByPhone(String phone) {
        return contactRepository.findByPhone(phone);
    }
    public Optional<Contact> findByEmail(String email) {
        return contactRepository.findByEmail(email);
    }

    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    public void deleteById(Long id) {
        contactRepository.deleteById(id);
    }
}
