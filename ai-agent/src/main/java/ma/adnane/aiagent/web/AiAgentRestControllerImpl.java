package ma.adnane.aiagent.web;

import lombok.AllArgsConstructor;
import ma.adnane.aiagent.agents.AiAgent;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AiAgentRestControllerImpl implements AiAgentRestController{
    private AiAgent aiAgent;
    @Override
    @GetMapping(value = "/chat",produces = MediaType.TEXT_PLAIN_VALUE)
    public String chat(String query) {
        return aiAgent.chat(query);
    }
}
