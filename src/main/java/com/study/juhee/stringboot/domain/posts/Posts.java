package com.study.juhee.stringboot.domain.posts;

import com.study.juhee.stringboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 엔티티 클래스 = 도메인 클래스 = 실제DB의 테이블과 매칭되는 클래스! / Entity 클래스를 기준으로 테이블이 생성됨.
 */

@Getter
@NoArgsConstructor  // 롬복 어노테이션 / 기본생성자 자동추가
@Entity     // JPA어노테이션 / 테이블과 링크될 클래스임을 명시.
public class Posts extends BaseTimeEntity {

    @Id     // PK필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)     //PK생성규칙. 스프링부트2.0부터는 'GenerationType.IDENTITY'붙여야 auto_increment가 된다 함.
    private Long id;

    // @Column :: 안붙여도 됨. 기본값 등 추가로 옵션설정 필요 시 사용.
    
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder    // 롬복 어노테이션 / 해당 클래스의 빌더패턴클래스를 생성 / 생성자 상단에 선언하면 생성자에 포함된 필드만 빌더에 포함함. (참고 : https://johngrib.github.io/wiki/builder-pattern/)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
