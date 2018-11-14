package com.campsite.users.repository;

import com.campsite.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findById(Long id);
    User findByEmailAddressAndFullName(String emailAddress, String fullName);
}
