package personal.ws.myservice.note;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Note {

	@RequestMapping("/createNote")
	public String createNote() {
		
		return null;
	}
}