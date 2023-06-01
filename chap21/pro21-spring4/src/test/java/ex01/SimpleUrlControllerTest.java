package ex01;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/action-servlet.xml")
@WebAppConfiguration
public class SimpleUrlControllerTest {

  @Autowired private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void before() {
    mockMvc = webAppContextSetup(context).build();
  }

  @Test
  public void testWebApplicationContext() {
    assertThat(context).isNotNull();
  }

  @Test
  public void testIndex() throws Exception {
    mockMvc
        .perform(get("/test/index.do"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("index.jsp"));
  }
}
