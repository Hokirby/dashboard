package com.example.dashboard.client;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

    // 요약 내용 생성
    public String generateSummaryText(String content, String type) {

        String promptText = String.format("""
                아래는 사용자의 메모입니다.
                핵심 내용을 요약해 주세요. 핵심 키워드와 인사이트를 포함헤 2~3 문장으로 간결하게 작성해주세요.
                
                [사용자 메모]
                %s
                """, type, content
        );

        Prompt prompt = new Prompt("당신은 훌륭한 비서입니다. 주어진 메모를 분석하고 깔끔하게 요약해주세요." + "\n\n" + promptText);

        ChatResponse response = chatClient.prompt(prompt)
                .call()
                .chatResponse();

        return response.getResult().getOutput().getText();
    }
}
