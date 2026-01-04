package com.back.boundedContext.postQuery.v1.out;

import com.back.boundedContext.postQuery.v1.domain.PostDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PostDocumentRepository extends ElasticsearchRepository<PostDocument, Integer> {
}
