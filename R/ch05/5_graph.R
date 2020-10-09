library(readxl)
exdata1 <- read_excel("../HelloR/Data/Sample1.xlsx")
exdata1
stem(exdata1$AGE)
hist(exdata1$AGE)
