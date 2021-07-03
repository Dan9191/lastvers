package dan.spring.demo.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dan.spring.demo.gsonserialize.CertificateSerializer;
import dan.spring.demo.gsonserialize.HibernateProxyTypeAdapter;
import dan.spring.demo.model.Certificate;
import dan.spring.demo.model.Person;
import dan.spring.demo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.List;

@RestController
public class PersonController {

    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    private PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    //метод для парсинга резюме из json в бд, для возврата инфы придется использовать модель
    @GetMapping("resumes/{id}")
    public void parsingResume(@PathVariable("id") String id){
        URL url = this.getClass().getClassLoader().getResource("people.json");

        if(url!=null) {
            File jsonFile = new File(url.getFile());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                List<Person> people= objectMapper.readValue(jsonFile, new TypeReference<List<Person>>(){});
                for(Person person: people) {
                    if (person.getId().equals(id)){
                        personRepository.save(person);
                    }
                }
                logger.info("record saved");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else{
            logger.warn("id is null");
        }



    }



    @PatchMapping("/update")
    public void update(String text, boolean anon){
        Person person =  personRepository.getById(1L);
        if (anon == true){
            person.setFirst_name("");
            person.setLast_name("");
            person.setMiddle_name("");
        }
        person.setText(text);
        personRepository.save(person);
    }



    @GetMapping("download_file_json")
    public void downloadJsonFile(HttpServletResponse response) throws IOException{

        Person person =  personRepository.getById(1L);
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.setPrettyPrinting()
                .registerTypeAdapter(Certificate.class, new CertificateSerializer())
                .create();
        String json = gson.toJson(person);
        System.out.println(json);


        try(FileOutputStream fos=new FileOutputStream("resume.json")) {
            byte[] buffer = json.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("The file has been written");


        File file = new File("resume.json");
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getName();

        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        BufferedInputStream inputStream =
                new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[8192]; //8 kb buffer
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();
    }


    @GetMapping("download_file_txt")
    public void downloadDocxFile(HttpServletResponse response) throws IOException{

        Person person =  personRepository.getById(1L);
        String s = person.toString();
        PrintStream pw = new PrintStream("resume.txt");
        pw.print(s);
        pw.flush();
        pw.close();

        File file = new File("resume.txt");
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + file.getName();

        response.setHeader(headerKey, headerValue);

        ServletOutputStream outputStream = response.getOutputStream();
        BufferedInputStream inputStream =
                new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[8192]; //8 kb buffer
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        inputStream.close();
        outputStream.close();
    }

}