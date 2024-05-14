package com.example.CRUDapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CRUDapp.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface userRepo extends JpaRepository<User, String>
{

}
