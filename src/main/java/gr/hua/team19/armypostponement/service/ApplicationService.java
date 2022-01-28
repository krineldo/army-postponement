package gr.hua.team19.armypostponement.service;

import gr.hua.team19.armypostponement.model.Application;

public interface ApplicationService {
    void saveApplication(Application application);

    boolean isApplicationAlreadyPresent(Application application);
}
