package kr.devdogs.algorhythm.template.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.devdogs.algorhythm.template.mapper.TemplateMapper;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
	@Autowired TemplateMapper templateMapper;
	
	@Override
	public String getTemplate(String language) {
		return templateMapper.getTemplate(language);
	}

}
