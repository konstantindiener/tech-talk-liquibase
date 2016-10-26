package biz.cosee.talks.loaning;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = LiquibaseApplication.class)
@WebAppConfiguration
public class LanguageRepositoryIntegrationTest {

    @Autowired
    private LanguageRepository languageRepository;

    @Test
    public void loadGermanLanguageDefinition() {
        Language deLanguage = languageRepository.findOne("de");
        assertThat(deLanguage).isNotNull();
        assertThat(deLanguage.getDescription()).isEqualTo("Deutsch");
    }
}
