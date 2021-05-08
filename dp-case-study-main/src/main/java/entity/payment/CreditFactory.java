package entity.payment;

public class CreditFactory extends CardAbstractFactory {
    @Override
    public Card createCredit(String cardCode, String owner, String dateExpired, int cvvCode) {
        return new CreditCard(cardCode,owner,dateExpired,cvvCode);
    }
}
