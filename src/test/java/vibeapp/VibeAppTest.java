package vibeapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class VibeAppTest {

    @Autowired
    private VibeApp vibeApp;

    @Test
    void testHelloMethod() {
        String result = vibeApp.hello();
        assertThat(result).isEqualTo("Hello, Vibe!");
    }
}
