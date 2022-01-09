package gr.hua.team19.armypostponement.service;

import gr.hua.team19.armypostponement.model.User;

public interface UserService {
    public void saveUser(User user);
    public boolean isUserAlreadyPresent(User user);
}
