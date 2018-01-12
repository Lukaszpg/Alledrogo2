package pro.lukasgorny.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pro.lukasgorny.model.Role;
import pro.lukasgorny.util.QueryBody;

/**
 * Created by lukaszgo on 2017-05-25.
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);

    @Query(QueryBody.RoleQuery.GET_ROLE_COUNT)
    Long countAll();
}
