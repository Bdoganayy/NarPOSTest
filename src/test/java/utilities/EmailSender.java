package utilities;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    public static void main(String[] args) {
        // SMTP sunucusu ayarları
        String host = "smtp.gmail.com";
        final String user = "blldgnyyy@gmail.com";  // Gönderen e-posta adresi
        final String password = ConfigReader.getProperty("password");  // Uygulama şifresi

        // E-posta özellikleri
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // E-posta oturumu oluşturma
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, password);
                    }
                });

        try {
            // E-posta mesajı oluşturma
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));  // Gönderen adresi
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("blldgnyyy@gmail.com"));  // Alıcı adresi
            message.setSubject("Test Subject");  // E-posta konusu
            message.setText("This is a test email sent from Java.");

            // E-posta gönderme
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
