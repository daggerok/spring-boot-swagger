package daggerok.rest;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

import static java.util.Collections.singletonMap;

@Slf4j
@RestController
@Api(tags = { "v1", "other" })
@RequestMapping("/v1/other-resource")
public class OtherResource {

  @GetMapping
  public Map<String, String> ok(@RequestParam(required = false) Optional<String> optional) {

    optional.ifPresent(s -> log.debug("handle {}", s));

    return singletonMap("response", optional.map("reversed: "::concat)
                                            .orElse("NOK"));
  }
}
