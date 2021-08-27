package restaurant.app.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import restaurant.app.Service.EmailSender;

@Controller
public class SendController {

    private EmailSender emailSender;

    @Autowired

    public SendController(EmailSender emailSender) {

        this.emailSender = emailSender;
    }

    @RequestMapping("/sendMail")
    public String sendForm(){
        return "send";
    }

    @RequestMapping("/send")
    public String kasuj(@RequestParam("to") String to, @RequestParam("title") String title, @RequestParam("contents") String contents) throws Exception {
        emailSender.send(to, title, contents);
        return "send";
    }

}