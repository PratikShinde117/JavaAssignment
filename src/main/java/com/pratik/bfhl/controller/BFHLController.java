@RestController
@RequiredArgsConstructor
public class BFHLController {

    private final BFHLService service;

    @GetMapping("/")
    public String home() {
        return "BFHL API Running Successfully";
    }

    @PostMapping("/bfhl")
    public ResponseEntity<ResponseDTO> process(
            @RequestBody RequestDTO request,
            @RequestHeader("X-Request-Id") String requestId
    ) {
        long start = System.currentTimeMillis();

        return ResponseEntity.ok(
                service.processData(
                        request,
                        requestId,
                        start
                )
        );
    }
}