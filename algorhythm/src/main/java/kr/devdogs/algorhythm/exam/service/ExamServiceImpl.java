package kr.devdogs.algorhythm.exam.service;

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
import kr.devdogs.algorhythm.utils.FileUtils;
import kr.devdogs.langexec.CompilerFactory;
import kr.devdogs.langexec.RunnerFactory;
import kr.devdogs.langexec.core.exception.CompileFailException;

@Service("examService")
public class ExamServiceImpl implements ExamService {
	public static final int RANK_COUNT = 100;
	public static final int PAGE_COUNT = 15;
	
	@Autowired private FileUtils fileUtils;
	@Autowired private ExamMapper examMapper;
	
	@Override
	public Map<String, Object> scoring(String source, Map<String, Object> param) {
		String username = (String)param.get("username");
		int examNo = (Integer)param.get("examNo");
		String uploadedPath = fileUtils.writeFile(source, "/"+username+"/", "Solution.java");
		
		List<Map<String, Object>> testIOPair = examMapper.getTestcase(examNo);
		List<String> inputs = testIOPair.stream().map(
				(Map<String, Object> map)->(String)map.get("input")
				).collect(Collectors.toList());
		List<String> outputs = testIOPair.stream().map(
				(Map<String, Object> map)->(String)map.get("output")
				).collect(Collectors.toList());
		
		inputs.add(0, String.valueOf(inputs.size()));
		
		File input = new File(uploadedPath);
		List<String> result = null;
		
		double totalScore = 0;
		int bingoCount = 0;
		int outputSize = outputs.size();
		
		Map<String, Object> res = new HashMap<String, Object>();
		
		try{
			result = RunnerFactory.getJavaExecutor().run(input, inputs);
			int caseIdx = 0;
			
			// 채점 
			for(int i=0; i<result.size(); i++) {
				if(("case #" + (caseIdx+1)).equals(result.get(i))) {
					if(("case #" + (caseIdx+2)).equals(result.get(i+1))) {
						continue;
					}
					if(outputs.get(caseIdx).equals(result.get(i+1))) {
						bingoCount++;
					}
					caseIdx++;
				}
			}
			
			if(outputSize == bingoCount) {
				totalScore = 100;
			} else {
				totalScore = 100.0 / outputSize * bingoCount;
			}
			
			int finalScore = (int)totalScore;

			String[] arr = result.stream().map((String str)->{
				return str;
				}).toArray(String[]::new);
			String outputResult = StringUtils.join(arr, "\n");
			
			param.put("score", finalScore);
			examMapper.writeRecord(param);
			
			res.put("result", "success");
			res.put("totalScore", finalScore);
			res.put("output", outputResult);
		}catch(CompileFailException e) {
			res.put("result", "fail");
			res.put("error", "Compile Fail");
		}
		return res;
	}
	
	@Override
	public String runSource(String source, String username) {
		String uploadedPath = fileUtils.writeFile(source, "/"+username+"/", "Solution.java");
		try(Scanner scan = new Scanner(new File("/Users/st/Test2.txt"));) {
			List<String> inputs = new ArrayList<>();
			String str = null;
			
			while(scan.hasNextLine() && (str = scan.nextLine()) != null) {
				inputs.add(str);
			}
			
			File input = new File(uploadedPath);
			List<String> result = RunnerFactory.getJavaExecutor().run(input, inputs);
			String[] arr = result.stream().toArray(String[]::new);
			String outputResult = StringUtils.join(arr, "\n");
			return outputResult;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	

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
	
	
	

	public List<Map<String, Object>> getExamList(int page, String subject, String difficulty) {
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
		
		System.out.println(examMapper.list(params));
		System.out.println(params);
		
		return examMapper.list(params);
	}
	
	public int getMaxPage(String subject, String difficulty) {
		Map<String, Object> params = new HashMap<String, Object>();
		
		if(subject != null) 
			params.put("subject", subject);
		else
			params.put("subject", "");
		
		if(difficulty != null) 
			params.put("difficulty", difficulty);
		else 
			params.put("difficulty", "");
		return examMapper.count(params) / PAGE_COUNT + 1;
	}

	@Override
	public Map<String, Object> getExamByNo(int examNo) {
		return examMapper.get(examNo);
	}

	@Override
	public int writeExam(Map<String, Object> param, List<Map<String, String>> testcase) {
		int insertedCount =examMapper.writeExam(param);
		
		if(insertedCount != 0) {
			int insertedNo = examMapper.getMaxNo();
			int insertedCaseCount = 0;
			for(int i=0; i<testcase.size(); i++) {
				Map<String, String> casePair = testcase.get(i);
				casePair.put("exam_no", String.valueOf(insertedNo));
				insertedCaseCount += examMapper.writeTestcase(casePair);
			}
			return insertedCaseCount;
		}
		return 0;
	}

	@Override
	public List<Map<String, Object>> getRank() {
		return examMapper.getRank(RANK_COUNT);
	}

	@Override
	public List<Map<String, Object>> getMyExam(Map<String, Object> param) {
		int page = Integer.parseInt(String.valueOf(param.get("page")));
		page = (page-1)*PAGE_COUNT;
		param.put("start", page);
		param.put("end", PAGE_COUNT);
		
		return examMapper.getMyExam(param);
	}
	
	@Override
	public int getMyExamMaxPage(Map<String, Object> param) {
		return examMapper.myExamCount(param) / PAGE_COUNT + 1;
	}
}
