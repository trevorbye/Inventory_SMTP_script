import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.simplejavamail.mailer.config.TransportStrategy;

import javax.activation.FileDataSource;

public class SMTPService {

    public Mailer connectToSMTP() {
        Mailer mailer = MailerBuilder
                .withSMTPServerHost("Appmail.darigold.com")
                .withSMTPServerPort(25)
                .withTransportStrategy(TransportStrategy.SMTP)
                .withDebugLogging(false)
                .buildMailer();

        return mailer;
    }

    public void sendEmail(String recipient, String fileName, Mailer connection) {
        String recipientEmail;
        String subject;

        switch (recipient) {
            case "Irene":
                recipientEmail = "IreneO@jfarrell.com";
                subject = "Lynden Powder";
                break;
            case "Nate":
                recipientEmail = "NateS@jfarrell.com";
                subject = "Sunnyside Produced 310724, 310726 and 310015";
                break;
            case "Nate-Jerome":
                recipientEmail = "NateS@jfarrell.com";
                subject = "Jerome Produced 310724, 310726 and 310015";
                break;
            case "Crystal" :
                recipientEmail = "CrystalW@jfarrell.com";
                subject = "Sunnyside Produced 310448 and 310447";
                break;
            default:
                recipientEmail = "trevor.bye@darigold.com";
                subject = "ID inventory test";
                break;
        }

        Email email = EmailBuilder.startingBlank()
                .from("Darigold Analytics", "scanalytics@darigold.com")
                .to(recipientEmail)
                .cc("Mary.McAllister@darigold.com")
                .bcc("trevor.bye@darigold.com")
                .withSubject(subject)
                .withPlainText("This is an automated service. If you have questions or any issues with the attached extract, please contact Mary McAllister.\n\nThank you,\n\nDarigold Analytics")
                .withAttachment(fileName, new FileDataSource("C:\\Users\\s-tbye\\scripts\\ID_inv_extracts\\" + fileName))
                .buildEmail();


        connection.sendMail(email);


    }
}
