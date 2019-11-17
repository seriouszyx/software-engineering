from snownlp import sentiment

sentiment.train('neg.txt', 'pos.txt')
sentiment.save('sentiment.marshal')