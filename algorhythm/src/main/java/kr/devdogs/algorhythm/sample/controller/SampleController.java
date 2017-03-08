package kr.devdogs.algorhythm.sample.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import kr.devdogs.algorhythm.sample.dto.Sample;
import kr.devdogs.algorhythm.sample.service.SampleService;
import kr.devdogs.algorhythm.visiter.dto.VisitCount;
import kr.devdogs.algorhythm.utils.FileUtils;
import kr.devdogs.algorhythm.utils.WebUtils;


/**
 * 
 * @author Daniel
* 스프링이 처음인 프렌드들을 위해 보고 따라할 수 있는 샘플 컨트롤러
 * 컨트롤러에는 웹에 종속된 동작만을 처리해야한다. 
 * 예를 들면 파라미터 유효성검사, 서비스사용 
 */
@RestController
public class SampleController {
	//변수명은 Service클래스에서 골뱅이로 선언한 서비스이름과 같아야한다
	@Autowired private SampleService sampleService;
	@Autowired private FileUtils fileUtils;
	
	/** GET방식으로 받는 예제 
	 *  RequestParam 어노테이션으로 intsample이라는 파라미터를 받고, 파라미터가 없다면 기본값으로 1을 준
	 *  requiresample이라는 파라미터는 값이 없다면 오류를 출력한다.
	 *  보다시피 파라미터는 지정해준 자료형으로 자동변환되어 변수에들어간다(엉뚱한 자료형넣으면 오류)
	 *  기타 마지막 매개변수처럼 세션을 가져올 수도 있다.
	 *  RestController이기 때문에 res객체에 담겨 반환되는 모든 값들은 JSON으로 변환되어 전송된다.
	 * 
	 */
	@RequestMapping(value="/api/getsample", method=RequestMethod.GET)
	public Map<String, Object> getSample(@RequestParam(name="intsample", defaultValue="1") int page, 
									@RequestParam(name="requiresample", required=true) String findString, 
										HttpSession session) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		res.put("result", "result");
		return res;
	}
	
	/**
	 *  파일업로드를 위한 MultipartFile을 받는 예제 
	 *  POST로 받는다
	 *  위에서 선언한 FileUtils 유틸리티로 파일업로드를 수행한다. 
	 *  uploadFile 메소드는 업로드된 경로를 반환한다. null이 들어간곳에 문자열을입력하면 추가 상세경로설정이 가능.
	 *  두 번째 매개변수처럼 파라미터값을 객체에 매핑해서 받을수도있다. 짱좋다.
	 */
	@RequestMapping(value="/api/file/upload", method=RequestMethod.POST)
	public Map<String, Object> fileUpload(@RequestParam("upload") MultipartFile uploadedFile, Sample sample, HttpSession session) {
		Map<String, Object> res = new HashMap<String, Object>();
		
		String uploadedPath = fileUtils.uploadFile(uploadedFile, null);
		
		if(uploadedPath == null) {
			Map<String, String> msg = new HashMap<String, String>();
			res.put("uploaded", 0);
			msg.put("message", "업로드에 실패했습니다.");
			res.put("error", msg);
			return res;
		}
		
		sample.setColumn2(uploadedPath);
		// 이와 같이 Service계층으로 값을 넘긴다.
		sampleService.insert(sample);
		
		res.put("uploaded", 1);
		res.put("fileName", uploadedFile.getOriginalFilename());
		res.put("url", uploadedPath);
		return res;
	}
	
	/** 
	 * 	URL에서 파라미터를 받는방법. 엄연히 말하면 파라미터는 아니고 Restful 방식의 API를 위해 사용 
	 * PathVariable 어노테이션으로 받을 수 있다.
	 *  */
	@RequestMapping(value="/api/get/{no}", method=RequestMethod.GET)
	public Map<String, Object> getPathVariable(@PathVariable(value="no") int no, 
										HttpServletRequest request,
										HttpSession session) {
		Map<String, Object> res = new HashMap<String, Object>();
		res.put("receive_no", no);
		return res;
	}
}