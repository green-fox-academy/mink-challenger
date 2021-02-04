package challenger.mink.users;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OauthController {
  public OauthController() {
  }

  @Value("${CLIENT_ID}")
  private String clientId;
  @Value("${CLIENT_SECRET}")
  public String clientSecret;

  @GetMapping("/callback")
  public ModelAndView forwardAndOnward(@RequestParam String code) {
    return new ModelAndView("redirect:" + "https://github.com/login/oauth/access_token?" + "client_id="  +
        clientId + "&client_secret=" + clientSecret + "&code=" + code);
  }

}
