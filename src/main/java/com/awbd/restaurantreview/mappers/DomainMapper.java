package com.awbd.restaurantreview.mappers;

public interface DomainMapper<T, U> {
    U mapEntityToDto(T entity);
    T mapDtoToEntity(U dto);
}
