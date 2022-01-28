package gr.hua.team19.armypostponement.service;

import gr.hua.team19.armypostponement.model.Role;
import gr.hua.team19.armypostponement.model.User;
import gr.hua.team19.armypostponement.model.Application;
import gr.hua.team19.armypostponement.repository.RoleRepository;
import gr.hua.team19.armypostponement.repository.UserRepository;
import gr.hua.team19.armypostponement.repository.ApplicationRepository;

import java.util.Arrays;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApplicationServiceImp implements ApplicationService{


    @Autowired
    ApplicationRepository applicationRepository;

    public ApplicationServiceImp(){}

    public void saveApplication(Application application) {
        application.setStatus("FOR_VALIDATION");
        this.applicationRepository.save(application);
    }

    public boolean isApplicationAlreadyPresent(Application application) {
        boolean isApplicationAlreadyExists = false;
        Application existingApplication = this.applicationRepository.findByEmail(application.getEmail());
        if (existingApplication != null) {
            isApplicationAlreadyExists = true;
        }

        return isApplicationAlreadyExists;
    }
}
