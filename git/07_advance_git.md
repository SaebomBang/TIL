# Git 명령어 취소

1. `Add` 취소

   ```bash
   $ git restore --staged <파일명> # 최신
   $ git reset HEAD <파일명>       # 구버전(작년)
   ```

   ```bash
   $ git status
   On branch master
   Changes to be committed:
    # unstage.. add를 취소하려면
    # git restore --staged
     (use "git restore --staged <file>..." to unstage)
           new file:   a.txt
           new file:   b.txt
   ```

   ```bash
   $ git restore --staged b.txt
   $ git status
   On branch master
   Changes to be committed:
     (use "git restore --staged <file>..." to unstage)
           new file:   a.txt
   
   Untracked files:
     (use "git add <file>..." to include in what will be committed)
           b.txt
   ```

2. `commit` 메시지 변경

   > 주의! 커밋 메시지를 변경하면, hash 값이 변경된다.
   >
   > 즉, 공개된 저장소에 `push`를 한 이후에는 하지 않는다.

   ```bash
   $ git commit --amend
   ```

   * vim 편집기 창에서 직접 메시지를 수정하고 저장

     ```bash
     $ git log --oneline
     03ca0cb (HEAD -> master) c.txt 추가
     538d35d Add b.txt
     3867506 Add a.txt
     76b305f README
     
     $ git commit --amend
     [master 30ca836] Add c.txt
      Date: Fri Sep 18 16:12:38 2020 +0900
      1 file changed, 0 insertions(+), 0 deletions(-)
      create mode 100644 c.txt
     
     $ git log --oneline
     30ca836 (HEAD -> master) Add c.txt
     538d35d Add b.txt
     3867506 Add a.txt
     76b305f README
     ```

3. 커밋 변경

   > 주의! 커밋 메시지를 변경하면, hash 값이 변경된다.
   >
   > 즉, 공개된 저장소에 `push`를 한 이후에는 하지 않는다.
   >
   > 2번과 동일한 명령어

   * 예) 내가 어떠한 파일을 빠뜨리고 커밋 했을 때

     ```bash
     $ touch d.txt
     $ touch omit.txt
     $ git add d.txt
     $ git commit -m 'Add d&omit'
     $ git status
     On branch master
     Untracked files:
       (use "git add <file>..." to include in what will be committed)
             omit.txt
     
     nothing added to commit but untracked files present (use "git add" to track)
     ```

     * 해결 방법

     ```bash
     $ git add omit.txt
     $ git status
     On branch master
     Changes to be committed:
       (use "git restore --staged <file>..." to unstage)
             new file:   omit.txt
     $ git commit --amend
     [master 7844532] Add d & omit
      Date: Fri Sep 18 16:20:18 2020 +0900
      2 files changed, 0 insertions(+), 0 deletions(-)
      create mode 100644 d.txt
      # 들어가있죠!
      create mode 100644 omit.txt
     $ git status
     On branch master
     nothing to commit, working tree clean
     ```

4. `working directory` 변경사항 삭제

   > 주의! 아래의 명령어를 입력하면 절대 과거로 돌아갈 수는 없다.
   > 커밋한 내용만 복구 가능!

   ```bash
   $ git restore <파일명>
   ```

   ```bash
   $ git status
   On branch master
   Changes not staged for commit:
     (use "git add/rm <file>..." to update what will be committed)
     # WD 있는 변경사항들을(changes) 버리기 위해서는...
     # git restore <파일>
     (use "git restore <file>..." to discard changes in working directory)
           deleted:    README.md
           deleted:    a.txt
           deleted:    b.txt
           deleted:    c.txt
           deleted:    d.txt
           deleted:    omit.txt
   
   no changes added to commit (use "git add" and/or "git commit -a")
   ```

   ```bash
   $ git restore .
   $ git status
   On branch master
   nothing to commit, working tree clean
   ```

   

   ## 이력 되돌리기 (reset vs revert)

   * 두 명령어는 특정 시점의 상태로 커밋을 되돌리는 작업을 한다.

   * `reset` : 이력을 삭제

     * `--mixed` : (default) 해당 커밋 이후 변경사항을 보관
     * `--hard` : 해당 커밋 이후 변경사항을 모두 삭제 (주의!)
     * `--soft` : 해당 커밋 이후 변경사항 및 WD 내용까지 보관

     ```bash
     $ git log --oneline
     da0ae77 (HEAD -> master) Update README
     7844532 Add d & omit
     30ca836 Add c.txt
     $ git reset 7844532
     $ git log --oneline
     7844532 (HEAD -> master) Add d & omit
     30ca836 Add c.txt
     ```

   * `revert` : 되돌렸다는 이력을 남긴다.

     ```bash
     $ git revert 7844532
     Removing omit.txt
     Removing d.txt
     [master f050b8e] Revert "Add d & omit"
      2 files changed, 0 insertions(+), 0 deletions(-)
      delete mode 100644 d.txt
      delete mode 100644 omit.txt
     $ git log --oneline
     f050b8e (HEAD -> master) Revert "Add d & omit"
     7844532 Add d & omit
     30ca836 Add c.txt
     ```

