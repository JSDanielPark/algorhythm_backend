package kr.devdogs.algorhythm.tourna.service;

import java.util.List;
import java.util.Map;

public interface TournaService {
	public String getCompileLog(String source, String username);

	public List<Map<String, Object>> getTournaList(int page, String title);

	public List<Map<String, Object>> getTournaDetail(int page, String title, String difficult, int tourna_no);
	
	public int getMaxPage(String title, String difficulty);

	public int getMaxPage_List(String title);
	
	public int writeTourna(Map<String, Object> param, List<Map<String,String>> testcase);
	
	public int addTourna(Map<String, Object> param);

}
