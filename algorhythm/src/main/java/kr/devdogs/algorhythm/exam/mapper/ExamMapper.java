package kr.devdogs.algorhythm.exam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import kr.devdogs.algorhythm.sample.dto.Sample;

@Mapper
public interface ExamMapper {
	
	@Select("SELECT count(*) FROM exam WHERE subject LIKE '%${subject}%' AND difficulty LIKE '%${difficulty}%'")
	public int count(Map<String, Object> param);
	
	@Select("SELECT exam_no, subject, difficulty,  DATE_FORMAT(dt, '%Y-%m-%d %H:%i:%s')  dt FROM exam WHERE subject LIKE '%${subject}%' AND difficulty LIKE '%${difficulty}%' ORDER BY exam_no DESC  LIMIT #{start}, #{end}")
	public List<Map<String, Object>> list(Map<String, Object> param);
	
	@Select("SELECT exam_no, subject, content, difficulty,  DATE_FORMAT(dt, '%Y-%m-%d %H:%i:%s')  dt, test_input, test_output FROM exam WHERE exam_no=#{value}")
	public Map<String, Object> get(int examNo);
	
	@Insert("INSERT INTO exam(subject, difficulty, content, dt, test_input, test_output, writer) "
			+ "VALUES(#{subject}, #{difficulty}, #{content}, now(), #{test_input}, #{test_output}, #{writer})")
	@SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="exam_no", before=false, resultType=int.class)
	public int writeExam(Map<String, Object> param);
	
	@Insert("INSERT INTO exam_testcase(exam_no, input, output) "
			+ "VALUES(#{exam_no}, #{input}, #{output})")
	public int writeTestcase(Map<String, String> param);
}
