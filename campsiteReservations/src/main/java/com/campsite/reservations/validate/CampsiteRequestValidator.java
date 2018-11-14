package com.campsite.reservations.validate;

import java.util.List;

public interface CampsiteRequestValidator <T>{
    List<String> validate(T user);
}
