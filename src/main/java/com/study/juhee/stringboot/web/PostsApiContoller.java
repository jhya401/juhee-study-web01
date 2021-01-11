package com.study.juhee.stringboot.web;

import com.study.juhee.stringboot.service.posts.PostsService;
import com.study.juhee.stringboot.web.dto.PostsResponseDto;
import com.study.juhee.stringboot.web.dto.PostsSaveRequestDto;
import com.study.juhee.stringboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor        // 생성자를 자동으로 마들어주는 롬복 어노테이션!(final로 지정된 필드 필수입력 생성자를 생성해 준다)
@RestController
public class PostsApiContoller {

    private final PostsService postsService;

    // save
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    //Q. Mapping주소가 '수정'이랑, '조회'랑 동일하네?

    // update
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    // find
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    // delete
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
}
