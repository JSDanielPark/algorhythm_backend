package kr.devdogs.algorhythm.tourna.service;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.devdogs.algorhythm.exam.mapper.ExamMapper;
import kr.devdogs.algorhythm.exam.service.ExamService;
import kr.devdogs.algorhythm.tourna.mapper.TournaMapper;
import kr.devdogs.algorhythm.utils.FileUtils;
import kr.devdogs.langexec.CompilerFactory;
import kr.devdogs.langexec.RunnerFactory;
import kr.devdogs.langexec.core.exception.CompileFailException;

@Service("tournaService")
public class TournaServiceImpl implements TournaService {
	public static final int RANK_COUNT = 100;
	public static final int PAGE_COUNT = 15;
	
	@Autowired private FileUtils fileUtils;
	@Autowired private TournaMapper tournaMapper;
	
	@Override
	public String getCompileLog(String source, String username) {
		String uploadedPath = fileUtils.writeFile(source, "/"+username+"/", "Solution.java");

		File input = new File(uploadedPath);
		List<String> result = null;
		String resultString = "";
		try {
			result = CompilerFactory.getJavaCompiler().compileAndGetLog(input);
			if(result == null) {
				return "success";
			}
			String[] arr = result.stream().toArray(String[]::new);
			resultString = StringUtils.join(arr, "\n");
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return resultString;
	}
	
	public List<Map<String, Object>> getTournaList(int page, String title){
		page = (page-1)*PAGE_COUNT;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", page);
		params.put("end", PAGE_COUNT);
			
		if(title != null) 
			params.put("title", title);
		else
			params.put("title", "");
		
		System.out.println(tournaMapper.list(params));
		System.out.println(params);
		
		return tournaMapper.list(params);
	}
	
	public List<Map<String, Object>> getTournaDetail(int page, String title, String difficult, int tourna_no){
		page = (page-1)*PAGE_COUNT;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", page);
		params.put("end", PAGE_COUNT);
			
		if(title != null) 
			params.put("title", title);
		else
			params.put("title", "");
		
		if(difficult != null) 
			params.put("difficult", difficult);
		else 
			params.put("difficult", "");
		
		params.put("tourna_no", tourna_no);
		
		System.out.println(tournaMapper.detail(params));
		
		System.out.println(params);
		
		return tournaMapper.detail(params);
	}

	public int getMaxPage(String title, String difficulty){
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(title != null) 
			params.put("title", title);
		else
			params.put("title", "");
		
		if(difficulty != null) 
			params.put("difficulty", difficulty);
		else 
			params.put("difficulty", "");
		return tournaMapper.count(params) / PAGE_COUNT + 1;
	}
	
	public int getMaxPage_List(String title){
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(title != null) 
			params.put("title", title);
		else
			params.put("title", "");

		return tournaMapper.count_List(params) / PAGE_COUNT + 1;
	}
	
	@Override
	public int writeTourna(Map<String, Object> param, List<Map<String, String>> testcase) {
		int insertedCount =tournaMapper.writeTourna(param);
		
		if(insertedCount != 0) {
			int insertedCaseCount = 0;
			for(int i=0; i<testcase.size(); i++) {
				Map<String, String> casePair = testcase.get(i);
				insertedCaseCount += tournaMapper.writeTestcase(casePair);
			}
			return insertedCaseCount;
		}
		return 0;
	}
	
	@Override
	public int addTourna(Map<String, Object> param) {
		int insertedCount =tournaMapper.addTourna(param);
		int getListMaxNo = tournaMapper.getTournaMaxNo();
		if(insertedCount != 0) {
			return getListMaxNo;
		}
		return 0;
	}
}
