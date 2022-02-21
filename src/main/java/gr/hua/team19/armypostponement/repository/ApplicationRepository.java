package gr.hua.team19.armypostponement.repository;

import gr.hua.team19.armypostponement.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{

    Application findByEmail(String email);


    Application findById(int application_id);

    @Override
    List<Application> findAll();
}
