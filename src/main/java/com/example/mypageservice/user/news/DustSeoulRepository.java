package com.example.mypageservice.user.news;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DustSeoulRepository extends JpaRepository<DustSeoul, Long> {
	DustSeoul findByDataTime(String dataTime);

}
