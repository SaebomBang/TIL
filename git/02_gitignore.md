# gitignore

> git으로 추적하지 않을 파일 또는 폴더를 정의

`git` 으로 관리되고 있는 로컬 저장소에 `.gitignore` 파일을 만든다.

```
*.xlsx					# 특정 확장자 제외
secret.csv			# 특정 파일
tests/ 					# 특정 폴더
!tests/a.txt		# 얘는 빼고
```

* 일반적으로 OS와 관련된 파일, 개발 환경(IDE, text edtor), 특정 프레임워크 혹은 언어에서 메인 소스코드가 아닌 경우
  * Https://gitignore.io/ 에서 참고할 수도 있다.
    * 예) `Windonws`, `java-web` `eclipse`



