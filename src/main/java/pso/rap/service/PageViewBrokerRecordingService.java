package pso.rap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import pso.rap.domain.PageView;

import java.util.List;

@Service
public class PageViewBrokerRecordingService {

	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	public void sendPageViewsToTopic(List<PageView> pageViewList) {
		this.simpMessagingTemplate.convertAndSend("/topic/pageview.all", pageViewList);
	}

}
