
package kr.devdogs.algorhythm.tourna.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TournaMapper {
	
	@Select("SELECT exam_no, subject, difficulty,  DATE_FORMAT(dt, '%Y-%m-%d %H:%i:%s')  dt FROM exam WHERE subject LIKE '%${subject}%' AND difficulty LIKE '%${difficulty}%' ORDER BY exam_no DESC  LIMIT #{start}, #{end}")
	public List<Map<String, Object>> list(Map<String, Object> param);
	
	@Select("SELECT count(*) FROM exam WHERE subject LIKE '%${subject}%' AND difficulty LIKE '%${difficulty}%'")
	public int count(Map<String, Object> param);
}
