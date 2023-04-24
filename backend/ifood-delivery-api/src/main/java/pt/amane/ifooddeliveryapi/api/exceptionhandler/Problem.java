package pt.amane.ifooddeliveryapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Getter;

import java.time.Instant;
import java.util.List;


@JsonInclude(Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private Integer status;
    private Instant timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Object> objects;

    @Getter
    @Builder
    public static class Object {

        private String name;
        private String userMessage;

    }

}