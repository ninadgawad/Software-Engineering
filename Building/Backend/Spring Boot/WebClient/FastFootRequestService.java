import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class FastFootRequestService {

    private final RequestRepository requestRepository;
    private final WebClient webClient;

    public FastFootRequestService(RequestRepository requestRepository, WebClient.Builder webClientBuilder) {
        this.requestRepository = requestRepository;
        this.webClient = webClientBuilder.baseUrl("http://localhost:1423").build();
    }

    @Transactional
    public RequestEntity initiateRequest() {
        RequestEntity request = new RequestEntity();
        request.setRequestStatus("IN_PROGRESS");
        request.setStartTime(LocalDateTime.now());
        request = requestRepository.save(request);

        Long requestId = request.getRequestId();

        webClient.get()
                .uri("/")
                .retrieve()
                .bodyToMono(String.class)
                .doOnSuccess(response -> updateRequestSuccess(requestId, response))
                .doOnError(error -> updateRequestError(requestId, error.getMessage()))
                .subscribe();

        return request;
    }

    @Transactional
    public void updateRequestSuccess(Long requestId, String responseDetails) {
        RequestEntity request = requestRepository.findById(requestId).orElseThrow();
        request.setRequestStatus("COMPLETED");
        request.setResponseDetails(responseDetails);
        request.setEndTime(LocalDateTime.now());
        requestRepository.save(request);
    }

    @Transactional
    public void updateRequestError(Long requestId, String errorMessage) {
        RequestEntity request = requestRepository.findById(requestId).orElseThrow();
        request.setRequestStatus("ERROR");
        request.setResponseDetails(errorMessage);
        request.setEndTime(LocalDateTime.now());
        requestRepository.save(request);
    }
}
