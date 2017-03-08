package kr.devdogs.algorhythm.visiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.devdogs.algorhythm.visiter.dto.VisitCount;
import kr.devdogs.algorhythm.visiter.mapper.VisiterMapper;

@Service("userService")
public class UserService {
	@Autowired private VisiterMapper userMapper;
	
	public int addContactLog(VisitCount info) {
		return userMapper.addContactLog(info);
	}
}
