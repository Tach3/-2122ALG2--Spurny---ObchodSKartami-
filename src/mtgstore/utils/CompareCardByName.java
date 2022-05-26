
package mtgstore.utils;
import java.util.Comparator;
import mtgstore.app.Card;
/**
 * porovna karty podla mena
 * @author peter
 */
public class CompareCardByName implements Comparator<Card>{
    @Override
    public int compare(Card c1, Card c2) {
        return c1.getName().compareTo(c2.getName());
    }
}
