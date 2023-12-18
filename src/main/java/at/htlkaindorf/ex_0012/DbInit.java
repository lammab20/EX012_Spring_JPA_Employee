package at.htlkaindorf.ex_0012;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class DbInit {
    private Logger log = LoggerFactory.getLogger(getClass());
    private DepartmentRepository departmentRepository;

    @Autowired
    public DbInit(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
    }

    @PostConstruct
    public void init(){
        log.info("-->  init()  !!!");
        //Path path = Path.of("src" , "main" , "resources" , "data" , "employeeData.json"); //Das ist, wenn man das Verzeichnis im resouces Ordner hat
        Path path = Path.of(".." , "data" , "employeeData.json").normalize(); //Das ist, wenn man den Ordner außerhalb der Projekt-Datei hat
        log.info(path.toAbsolutePath().toString());

        try {
            String text = Files.readString(path);

            ObjectMapper om = new ObjectMapper();
            List<Department> departmentList = om.readValue(text, new TypeReference<List<Department>>() {});

            departmentRepository.saveAll(departmentList);
        } catch (Exception e) {
            log.info(e.toString());
        }


    }
}
