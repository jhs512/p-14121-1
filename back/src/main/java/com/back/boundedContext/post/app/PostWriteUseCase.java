package com.back.boundedContext.post.app;

import com.back.boundedContext.post.domain.Post;
import com.back.boundedContext.post.domain.PostMember;
import com.back.boundedContext.post.out.PostRepository;
import com.back.global.eventPublisher.EventPublisher;
import com.back.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriteUseCase {
    private final PostRepository postRepository;
    private final EventPublisher eventPublisher;

    public RsData<Post> write(PostMember author, String title, String content) {
        Post post = postRepository.save(new Post(author, title, content));

        return new RsData<>(
                "201-1",
                "%d번 글이 생성되었습니다."
                        .formatted(post.getId()),
                post
        );
    }
}
