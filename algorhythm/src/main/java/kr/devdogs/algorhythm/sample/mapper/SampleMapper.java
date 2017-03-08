package kr.devdogs.algorhythm.sample.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import kr.devdogs.algorhythm.sample.dto.Sample;

@Mapper
public interface SampleMapper {
	
	// 리턴값은 적용된 라인수 
	@Insert("INSERT SQL")
	public int insert(Sample sample);
	
	// Sample 여러개를 조회할 때 
	// #{변수명 or Map의 키이름} : 자동으로 따옴표 escape  ex) 박중수  =>  where name=#{name}  => where name= '박중수'
	// ${변수명 or Map의 키이름} : 따옴표 escape를 안해줌  ex) 박중수  =>  where name=${name}  => where name= 박중수
	@Select("SELECT COL1, COL2 FROM SAMPLE WHERE escape=#{column1} AND not_escape =${column2}")
	public List<Sample> getList(Sample params);
	
	// Sample 1개만 조회할 때 
	@Select("SELECT SQL")
	public Sample getOne(Map<String, Object> params);
	
	//리턴값은 적용된 라인수 
	@Update("Update SQL")
	public int update(Map<String, Object> params);
	
	// 리턴값은 적용된 라인수 
	//이거의 매개변수처럼 Map이나 DTO형식의 오브젝트가 아니면 #{value}로 매개변수를 값으로 전달가능
	@Delete("DELETE FROM SAMPLE WHERE no=#{value}")
	public int delete(int no);
	
}
