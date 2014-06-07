package pso.rap.web.cf;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/cfdetails")
public class CfDetailsController {
	@Autowired
	private Environment environment;
	
	@RequestMapping("/rest/vcap")
	@ResponseBody
	public Map<String, String> vcapProperties() {
		Map<String, String> vcap = new LinkedHashMap<String, String>();
		vcap.put("VCAP_APPLICATION", environment.getProperty("VCAP_APPLICATION", "{}"));
		vcap.put("VCAP_SERVICES", environment.getProperty("VCAP_SERVICES", "{}"));
		vcap.putAll(System.getenv());
		return vcap;
	}
	

	@RequestMapping(value="/partialsVcap", method=RequestMethod.GET)
	public String partialsVcap() {
		return "cf/partialsVcap::content";
	}	
}
