package com.example.mypageservice.user.news;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DustSeoulResponse {

	public List<ResponseData> list;

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public class ResponseData {

		private String dataTime;
		private String stationName;
		private String mangName;
		private String so2Value;
		private String coValue;
		private String o3Value;
		private String no2Value;
		private String pm10Value;
		private String pm10Value24;
		private String pm25Value;
		private String pm25Value24;
		private String khaiValue;
		private String khaiGrade;
		private String so2Grade;
		private String coGrade;
		private String o3Grade;
		private String no2Grade;
		private String pm10Grade;
		private String pm25Grade;
		private String pm10Grade1h;
		private String pm25Grade1h;

	}

}
