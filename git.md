# Git

> Git은 분산형 버전관리 시스템(DVCS) 중 하나이다.

## Git 사전준비

> git을 사용하기 전에 커밋을 남기는 사람에 대한 정보를 설정(최초)

```bash
git config --global user.name 'SaebomBang'
git config --global user.eamil 'saebom023@gmail.com'
```

- 추후에 commit을 하면, 작성한 사람( author)로 저장된다.
- Email 정보는 github에 등록된 이메일로 설정 하는 것을 추천한다(잔디밭)
- 설정 내용을 확인하기 위해서는 아래의 명령어를 입력한다.

```bash
git config --global -l
```

> git bash 설치 [링크](https://gitforwindows.org/)

## 기초 흐름

### 0. 저장소 설정

```bash
git init
```

- git 저장소를 만들게 되면 해당 디렉토리 내에 `.git/`폴더가 생성
- git bash에서는 (master)로 현재 작업중인 브랜치가 표기된다.

### 1. `add`

> 커밋을 위한 파일 목록(Standing area)

```bash
$ git add .						#현재 디렉토리의 모든 파일 및 폴더
$ git add a.txt 			# 특정 파일
$ git add md-images/	# 특정 폴더
$ git status
$ git rm --cached <file>	#unstaging
```

### 2. `commit`

> 버전을 기록(스냅샷)

```bash
$ git commit -m '커밋메시지'
```

- 커밋 메시지는 현재 버전을 알 수 있도록 명확하게 작성한다.

- 커밋 이력을 남기기 확인하기 위해서는 아래의 명령어를 입력한다.

  ```bash
  $ git log
  $ git log -l 						# 최근 한개의 버전
  $ git log --oneline			# 한줄로 간단하게 표현
  $ git log -1 --oneline
  ```

## status 상태확인

> git에 대한 모든 정보는 status에서 확인할 수 있다.

```bash
$ git status
```



## 원격 저장소 활용하기

> 원격 저장소를 제공하는 서비스는 github, gitlab, bitbucket등이 있다.

### 1. 원격 저장소 설정하기

```bash
$ git remote add origin {url}
```

### 2. 원격 저장소 확인하기

```bash
$ git remote -v
origin	https://github.com/SaebomBang/git-test.git (fetch)
origin	https://github.com/SaebomBang/git-test.git (push)
```

### 3. push

```bash
$ git push origin master
```

- origin  원격 저장소의 master 브랜치

