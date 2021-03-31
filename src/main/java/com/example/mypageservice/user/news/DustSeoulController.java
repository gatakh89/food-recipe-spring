package com.example.mypageservice.user.news;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DustSeoulController {

	private DustSeoulRepository dustRepo;

	@Autowired
	public DustSeoulController(DustSeoulRepository dustRepo) {
		this.dustRepo = dustRepo;

	}

	@RequestMapping(value = "/dust-hourly", method = RequestMethod.GET)
	public List<DustSeoul> getFeeds() {
		return dustRepo.findAll(Sort.by("id").descending());
	}

//		@RequestMapping
//		(value="/dust-hourly/{dataType}", method=RequestMethod.GET)
//		public List<DustSeoul> getFeedsPaging(@PathVariable("dataType") String dataType,
//				
//				 {
//
//			return dustRepo.
//				
//		}

}
