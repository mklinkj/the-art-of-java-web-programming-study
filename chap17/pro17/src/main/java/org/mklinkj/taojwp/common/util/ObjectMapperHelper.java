package org.mklinkj.taojwp.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperHelper {
  private static final ObjectMapper MAPPER = ObjectMapperHolder.INSTANCE;

  public static ObjectMapper objectMapper() {
    return MAPPER;
  }

  private static class ObjectMapperHolder {
    private static final ObjectMapper INSTANCE;

    static {
      INSTANCE = new ObjectMapper().registerModule(new JavaTimeModule());
    }
  }
}
