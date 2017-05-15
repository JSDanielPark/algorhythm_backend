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
	
	public List<Map<String, Object>> getTournaList(int page, String subject, String difficulty){
		page = (page-1)*PAGE_COUNT;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("start", page);
		params.put("end", PAGE_COUNT);
			
		if(subject != null) 
			params.put("subject", subject);
		else
			params.put("subject", "");
		
		if(difficulty != null) 
			params.put("difficulty", difficulty);
		else 
			params.put("difficulty", "");
		
		System.out.println(tournaMapper.list(params));
		System.out.println(params);
		
		return tournaMapper.list(params);
	}

	public int getMaxPage(String subject, String difficulty){
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(subject != null) 
			params.put("subject", subject);
		else
			params.put("subject", "");
		
		if(difficulty != null) 
			params.put("difficulty", difficulty);
		else 
			params.put("difficulty", "");
		return tournaMapper.count(params) / PAGE_COUNT + 1;
	}
}
