package com.example.mypageservice.userLecture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLectureRepository extends JpaRepository<UserLecture, Long> {
	// List<UserLecture> findByLectureId (long lectureId);
	List<UserLecture> findByUserId(String userId);

	List<UserLecture> findByLectureId(long lectureId);

}
