
package kr.devdogs.algorhythm.tourna.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TournaMapper {
	
	@Select("SELECT exam_no, subject, difficulty,  DATE_FORMAT(dt, '%Y-%m-%d %H:%i:%s')  dt FROM exam WHERE subject LIKE '%${subject}%' AND difficulty LIKE '%${difficulty}%' ORDER BY exam_no DESC  LIMIT #{start}, #{end}")
	public List<Map<String, Object>> list(Map<String, Object> param);

	@Select("SELECT exam_no, subject, difficulty,  DATE_FORMAT(dt, '%Y-%m-%d %H:%i:%s')  dt FROM exam WHERE subject LIKE '%${subject}%' AND difficulty LIKE '%${difficulty}%' ORDER BY exam_no DESC  LIMIT #{start}, #{end}")
	public List<Map<String, Object>> detail(Map<String, Object> param);
	
	@Select("SELECT count(*) FROM exam WHERE subject LIKE '%${subject}%' AND difficulty LIKE '%${difficulty}%'")
	public int count(Map<String, Object> param);

	
	@Insert("INSERT INTO exam(subject, difficulty, content, dt , test_input, test_output, writer) "
			+ "VALUES(#{subject}, #{difficulty}, #{content}, now(), #{test_input}, #{test_output}, #{writer})")
	@Options(useGeneratedKeys=true, keyColumn="exam_no", keyProperty="exam_no")
	public int writeExam(Map<String, Object> param);
	
	@Select("SELECT max(exam_no) FROM exam")
	public int getMaxNo();
	
	@Insert("INSERT INTO exam_testcase(exam_no, input, output) "
			+ "VALUES(#{exam_no}, #{input}, #{output})")
	public int writeTestcase(Map<String, String> param);
	
	
	@Insert("INSERT INTO exam(subject, difficulty, content, dt , test_input, test_output, writer) "
			+ "VALUES(#{subject}, #{difficulty}, #{content}, now(), #{test_input}, #{test_output}, #{writer})")
	@Options(useGeneratedKeys=true, keyColumn="exam_no", keyProperty="exam_no")
	public int addTourna(Map<String, Object> param);

	@Select("SELECT max(exam_no) FROM exam")
	public int getTournaMaxNo();
	
}