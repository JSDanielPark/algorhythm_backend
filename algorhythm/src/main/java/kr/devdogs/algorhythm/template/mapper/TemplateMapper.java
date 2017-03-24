package kr.devdogs.algorhythm.template.mapper;

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
public interface TemplateMapper {
	@Insert("INSERT INTO lang_template(lang, template) VALUES(#{lang}, #{value})")
	public int addTemplate(Map<String, Object> param);
	
	@Select("SELECT template FROM lang_template WHERE lang=#{value}")
	public String getTemplate(String language);
}
