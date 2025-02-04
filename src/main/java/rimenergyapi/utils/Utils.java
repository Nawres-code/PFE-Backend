package rimenergyapi.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.stream.IntStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import rimenergyapi.dto.CronPayloadDto;
import rimenergyapi.dto.DaysDto;

public class Utils {
	public static CronPayloadDto getCronPayloadFromRegEx(String value) {

		if (value != null && !value.isEmpty()) {

			String[] sections = value.split(":");

			if (sections != null && sections.length == 5) {

				CronPayloadDto cron = new CronPayloadDto();

				DaysDto days = new DaysDto();

				IntStream.of(2, 3, 4).forEach(index -> {

					String interval = sections[index].substring(1, sections[index].length() - 1);

					/** days */
					if (index == 2) {
						if (interval.equals("*")) {
							days.loadAllDays();
						} else {
							days.loadSpecificDays(interval.split(","));
						}
					}

					/** hours */
					if (index == 3) {
						if (interval.equals("*")) {
							cron.setNight(true);
							cron.setMorning(true);
						} else {
							String[] hours = interval.split("-");
							int fromHour = Integer.parseInt(hours[0]);
							int toHour = Integer.parseInt(hours[1]);

							if (fromHour == 6 && toHour == 18) {
								cron.setMorning(true);
							} else if (fromHour == 18 && toHour == 6) {
								cron.setNight(true);
							} else {
								cron.setNight(true);
								cron.setMorning(true);
							}
						}
					}

				});

				cron.setDays(days);

				return cron;
			}

			return null;
		}

		return null;
	}

	public static void upResponseXSSF(XSSFWorkbook ws, HttpServletResponse response) throws IOException {

		if (ws != null) {
			response.setContentType("application/octet-stream");
			OutputStream out = response.getOutputStream();
			try {
				ws.write(out);
			}catch (IOException ioe) {
				
			} finally {
				ws.close();
			}
			response.flushBuffer();
		}
	}


}
