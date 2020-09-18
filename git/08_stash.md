# Stash

> 지금까지 작업했던 내용을 임시적으로 저장하는 공간

## 기본 명령어

* stash 보관

  ```bash
  $ git stash
  ```

* stash 반영

  ```bash
  $ git stash pop # 반영 + 삭제
  # $ git stash apply - 반영
  # $ git stash drop - 삭제
  ```

* stash 목록

  ```bash
  $ git stash list
  # 0974aba 커밋에서 마스터 브랜치에서 작업중인 내용을 보관 중
  stash@{0}: WIP on master: 0974aba Merge pull request #1 from edutak/feature/chatting
  ```

  

## 상황

```bash
$ git pull origin master
remote: Enumerating objects: 8, done.
remote: Counting objects: 100% (7/7), done.
remote: Compressing objects: 100% (4/4), done.
remote: Total 4 (delta 1), reused 0 (delta 0), pack-reused 0
Unpacking objects: 100% (4/4), 1.34 KiB | 76.00 KiB/s, done.
From https://github.com/edutak/branch
 * branch            master     -> FETCH_HEAD
   0974aba..02b022d  master     -> origin/master
# 에러
# 로컬 변경사항이, 지금 덮어씌어질 수 있다.
error: Your local changes to the following files would be overwritten by merge:
        README.md
# 1) 커밋을 하거나, 
# 2) stash를 해서 merge!!!!!!!!
Please commit your changes or stash them before you merge.
Aborting
Updating 0974aba..02b022d
```

```bash
# 1. 작업 중이었는데,, 그래서 안되었는데
$ git status
On branch master
Your branch is behind 'origin/master' by 3 commits,
  (use "git pull" to update your local branch)

Changes not staged for commit:
  (use "git add <file>..." to update what will be co
  (use "git restore <file>..." to discard changes in
        modified:   README.md

no changes added to commit (use "git add" and/or "gi

# 2. 임시공간(stash)에 넣어놓고
$ git stash
Saved working directory and index state WIP on mastefrom edutak/feature/chatting

# 3. WD 깨끗해졌고,
$ git status
On branch master
Your branch is behind 'origin/master' by 3 commits,
  (use "git pull" to update your local branch)

nothing to commit, working tree clean

# 4. pull을 받는다.
$ git pull origin master
From https://github.com/edutak/branch
 * branch            master     -> FETCH_HEAD
Updating 0974aba..02b022d
Fast-forward
 README.md | 2 ++
 ff.txt    | 0
 2 files changed, 2 insertions(+)
 create mode 100644 ff.txt
 
# 5. stash에서 꺼낸다.
$ git stash pop
Auto-merging README.md
# 원격에서 받아온 거랑 작업 내용이 달라서,
CONFLICT (content): Merge conflict in README.md
The stash entry is kept in case you need it again.

# 6. 충돌난 것을 확인하고, 고친다.
# 7. 그리고 작업을 이어나간다.
```

* 예시)

  ```
  <<<<<<< Updated upstream    # pull 받은 내용
  * 친구가 수정했는데,
  * 이 내용을 포함해서 개발을 해나가야함...
  =======
  * 작성작성
  * 코드작업
  * 우와와와
  >>>>>>> Stashed changes     # 임시공간에 있던 내용
  ```

  