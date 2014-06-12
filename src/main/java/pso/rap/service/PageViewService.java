package pso.rap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pso.rap.domain.PageView;
import pso.rap.repository.PageViewRepository;

import java.util.List;

@Service
public class PageViewService {
	private final PageViewRepository pageViewRepository;

	@Autowired
	public PageViewService(PageViewRepository pageViewRepository) {
		this.pageViewRepository = pageViewRepository;
	}

	public List<PageView> top5ByPageViews() {
		Pageable page = new PageRequest(0, 5);
		return this.pageViewRepository.findAllOrderByCounterDesc(page);
	}
}
