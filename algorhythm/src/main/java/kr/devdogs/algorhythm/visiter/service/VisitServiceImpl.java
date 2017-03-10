package kr.devdogs.algorhythm.visiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.devdogs.algorhythm.visiter.dto.VisitCount;
import kr.devdogs.algorhythm.visiter.mapper.VisitMapper;

@Service("visitService")
public class VisitServiceImpl implements VisitService {
	@Autowired private VisitMapper visitMapper;
	
	public int addContactLog(VisitCount info) {
		return visitMapper.addContactLog(info);
	}
}
