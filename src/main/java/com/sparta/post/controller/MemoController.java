package com.sparta.post.controller;

import com.sparta.post.domain.Memo;
import com.sparta.post.dto.MemoRequestDto;
import com.sparta.post.repository.MemoRepository;
import com.sparta.post.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class MemoController {

    private final MemoRepository memoRepository;
    private final MemoService memoService;

    @GetMapping("/api/memos") // 전체 게시물 조회 API (제목, 작성자명, 작성 날짜)
    public List<Memo> getMemos() {
        return memoRepository.findAllByOrderByModifiedAtDesc();
    }

    @PostMapping("/api/memos") // 게시글 작성 API (제목, 작성자명, 비밀번호, 작성 내용)
    public Memo createMemo(@RequestBody MemoRequestDto requestDto) {
        Memo memo = new Memo(requestDto);
        return memoRepository.save(memo);
    }

    @GetMapping("/api/memos/{id}") // 게시글 조회 API (제목, 작성자명, 작성 날짜, 작성 내용)
    public Optional<Memo> getMemos(@PathVariable Long id) {
        return memoRepository.findById(id);
    }

    @PostMapping("/api/momos/{id}") //비밀번호 확인 API (비밀번호 일치여부 확인))
    public String pwcheck(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        return memoService.pwCheck(id,requestDto);
    }

    @PutMapping("/api/memos/{id}") // 게시글 수정 API (제목, 작성자명, 비밀번호, 작성 내용)
    public Long updateMemo(@PathVariable Long id, @RequestBody MemoRequestDto requestDto) {
        memoService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/memos/{id}") // 게시글 삭제 API (글 삭제)
    public Long deleteMemo(@PathVariable Long id) {
        memoRepository.deleteById(id);
        return id;
    }
}