package org.mklinkj.taojwp.test.support;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.mklinkj.taojwp.common.util.ProjectDataUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TestConstants {
  public static final String CONTEXT_PATH = "/" + ProjectDataUtils.getProperty("project.name");
}
