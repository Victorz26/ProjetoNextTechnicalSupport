package Assistencias;

import Assistencias.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssistenciaMain implements CommandLineRunner {
    @Autowired
    private ClienteService clienteservice;


    public static void main(String[] args) {
        SpringApplication.run(AssistenciaMain.class, args);


    }

    @Override
    public void run(String... args) throws Exception {

    }
}
