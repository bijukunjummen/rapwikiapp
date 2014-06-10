package pso.rap.init;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import pso.rap.domain.Band;
import pso.rap.domain.BandSummary;
import pso.rap.repository.BandRepository;
import pso.rap.repository.BandSummaryRepository;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class RapDataInitializer {

	@Autowired
	private BandRepository bandRepository;

	@Autowired
	private BandSummaryRepository bandSummaryRepository;

	@PostConstruct
	public void postConstruct() {
		for (long i=1;i<=10;i++) {
			Band band = this.bandRepository.findOne(i);
			if (band!=null) {
				BandSummary bandSummary = bandSummaryRepository.findByBand(band);
				if (bandSummary==null) {
					ClassPathResource awardsResource = new ClassPathResource("banddata/awards-" + i + ".txt");
					ClassPathResource summaryResource = new ClassPathResource("banddata/summary-" + i + ".txt");
					String awards = "";
					String summary = "";
					try {
						awards = IOUtils.toString(awardsResource.getInputStream());
						summary = IOUtils.toString(summaryResource.getInputStream());
					} catch (IOException e) {
						e.printStackTrace();
					}
					bandSummary = new BandSummary();
					bandSummary.setAwards(awards);
					bandSummary.setSummary(summary);
					bandSummary.setVersion(0l);
					bandSummary.setBand(band);
					this.bandSummaryRepository.save(bandSummary);
				}
			}
		}
	}
}
