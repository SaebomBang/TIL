library(readxl)
student <- read.table("a.txt", encoding="utf-8", header=TRUE)

student$총합 <- apply(student[3:5], 1, sum)
student$평균 <-apply(student[3:5], 1,mean)
View(student)
