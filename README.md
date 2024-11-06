


<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/github_username/repo_name">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">Turbine Insight API</h3>

  <p align="center">
    Spring Boot로 구축된 백엔드 API로, 사용자와 비즈니스(풍력단지) 정보를 관리합니다.<br/> 
    Turbine Insight 앱, 웹과 연동되어 사용자는 어느 환경에서나 접근할 수 있습니다.<br/>
    <a href="https://github.com/github_username/repo_name"><strong>Explore the docs »</strong></a>
  </p>
</div>



<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#collaborators">Collaborators</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## About The Project
풍력단지 개발 예정지에 실제 건설 전 AR로 미리 풍력발전기의 설치된 모습을 확인할 수 있습니다.

<h4>주요 기능</h4>
<ul>
  <li>사용자 인증</li>
  <li>사용자별 비즈니스 목록 관리</li>
  <li>사용자별 풍력발전 설치 제한구역 관리</li>
  <li>비즈니스별 마커, 메모 관리</li>
  <li>비즈니스별 이미지 관리</li>
  <li>AWS S3에 이미지, 제한구역 저장 및 삭제</li>
</ul>

웹의 프론트엔드는 React로 개발되어 있으며, 별도의 레포지토리에서 관리됩니다.</br>
앱 또한 Android Studio와 Unity로 개발되어 별도의 레포지토리에서 관리됩니다.

### Built With

* [![Spring Boot][SpringBoot]][SpringBoot-url]
* [![Hibernate][Hibernate]][Hibernate-url]
* [![Lombok][Lombok]][Lombok-url]
* [![PostgreSQL][PostgreSQL]][PostgreSQL-url]
* [![AWS S3][AWS]][AWS-url]
* [![AWS EC2][EC2]][EC2-url]

<!-- GETTING STARTED -->
## Getting Started

### Prerequisites

이 프로젝트를 실행하려면 다음 소프트웨어가 설치되어 있어야 합니다:

- **Java 11**: 애플리케이션의 주 프로그래밍 언어입니다. Java 11이 설치되어 있는지 확인하세요.
- **Gradle**: 프로젝트 빌드 도구로, 이 프로젝트에서는 Gradle을 사용하여 의존성 관리 및 빌드를 수행합니다.
- **PostgreSQL**: 데이터베이스 서버로, PostgreSQL이 설치되어 있고 데이터베이스 및 사용자 계정이 설정되어 있어야 합니다.
- **AWS S3 & EC2 계정**: AWS S3를 사용하여 파일을 저장하고 EC2에서 애플리케이션을 배포할 수 있습니다. AWS 계정과 필요한 권한이 있는지 확인하세요.

### Installation

1. 레포지토리 클론
   ```sh
   git clone https://github.com/mg-seo/TurbineInsight_API.git
   ```
2. Git 원격 URL을 변경
   ```sh
   git remote set-url origin github_username/repo_name
   git remote -v # confirm the changes
   ```
3. 데이터베이스 설정
   <br/>
   application.properties 파일에서 데이터베이스 설정을 구성합니다.
   ```sh
   spring.datasource.url=jdbc:postgresql://your_ip_address/your_db_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
4. AWS S3 설정
   <br/>
   application.properties 파일에 S3 접근 키를 추가합니다.
   ```sh
   cloud.aws.credentials.access-key=your_public_key
   cloud.aws.credentials.secret-key=your_private_key
   cloud.aws.region.static=your_region
   ```
   ImageServiceImpl.java 및 RegulatedServiceImpl.java 파일에 S3 버킷 이름을 추가합니다.
   ```sh
   private final String bucketName = "your-bucket-name";
   ```
5. 빌드 및 실행
   <br/>
   터미널에서 빌드 후 EC2에 업로드, 접속하여 실행합니다.
   ```sh
   /*빌드*/
   ./gradlew bootJar
   ```
   ```sh
   /*업로드*/
   scp -i "YourEC2KeyPair.pem" "YourBuildFile.jar" ubuntu@EC2_public_ip_address:/home/ubuntu/
   ```
   ```sh
   /*EC2 접속 및 자바 설치*/
   ssh -i "YourEC2KeyPair.pem" ubuntu@EC2_public_ip_address
   sudo apt update
   sudo apt install openjdk-11-jdk -y
   ```
   ```sh
   /*업로드 확인 & 실행 & 실행 확인*/
   ls /home/ubuntu/
   nohup java -jar /home/ubuntu/YourBuildFile.jar > app.log 2>&1 &
   ps -ef | grep java
   ```

<!-- Collaborators -->
## Collaborators

<table>
  <tr>
    <td align="center" style="padding: 20px;">
      <img src="https://via.placeholder.com/100" alt="Collaborator 1's Profile Picture" width="100" height="100">
      <h3>이진석</h3>
      <p><a href="https://collaborator1blog.com" target="_blank">Personal Blog</a></p>
    </td>
    <td align="center" style="padding: 20px;">
      <img src="https://via.placeholder.com/100" alt="Collaborator 2's Profile Picture" width="100" height="100">
      <h3>서무경</h3>
      <p><a href="https://collaborator2blog.com" target="_blank">Personal Blog</a></p>
    </td>
  </tr>
</table>
<p align="right">(<a href="#readme-top">back to top</a>)</p>







<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[SpringBoot]: https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring&logoColor=white
[SpringBoot-url]: https://spring.io/projects/spring-boot
[Hibernate]: https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=hibernate&logoColor=white
[Hibernate-url]: https://hibernate.org/
[Lombok]: https://img.shields.io/badge/Lombok-9C3E00?style=for-the-badge&logo=lombok&logoColor=white
[Lombok-url]: https://projectlombok.org/
[PostgreSQL]: https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql&logoColor=white
[PostgreSQL-url]: https://www.postgresql.org/
[AWS]: https://img.shields.io/badge/Amazon_S3-569A31?style=for-the-badge&logo=amazonaws&logoColor=white
[AWS-url]: https://aws.amazon.com/s3/
[EC2]: https://img.shields.io/badge/Amazon_EC2-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white
[EC2-url]: https://aws.amazon.com/ec2/
