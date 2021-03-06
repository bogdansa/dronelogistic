package directdronedelivery.warehouse.businessrules;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import directdronedelivery.cargo.CargoAggregate;
import directdronedelivery.cargo.Size;
import directdronedelivery.drone.DroneType;

public class CargoSpecyfication {
    
    private static final int MAX_WEIGHT = 5000;
    private static final Size MAX_SIZE = Size.newSizeInMilimeters(250, 150, 100);
    private static final List<DroneType> POSSIBLE_DRONE_TYPES = Arrays.asList(DroneType.values());
    
    public List<DroneType> isSatisfiedForDronTypes(CargoAggregate cargo) {
        
        if (cargo.getWeightInGrams() > MAX_WEIGHT) {
            return Collections.emptyList();
        }
        
        Size size = cargo.getSize();
        if (!cargo.isFixedOrientation() && !size.fitsIn(MAX_SIZE)) {
            return Collections.emptyList();
        }
        if (cargo.isFixedOrientation() && !size.fitsInWithFixedOrientation(MAX_SIZE)) {
            return Collections.emptyList();
        }
        
        if (cargo.isFragileCommodity()) {
            return Collections.emptyList();
        }
        
        if (cargo.isDangerousGoods()) {
            return Collections.emptyList();
        }
        
        return POSSIBLE_DRONE_TYPES;
    }
    
}
