package study.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import study.qa.model.Beeline;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonParsingTest {

    ClassLoader cl = JsonParsingTest.class.getClassLoader();
    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    @DisplayName("Проверка содержимого оыщт-файла")
    void jsonParseTest() throws Exception {
        try (
                InputStream resource = cl.getResourceAsStream("beeline.json");
                InputStreamReader reader = new InputStreamReader(resource)
        ) {
            Beeline beeline = objectMapper.readValue(reader, Beeline.class);
            assertThat(beeline.region.label).isEqualTo("Москва");
            assertThat(beeline.tariffs).size().isBetween(1, 5);
            assertThat(beeline.tariffs.get(0).name).isEqualTo("для дома 100");
            assertThat(beeline.tariffs.get(1).fee.textValue).isEqualTo("на 3 месяца");
        }
    }
}
