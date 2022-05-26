
package mtgstore.app;

import mtgstore.app.Order;
import mtgstore.app.Card;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

public class SendMail {
    @SuppressWarnings("deprecation")
    /**
     * stringbuilder pre objednavku kvoli pisaniu do mailu
     */
    public String toString(Order order) {
        StringBuilder sb = new StringBuilder();
        for (Card card : order.getCards()) {
            sb.append(card).append("\n");
        }
        return sb.toString();
    }
    /**
     * vytvori email a posle ho, ziska parametry z objednavky
     * authenticator je username/heslo pre email z ktoreho sa to posiela
     * hostname je meno SMTP serveru
     * subject je subjekt v maili
     * setmsg je obsah mailu
     * addto je recepient mailu
     * setTLS je protokol na sifrovanie dat
     * send mail posle
     * @param order
     * @throws EmailException 
     */
    public static void sendOrderMail(Order order) throws EmailException{
        Email sendemail = new SimpleEmail();
        sendemail.setSmtpPort(587);
        sendemail.setAuthenticator(new DefaultAuthenticator("peterspurnysemestralka@gmail.com","TUL2022ALG"));//password and email
        sendemail.setDebug(false);
        sendemail.setHostName("smtp.gmail.com");
        sendemail.setFrom("peterspurnysemestralka@gmail.com");
        sendemail.setSubject("Order Information");
        StringBuilder sb = new StringBuilder();
        sb.append("Your order is ready. Please find details attached below:").append("\n");
        sb.append(order).append("\n");
        sb.append("Total price is: ").append(Order.getPriceOfOrder(order));
        sendemail.setMsg(sb.toString()); //Your Email Address
        sendemail.addTo(order.getMail()); // Receiver Email Address
        sendemail.setTLS(true);
        sendemail.send(); //sending email
        System.out.println("You have sent the email using Apache Commons Mailing API");
    }
    
    public static void main(String[] args) throws EmailException {
        Email sendemail = new SimpleEmail();
        sendemail.setSmtpPort(587);
        sendemail.setAuthenticator(new DefaultAuthenticator("peterspurnysemestralka@gmail.com","TUL2022ALG"));//password and email
        sendemail.setDebug(false);
        sendemail.setHostName("smtp.gmail.com");
        sendemail.setFrom("peterspurnysemestralka@gmail.com");
        sendemail.setSubject("pokus2");
        sendemail.setMsg("funguje pica"); //Your Email Address
        sendemail.addTo("peter.spurny@outlook.com"); // Receiver Email Address
        sendemail.setTLS(true);
        sendemail.send(); //sending email
        System.out.println("You have sent the email using Apache Commons Mailing API");
    }
}