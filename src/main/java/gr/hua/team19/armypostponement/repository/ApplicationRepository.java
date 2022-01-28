package gr.hua.team19.armypostponement.repository;


import gr.hua.team19.armypostponement.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Integer>{

    Application findByEmail(String email);
}
