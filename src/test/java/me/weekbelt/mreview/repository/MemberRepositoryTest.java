package me.weekbelt.mreview.repository;

import java.util.stream.IntStream;
import me.weekbelt.mreview.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void insertMembers() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                .email("r" + i + "@zerock.org")
                .pw("1111")
                .nickname("reviewer" + i)
                .build();
            memberRepository.save(member);
        });
    }

}