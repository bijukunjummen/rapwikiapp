package pso.rap.init;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.ArrayList;
import java.util.List;

@Component
public class RapDataInitializer {

	@Autowired
	private BandRepository bandRepository;

	@Autowired
	private BandSummaryRepository bandSummaryRepository;

	@PostConstruct
	public void postConstruct() {
		ClassPathResource bandsJson = new ClassPathResource("banddata/bands.json");


		ObjectMapper objectMapper = new ObjectMapper();
		List<Band> bands = new ArrayList<Band>();
		try {
			bands = objectMapper.readValue(bandsJson.getInputStream(), new TypeReference<List<Band>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Band band : bands) {
			Band dbBand = this.bandRepository.findByName(band.getName());

			if (dbBand == null) {
				System.out.println("**********About to save :" + band.getId() + ":" + band.getName());
				band = this.bandRepository.save(band);
				saveBandSummary(band);
			}
		}
	}

	private void saveBandSummary(Band band) {
		Band fromDb = this.bandRepository.findOne(band.getId());

		if (fromDb!=null) {
			long id = fromDb.getId();
			String twithash = fromDb.getTwithash();
			BandSummary bandSummary = bandSummaryRepository.findByBand(fromDb);
			if (bandSummary==null) {
				ClassPathResource awardsResource = new ClassPathResource("banddata/awards-" + twithash + ".txt");
				ClassPathResource summaryResource = new ClassPathResource("banddata/summary-" + twithash + ".txt");
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
				bandSummary.setVersion(1l);
				bandSummary.setBand(band);
				this.bandSummaryRepository.save(bandSummary);
			}
		}
	}
}


