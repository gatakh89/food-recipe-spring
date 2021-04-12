package com.example.mypageservice.userLecture;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLectureService {

	private UserLectureRepository orderRepo;

	@Autowired
	public UserLectureService(UserLectureRepository orderRepo) {
		this.orderRepo = orderRepo;

	}

	@RabbitListener(queues = "lecture.subscribe")
	public void receiveOrder(UserLecture userLecture) {

		UserLecture userLectures = UserLecture.builder().lectureId(userLecture.getLectureId())
				.userId(userLecture.getUserId()).lectureImageSrc(userLecture.getLectureImageSrc())
				.lectureSummary(userLecture.getLectureSummary()).lectureTitle(userLecture.getLectureTitle())
				.subscribedTime(userLecture.getSubscribedTime()).build()

		;
		// System.out.println("------------------" + userLectures);
		orderRepo.save(userLectures);
	}

	@RabbitListener(queues = "lecture.unsubscribe")
	public void receiveDeleteOrder(UserLecture userLecture) {
		// System.out.println("----------1--------" + userLecture);
		List<UserLecture> users = orderRepo.findByLectureId(userLecture.getLectureId());
		for (UserLecture user : users) {
			// System.out.println("---------2---------" + user);
			if (user.getUserId().equals(userLecture.getUserId()))
				// System.out.println("--------3----------" + user.getUserId());
				orderRepo.deleteById(user.getId());
			;
		}
//		long id = orderRepo.findByLectureId(lectureId) == null ? 0 : orderRepo.findByLectureId(lectureId).getId();
//		UserLecture userLectures = orderRepo.findById(id).orElse(null);
//		// System.out.println(id);
//		if (userLectures == null) {
//			return;
//		}
//		orderRepo.delete(userLectures);
	}

}
