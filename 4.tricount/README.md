## 1. 데이터 베이스 구조
![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/5f749832-5d97-42eb-a736-d5c0117243c1)

- users : 고객 테이블
- settlement : 정산 테이블
- users_has_settlemnt : 고객 정산 다대다 해소 테이블
- expense : 지출 테이블
- expense_has_users_has_settlement : 지출에 포함된 사람들 테이블.

- 구현 시 에로 사항
  1. 다대다 테이블은 기본키를 도입하는게 좋음, 왜냐하면 em.find()를 많이 사용안할 수 있어서 쿼리를 덜 보낼 수 있다.
  2. user는 예약어기 때문에 users로 하였음. 원래는 단수로 작성하는 것이 맞다.
  3. expense_has_users_has_settlment의 경우 기본키를 따로 만들었다.

## 2. 구현한 API 기능들
- [notion 정리 바로가기](https://animated-handspring-1a7.notion.site/API-06f1eba2d03d4619af79eab1455b1ab4)
- [JSON 파일로 바로가기](https://github.com/leebongseung/goormtoon-backend-assignment/files/13423511/Tricount.Clone.postman_collection.json)
  

## 3. 중점적으로 생각한 부분
- [로그인 세션 기능](src/main/java/goorm/tricount/controller/LoginController.java)
  - 세션 로그인은 세션 유지시간이 30분으로 HttpServletRequest의 세션을 이용하여 구현하였습니다.
- [인터셉터로 로그인 체크 기능](src/main/java/goorm/tricount/interceptor/LoginCheck.java)
  - 세션도 메모리인 만큼 userId만을 이용하여 세션을 저장하는 로직을 구현함.
  - 인터셉터를 이용하여 세션이 있는지 확인하는 로직을 추가하였고
  - 만약에 로그인 하지 않은 사용자라면 [ForbiddenException](src/main/java/goorm/tricount/exception/users/ForbiddenException.java) 에러가 발생하도록 구현하였습니다.

- [balance 구현](src/main/java/goorm/tricount/controller/SettlementController.java)
  - 지출에 포함된 유저끼리만 정산하도록 구현
- [N+1 문제 해결하기](src)
  - 패치조인을 이용하여 최대한 구현하려고 노력했고 검증을 위해 보내는 쿼리를 최소화
  - 연관관계 메서드 활용하기, N+1이 발생하는 부분과 무관하다고 생각했는데 객체 끼리 연결되어 있는 그런 부분은 연관관계 메서드가 중요한 역할을 하기 때문에 꼼꼼하게 작성
    - 지연로딩으로 구현 구현하고 N+1 시 패치조인을 하였습니다.
    - [1. 계모임 조회 시 포함된 유저도 함께 조회하기](src/main/java/goorm/tricount/repository/JpaSettlementRepository.java)
    - [2. 정산하기 위해 지출들과 지출에 포함된 사람들 함께 조회하기](src/main/java/goorm/tricount/repository/JpaExpenseRepository.java)
- [@Login 애노테이션을 이용하여 user 값 전달하기](src/main/java/goorm/tricount/annotation/Login.java)
  - 애노테이션을 파라미터단에서 활용해서 RUNTIME 동안 이 어노테이션이 적용되면 HttpServletRequest의 세션값 즉 usersId로 변환해주는 기능을 도입했습니다!

- [@RestControllerAdvice를 활용하여 전역에러 처리](src/main/java/goorm/tricount/exception/exhandler)
  - 컨트롤러가 하나로 묶여있기 때문에 전역처리가 되었습니다. 추후에 프로젝트가 더 커져서 패키지가 세분화되거나, 도메인 단위로 패키지 구성을 변경한다면 부분 처리가 가능합니다.
  - 
- [SoftDelete 처리로 데이터베이스 상태 유지하기](src/main/java/goorm/tricount/repository/JpaSettlementRepository.java)
  - settlement 삭제시 soft 처리되고 DB에는 남아있게 구현

## 4. 프로젝트 후
- 추후 AWS를 통해 API만 배포를 해보려고 합니다.