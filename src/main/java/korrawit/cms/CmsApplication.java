package korrawit.cms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import korrawit.cms.error.DatabaseConnectionException;

@SpringBootApplication
@EnableAsync
public class CmsApplication {

	private static final Logger log = LoggerFactory.getLogger(CmsApplication.class);

	public static void main(String[] args) {
		try {
			SpringApplication.run(CmsApplication.class, args);
		} catch (Exception e) {
			DatabaseConnectionException dbFailure = findCause(e, DatabaseConnectionException.class);
			if (dbFailure != null) {
				log.error("Startup failed: {}", dbFailure.getMessage());
			} else {
				log.error("Startup failed: {}", e.getMessage(), e);
			}
			System.exit(1);
		}
	}

	private static <T extends Throwable> T findCause(Throwable e, Class<T> type) {
		Throwable current = e;
		while (current != null) {
			if (type.isInstance(current)) {
				return type.cast(current);
			}
			current = current.getCause();
		}
		return null;
	}

}
