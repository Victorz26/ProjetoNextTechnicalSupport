package TechnicalAssistance.Services;

import TechnicalAssistance.DAO.AssistanceDAO;
import TechnicalAssistance.Entities.Assistances;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AssistancesService {
    @Autowired
    private AssistanceDAO assistanceDAO;

    public void postAssistance(Assistances assistances) {
        assistanceDAO.save(assistances);
    }

    public List<Assistances> getAllAssistances() {
        return assistanceDAO.findAll();
    }

    public Assistances getAssistance(Long id){
        return assistanceDAO.findById(id).get();
    }

    public void deleteAssistance(Long id) {
        assistanceDAO.deleteById(id);
    }
}
