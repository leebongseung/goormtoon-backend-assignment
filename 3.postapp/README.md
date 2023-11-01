## 1. 요구사항 API

![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/90436f63-76ae-452c-9f97-b20776a73310)


## 2. 설계한 최종구현한 테이블

- ERD 만 MySQL로 그리고 H2 데이터베이스를 사용하였습니다.
![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/24c42c31-986a-4361-9c38-969bce999fa7)


## 3. 주요 기능과 응답 결과
### 1. 게시글 등록 

![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/57075980-bf66-45de-8e7c-8ad52c1a51b1)

---

### 2. 게시글 목록 조회 구현- offset 기반 페이징을 이용

![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/460fc0df-4862-4f4f-b554-af2e3895152e)


---

### 3. 게시글 수정

![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/b242d983-f4a7-4127-b788-a0d12b1628cf)

---

### 4. 게시글 삭제 - 삭제 시 목록으로 리다이렉트
- 게시글 삭제 시 softDelete로 데이터베이스 내에는 존재한다.
- 게시글 삭제 시 댓글로 함께 softDelete로 삭제.
- 게시글 관련 댓글은 한번에 제거하기 위해 벌크 연산을 사용하였다.
- 벌크연산 자세한 코드 확인하기! [코드 바로가기](https://github.com/leebongseung/goormtoon-backend-assignment/blob/974a02225be224b2fe366f48bd2bead3b6f14c9c/3.postapp/src/main/java/goormtoon/postapp/repository/CommentRepository.java#L14)

![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/c25c5fd8-9672-4f5d-b83e-8aac964fad5e)

---

### 5. 게시글 상세 조회
- 게시글에 포함된 댓글이 commentList로 포함시켰다.
- 여러 코멘트들이 조회되는 N+1 문제를 fetch join을 통해 해결 함.
- fetchjoin 자세한 코드 확인하기! [코드 바로가기](https://github.com/leebongseung/goormtoon-backend-assignment/blob/974a02225be224b2fe366f48bd2bead3b6f14c9c/3.postapp/src/main/java/goormtoon/postapp/repository/BoardRepository.java#L17)

![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/cd7a2390-7de4-45bb-a4fc-aedd47baed2b)

---

### 6. 댓글 등록하기
- 댓글 등록 시 5.게시글 상세 조회로 리다이렉트 하였음.

![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/ad182749-b13b-4c7e-af7a-c874c45dfb9c)


---

### 7. 댓글 수정하기
- 댓글 수정 시 5.게시글 상세 조회로 리다이렉트 하였음.

![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/24c8aaee-11a5-415d-b6a8-0241e569b45d)


---

### 8. 댓글 삭제하기

- 댓글 삭제 시 5.게시글 상세 조회로 리다이렉트 하였음.


![image](https://github.com/leebongseung/goormtoon-backend-assignment/assets/101985441/cf387a5e-db66-4ca2-b152-003c6b118dbe)
