package daggerok.rest;

import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static java.util.Collections.singletonMap;

@Slf4j
@RestController
@Api(tags = { "v1", "some" })
@RequestMapping("/v1/some-resource")
public class SomeResource {

  @PostMapping("/{param}")
  @ApiOperation(
      value = "Some operation",
      protocols = "http,https",
      httpMethod = "POST"
  )
  @ApiImplicitParams({
      @ApiImplicitParam(
          name = "param",
          value = "some path param",
          required = true,
          paramType = "path",
          dataType = "String"
      ),
      @ApiImplicitParam(
          name = "body",
          value = "body payload",
          paramType = "body",
          dataType = "String"
      ),
  })
  @ApiResponses({
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 404, message = "Resource not found"),
  })
  public Map<String, String> withDefaults(@PathVariable String param,
                                          @RequestBody(required = false) Optional<String> body) {

    return singletonMap(param, body.map(s -> new StringBuilder(s))
                                   .map(StringBuilder::reverse)
                                   .map(StringBuilder::toString)
                                   .orElse("fallback data"));
  }
}
