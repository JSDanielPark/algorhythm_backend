package kr.devdogs.algorhythm.exam.service;

import java.util.List;
import java.util.Map;

public interface ExamService {
	public String runSource(String source, String username);
	public String getCompileLog(String source, String username);
	public List<Map<String, Object>> getExamList(int page, String subject, String difficulty);
	public int getMaxPage(String subject, String difficulty);
	public Map<String,Object> getExamByNo(int examNo);
	public int writeExam(Map<String, Object> param, List<Map<String,String>> testcase);
}
