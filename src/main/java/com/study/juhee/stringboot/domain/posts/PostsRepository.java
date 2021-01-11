package com.study.juhee.stringboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


// 인터페이스 + JpaRepository<Entity클래스, ID데이터타입> 상속 = 기본 CRUD메소드 자동생성
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")  // @Query : SpringDataJpa에서 제공하지 않는 메소드를 쿼리로 작성 가능.
    List<Posts> findAllDesc();

}
