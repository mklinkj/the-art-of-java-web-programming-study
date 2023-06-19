# React와 Spring 프로젝트 연동

> 리엑트와 스프링을 연동해보고 싶다.
>
> Gralde 테스크로 먼저 npm build를 한 다음 웹 경로로 그냥 주면 되는 것 같은데...
>
> 한번 해보자!  일단 Spring Boot 없이 해보자 😁





## 연동방법 웹 검색

### 🎇 React  + Spring 연동 빌드 방법

* 설명을 제일 잘하신것같다. 👍 Gradle 빌드 스크립트 참고해서 따라해보면 될듯.
* https://7942yongdae.tistory.com/136
* 역시 프론트앤트 디렉토리는 src이하에 포함시키지 않는게 나은듯.



### `http://localhost:8080/ `접근시 index.html로 가도록 하기

* Spring Boot 프로젝트가 아니다보니 설정이 필요함. 그냥 부트를 사용하는게 나을련지... 😂

  ```java
  @Configuration
  @EnableWebMvc
  @ComponentScan("org.mklinkj.qna.react_spring")
  public class WebConfiguration implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/").setViewName("forward:/index.html");
    }
  
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry
          .addResourceHandler("/**") //
          .addResourceLocations("/statics/");
    }
  }
  ```

  *  https://stackoverflow.com/a/27383522
  * 아래 static 연동 부분은 내가 그냥 추가했는데, 맞는지 모르겠다.



## 진행 결과

* build.gradle

  ```groovy
  // React 빌드 통합
  def frontendDir = "$projectDir/frontend"
  
  task installReact(type: Exec) {
    workingDir "$frontendDir"
    inputs.dir "$frontendDir"
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
      commandLine "npm.cmd", "audit", "fix"
      commandLine 'npm.cmd', 'install'
    } else {
      commandLine "npm", "audit", "fix"
      commandLine 'npm', 'install'
    }
  }
  
  task buildReact(type: Exec) {
    dependsOn "installReact"
    workingDir "$frontendDir"
    inputs.dir "$frontendDir"
    group = BasePlugin.BUILD_GROUP
    if (System.getProperty('os.name').toLowerCase(Locale.ROOT).contains('windows')) {
      commandLine "npm.cmd", "run-script", "build"
    } else {
      commandLine "npm", "run-script", "build"
    }
  }
  
  task copyReactBuildFiles(type: Copy) {
    dependsOn "buildReact"
    from "$frontendDir/build"
    into "$buildDir/frontend/statics"
  }
  
  // Gretty appRun으로 실행시 참조할 경로에 React 빌드 파일 복사
  task updateReactView(type: Copy) {
    dependsOn "buildReact"
    from "$frontendDir/build"
    into "$buildDir/inplaceWebapp/statics"
  }
  
  war {
    // webapp의 루트에다 React 빌드 파일 복사
    from "$buildDir/frontend"
  }
  
  // appRunWar로 실행시 React 빌드 결과물을 포함하여 war를 생성하므로 미리 빌드가 되어있어야함.
  tasks.war {
   dependsOn copyReactBuildFiles
  }
  
  // War 생성하지 않고 리액트 빌드 결과물 포함하여 실행
  import org.akhikhl.gretty.AppStartTask
  task('appRunReact', type: AppStartTask) {
    dependsOn updateReactView
  }
  ```

  Spring Boot 없이 그냥 Spring + Gretty로 사용하고 있어서, 아래와 같이 해보았다.

  * `gradle clean appRunWar`로 실행

    * React 빌드 결과물이 포함하여 패키지된 war 파일을 Gretty의 Tomcat으로 실행

  * `gradle clean appRunReact` 로 실행

    * war 패키지를 만들지 않고 inplace 모드에서 바로 실행

    * 실행 전 `$buildDir/inplaceWebapp/statics` 경로로 React 빌드 결과물을 복사함

    * 이 상태에서는 React 쪽 코드 수정이 되었을때... 다른 터미널 띄어서 ...

      ```sh
      gradle updateReactView
      ```

      위의 명령을 실행해서 내용을 업데이트 해주면 됨..

      그래도 뭐 번거롭긴함.. 😅 한창 개발할때는 따로 개발하고 Mock 데이터 쓰다가 프로젝트 통합이 필요할 경우에만 하는게 나을 것 같긴함...

  * `gradle clean appRun` 으롤 실행

    * npm 작업 실행 없이 스프링 프로젝트만 빌드함.



## 실행화면

![image-20230313112120307](doc-resources/image-20230313112120307.png)



## 참조

* https://7942yongdae.tistory.com/136
*  https://stackoverflow.com/a/27383522
* https://akhikhl.github.io/gretty-doc/appRun-task.html
* https://akhikhl.github.io/gretty-doc/appRunWar-task.html





## 의견

그런데... 왠지... 단순한 프로젝트면 React와 Spring 프로젝트를 같이둬도 괜찮을지 모르지만... 복잡해지면 프론트와 백앤드를 별도로 두는게 나을 것 같은? 생각이 듬.

그런데.. Intelli J 에서 이 Gradle - Spring 프로젝트를 로드해서 쓰더라도 React 의 js파일 편집을 편하게 잘할 수 있었음..

생활코딩 리액트 스터디 할 때.. 백엔드를 Spring 으로 붙여서 프로젝트를 만들어서 재밌을 것 같다. 😁

