package pso.rap.web.bands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pso.rap.domain.Band;
import pso.rap.domain.BandSummary;
import pso.rap.repository.BandRepository;
import pso.rap.Utils;
import pso.rap.repository.BandSummaryRepository;

import java.util.List;

@Controller
@RequestMapping("/rap/rest/bands")
public class RestBandsController {

	@Autowired
	private BandRepository bandRepository;

	@Autowired
	private BandSummaryRepository bandSummaryRepository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public List<Band> list() {
		return Utils.iterablesToList(this.bandRepository.findAll());
	}

	@RequestMapping(value="/bandSummary", method = RequestMethod.GET)
	@ResponseBody
	public BandSummary getBandSummary(long bandId) {
		Band band = this.bandRepository.findOne(bandId);
	   return this.bandSummaryRepository.findByBand(band);
	}
}
