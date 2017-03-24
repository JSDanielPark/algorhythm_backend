package kr.devdogs.algorhythm.exam.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import kr.devdogs.algorhythm.sample.dto.Sample;

@Mapper
public interface ExamMapper {
	
	@Select("SELECT count(*) FROM exam WHERE subject LIKE '%${subject}%' AND difficulty LIKE '%${difficulty}%'")
	public int count(Map<String, Object> param);
	
	//@Select("SELECT exam_no, subject, difficulty,  DATE_FORMAT(dt, '%Y-%m-%d %H:%i:%s')  dt FROM exam WHERE subject LIKE '%${subject}%.exam_no GROUP BY a.exam_no;' AND difficulty LIKE '%${difficulty}%' ORDER BY exam_no DESC  LIMIT #{start}, #{end}")
	@Select("SELECT exam_no, subject, difficulty,  DATE_FORMAT(dt, '%Y-%m-%d %H:%i:%s')  dt FROM exam WHERE subject LIKE '%${subject}%' AND difficulty LIKE '%${difficulty}%' ORDER BY exam_no DESC  LIMIT #{start}, #{end}")
	public List<Map<String, Object>> list(Map<String, Object> param);
	
	@Select("SELECT exam_no, subject, content, difficulty,  DATE_FORMAT(dt, '%Y-%m-%d %H:%i:%s')  dt, test_input, test_output FROM exam WHERE exam_no=#{value}")
	public Map<String, Object> get(int examNo);
	
	@Insert("INSERT INTO exam(subject, difficulty, content, dt , test_input, test_output, writer) "
			+ "VALUES(#{subject}, #{difficulty}, #{content}, now(), #{test_input}, #{test_output}, #{writer})")
	@Options(useGeneratedKeys=true, keyColumn="exam_no", keyProperty="exam_no")
	public int writeExam(Map<String, Object> param);
	
	@Select("SELECT max(exam_no) FROM exam")
	public int getMaxNo();
	
	
	@Insert("INSERT INTO exam_testcase(exam_no, input, output) "
			+ "VALUES(#{exam_no}, #{input}, #{output})")
	public int writeTestcase(Map<String, String> param);
	
	@Select("SELECT input, output FROM exam_testcase WHERE exam_no=#{value}")
	public List<Map<String, Object>> getTestcase(int examNo);
	
	@Insert("INSERT INTO exam_result(member_no, exam_no, score, solv_time, dt, start_date, end_date) "
			+  "VALUES(#{userNo}, #{examNo}, #{score}, 0, now(), now(), now())")
	public int writeRecord(Map<String, Object> param);
	
	@Select("SELECT B.nickname, A.member_no, round(avg(A.score), 2) score FROM (SELECT member_no, exam_no, score, min(dt) FROM exam_result GROUP BY member_no, exam_no) A, member B WHERE A.member_no=B.member_no GROUP BY member_no ORDER BY score DESC LIMIT 0, #{value}")
	public List<Map<String, Object>> getRank(int count);
	
	@Select("SELECT a.member_no,a.exam_no, max(a.score) max_score, b.subject, b.difficulty, DATE_FORMAT(a.dt, '%Y-%m-%d %H:%i:%s')  dt FROM exam_result a, exam b WHERE a.exam_no=b.exam_no AND a.member_no=#{member_no} GROUP BY a.exam_no  ORDER BY a.dt DESC LIMIT #{start}, #{end}")
	public List<Map<String, Object>> getMyExam(Map<String, Object> param);
	
	@Select("SELECT count(*) FROM (SELECT a.member_no,a.exam_no, max(a.score) max_score, b.subject, b.difficulty, a.dt FROM exam_result a, exam b WHERE a.exam_no=b.exam_no AND a.member_no=#{member_no} GROUP BY a.exam_no) A")
	public int myExamCount(Map<String, Object> param);
}
