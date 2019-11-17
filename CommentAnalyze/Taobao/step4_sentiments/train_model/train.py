from snownlp import sentiment

sentiment.train('negative_dict.txt', 'positive_dict.txt')
sentiment.save('sentiment.marshal')