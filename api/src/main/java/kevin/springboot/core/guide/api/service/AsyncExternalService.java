package kevin.springboot.core.guide.api.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class AsyncExternalService {

    // 외부 API 1개 호출 (3초 지연)
    public String callExternalAPI(String name) {
        simulateDelay();
        return "Result from API-" + name;
    }

    /**
     * 외부 API를 별도의 스레드에서 비동기 실행하고, 결과를 CompletableFuture로 래핑하여 반환.
     * 기존 동기 API는 Tomcat 스레드가 3초씩 묶이므로, 200개 요청이 들어오면 거의 대기 상태에 들어갑니다.
     * Async 적용 후에는 Tomcat 스레드는 즉시 풀에 반환되고, 3초 지연은 별도 스레드에서 수행됨. 수백 개의 요청이 동시에 들어와도 응답 대기열이 생기지 않습니다.
     * 요약하자면 👇
     * ✅ API 응답은 여전히 simulateDelay()가 끝난 뒤(3초 후)에 도착하지만,
     * ✅ WAS의 스레드 점유 시간이 사라져 서버 처리 효율이 대폭 향상됩니다.
     */
    @Async
    public CompletableFuture<String> callExternalAPIAsync(String name) {
        simulateDelay();
        return CompletableFuture.completedFuture("Result from API-" + name);
    }

    // 3초 지연 작업
    private void simulateDelay() {
        try {
            Thread.sleep(3000); // 3초 지연
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
