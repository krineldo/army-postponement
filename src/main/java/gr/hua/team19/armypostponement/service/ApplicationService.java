package gr.hua.team19.armypostponement.service;

import gr.hua.team19.armypostponement.model.Application;
import java.util.List;

public interface ApplicationService {
    void saveApplication(Application application);
    List<Application> getApplications();
    Application getApplicationById(int application_id);
    boolean isApplicationAlreadyPresent(Application application);
    void rejectApplication(Application application);
    void validateApplication(Application application);
    void approveApplication(Application application);
    String findApplicationStatus(String email);
}
