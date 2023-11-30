package bd.uber.zafor.model.operationsmanager;

import java.io.Serializable;

public interface FareModifier extends Serializable {
    float modifyBaseFare(float baseFare);

    float modifyDistanceRate(float distanceFare);

    float modifyTimeRate(float timeFare);
}