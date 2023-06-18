package roadtree.post.entity.embed;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NickNameTest {

    @Test
    public void testNickName() {
        String stringNickName1 = "Seoul";

        NickName nickName = new NickName(stringNickName1);
        // 빈값인지
        assertNotNull(nickName);
        // 생성한게 맞는지?
        assertEquals(stringNickName1, nickName.getNickname());
    }
}