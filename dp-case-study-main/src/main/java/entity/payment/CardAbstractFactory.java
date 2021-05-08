package entity.payment;

public abstract class CardAbstractFactory {

    public abstract Card createCredit(String cardCode, String owner, String dateExpired, int cvvCode);

}
