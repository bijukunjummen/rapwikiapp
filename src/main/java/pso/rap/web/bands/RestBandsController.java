package pso.rap.web.bands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pso.rap.domain.Band;
import pso.rap.domain.BandSummary;
import pso.rap.domain.PageView;
import pso.rap.repository.BandRepository;
import pso.rap.Utils;
import pso.rap.repository.BandSummaryRepository;
import pso.rap.service.PageAccessGateway;
import pso.rap.service.PageViewService;

import java.util.List;

@RestController
@RequestMapping("/rest/bands")
public class RestBandsController {

	@Autowired
	private BandRepository bandRepository;

	@Autowired
	private BandSummaryRepository bandSummaryRepository;

	@Autowired
	private PageAccessGateway pageAccessGateway;

	@Autowired
	private PageViewService pageViewService;

	@RequestMapping(method = RequestMethod.GET)
	public List<Band> list() {
		return Utils.iterablesToList(this.bandRepository.findAll());
	}

	@RequestMapping(value="/bandSummary", method = RequestMethod.GET)
	public BandSummary getBandSummary(long bandId) {
		Band band = this.bandRepository.findOne(bandId);
		this.pageAccessGateway.recordAccess(bandId);
	   return this.bandSummaryRepository.findByBand(band);
	}

	@RequestMapping(value="/topPageView", method=RequestMethod.GET)
	public List<PageView> topPageView() {
		return this.pageViewService.top5ByPageViews();
	}

	@RequestMapping(value="/bandSummary", method = RequestMethod.PUT)
	public BandSummary updateBandSummary(@RequestBody BandSummary bandSummary) {
		Band band = bandSummary.getBand();
		this.bandRepository.save(band);
		return this.bandSummaryRepository.save(bandSummary);
	}

	@RequestMapping(value="/bandSummary", method = RequestMethod.POST)
	public BandSummary createBandSummary(@RequestBody BandSummary bandSummary) {
		Band band = bandSummary.getBand();
		Band bandFromDb = this.bandRepository.save(band);
		bandSummary.setBand(bandFromDb);
		return this.bandSummaryRepository.save(bandSummary);
	}
}
