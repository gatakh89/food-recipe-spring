package com.example.mypageservice.user.Stuff;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StuffRepository extends JpaRepository<Stuff, Long> {

}
