package entity.shipping;
import org.example.AlternativeDistanceCalculator;
public class AltDistanceAdapter implements CalcuDistance{

    protected AlternativeDistanceCalculator altDistanceCalculator;

    public AltDistanceAdapter(){
        this.altDistanceCalculator=new AlternativeDistanceCalculator();
    }

    @Override
    public int calculateDistance(String address, String province) {
        String infoAddress=province+address;
        return altDistanceCalculator.calculateDistance(infoAddress);
    }
}
