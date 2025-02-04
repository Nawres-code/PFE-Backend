package rimenergyapi.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.validator.routines.EmailValidator;
import org.modelmapper.ModelMapper;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class GenericUtil {

	public static boolean isValidEmailAddress(String email) {
		return EmailValidator.getInstance().isValid(email);
	}

	public static String file2String(File file) {
		String value = null;
		try {
			value = "";
			List<String> lines = Files.readLines(file, Charsets.UTF_8);
			for (String line : lines) {
				value += line;
			}
		} catch (IOException e2) {

		}
		return value;
	}

	public static List<String> getThemes() {
		return Arrays.asList("king-yna", "pomegranate", "ibiza-sunset", "flickr", "purple-bliss", "man-of-steel",
				"purple-love", "black", "white", "primary", "success", "warning", "info", "danger");
	}

	/** map List to List */
	public static <T, U> List<U> map(ModelMapper mapper, final Collection<T> source, final Class<U> destType) {

		final List<U> dest = new ArrayList<>();

		for (T element : source) {
			dest.add(mapper.map(element, destType));
		}

		return dest;
	}
}
