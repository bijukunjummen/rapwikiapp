package pso.rap.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import pso.rap.domain.Band;
import pso.rap.domain.PageView;

import java.util.List;

public interface PageViewRepository extends PagingAndSortingRepository<PageView, Long> {
	PageView findByBand(Band band);

	@Query("select v from PageView v order by counter desc")
	List<PageView> findAllOrderByCounterDesc(Pageable page);
}
