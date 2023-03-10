## Test Coverage with Jacoco

## Test Coverage란?
> 테스트 커버리지란 테스트 케이스가 소스 코드에 대해 얼마나 충족(커버)되었는지를 나타내는 지표 중 하나다.
> 테스트 커버리지가 높으면 더 많은 소스 코드가 실행되므로 버그를 찾을 가능성이 더 높아진다.

### 테스트 커버리지 측정 기준
테스트 커버리지 측정 기준을 알아보기 전에 블랙박스 테스트와 화이트박스 테스트를 알아보자.

#### 블랙박스 테스트
- 블랙박스 테스트는 소프트웨어의 내부 구조나 작동 원리를 모르는 상태에서 소프트웨어의 동작을 검사하는 방식이다.
- 즉 입력을 통해 출력을 테스트하는 방식

#### 화이트박스 테스트
- 개발자가 관점의 테스트 방식으로 소프트웨어의 내부 구조와 동작을 검사하는 테스트 방식이다.

---

- Function coverage
  - 프로그램 내의 각 메서드가 호출되었는지 체크한다.
- Statement coverage
  - Line coverage라고도 하며, 코드 한 줄이 실행되었는 지 체크한다.
- Edge coverage(Branch coverage)
  - 예를 들어 if문의 true/false를 모두 테스트 코드를 작성한 경우 충족한다.
- Condition coverage
  - 조건식의 내부 조건의 true/false를 모드 작성한 경우 충족한다.

### 테스트 커버리지의 중요성
> 우선 테스트 코들르 작성하는 목적은 우리가 작성한 프로그램이 잘 작동하는지, 모든 시나리오에 대해 테스트 코들르 작성해 우리의 
프로그램을 검증하기 위해서다. 그런데 복잡한 비즈니스 로직인 경우, 개발자가 놓친 경우 등 다양한 변수로 인해 테스트 코드에서도 잡지 못한 에러가 있을 수 있다.(거의 항상?)
> 그래서 이런 실수를 방지하기 위해서 테스트 커버리지를 사용해 얼마나 많은 코드를 테스트 케이스가 커버하고 있는지 확인하고 부족한 부분을 채울 수 있다고 생각한다.



> Jacoco Gradle plugin에는 jacocoTestReport와 jacocoTestCoverageVerification task가 있다.

## Jacoco란?
- Jacoco는 jaca 코드의 커버리지를 체크해주는 라이브러리다. 
- 테스트 코드를 돌리고 결과를 html, csv, html 등으로 리포르를 작성해준다.
- 테스트 코드의 커버리지 기준을 설정해 만족하지 못하면 배포를 하지 못 하게 할 수도 있다.

> jacoco gradle 플러그인에는 jacocoTestReport와 jacocoTestCoverageVerification task가 있다.

### jacocoTestReport
> 커버리지 결과를 html, csv, xml 등의 형태로 리포트를 생성해주는 Task다.
```groovy
plugins {
    id 'jacoco'
}
```
- 위의 플러그인을 추가하면, 'jacocoTestReport' task  생성된다. 설정을 변경하지 않으면   
테스트 결과 리포트(HTML) 파일이 '$buildDir/reports/jacoco/test' 위치에 생성된다.

```groovy
jacocoTestReport {
    reports {
        // 원하는 리포트를 on/off 할 수 있다.
        html.enabled true
        xml.enabled false
        csv.enabled false
// 원한다면 html 리포트 결과 파일이 저장될 위치를 설정할 수 있다.
//  html.destination file("$buildDir/jacocoHtml")
//  xml.destination file("$buildDir/jacoco.xml")
    }
}
```

```groovy
// 모든 타입의 Test task에 default로 설정된 값들로 오버라이드 할 수 있다.
test {
    jacoco {
        enabled = true
        destinationFile = layout.buildDirectory.file("jacoco/${name}.exec").get().asFile
        includes = []
        excludes = []
        excludeClassLoaders = []
        includeNoLocationClasses = false
        sessionId = "<auto-generated value>"
        dumpOnExit = true
        classDumpDir = null
        output = JacocoTaskExtension.Output.FILE
        address = "localhost"
        port = 6300
        jmx = false
    }
}

```
### jacocoTestCoverageVerification
- 테스트 커버리지 기준을 설정할 수 있는 task다.
```groovy
jacocoTestCoverageVerification {
    // violationRule로 커버리지 기준을 정할 수 있다.
    violationRules {
        rule {
            limit {
                minimum = 0.5
            }
        }

        rule {
            enabled = false
            element = 'CLASS'
            includes = ['org.gradle.*']

            limit {
                counter = 'LINE'
                value = 'TOTALCOUNT'
                maximum = 0.3
            }
        }
    }
}
```


#### references
[Gradle 프로젝트에 Jacoco 설정하기](https://techblog.woowahan.com/2661/)
