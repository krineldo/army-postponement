package gr.hua.team19.armypostponement.repository;

import gr.hua.team19.armypostponement.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    public Role findByRole(String Role);
}
