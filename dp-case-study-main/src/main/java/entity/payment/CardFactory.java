package entity.payment;

public class CardFactory {
    private CardFactory(){

    }
    public static CardAbstractFactory getFactory(CardType type){
        switch (type){
            case CREDIT:
                return new CreditFactory();
//            case DOMESTIC:
//                return new  DomesticFactory();
            default:
                throw new UnsupportedOperationException("This Card is unsupported");
        }

    }

}
