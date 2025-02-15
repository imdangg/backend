package rest;

import com.project.imdang.domain.message.ExchangeRequestRejectedCountRequestMessage;
import com.project.imdang.member.service.domain.handler.ExchangeRequestRejectedCountMessageListenerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.mockito.Mockito.*;
import static rest.TestData.*;

@AutoConfigureMockMvc
@SpringBootTest(classes = TestConfiguration.class)
public class CouponEventTest {

    @Autowired
    private ApplicationContext applicationContext;

    @MockBean
    private ExchangeRequestRejectedCountMessageListenerImpl listener;


    @Test
    @DisplayName("이벤트가 호출되는지 확인")
    void exchangeRequestRejectedCountMessageEventListener() {
        // Given
        ExchangeRequestRejectedCountRequestMessage message = new ExchangeRequestRejectedCountRequestMessage(memberId);
        // When
       applicationContext.publishEvent(message);
        // Then
        verify(listener).handle(message);
    }
}
