package Assistencias;

import Assistencias.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AssistenciaMain {
    @Autowired
    private ClienteService clienteservice;


    public static void main(String[] args) {
        SpringApplication.run(AssistenciaMain.class, args);


    }
}
