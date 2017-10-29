package hello;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webhook")
public class WebhookController {

	@RequestMapping(method = RequestMethod.POST)
    public @ResponseBody WebhookResponse webhook(@RequestBody String obj) throws JsonParseException, JsonMappingException, IOException{

        ObjectMapper mapper = new ObjectMapper();
        APIPojo apiPojo = mapper.readValue(obj, APIPojo.class);
        System.out.println(apiPojo.getResult().getAction());
         if (apiPojo.getResult().getAction().equals("input.welcome")) {
        	 return new WebhookResponse("Welcome Intent", "Welcome Intent");
         }        
         return new WebhookResponse("Not Welcome Intent", " Not Welcome Intent");
    }
}
