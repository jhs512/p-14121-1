package com.back.boundedContext.postQuery.v1.in;

import com.back.boundedContext.post.out.PostRepository;
import com.back.boundedContext.postQuery.v1.domain.PostDocument;
import com.back.boundedContext.postQuery.v1.out.PostDocumentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Configuration
@Slf4j
public class PostQueryDataInit {
    private final PostQueryDataInit self;
    private final PostRepository postRepository;
    private final PostDocumentRepository postDocumentRepository;
    private final ElasticsearchOperations elasticsearchOperations;

    public PostQueryDataInit(
            @Lazy PostQueryDataInit self,
            PostRepository postRepository,
            PostDocumentRepository postDocumentRepository,
            ElasticsearchOperations elasticsearchOperations
    ) {
        this.self = self;
        this.postRepository = postRepository;
        this.postDocumentRepository = postDocumentRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }

    @Bean
    @Order(3)
    public ApplicationRunner postQueryDataInitApplicationRunner() {
        return args -> {
            self.recreatePostIndex();
        };
    }

    @Transactional
    public void recreatePostIndex() {
        IndexOperations indexOps = elasticsearchOperations.indexOps(PostDocument.class);

        if (indexOps.exists()) {
            indexOps.delete();
            log.debug("v1_post 인덱스 삭제");
        }

        indexOps.create();
        indexOps.putMapping(indexOps.createMapping());
        log.debug("v1_post 인덱스 생성");

        List<PostDocument> postDocuments = postRepository.findAll()
                .stream()
                .map(PostDocument::from)
                .toList();

        postDocumentRepository.saveAll(postDocuments);
        log.debug("v1_post 인덱스에 {} 개의 문서 저장", postDocuments.size());
    }
}
