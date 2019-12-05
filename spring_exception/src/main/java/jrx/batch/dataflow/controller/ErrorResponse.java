package jrx.batch.dataflow.controller;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ErrorResponse {
    private long timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private String exception;
}