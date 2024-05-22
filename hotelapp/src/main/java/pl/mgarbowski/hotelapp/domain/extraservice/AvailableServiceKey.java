package pl.mgarbowski.hotelapp.domain.extraservice;

import lombok.Data;

import java.io.Serializable;

@Data
public class AvailableServiceKey implements Serializable {
    private Integer extraServiceId;
    private Integer hotelId;
}
