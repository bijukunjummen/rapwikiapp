package pso.rap.web.bands;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/rap/bands")
public class BandsController {

	@RequestMapping("/home")
	public String home() {
		return "bands/home";
	}


	@RequestMapping("/partialsHome")
	public String partialsHome() {
		return "bands/partialsHome";
	}

}
