package pso.rap.web.bands;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bands")
public class BandsController {

	@RequestMapping("/home")
	public String home() {
		return "bands/home";
	}

}
