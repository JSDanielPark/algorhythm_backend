
package kr.devdogs.algorhythm.tourna.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TournaMapper {
	
	@Select("SELECT Tourna_No, Title, DATE_FORMAT(Reg_Date, '%Y-%m-%d %H:%i:%s')  Reg_Date FROM Tourna_List ORDER BY Tourna_No DESC  LIMIT #{start}, #{end}")
	public List<Map<String, Object>> list(Map<String, Object> param);

	@Select("SELECT count(*) FROM Tourna_Detail_List WHERE Title LIKE '%${Title}%' AND difficult LIKE '%${difficulty}%'")
	public int count(Map<String, Object> param);

	@Select("SELECT Tourna_Detail_No, Title, Difficult,  DATE_FORMAT(Reg_Date, '%Y-%m-%d %H:%i:%s')  Reg_Date FROM Tourna_Detail_List WHERE title LIKE '%${title}%' AND Difficult LIKE '%${difficulty}%' AND Tourna_No = '${tourna_no}' ORDER BY Tourna_Detail_No DESC  LIMIT #{start}, #{end}")
	public List<Map<String, Object>> detail(Map<String, Object> param);
	
	@Select("SELECT count(*) FROM Tourna_List")
	public int count_List(Map<String, Object> param);
	
	@Insert("INSERT INTO tourna_testcase(tourna_no, input, output) "
			+ "VALUES(#{tourna_no}, #{input}, #{output})")
	public int writeTestcase(Map<String, String> param);
	
	@Insert("insert into Tourna_Detail_List(Tourna_No, Title, Difficult, Content, Reg_Date, Test_Input, Test_Output, Writer) "
			+ "VALUES(#{tourna_no}, #{subject}, #{difficulty}, #{content}, now(), #{test_input}, #{test_output}, #{writer})")
	@Options(useGeneratedKeys=true, keyColumn="tourna_no", keyProperty="tourna_no")
	public int writeTourna(Map<String, Object> param);
	
	@Insert("insert into Tourna_List(Title,Writer,Reg_Date) "
			+ "VALUES(#{subject}, #{writer}, now())")
	public int addTourna(Map<String, Object> param);

	@Select("SELECT max(tourna_no) FROM tourna_list")
	public int getTournaMaxNo();
	
}