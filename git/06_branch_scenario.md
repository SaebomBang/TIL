1. ### 상황 1. fast-foward

   > fast-foward는 feature 브랜치 생성된 이후 master 브랜치에 변경 사항이 없는 상황

   

   1. feature/test branch 생성 및 이동

      ```bash
      $ git checkout -b feature/test
      Switched to a new branch 'feature/test'
      ```

   2. 작업 완료 후 commit

      ```bash
      $ touch test.txt 
      $ git add .
      $ git commit -m "add test.txt"
      e8553ed add test.txt
      ```


   3. master 이동

      ```bash
      $ git checkout master
      ```


   4. master에 병합

      ```bash
      $ git merge feature/test 
      Updating ffe7164..e8553ed
      Fast-forward
       test.txt | 0
       1 file changed, 0 insertions(+), 0 deletions(-)
       create mode 100644 test.txt
      ```


   5. 결과 -> fast-foward (단순히 HEAD를 이동)

      ```bash
      $ git log --oneline
      ```
      
   6. branch 삭제

      ```bash
      $ git branch -d feature/test
      # merge 안한 상태에서 지우고 싶다면 -D
      ```

   ---

   ### 상황 2. merge commit

   > 서로 다른 이력(commit)을 병합(merge)하는 과정에서 다른 파일이 수정되어 있는 상황
   >
   > git이 auto merging을 진행하고, commit이 발생된다.

   1. feature/signout branch 생성 및 이동

      ```bash
      $ git checkout -b feature/signout
      Switched to a new branch 'feature/signout'
      ```

   2. 작업 완료 후 commit

      ```bash
      $ touch signout.txt
      $ git add .
      $ git commit -m "add signout.txt"
      ad066a0 add signout.txt
      e8553ed add test.txt
      ```
      
   3. master 이동

      ```bash
      $ git checkout master
      e8553ed add test.txt
      ```
      
   4. *master에 추가 commit 이 발생시키기!!*

      * **다른 파일을 수정 혹은 생성하세요!**

        ```bash
        $ touch hotfix.html
        $ git add .
        $ git commit -m 'hotfix.txt'
        $ git log --oneline
        f1204e7 hotfix.txt
        e8553ed add test.txt
        ```
      
   5. master에 병합

      ```bash
      $ git merge feature/signout 
      Merge made by the 'recursive' strategy.
       signout.txt | 0
       1 file changed, 0 insertions(+), 0 deletions(-)
       create mode 100644 signout.txt                 
      ```

   6. 결과 -> 자동으로 *merge commit 발생*

      * vim 편집기 화면이 나타납니다.
      
      * 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하여 저장 및 종료를 합니다.
         * `w` : write
         * `q` : quit
         
      * 커밋이  확인 해봅시다.
      
   7. 그래프 확인하기

      ```bash
      $ git log --oneline --graph
      *   33f05f6 Merge branch 'feature/signout'
      |\  
      | * ad066a0 add signout.txt
      * | f1204e7 hotfix.txt
      |/  
      * e8553ed add test.txt
      ```
      
   8. branch 삭제

      ```bash
      git branch -d feature/signout 
      Deleted branch feature/signout (was ad066a0).
      ```

   ---

   ### 상황 3. merge commit 충돌

   > 서로 다른 이력(commit)을 병합(merge)하는 과정에서 동일 파일이 수정되어 있는 상황
   >
   > git이 auto merging을 하지 못하고, 해당 파일의 위치에 라벨링을 해준다.
   >
   > 원하는 형태의 코드로 직접 수정을 하고 merge commit을 발생 시켜야 한다.

   1. feature/board branch 생성 및 이동

      ```bash
      $  git checkout -b feature/board
      ```

   2. 작업 완료 후 commit

      ```bash
      $ touch board.txt
      $ vi README.md 
      $ git status
      On branch feature/board
      Changes not staged for commit:
        (use "git add <file>..." to update what will be committed)
        (use "git checkout -- <file>..." to discard changes in working directory)
      # 1) 동일파일 수정
      	modified:   README.md
      
      Untracked files:
        (use "git add <file>..." to include in what will be committed)
      # 2) 신규 작업
      	board.txt
      
      no changes added to commit (use "git add" and/or "git commit -a") 
      $ git add .
      $ git commit -m "board, readme"
      $ git log --oneline
      8fb6acf board, readme
      33f05f6 Merge branch 'feature/signout'
      ```


   3. master 이동

      ```bash
      $ git checkout master
      ```


   4. *master에 추가 commit 이 발생시키기!!*

      * **동일 파일을 수정 혹은 생성하세요!**

      ```bash
      $ vi README.md
      $ git add .
      $ git commit -m "update REAEMD.md"
      $ git log --oneline
      0f63246 update REAEMD.md
      33f05f6 Merge branch 'feature/signout'
      ```
      
   5. master에 병합

      ```bash
      $ git merge feature/board 
      # 자동 병합을 하고 있는데..
      Auto-merging README.md
      # 충돌! 
      CONFLICT (content): Merge conflict in README.md
      Automatic merge failed; 
      # 충돌을 고치고, 결과를 커밋하세요.
      fix conflicts and then commit the result.
      ```


   6. 결과 -> *merge conflict발생*

      ```bash
      $ git status
      On branch master
      # 병합되지 않은 파일들이 있습니다.
      You have unmerged paths.
      # 충돌을 고치고, commit 하세요.
        (fix conflicts and run "git commit")
      # 병합을 취소하려면 
        (use "git merge --abort" to abort the merge)
      # staging area 
      # -> 충돌나지 않은 파일
      Changes to be committed:
      	new file:   board.txt
      # 유사 Working directory...
      # -> 충돌난 파일
      Unmerged paths:
        # 해결하고 add하세요!!!
        (use "git add <file>..." to mark resolution)
      
      	both modified:   README.md
      ```


   7. 충돌 확인 및 해결

      ```bash
      # 충돌 해결하고
      $ git add .
      ```


   8. merge commit 진행

       ```bash
       $ git commit
       ```

      * vim 편집기 화면이 나타납니다.
      
      * 자동으로 작성된 커밋 메시지를 확인하고, `esc`를 누른 후 `:wq`를 입력하여 저장 및 종료를 합니다.
         * `w` : write
         * `q` : quit
         
      * 커밋이  확인 해봅시다.
      
   9. 그래프 확인하기

       ```bash
      $ git log --oneline --graph
      *   29b1b32 Merge branch 'feature/board'
      |\  
      | * 8fb6acf board, readme
      * | 0f63246 update REAEMD.md
      |/  
      *   33f05f6 Merge branch 'feature/signout'
      
      ```


   10. branch 삭제

       ```bash
       $ git branch -d feature/board 
       Deleted branch feature/board (was 8fb6acf).
       ```