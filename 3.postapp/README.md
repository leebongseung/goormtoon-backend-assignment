## 1. 요구사항 API

![api요구사항](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/f18ce5b6-e455-4f7a-a400-8b3084e8e885)

## 2. 설계한 최종구현한 테이블


- (추가 예정)

## 3. 주요 기능과 응답 결과
### 1. 게시글 등록 

![게시글등록API](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/708ead5b-b2d0-4b5f-b8de-cc7bdafca5ea)

---

### 2. 게시글 목록 조회 구현- offset 기반 페이징을 이용

![게시글목록조회API](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/1143f8a8-4021-4711-8a37-1011787d2719)

---

### 3. 게시글 수정

![게시글업데이트API](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/33dd208b-f015-4170-a7f4-f7ede2ddb0b7)

---

### 4. 게시글 삭제 - 삭제 시 목록으로 리다이렉트
- 게시글 삭제 시 softDelete로 데이터베이스 내에는 존재한다.
- 게시글 삭제 시 댓글로 함께 softDelete로 삭제.
- 게시글 관련 댓글은 한번에 제거하기 위해 벌크 연산을 사용하였다.
- 벌크연산 자세한 코드 확인하기! [코드 바로가기](https://github.com/leebongseung/goormtoon-backend-assignment/blob/974a02225be224b2fe366f48bd2bead3b6f14c9c/3.postapp/src/main/java/goormtoon/postapp/repository/CommentRepository.java#L14)

![게시글삭제API](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/62e59d65-d364-4902-8752-734dd7a58302)

---

### 5. 게시글 상세 조회
- 게시글에 포함된 댓글이 commentList로 포함시켰다.
- 여러 코멘트들이 조회되는 N+1 문제를 fetch join을 통해 해결 함.
- fetchjoin 자세한 코드 확인하기! [코드 바로가기](https://github.com/leebongseung/goormtoon-backend-assignment/blob/974a02225be224b2fe366f48bd2bead3b6f14c9c/3.postapp/src/main/java/goormtoon/postapp/repository/BoardRepository.java#L17)

![게시글상세조회API](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/eec5eee9-2b89-4be3-8666-1490c787b0a7)

---

### 6. 댓글 등록하기
- 댓글 등록 시 5.게시글 상세 조회로 리다이렉트 하였음.

![댓글등록API](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/202ee326-a89d-4536-8f73-1d6ecfe2a654)

---

### 7. 댓글 수정하기
- 댓글 수정 시 5.게시글 상세 조회로 리다이렉트 하였음.

![댓글수정API](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/3a7da2d0-dc3d-40c1-a0ea-04050d522ca3)

---

### 8. 댓글 삭제하기

- 댓글 삭제 시 5.게시글 상세 조회로 리다이렉트 하였음.

![댓글삭제API](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/ddcfd692-ba55-4d3e-99a8-e5f0595f6469)



## 4. 향후 개선할점

1. 게시글 상세 조회 시 Content 내용 없애기
2. 대댓글 고민해보기
3. 피드백 : HTTP Method를 적절하게 바꾸기
  ex) PUT보다 의미적으로 DELETE 이용하는게 낫다는 의견.