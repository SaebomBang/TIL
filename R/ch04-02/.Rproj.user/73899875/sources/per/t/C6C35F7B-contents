#원시 자료 입력
shop <- read.csv("a.txt", header = F,
               stringsAsFactors = F)
colnames(shop) <- c("id", "name", "age", "temp", "price", "qt");
View(shop)
total <- shop$price * shop$qt
shop$total <- total
View(shop)

write.csv(shop,
  file="aTotal.csv",
  row.names = TRUE
)
save(shop, file="shopTotal.rda")
shoptotal <- load("shopTotal.rda")
View(shoptotal)
