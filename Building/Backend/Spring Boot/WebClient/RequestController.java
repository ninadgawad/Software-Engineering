import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    private final FastFoodRequestService service;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping("/start")
    public RequestEntity startRequest() {
        return service.initiateRequest();
    }
}
