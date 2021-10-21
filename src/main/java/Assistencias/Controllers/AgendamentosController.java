package Assistencias.Controllers;

import Assistencias.Services.AssistenciasService;
import Assistencias.Services.ClientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AgendamentosController {

    @Autowired
    private ClientesService clientesService;

    @Autowired
    private AssistenciasService assistenciasService;


}
