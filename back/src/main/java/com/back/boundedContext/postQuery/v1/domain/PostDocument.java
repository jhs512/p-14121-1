package com.back.boundedContext.postQuery.v1.domain;

import com.back.boundedContext.post.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDateTime;

@Document(indexName = "v1_post")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostDocument {
    @Id
    private int id;
    @Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime createDate;
    @Field(type = FieldType.Date, pattern = "uuuu-MM-dd'T'HH:mm:ss.SSSSSS")
    private LocalDateTime modifyDate;
    @Field(type = FieldType.Text)
    private String title;
    @Field(type = FieldType.Text)
    private String content;
    @Field(type = FieldType.Keyword)
    private String author;

    public static PostDocument from(Post post) {
        return PostDocument.builder()
                .id(post.getId())
                .createDate(post.getCreateDate())
                .modifyDate(post.getModifyDate())
                .title(post.getTitle())
                .content(post.getContent())
                .author(post.getAuthor().getNickname())
                .build();
    }
}
