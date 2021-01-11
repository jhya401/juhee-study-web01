package com.study.juhee.stringboot.web;

import com.study.juhee.stringboot.service.posts.PostsService;
import com.study.juhee.stringboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());        // postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달함.
        return "index";     // resources/templates/경로 안에 해당 명의 .mustache 파일로 전환함.
    }
    /*public String index() {
        return "index";
    }*/
    
    // 신규등록 화면으로 전환
    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }

    //수정 화면으로 전환
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {     // @PathVariable : URI에 포함된 변수라고 명시해 줌
        // 수정할 데이터 조회 후 수정 화면으로 데이터 전달.
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
