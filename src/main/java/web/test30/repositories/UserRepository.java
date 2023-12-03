package web.test30.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.test30.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findUserByEmail (String email);
    Optional<User> findUserByAddress (String address);
}
