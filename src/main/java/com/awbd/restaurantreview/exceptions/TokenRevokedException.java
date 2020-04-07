package com.awbd.restaurantreview.exceptions;

import java.util.UUID;

public class TokenRevokedException extends BaseException {

    private static final long serialVersionUID = -8709503359359094490L;
    private static final String code = "token_revoked";

    public TokenRevokedException(UUID tokenId) {
        super(code, String.format("Refresh token: '%s' was revoked.", tokenId));
    }
}
