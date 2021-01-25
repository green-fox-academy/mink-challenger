package challenger.mink.users;

import java.util.NoSuchElementException;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//import kong.unirest.Unirest;
//import kong.unirest.UnirestException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import kong.unirest.HttpResponse;
//import kong.unirest.JsonNode;
//import lombok.RequiredArgsConstructor;
//import com.greenfoxacademy.springwebapp.user.UserRepository;
//import com.greenfoxacademy.springwebapp.user.exceptions.NoSuchUserException;


@RequiredArgsConstructor
@Component
public class MailGun {

  private final UserRepository userRepository;

  public static final String YOUR_DOMAIN_NAME =
      "sandboxd89b3ede17c8401cbfbbc4f93c53ccda.mailgun.org";

  @Value("${API_KEY}")
  public String apiKey;

  public void sendSimpleMessage(String uuid) {
    Unirest.post("https://api.mailgun.net/v3/" + YOUR_DOMAIN_NAME + "/messages")
        .basicAuth("api", apiKey)
        .queryString("from", "Excited User <ws.eclipse@gmail.com>")
        .queryString("to",
            userRepository.findUserByUuid(uuid).orElseThrow(() -> new NoSuchElementException("no"))
                .getEmail())
        .queryString("subject", "listen up")
        .queryString("html", responseEmail(
            userRepository.findUserByUuid(uuid).orElseThrow(() -> new NoSuchElementException("no"))
                .getUsername(), uuid))
        .asJson();
  }

  private String responseEmail(String username, String uuid) {
    return
        "<html>"
            + "<body>" + "<p>"
            + "Hello there,  "
            + username
            + "!"
            + "</p>"
            + "<p>"
            + "Click the link or the picture to verify your email address. "
            + "<br>"
            + "<a href=\"http://localhost:8080/verify/"
            + uuid
            + "\">"
            +
            "<img src=\"https://i.pinimg.com/originals/ac/d9/81/acd981a162499b120ebcaad5b5258981.jpg\">"
            + "</a>"
            + "<br>"
            + "<a href = \"http://localhost:8080/verify/"
            + uuid
            + "\">"
            + "the link"
            + "</a>"
            + "</p>"
            + "</body>"
            + "</html>";
  }
}

