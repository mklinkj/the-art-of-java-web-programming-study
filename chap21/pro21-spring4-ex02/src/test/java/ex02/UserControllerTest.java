package ex02;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class UserControllerTest {

  @Autowired private WebApplicationContext context;

  private MockMvc mockMvc;

  @Before
  public void before() {
    mockMvc = webAppContextSetup(context).build();
  }

  @Test
  public void testWebApplicationContext() {
    LOGGER.info("### 웹 컨텍스트 로드 ###");
    assertThat(context).isNotNull();
  }

  @Test
  public void testLoginDo() throws Exception {
    mockMvc
        .perform(
            post("/test/login.do") //
                .param("userID", "사용자 ID")
                .param("passwd", "사용자 암호"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(model().attribute("userID", "사용자 ID"))
        .andExpect(model().attribute("passwd", "사용자 암호"))
        .andExpect(forwardedUrl("/test/result.jsp"));
  }
}
