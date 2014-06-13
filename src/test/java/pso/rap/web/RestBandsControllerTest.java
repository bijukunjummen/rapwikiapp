package pso.rap.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import pso.rap.repository.BandRepository;
import pso.rap.repository.BandSummaryRepository;
import pso.rap.service.PageAccessGateway;
import pso.rap.service.PageViewService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * A Basic Spring MVC Test for the Rest Bands Controller
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
public class RestBandsControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
	}

	@Autowired
	private PageAccessGateway pageAccessGateway;

	@Test
	public void testGetBandSummaryShouldResultInPageAccessBeingRecorded() throws Exception {
		this.mockMvc.perform(get("/rest/bands/bandSummary").param("bandId","100"))
			.andExpect(status().isOk());
		this.mockMvc.perform(get("/rest/bands/bandSummary").param("bandId","100"))
				.andExpect(status().isOk());
		verify(pageAccessGateway, times(2));
	}

	@Configuration
	@ComponentScan("pso.rap.web")
	@EnableWebMvc
	static class ContextConfig {

		@Bean
		public BandRepository bandRepository() {
			return mock(BandRepository.class);
		}

		@Bean
		public BandSummaryRepository bandSummaryRepository() {
			return mock(BandSummaryRepository.class);
		}

		@Bean
		public PageAccessGateway pageAccessGateway() {
			return mock(PageAccessGateway.class);
		}

		@Bean
		public PageViewService pageViewService() {
			return mock(PageViewService.class);
		}

	}
}
