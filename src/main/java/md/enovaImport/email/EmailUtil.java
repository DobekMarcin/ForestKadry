package md.enovaImport.email;

import md.enovaImport.modelsFX.SendMailFX;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.UnsupportedEncodingException;
import java.util.Date;

public class EmailUtil {

    private final static String EMAIL="place@forest.gorlice.pl";

    public static void sendAttachmentEmail(Session session, String toEmail, String subject, String body, SendMailFX sendMailFX) throws MessagingException, UnsupportedEncodingException {

            MimeMessage msg = new MimeMessage(session);
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(EMAIL, "DZIAŁ KADR I PŁAC"));
            msg.setReplyTo(InternetAddress.parse(EMAIL, false));
            msg.setSubject(subject, "UTF-8");
            msg.setSentDate(new Date());
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);

            messageBodyPart = new MimeBodyPart();
            String filename = sendMailFX.getPathFile();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));

            messageBodyPart.setFileName(sendMailFX.getName()+" "+sendMailFX.getPaymentDate()+".pdf");
            multipart.addBodyPart(messageBodyPart);

            msg.setContent(multipart);

            Transport.send(msg);
    }
}
