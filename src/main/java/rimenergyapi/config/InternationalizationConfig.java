package rimenergyapi.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@Configuration
public class InternationalizationConfig {

	@Bean
	public LocaleResolver localeResolver() {
		return new SmartLocaleResolver();
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames("i18n/message"); // name of the resource bundle
		source.setDefaultEncoding("UTF-8");
		source.setUseCodeAsDefaultMessage(true);
		return source;
	}
}

class SmartLocaleResolver extends AcceptHeaderLocaleResolver {

	List<Locale> LOCALES = Arrays.asList(new Locale("en"), new Locale("fr"), new Locale("ar"));

	@Override
	public Locale resolveLocale(HttpServletRequest request) {
		if (StringUtils.isBlank(request.getHeader("Accept-Language"))) {
			return Locale.getDefault();
		}
		List<Locale.LanguageRange> list = Locale.LanguageRange.parse(request.getHeader("Accept-Language"));
		Locale locale = Locale.lookup(list, LOCALES);
		return locale;
	}
}
