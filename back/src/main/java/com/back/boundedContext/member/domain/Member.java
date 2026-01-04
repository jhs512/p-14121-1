package com.back.boundedContext.member.domain;

import com.back.shared.member.domain.SourceMember;
import com.back.shared.member.dto.MemberDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MEMBER_MEMBER")
@Getter
@NoArgsConstructor
public class Member extends SourceMember {
    public Member(String username, String password, String nickname) {
        super(username, password, nickname);
    }

    public MemberDto toDto() {
        return new MemberDto(
                getId(),
                getCreateDate(),
                getModifyDate(),
                getUsername(),
                getNickname()
        );
    }
}
