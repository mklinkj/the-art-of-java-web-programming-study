# 코틀린 전환 노트 - pro19

IntelliJ에 Java 코드를 kotlin으로 바꿔주는 기능이 있길래 간단한 클래스 하나만 바꿔봤다.



#### 대상 클래스

```java
package org.mklinkj.taojwp.common.util;

import java.nio.file.Paths;
import java.util.Optional;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommonUtils {
  public static String ifNullToNullString(String string) {
    return String.valueOf(string);
  }

  public static String fileNameOnly(String fileName) {
    return Paths.get(fileName).getFileName().toString();
  }
}

```



### 변환한 코틀린 클래스

```kotlin
package org.mklinkj.taojwp.common.util

import java.nio.file.Paths

object CommonUtils {
    @JvmStatic
    fun ifNullToNullString(string: String?): String {
        return string ?: "null"
    }

    @JvmStatic
    fun fileNameOnly(fileName: String?): String {
        return Paths.get(fileName).fileName.toString()
    }
}
```

* `@NoArgsConstructor(access = AccessLevel.PRIVATE)`는 내가 제거했다.

  * object로 클래스를 정의하면 생성자가 알아서 private로 정의됨

* `ifNullToNullString` 메서드는 null 이 들어올 수 있는 메서드여서 타입 뒤에?를 붙임

* `string ?: "null"` 은 입력된 문자열이 null이라면 문자열 "null"을 반환하게 함, 그러므로 반환타입은 null이 될 수 없어 ?가 없는 String 임.

* `@JvmStatic`을 붙인 이유

  * 다른 코틀린 클래스에서 이 메서드를 호출할 때는 정적 메서드 호출하드시 그냥 클래스명.메서드명으로 사용할 수 있는데, 자바 코드에서 호출할 때는

    ```kotlin
    클래스명.INSTANCE.메서드명()
    ```

    위와 같이 호출해야함. 그런데 `@JvmStatic`을 붙이면 그냥 클래스명.이름으로 호출 할 수 있음.

  

  

## build.gradle의 변화

```groovy
plugins {
  ...
  id 'org.jetbrains.kotlin.jvm' version "${kotlinJvmPluginVersion}"
}

...
dependencies {
  ...
  implementation "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
}

...
kotlin {
  jvmToolchain(Integer.valueOf("${javaVersion}"))
}
```

* kotlin jvm 플러그인 추가됨
* 디펜던시에 `kotlin-stdlib-jdk8` 추가
* kotlin 테스크 및 관련 툴 체인 설정 추가



## 의견

아직 복잡한 클래스는 진행하지 말고.. 간단한 유틸리티 함수 위주로 조금씩 바꿔봐야겠다.