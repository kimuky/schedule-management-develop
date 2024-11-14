package com.example.schedulemanagementdevelop.dto.comment;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentRequestDto {

    @NotBlank
    private final String contents;

    // TODO 3시간 동안 해결 못했습니다..
    // 도대체 싱글 필드로 만들면 왜 자동으로 맵핑이 안되는건지
    // KEYWORD: 역직렬화, 직렬화, Jackson 무슨 소리인지 하나도 모르겠습니다...
    // contents 하나면 안되고, 필드를 하나 더 만들면 해결이 되는데.. 음....
    // 듣기로는 jackson이 변환 해주는데 싱글 필드일 때는 delegating properties 방식 고민한다?....????
    @JsonCreator
    public CommentRequestDto(String contents) {
        this.contents = contents;
    }
}
