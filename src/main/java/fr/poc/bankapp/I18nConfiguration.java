package fr.poc.bankapp;

import java.nio.charset.StandardCharsets;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

/**
 * Base i18n configuration.
 */
@Configuration
public class I18nConfiguration implements WebMvcConfigurer {

    /**
     * Use {@link CookieLocaleResolver} for cookie locale resolving.
     *
     * @return {@link LocaleResolver}
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver clr = new CookieLocaleResolver();
        clr.setDefaultLocale(Locale.FRENCH);
        clr.setCookieName("lang");
        return clr;
    }

    /**
     * Use {@link ReloadableResourceBundleMessageSource} for handling bundles.
     *
     * @return {@link MessageSource}
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:/messages");
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return messageSource;
    }

}
