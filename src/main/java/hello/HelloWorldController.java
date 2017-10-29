package hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET)
    public @ResponseBody WebhookResponse webhook(){

        return new WebhookResponse("Hello!", "Hello!");
    }
        
}
