package com.study.juhee.stringboot.service.posts;

import com.study.juhee.stringboot.domain.posts.Posts;
import com.study.juhee.stringboot.domain.posts.PostsRepository;
import com.study.juhee.stringboot.web.dto.PostsListResponseDto;
import com.study.juhee.stringboot.web.dto.PostsResponseDto;
import com.study.juhee.stringboot.web.dto.PostsSaveRequestDto;
import com.study.juhee.stringboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor        // 생성자를 자동으로 마들어주는 롬복 어노테이션!(final로 지정된 필드 필수입력 생성자를 생성해 준다)
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts post = postsRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        // Q. 왜 postsRepository.update()가 아니지?????????????????????
        post.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById (Long id) {
        // ? 왜 Posts 엔티티로 받지?
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)     // 옵션 : 트랜잭션 범위는 유지(?)하되, 조회 기능만 남겨줘서 조회 속도가 개선된다고 함(p.148)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        Posts post = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        // 오홍 Entity 한개를 아예 조회해 와서 지우네
        postsRepository.delete(post);   // 기본으로 JpaRepository에서 제공하는 delete사용.
        // ID로 바로 삭제하기 :: postsRepository.deleteById(id);   // 여기서는 게시글이 있는지 확인하기 위해 위처럼 한번 조회하고 삭제한다고 함(p.159)
    }
}
