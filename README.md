# URL Shortening
* 과제사항
  * URL 입력폼 제공 및 결과출력
  * URL Shortening Key는 8 Character 이내로 생성
  * 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답
  * 동일한 URL에 대한 요청 수 정보저장
  * Shortening된 URL 요청시 원래 URL로 리다이렉트

* 실행환경
  * 사용중인 8080 포트가 있다면 포트죽이기
  * JAVA 8 이상
  * Ubuntu 18.04
  
* 환경구축
  * JAVA 설치
    ```txt
    sudo apt-get install openjdk-8-jdk
    ```
  * JAVA 정상설치된지 체크
    ```txt
    java -version
    ```
  * Git 설치
    ```txt
    sudo apt-get install git
    ```
  * Git 정상설치된지 체크
    ```txt
    git --version 
    ```
    
* 실행방법
  * 프로젝트 clone 받기
    ```txt
    git clone https://github.com/kim-jong-hyun1811/short-url
    ```
  * 프로젝트 루트경로 접근
    ```txt
    cd short-url
    ```
  * jar파일 실행권한 추가
    ```txt
    chmod -R 777 shorturl/build
    ```
  * 애플리케이션 시작
    ```txt
    sh startup.sh
    ```
  * 애플리케이션 종료
    ```txt
    sh shutdown.sh
    ```
    
# 개발환경
  * JAVA
  * Spring Boot
  * MySQL
  * Mybatis
  * Junit5
  * Mockito
  
# 과제사항 분석
  * URl 입력폼 제공 및 결과출력
    * HTTP/S URL을 입력할 수 있는 입력폼을 제공
    * 입력한 URL에 대해 검증
    * 입력한 URL로 리다이렉트를 하기위한 Short Url 발급
  
  <br>
  
  * URL Shortening Key는 8 Character 이내로 생성
    * 해당 URL 값을 base62 방식으로 인코딩 및 디코딩 방법을 활용
    * 참고문서
      * https://rocksea.tistory.com/348
      * https://jungahshin.tistory.com/15
  
  <br>

  * 동일한 URL에 대한 요청은 동일한 Shortening Key로 응답
    * 사용자가 입력한 URL을 데이터베이스에 저장후 생성된 row의 고유 key값으로 Shorting된 URL을 제공
    * 동일한 URL에 대해 요청이 올 경우 기존에 등록된 Shorting URL을 제공
    * <b>이때 입력포맷이 잘못되면 alert으로 잘못된 url입니다 라는 경고출력</b>
  
  <br>
  
  * 동일한 URL에 대한 요청 수 정보저장
    * Shorting된 URL로 요청이 올 경우 Shorting된 URL을 Base62로 디코딩 후 key를 추출하여 데이터베이스에 해당 key로 row를 조회하여
      요청수 +1 업데이트
   
  <br>
  
  * Shortening된 URL 요청시 원래 URL로 리다이렉트
    * Shorting된 URL을 요청시 Shorting URL과 매핑되는 Origin URL로 리다이렉트 (Origin URL은 사용자가 입력한 URL)
    * <b>발급되지않은 Shorting URL로 요청시 존재하지 않는 URL 입니다. 경고출력</b>
      
      
      
    
