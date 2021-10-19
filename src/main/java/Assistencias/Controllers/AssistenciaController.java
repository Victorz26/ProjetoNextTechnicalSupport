package Assistencias.Controllers;

import Assistencias.Services.AssistenciaService;
import Assistencias.Services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AssistenciaController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private AssistenciaService assistenciaService;
}
