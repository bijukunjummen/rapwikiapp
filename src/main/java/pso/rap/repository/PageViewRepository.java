package pso.rap.repository;

import org.springframework.data.repository.CrudRepository;
import pso.rap.domain.Band;
import pso.rap.domain.PageView;

public interface PageViewRepository extends CrudRepository<PageView, Long> {
	PageView findByBand(Band band);
}
