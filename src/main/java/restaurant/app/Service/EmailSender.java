package restaurant.app.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class EmailSender {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String to, String title, String contents) throws Exception {

        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper mailsend = new MimeMessageHelper(mail, true, "UTF-8");
        mailsend.setTo(to);
        mailsend.setReplyTo(to);
        mailsend.setFrom("sushiaplikacja@gmail.com");
        mailsend.setSubject(title);
        mailsend.setText(contents);

        javaMailSender.send(mail);
    }
}



