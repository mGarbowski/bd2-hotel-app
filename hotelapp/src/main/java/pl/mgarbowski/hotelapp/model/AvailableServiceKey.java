package pl.mgarbowski.hotelapp.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class AvailableServiceKey implements Serializable {
    private Integer servicesId;
    private Integer hotelId;
}
