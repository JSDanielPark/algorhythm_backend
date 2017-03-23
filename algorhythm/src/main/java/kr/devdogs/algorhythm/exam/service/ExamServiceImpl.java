package kr.devdogs.algorhythm.exam.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.devdogs.algorhythm.exam.mapper.ExamMapper;
import kr.devdogs.algorhythm.utils.FileUtils;
import kr.devdogs.langexec.CompilerFactory;
import kr.devdogs.langexec.RunnerFactory;

@Service("examService")
public class ExamServiceImpl implements ExamService {
	public static final int PAGE_COUNT = 15;
	
	@Autowired private FileUtils fileUtils;
	@Autowired private ExamMapper examMapper;
	
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
			String result = RunnerFactory.getJavaExecutor().run(input, inputs);
			return result;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	

	@Override
	public String getCompileLog(String source, String username) {
		String uploadedPath = fileUtils.writeFile(source, "/"+username+"/", "Solution.java");
		
		File input = new File(uploadedPath);
		String result = null;;
		try {
			result = CompilerFactory.getJavaCompiler().compileAndGetLog(input);
		} catch (IOException e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return result;
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
		int insertedNo = examMapper.writeExam(param);
		if(insertedNo != 0) {
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

}
