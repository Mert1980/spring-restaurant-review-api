package com.awbd.restaurantreview;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.awbd.restaurantreview.exceptions.BaseException;
import com.awbd.restaurantreview.models.RefreshTokenModel;
import com.awbd.restaurantreview.security.RefreshTokenHandler;

@SpringBootTest
@ActiveProfiles("dev")
public class RefreshTokenTests {
    @Autowired
    private RefreshTokenHandler refreshTokenHandler;

    @Test
    public void revokeToken() throws BaseException {
        String token = "E5F187BFAD04400E9EE3215FED3209A2";
        UUID userId = UUID.fromString("9D5AA841-4877-41C2-8247-724C453D935F");

        refreshTokenHandler.revoke(userId, token);
        boolean isValid = refreshTokenHandler.validate(token);

        Assert.isTrue(!isValid, "Refresh token should be revoked");
    }

    @Test
    public void refreshToken() throws BaseException {
        String token = "2CE9AE48AD484BACB9F125519DCD3C76";

        RefreshTokenModel model =  refreshTokenHandler.createAccessToken(token);

        Assert.notNull(model, "Token was successfully refreshed.");
    }

    @Test
    public void refreshTokenThrowsExceptionWhenRefreshed() throws BaseException {
        String expectedMessage = "Refresh token: 'e731b785-f317-40c0-9dab-52d8db02cb7d' was revoked.";

        BaseException exception = assertThrows(BaseException.class, () -> {
            refreshTokenHandler.createAccessToken("A0FB468E55EB469EBA61B63F0B60AA7C");
        });
        String actualMessage = exception.getMessage();

        Assert.isTrue(expectedMessage.equals(actualMessage), "Exception is thrown when refreshing a token");
    }
}
