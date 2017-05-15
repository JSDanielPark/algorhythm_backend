package kr.devdogs.algorhythm.tourna.service;

import java.util.List;
import java.util.Map;

public interface TournaService {
	public String getCompileLog(String source, String username);

	public List<Map<String, Object>> getTournaList(int page, String subject, String difficulty);

	public int getMaxPage(String subject, String difficulty);
}
