package com.sparta.post.service;

import com.sparta.post.domain.Memo;
import com.sparta.post.dto.MemoRequestDto;
import com.sparta.post.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class MemoService { //업데이트, 생성
    private final MemoRepository memoRepository;

    @Transactional
    public Long update(Long id, MemoRequestDto requestDto) {
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        memo.update(requestDto);
        return memo.getId();
    }

    @Transactional
    public String pwCheck(Long id, MemoRequestDto requestDto) {
        String msg;
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        if (memo.getPassword().equals(requestDto.getPassword())) {
            msg = "비밀번호 확인";
        } else {
            msg = "비밀번호 불일치";
        }
        return msg;
    }
}