package gr.hua.team19.armypostponement.service;

import gr.hua.team19.armypostponement.model.Application;
import gr.hua.team19.armypostponement.repository.ApplicationRepository;
import java.util.List;
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

    public List<Application> getApplications(){
        List<Application> applicationList = this.applicationRepository.findAll();
        for(Application application : applicationList) System.out.println(application.getApplication_id());
        return applicationList;
    }


    public Application getApplicationById(int application_id) {
        Application application = this.applicationRepository.findById(application_id);
        return application;
    }

    public boolean isApplicationAlreadyPresent(Application application) {
        boolean isApplicationAlreadyExists = false;
        Application existingApplication = this.applicationRepository.findByEmail(application.getEmail());
        if (existingApplication != null) {
            isApplicationAlreadyExists = true;
        }

        return isApplicationAlreadyExists;
    }

    public void rejectApplication(Application application){
        application.setStatus("REJECTED");
        this.applicationRepository.save(application);
    }

    public void validateApplication(Application application){
        application.setStatus("FOR_APPROVAL");
        this.applicationRepository.save(application);
    }

    public void approveApplication(Application application){
        application.setStatus("APPROVED");
        this.applicationRepository.save(application);
    }

    public String findApplicationStatus(String email){
        Application existingApplication = this.applicationRepository.findByEmail(email);
        String result;
        if(existingApplication == null){
            result = "null";
        }else
        {
            result = existingApplication.getStatus();
        }
        return result;
    }
}
