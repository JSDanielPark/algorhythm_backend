package kr.devdogs.algorhythm.visiter.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import kr.devdogs.algorhythm.visiter.dto.VisitCount;

@Mapper
public interface VisitMapper {
	/** 접속 로그 */
	@Insert("INSERT INTO visiter_count(dt, ip) VALUES(now(), #{ip})")
	public int addContactLog(VisitCount info);
}
