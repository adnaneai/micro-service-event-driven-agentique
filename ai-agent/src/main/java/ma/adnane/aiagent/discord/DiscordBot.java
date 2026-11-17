package ma.adnane.aiagent.discord;

import com.zgamelogic.discord.annotations.DiscordController;
import com.zgamelogic.discord.annotations.DiscordMapping;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.adnane.aiagent.agents.AiAgent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@DiscordController
@AllArgsConstructor
@Slf4j
public class DiscordBot {
    private AiAgent agent;

    @DiscordMapping
    public void perform(MessageReceivedEvent messageReceivedEvent){
        if (messageReceivedEvent.getAuthor().isBot())
            return;
        String query = messageReceivedEvent.getMessage().getContentRaw();
        if (query.isBlank()) {
            return;
        }
        try {
            String response = agent.chat(query);
            if (response == null || response.isBlank()) {
                response = "Je n'ai pas reçu de réponse du modèle. Vérifie qu'Ollama est démarré et que le modèle est disponible.";
            }
            messageReceivedEvent.getChannel().sendMessage(response).queue();
        } catch (Exception exception) {
            log.error("Échec du traitement d'un message Discord", exception);
            messageReceivedEvent.getChannel()
                    .sendMessage("Je n'arrive pas à joindre le modèle ou le service ACCOUNT. Vérifie les journaux de ai-agent et que Ollama/ACCOUNT sont démarrés.")
                    .queue();
        }
    }
}
