package server.server;

import agh.edu.pl.Main;
import agh.edu.pl.commands.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.web.bind.annotation.RestController
public class RestController {
    Main main = new Main("COM5");

    @PostMapping("/api")
    public String sayHello(@RequestBody Response receivedCode){
        //System.out.println(receivedCode.getCode() +", " + receivedCode.getLanguage());
//        return receivedCode.run();
//        return "I would compile this.. the other day.";
        System.out.println("Command: "+receivedCode.getData());
        return main.executeCommand(receivedCode.getData());
    }
}
