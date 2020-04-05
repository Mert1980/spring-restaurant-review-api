package com.awbd.restaurantreview.mappers;

public interface Mapper<TEntity, TReqDto, TResDto> {
    static final String DATA_IMAGE = "data:image/png;base64,";
    static final String DATA_VIDEO = "data:video/mp4;base64,";

    TEntity mapDtoToEntity(TReqDto dto);

    TResDto mapEntityToDto(TEntity entity);
}
