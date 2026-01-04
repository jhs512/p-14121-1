package com.back.boundedContext.post.in;

import com.back.boundedContext.post.app.PostFacade;
import com.back.shared.member.event.MemberJoinedEvent;
import com.back.shared.member.event.MemberModifiedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class PostEventListener {
    private final PostFacade postFacade;

    @KafkaListener(topics = "MemberJoinedEvent", groupId = "PostEventListener__handle")
    @Transactional
    public void handle(MemberJoinedEvent event) {
        postFacade.syncMember(event.getMember());
    }

    @KafkaListener(topics = "MemberModifiedEvent", groupId = "PostEventListener__handle")
    @Transactional
    public void handle(MemberModifiedEvent event) {
        postFacade.syncMember(event.getMember());
    }
}
