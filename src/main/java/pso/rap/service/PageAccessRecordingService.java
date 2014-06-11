package pso.rap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pso.rap.domain.Band;
import pso.rap.domain.PageView;
import pso.rap.repository.BandRepository;
import pso.rap.repository.PageViewRepository;

@Service
public class PageAccessRecordingService {

	private PageViewRepository pageViewRepository;

	private BandRepository bandRepository;

	@Autowired
	public PageAccessRecordingService(PageViewRepository pageViewRepository, BandRepository bandRepository) {
		this.pageViewRepository = pageViewRepository;
		this.bandRepository = bandRepository;
	}

	public void recordAccess(Long bandId) {
		Band band = this.bandRepository.findOne(bandId);
		PageView pageView = this.pageViewRepository.findByBand(band);

		if (pageView==null) {
			pageView = new PageView();
			pageView.setCounter(0);
			pageView.setBand(band);
		}
		pageView.setCounter(pageView.getCounter() + 1);
		this.pageViewRepository.save(pageView);
	}
}
