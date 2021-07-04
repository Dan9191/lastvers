package dan.spring.demo.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dan.spring.demo.controllers.PersonController;
import dan.spring.demo.gsonserialize.CertificateSerializer;
import dan.spring.demo.gsonserialize.HibernateProxyTypeAdapter;
import dan.spring.demo.model.Certificate;
import dan.spring.demo.model.Person;
import dan.spring.demo.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.net.URL;

public class PersonService {


    private final static Logger logger = LoggerFactory.getLogger(PersonController.class);

    public static void parsingResume(URL url, String id, PersonRepository personRepository){
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


    public static ResponseEntity updateFile(DataEditResume dataEditResume, Person person, PersonRepository personRepository){
        try {
            if (dataEditResume.isFio() == false){
                person.setFirst_name("");
                person.setLast_name("");
                person.setMiddle_name("");
            }
            person.setText(dataEditResume.getText());
            personRepository.save(person);
            logger.info("record update");
            return ResponseEntity.ok("пользователь сохранен");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Exception");
        }
    }

    public static void downloadFile(HttpServletResponse response, String fileName) throws IOException {
        File file = new File(fileName);
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

    public static void createTxtFile(Person person) throws FileNotFoundException {
        String text = person.toString();
        PrintStream pw = new PrintStream("resume.txt");
        pw.print(text);
        pw.flush();
        pw.close();
    }

    public  static void createJsonFile(Person person) {
        GsonBuilder b = new GsonBuilder();
        b.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = b.setPrettyPrinting()
                .registerTypeAdapter(Certificate.class, new CertificateSerializer())
                .create();
        String json = gson.toJson(person);

        try(FileOutputStream fos=new FileOutputStream("resume.json")) {
            byte[] buffer = json.getBytes();
            fos.write(buffer, 0, buffer.length);
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
