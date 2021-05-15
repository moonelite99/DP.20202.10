package entity.shipping;

public class CalShippingFee implements CalculatorShipFreeStrategy{

    @Override
    public int calculatorShippingFee(int  distance) {
        return (int) (distance * ShippingConfigs.HANG_SO);
    }
}
