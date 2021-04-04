package entity.payment;

public abstract class CardAbstractFactory {

    public abstract Credit createCredit(String cardCode, String owner, String dateExpired, int cvvCode);

}
