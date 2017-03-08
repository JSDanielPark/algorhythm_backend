package kr.devdogs.algorhythm.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.devdogs.algorhythm.sample.dto.Sample;
import kr.devdogs.algorhythm.sample.mapper.SampleMapper;
import kr.devdogs.algorhythm.visiter.dto.VisitCount;

/**
 * 
 * @author Daniel
 * Service 객체에는 비즈니스 로직이 들어간다. 웹에 종속되지 않은 로직들을 여기서 구현한다.
 */
@Service("sampleService")
public class SampleService {
	@Autowired private SampleMapper sampleMapper;
	
	// 이런식으로 mapper의 메소드로 데이터를 조작한다.
	public int insert(Sample sample) {
		return sampleMapper.insert(sample);
	}
	
}
