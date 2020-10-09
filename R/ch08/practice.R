#01
library(readxl)
middle_mid_exam <- read_excel("../HelloR/Data/middle_mid_exam.xlsx")
middle_mid_exam

#02
library(reshape2)
math_select <- middle_mid_exam %>% select(CLASS, ID, MATHEMATICS)
MATHEMATICS <- dcast(math_select, ID~CLASS)
MATHEMATICS

eng_select <- middle_mid_exam %>% select(CLASS, ID, ENGLISH)
ENGLISH <- dcast(eng_select, ID~CLASS)
ENGLISH

#03
library(dplyr)
eng_sum <- middle_mid_exam %>% group_by(CLASS) %>% summarise(.groups = 'drop', ENG_SUM=sum(ENGLISH), ENG_AVG=mean(ENGLISH), MATH_SUM=sum(MATHEMATICS), MATH_AVG=mean(MATHEMATICS))
eng_sum

#04
math_filter <- MATHEMATICS %>% filter(class1 >= 80)
math_filter

#05
middle_arrange <- arrange(middle_mid_exam, desc(MATHEMATICS), ENGLISH)
middle_arrange

#06
middle_filter2 <- middle_mid_exam %>% filter(MATHEMATICS >= 80 & ENGLISH >= 85) %>% summarise(n())
middle_filter2
