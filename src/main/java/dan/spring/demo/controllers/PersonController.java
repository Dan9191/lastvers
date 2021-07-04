package dan.spring.demo.controllers;

import dan.spring.demo.model.Person;
import dan.spring.demo.repository.PersonRepository;
import dan.spring.demo.service.DataEditResume;
import dan.spring.demo.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;

@RestController
public class PersonController {

    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private PersonRepository personRepository;


    @Autowired
    public PersonController(PersonRepository personRepository) { this.personRepository = personRepository; }

    @GetMapping("resumes/{id}")
    public Person parsingResume(@PathVariable("id") String id){
        URL url = this.getClass().getClassLoader().getResource("people.json");
        PersonService.parsingResume(url, id, personRepository);
        List<Person> people = personRepository.findAll();
        Person person = people.get(people.size()-1);
        return person;
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody DataEditResume dataEditResume){
        List<Person> people = personRepository.findAll();
        Person person = people.get(people.size()-1);
        return PersonService.updateFile(dataEditResume, person, personRepository);
    }

    @GetMapping("download_file_json")
    public void downloadJsonFile(HttpServletResponse response) throws IOException{
        List<Person> people = personRepository.findAll();
        Person person = people.get(people.size()-1);
        PersonService.createJsonFile(person);
        PersonService.downloadFile(response, "resume.json");
        logger.info("file download");
    }

    @GetMapping("download_file_txt")
    public void downloadDocxFile(HttpServletResponse response) throws IOException{
        List<Person> people = personRepository.findAll();
        Person person = people.get(people.size()-1);
        PersonService.createTxtFile(person);
        PersonService.downloadFile(response, "resume.txt");
        logger.info("file download");
    }

}