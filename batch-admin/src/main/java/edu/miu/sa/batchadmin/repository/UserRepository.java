package edu.miu.sa.batchadmin.repository;

import edu.miu.sa.batchadmin.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> getUserByUsername(String username);
}
