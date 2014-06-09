package pso.rap.repository;


import org.springframework.data.repository.CrudRepository;
import pso.rap.domain.Band;
import pso.rap.domain.BandSummary;

public interface BandSummaryRepository extends CrudRepository<BandSummary, Long> {
	BandSummary findByBand(Band band);
}
