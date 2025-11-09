package kevin.springboot.core.guide.api.controller;

import kevin.springboot.core.guide.api.service.AsyncExternalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
@RequestMapping("/async")
public class AsyncApiController {
    private final AsyncExternalService service;

    // 동기식 외부 api 3개 호출 api -> 응답시간 9초, tomcat 스레드풀 9초 점유
    @GetMapping("/call-sync")
    public Map<String, Object> getDataSync() {
        Instant start = Instant.now();

        // 외부 API 순차 호출 (3초 + 3초 + 3초 = 9초)
        String r1 = service.callExternalAPI("A");
        String r2 = service.callExternalAPI("B");
        String r3 = service.callExternalAPI("C");

        long elapsed = Instant.now().toEpochMilli() - start.toEpochMilli();
        return Map.of(
                "mode", "sync",
                "elapsedMs", elapsed,
                "results", List.of(r1, r2, r3)
        );
    }

    // 비동기식 (supplyAsync) 외부 api 3개 호출 api -> 응답시간 3초, tomcat 스레드풀 즉시반환
    @GetMapping("/call-supply-async")
    public CompletableFuture<Map<String, Object>> getDataNonBlocking() {
        Instant start = Instant.now();

        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> service.callExternalAPI("A"));
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> service.callExternalAPI("B"));
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> service.callExternalAPI("C"));

        return CompletableFuture.allOf(f1, f2, f3)
                .thenApply(v -> {
                    long elapsed = Instant.now().toEpochMilli() - start.toEpochMilli();
                    return Map.of(
                            "mode", "supplyAsync",
                            "elapsedMs", elapsed,
                            "results", List.of(f1.join(), f2.join(), f3.join())
                    );
                });
    }

    // 비동기식 (@Async) 외부 api 3개 호출 api -> 응답시간 3초, tomcat 스레드풀 즉시반환
    @GetMapping("/call-async")
    public CompletableFuture<Map<String, Object>> getDataAsync() {
        Instant start = Instant.now();

        // 비동기 병렬 실행
        CompletableFuture<String> f1 = service.callExternalAPIAsync("A");
        CompletableFuture<String> f2 = service.callExternalAPIAsync("B");
        CompletableFuture<String> f3 = service.callExternalAPIAsync("C");

        return CompletableFuture.allOf(f1, f2, f3)
                .thenApply(v -> {
                    long elapsed = Instant.now().toEpochMilli() - start.toEpochMilli();
                    return Map.of(
                            "mode", "@Async",
                            "elapsedMs", elapsed,
                            "results", List.of(f1.join(), f2.join(), f3.join())
                    );
                });
    }
}
