package com.study.juhee.stringboot.domain.posts;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @AfterEach  // Junit 테스트가 끝날때마다 실행되는 메서드.
    public void cleanup() {
        postsRepository.deleteAll();
    } // 다음 테스트 시 오류나지 않도록 지워준다.

    @Test
    public void 게시글저장_불러오기() {
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        // update or save를 함 / ID값이 있는지 없는지에 따라 결정됨
        postsRepository.save(Posts.builder()
        .title(title)
        .content(content)
        .author("juheeya401@gmail.com")
        .build());

        // when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        //given
        //LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(">>>>>>>>>>>>> now=" + now);

        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>>>>>>> createData=" + posts.getCreatedDate()
                + ", modifiedDate=" + posts.getModifiedDate());

        assertThat(posts.getCreatedDate()).isAfterOrEqualTo(now);
        assertThat(posts.getModifiedDate()).isAfterOrEqualTo(now);
    }
}