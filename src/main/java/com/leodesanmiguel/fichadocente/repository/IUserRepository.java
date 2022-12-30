package com.leodesanmiguel.fichadocente.repository;


import com.leodesanmiguel.fichadocente.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByName(String name);

    Optional<User> findUserByName(String name);
    boolean existsByName(String name);

    void deleteByName(@Param("name") String name);

}
