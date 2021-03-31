package com.example.mypageservice.user.news;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DustSeoul {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
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

	public DustSeoul(DustSeoulResponse.ResponseData res) {
		this.dataTime = res.getDataTime();

		this.stationName = res.getStationName();
		this.mangName = res.getMangName();
		this.so2Value = res.getSo2Value();
		this.coValue = res.getCoValue();
		this.o3Value = res.getO3Value();
		this.no2Value = res.getNo2Value();
		this.pm10Value = res.getPm10Value();
		this.pm10Value24 = res.getPm10Value24();
		this.khaiValue = res.getKhaiValue();
		this.khaiGrade = res.getKhaiGrade();
		this.so2Grade = res.getSo2Grade();
		this.coGrade = res.getCoGrade();
		this.pm10Grade = res.getPm10Grade();
		this.pm25Grade = res.getPm25Grade();
		this.pm10Grade1h = res.getPm10Grade1h();
		this.pm25Grade1h = res.getPm25Grade1h();

	}

}
