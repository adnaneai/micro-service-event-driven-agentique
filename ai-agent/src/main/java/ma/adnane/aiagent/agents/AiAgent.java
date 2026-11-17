package ma.adnane.aiagent.agents;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.stereotype.Component;

@Component
public class AiAgent {

    private ChatClient chatClient;

    public AiAgent(ChatClient.Builder chatClient,
                   ToolCallbackProvider toolCallbackProvider,
                   ChatMemory chatMemory){
        this.chatClient = chatClient.defaultTools(toolCallbackProvider)
                .defaultAdvisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .build();
    }

    public String chat(String query){
        return chatClient.prompt(query)
                .advisors(advisor -> advisor.param(ChatMemory.CONVERSATION_ID,"C1"))
                 .call().content();
    }
}
