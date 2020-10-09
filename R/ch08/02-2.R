
wd <- readLines("https://www.nongmin.com/news/NEWS/POL/FRM/327543/view")
wd <- sapply(wd, extractNoun, USE.NAMES = F)
lwd <- unlist(wd)
lwd <- gsub("[[:punct:]]", "", lwd)

lwd <- gsub("[a-z]", "", lwd)
lwd <- gsub("[A-Z]", "", lwd)
lwd <- gsub("[0-9]", "", lwd)
lwd <- gsub(" ", "", lwd)

lwd2 <- Filter(function(x){nchar(x) >= 2}, lwd)

wc <- table(lwd2)
wc <- head(sort(wc, decreasing = T), 100)
wc

wordcloud2(wc, color="random-light", backgroundColor = "black")