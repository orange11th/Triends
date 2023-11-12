package com.ssafy.triends.attraction.model.service;

import java.util.List;

import com.ssafy.triends.attraction.model.Attraction;

public interface AttractionService {

	List<Attraction> listAttraction(Attraction attraction);

	Attraction detailAttraciton(int contentId);
}
